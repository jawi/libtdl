/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import static nl.lxtreme.libtdl.grammar.Util.*;

import java.io.*;
import java.util.*;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.grammar.*;
import nl.lxtreme.libtdl.grammar.Compiler;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.EdgeDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.EdgeTermDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.ElseActionContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.ExprContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.RangeDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.StageDefContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.TermDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.TermExprContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.TimerDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.WhenActionContext;

/**
 * Provides a visitor for collecting the defined terms and stages.
 */
public class AdvTdlCompiler extends AdvTdlBaseVisitor<AdvTdlCompiler> implements Compiler<AdvTdlCompiler> {
    // INNER TYPES

    /**
     * Wraps an {@link OutputStream} to hide the way the Advanced Triggers need
     * to be written.
     */
    static class AdvTdlOutputStream implements TdlOutputStream {
        // CONSTANTS

        private static final int OP_CONFIG_SELECT = 0x9E;
        private static final int OP_WRITE_DATA = 0x9F;

        // VARIABLES

        private final OutputStream m_os;

        // CONSTRUCTORS

        /**
         * Creates a new {@link AdvTdlOutputStream} instance.
         */
        public AdvTdlOutputStream(OutputStream os) {
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
            writeLongCommand(OP_CONFIG_SELECT, opcode);
        }

        @Override
        public void writeData(int data) throws IOException {
            writeLongCommand(OP_WRITE_DATA, data);
        }

        /**
         * Writes a "long" command.
         * 
         * @param opcode
         *            the 8-bit opcode to write;
         * @param data
         *            the 32-bit data to write.
         */
        private void writeLongCommand(int opcode, int data) throws IOException {
            final byte[] raw = new byte[5];
            int mask = 0xff;
            int shift = 0;

            raw[0] = (byte) (opcode & 0xff);
            for (int i = 1; i < 5; i++) {
                raw[i] = (byte) ((data & mask) >> shift);
                mask = mask << 8;
                shift += 8;
            }

            m_os.write(raw);
            m_os.flush();
        }
    }

    // VARIABLES

    private final Definitions m_definitions;
    private final TriggerStages m_stages;
    private final Stack<SumPart> m_sumParts;

    private volatile TriggerSum m_sum;
    private volatile TriggerStageAction m_action;

    // CONSTRUCTORS

    /**
     * Creates a new {@link AdvTdlCompiler} instance.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>.
     */
    public AdvTdlCompiler(TdlConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Config cannot be null!");
        }
        m_definitions = new Definitions();
        m_stages = new TriggerStages(config.getMaxStages(), config.isDdrMode());

