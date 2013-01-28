/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import static nl.lxtreme.libtdl.grammar.Util.*;

import java.io.*;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.grammar.*;
import nl.lxtreme.libtdl.grammar.Compiler;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.ActiveClauseContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.ExprContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.StageDefContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.TermDeclContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.TermExprContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.WhenActionContext;

/**
 * Provides a visitor for collecting the defined terms and stages.
 */
public class BasicTdlCompiler extends BasicTdlBaseVisitor<BasicTdlCompiler> implements Compiler<BasicTdlCompiler> {
    // INNER TYPES

    /**
     * Wraps an {@link OutputStream} to hide the way the Basic Triggers need
     * to be written.
     */
    static class BasicTdlOutputStream implements TdlOutputStream {
        // VARIABLES

        private final OutputStream m_os;

        // CONSTRUCTORS

        /**
         * Creates a new {@link BasicTdlOutputStream} instance.
         */
        public BasicTdlOutputStream(OutputStream os) {
            m_os = os;
        }

        // METHODS

        @Override
        public void close() throws IOException {
            // Only flush the wrapped stream; we do NOT close it...
            m_os.flush();
        }

        @Override
        public void writeSelect(int opcode) throws IOException {
            m_os.write(opcode);
        }

        @Override
        public void writeData(int data) throws IOException {
            m_os.write((data >>> 24) & 0xFF);
            m_os.write((data >>> 16) & 0xFF);
            m_os.write((data >>> 8) & 0xFF);
            m_os.write((data >>> 0) & 0xFF);
        }
    }

    // VARIABLES

    private final Definitions m_declarations;
    private final TriggerStages m_stages;

    private volatile TriggerStage m_stage;
    private volatile TermExpression m_expr;

    // CONSTRUCTORS

    /**
     * Creates a new {@link BasicTdlCompiler} instance.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>.
     */
    public BasicTdlCompiler(TdlConfig config) {
        this(config.getMaxStages(), config.isDdrMode());
    }

    /**
     * Creates a new {@link BasicTdlCompiler} instance.
     * 
     * @param maxStages
     *            the maximum number of stages, > 0;
     * @param ddrMode
     *            <code>true</code> if DDR mode is to be enabled,
     *            <code>false</code> otherwise.
     */
    public BasicTdlCompiler(int maxStages, boolean ddrMode) {
        m_declarations = new Definitions();
        m_stages = new TriggerStages(maxStages, ddrMode);
    }

    // METHODS

    /**
     * Returns the current value of stages.
     * 
     * @return the stages
     */
    public TriggerStages getStages() {
        return m_stages;
    }

    @Override
    public BasicTdlCompiler visitActiveClause(ActiveClauseContext ctx) {
        if (m_stage == null) {
            throw new IllegalStateException("No trigger stage available!");
        }

        int activationLevel = 0;
        if (ctx.n != null) {
            Long value = decode(ctx.n.getText());
            activationLevel = value.intValue();
        }

        m_stage.setActivationLevel(activationLevel);

        return super.visitActiveClause(ctx);
    }

    @Override
    public BasicTdlCompiler visitExpr(ExprContext ctx) {
        if (m_expr == null) {
            throw new IllegalStateException("No expression available!");
        }

        if (ctx.term != null) {
            m_expr.defineTerm(ctx.term);
        } else {
            m_expr.invert();
        }

        return super.visitExpr(ctx);
    }

    @Override
    public BasicTdlCompiler visitStageDef(StageDefContext ctx) {
        Long n = decode(ctx.n.getText());
        if (n == null) {
            throw new IllegalStateException("Failed to decode stage index!");
        }

        setTriggerStage(m_stages.createTriggerStage(n.intValue() - 1));

        // activate...
        visit(ctx.activeClause());

        // when clause...
        visit(ctx.termExpr());
        visit(ctx.whenAction());

        m_stages.add(m_stage);
        m_stage = null;

        return null;
    }

    @Override
    public BasicTdlCompiler visitTermDecl(TermDeclContext ctx) {
        String name = normalizeName(ctx.name.getText());
        Long value = decode(ctx.value.getText());
        Long mask = decode(ctx.mask.getText());

        m_declarations.addTermDefinition(name, value, mask);

        return super.visitTermDecl(ctx);
    }

    @Override
    public BasicTdlCompiler visitTermExpr(TermExprContext ctx) {
        if (m_stage == null) {
            throw new IllegalStateException("No trigger stage available!");
        }

        if ((ctx.AT() != null) && (ctx.n != null)) {
            // Serial trigger...
            Long channel = decode(ctx.n.getText());
            m_stage.setSerialChannel(channel.intValue());
        }

        setExpression(new TermExpression());

        super.visitTermExpr(ctx);

        Term term = m_declarations.getTerm(m_expr.getName());
        if (term == null) {
            throw new IllegalStateException("No such term: " + m_expr.getName());
        }

        m_stage.setTerm(term, m_expr.isInverted());
        m_expr = null;

        return null;
    }

    @Override
    public BasicTdlCompiler visitWhenAction(WhenActionContext ctx) {
        if (m_stage == null) {
            throw new IllegalStateException("No trigger stage available!");
        }

        if (ctx.START() != null) {
            if (ctx.n != null) {
                Long value = decode(ctx.n.getText());
                m_stage.setDelay(value.intValue());
            }

            m_stage.setStartCapture(true);
        } else if (ctx.GOTO() != null) {
            // We do not start the capture, but increase the trigger level
            // instead...
            m_stage.setStartCapture(false);
        }

        return super.visitWhenAction(ctx);
    }

    /**
     * Writes the compiled bitstream contents to the given output stream.
     * <p>
     * NOTE: this method should be called <em>after</em> this compiler has
     * visited a parse tree!
     * </p>
     * 
     * @param outputStream
     *            the output stream to write to, cannot be <code>null</code>.
     */
    @Override
    public void write(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream cannot be null!");
        }

        BasicTdlOutputStream os = new BasicTdlOutputStream(outputStream);
        try {
            m_stages.write(os);
        } finally {
            os.close();
            os = null;
        }
    }

    /**
     * Sets the current trigger state.
     * 
     * @param stage
     *            the trigger stage to set, cannot be <code>null</code>.
     */
    final TriggerStage setTriggerStage(TriggerStage stage) {
        m_stage = stage;
        return stage;
    }

    /**
     * Sets the current term expression.
     * 
     * @param expr
     *            the term expression to set, cannot be <code>null</code>.
     */
    final TermExpression setExpression(TermExpression expr) {
        m_expr = expr;
        return expr;
    }
}