        m_sumParts = new Stack<SumPart>();
    }

    // METHODS

    /**
     * Returns the term definitions.
     * 
     * @return the definitions, never <code>null</code>.
     */
    public Definitions getDefinitions() {
        return m_definitions;
    }

    /**
     * Returns the trigger stages.
     * 
     * @return the stages, never <code>null</code>.
     */
    public TriggerStages getStages() {
        return m_stages;
    }

    @Override
    public AdvTdlCompiler visitEdgeDecl(EdgeDeclContext ctx) {
        String name = normalizeName(ctx.name.getText());

        Map<String, Long> values = new HashMap<String, Long>();
        for (EdgeTermDeclContext term : ctx.terms) {
            values.put(term.name.getText(), decode(term.mask.getText()));
        }

        m_definitions.addEdgeDefinition(name, values);

        return super.visitEdgeDecl(ctx);
    }

    @Override
    public AdvTdlCompiler visitElseAction(ElseActionContext ctx) {
        if (m_action == null) {
            throw new IllegalStateException("No trigger action available!");
        }

        Long n = decode(ctx.n.getText());
        if (n == null) {
            throw new IllegalStateException("No else state available!");
        }

        m_action.setElseState(n.intValue());

        return super.visitElseAction(ctx);
    }

    @Override
    public AdvTdlCompiler visitExpr(ExprContext ctx) {
        if (m_sum == null) {
            throw new IllegalStateException("No trigger sum available!");
        }

        if (ctx.op != null) {
            int type = ctx.op.getType();

            if (type == AdvTdlLexer.NOT) {
                visit(ctx.rhs);

                m_sumParts.push(m_sum.invert(m_sumParts.pop()));
            } else if ((type == AdvTdlLexer.XOR) || (type == AdvTdlLexer.AND) || (type == AdvTdlLexer.OR)) {
                visit(ctx.lhs);
                SumPart lhs = m_sumParts.pop();

                visit(ctx.rhs);
                SumPart rhs = m_sumParts.pop();

                m_sumParts.push(m_sum.defineOperator(ctx.op, lhs, rhs));
            } else if (type == AdvTdlLexer.LPAREN) {
                visit(ctx.lhs);
            }
        } else if (ctx.term != null) {
            // Simple term...
            m_sumParts.push(m_sum.defineInput(ctx.term));
        }

        return null;
    }

    @Override
    public AdvTdlCompiler visitRangeDecl(RangeDeclContext ctx) {
        String name = normalizeName(ctx.name.getText());
        Long lower = decode(ctx.lowerBound.getText());
        Long upper = decode(ctx.upperBound.getText());

        m_definitions.addRangeDefinition(name, lower, upper);

        return super.visitRangeDecl(ctx);
    }

    @Override
    public AdvTdlCompiler visitStageDef(StageDefContext ctx) {
        Long n = decode(ctx.n.getText());
        if (n == null) {
            throw new IllegalStateException("Failed to decode stage index!");
        }

        TriggerStage stage = m_stages.createStage(n.intValue());

        setSum(stage.getCapture());
        visit(ctx.captureExpr);

        setSum(stage.getIf());
        visit(ctx.ifExpr);

        if (ctx.occurrence != null) {
            n = decode(ctx.occurrence.getText());
            if (n == null) {
                throw new IllegalStateException("Failed to decode occurrence count!");
            }
            stage.setOccurrenceCount(n.intValue());
        }

        m_action = stage.getAction();
        visit(ctx.whenAction());

        setSum(stage.getElse());
        visit(ctx.elseExpr);

        visit(ctx.elseAction());

        m_stages.add(stage);
        setSum(null);

        return null;
    }

    @Override
    public AdvTdlCompiler visitTermDecl(TermDeclContext ctx) {
        String name = normalizeName(ctx.name.getText());
        Long value = decode(ctx.value.getText());
        Long mask = decode(ctx.mask.getText());

        m_definitions.addTermDefinition(name, value, mask);

        return super.visitTermDecl(ctx);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdvTdlCompiler visitTermExpr(TermExprContext ctx) {
        if (m_sum == null) {
            throw new IllegalStateException("No trigger sum available!");
        }

        m_sumParts.clear();

        if ((ctx.e != null) && (ctx.e.getType() == AdvTdlLexer.NOP)) {
            // No operation...
            m_sumParts.push(m_sum.nop());
        } else if ((ctx.e != null) && (ctx.e.getType() == AdvTdlLexer.ANY)) {
            // Any operation...
            m_sumParts.push(m_sum.any());
        } else {
            visitChildren(ctx);
        }

        // There can be only one item on our part-stack...
        if (m_sumParts.size() != 1) {
            throw new IllegalStateException("Unbalanced set of sum parts!");
        }

        m_sumParts.pop();
        m_sum.fillMissingOperators();

        return null;
    }

    @Override
    public AdvTdlCompiler visitTimerDecl(TimerDeclContext ctx) {
        String name = normalizeName(ctx.name.getText());
        Long time = decode(ctx.value.getText());
        String unit = ctx.unit.getText();

        m_definitions.addTimerDefinition(name, time, unit);

        return super.visitTimerDecl(ctx);
    }

    @Override
    public AdvTdlCompiler visitWhenAction(WhenActionContext ctx) {
        if (m_action == null) {
            throw new IllegalStateException("No trigger action available!");
        }

        if (ctx.action != null) {
            // start/stop/clear timer or start/stop capture
            int nameType = ctx.name.getType();
            if (nameType == AdvTdlLexer.CAPTURE) {
                m_action.setEndState(true);

                switch (ctx.action.getType()) {
                    case AdvTdlLexer.START:
                        m_action.setSetTrigger(true);
                        break;
                    case AdvTdlLexer.STOP:
                        m_action.setSetTrigger(false);
                        break;
                    default:
                        throw new IllegalStateException("Invalid action!");
                }
            } else {
                m_action.setEndState(false);

                switch (ctx.action.getType()) {
                    case AdvTdlLexer.START:
                        m_action.setStartTimer(ctx.name);
                        break;
                    case AdvTdlLexer.STOP:
                        m_action.setStopTimer(ctx.name);
                        break;
                    case AdvTdlLexer.CLEAR:
                        m_action.setClearTimer(ctx.name);
                        break;
                    default:
                        throw new IllegalStateException("Invalid action!");
                }
            }
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

        AdvTdlOutputStream os = new AdvTdlOutputStream(outputStream);
        try {
            m_definitions.write(os);
            m_stages.write(os);
        } finally {
            os.close();
            os = null;
        }
    }

    /**
     * Sets the context trigger sum, used to gather information about
     * expressions.
     * 
     * @param sum
     *            the trigger sum to set.
     * @see #visitTermExpr(TermExprContext)
     * @see #visitExpr(ExprContext)
     */
    void setSum(TriggerSum sum) {
        m_sum = sum;
    }
}
