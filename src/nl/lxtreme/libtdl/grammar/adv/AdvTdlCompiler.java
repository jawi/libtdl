/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import static nl.lxtreme.libtdl.grammar.ValidationUtil.*;

import java.io.*;
import java.util.*;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.grammar.*;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.EdgeDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.EdgeTermDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.ElseActionContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.ExprContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.ProgContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.RangeDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.StageDefContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.TermDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.TermExprContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.TimerDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.WhenActionContext;

/**
 * Provides a visitor for collecting the defined terms and stages.
 */
public class AdvTdlCompiler extends AdvTdlBaseVisitor<AdvTdlCompiler> {
    // VARIABLES

    private final TdlOutputStream m_outputStream;
    private final TdlDefinitions m_declarations;
    private final TdlTriggerStages m_stages;
    private final Stack<ITdlSumPart> m_sumParts;

    private volatile TdlTriggerSum m_sum;
    private volatile TdlTriggerStageAction m_action;

    // CONSTRUCTORS

    /**
     * Creates a new {@link AdvTdlCompiler} instance.
     */
    public AdvTdlCompiler(TdlOutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream cannot be null!");
        }
        m_outputStream = outputStream;
        m_declarations = new TdlDefinitions();
        m_stages = new TdlTriggerStages();

        m_sumParts = new Stack<ITdlSumPart>();
    }

    // METHODS

    @Override
    public AdvTdlCompiler visitEdgeDecl(EdgeDeclContext ctx) {
        String name = normalizeName(ctx.name.getText());

        Map<String, Long> values = new HashMap<String, Long>();
        for (EdgeTermDeclContext term : ctx.terms) {
            values.put(term.name.getText(), decode(term.mask.getText()));
        }

        m_declarations.addEdgeDefinition(name, values);

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
                ITdlSumPart lhs = m_sumParts.pop();

                visit(ctx.rhs);
                ITdlSumPart rhs = m_sumParts.pop();

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
    public AdvTdlCompiler visitProg(ProgContext ctx) {
        AdvTdlCompiler result = super.visitProg(ctx);
        // Done walking through the entire program; compile the output to single
        // bitstream...
        try {
            m_declarations.write(m_outputStream);
            m_stages.write(m_outputStream);
            return result;
        } catch (IOException exception) {
            // Wrap in a runtime exception...
            throw new RuntimeException("Compilation failed!", exception);
        }
    }

    @Override
    public AdvTdlCompiler visitRangeDecl(RangeDeclContext ctx) {
        String name = normalizeName(ctx.name.getText());
        Long lower = decode(ctx.lowerBound.getText());
        Long upper = decode(ctx.upperBound.getText());

        m_declarations.addRangeDefinition(name, lower, upper);

        return super.visitRangeDecl(ctx);
    }

    @Override
    public AdvTdlCompiler visitStageDef(StageDefContext ctx) {
        Long n = decode(ctx.n.getText());
        if (n == null) {
            throw new IllegalStateException("Failed to decode stage index!");
        }

        TdlTriggerStage stage = new TdlTriggerStage(n.intValue(), false);

        m_sum = stage.getCapture();
        visit(ctx.captureExpr);

        m_sum = stage.getIf();
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

        m_sum = stage.getElse();
        visit(ctx.elseExpr);

        visit(ctx.elseAction());

        System.out.println("STAGE = " + stage);

        return null;
    }

    @Override
    public AdvTdlCompiler visitTermDecl(TermDeclContext ctx) {
        String name = normalizeName(ctx.name.getText());
        Long value = decode(ctx.value.getText());
        Long mask = decode(ctx.mask.getText());

        m_declarations.addTermDefinition(name, value, mask);

        return super.visitTermDecl(ctx);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdvTdlCompiler visitTermExpr(TermExprContext ctx) {
        m_sumParts.clear();

        if ((ctx.e != null) && (ctx.e.getType() == AdvTdlLexer.NOP)) {
            // No operation...
            m_sum.nop();
        } else if ((ctx.e != null) && (ctx.e.getType() == AdvTdlLexer.ANY)) {
            // Any operation...
            m_sum.any();
        } else {
            visitChildren(ctx);
        }

        m_sum.fillMissingOperators();

        return null;
    }

    @Override
    public AdvTdlCompiler visitTimerDecl(TimerDeclContext ctx) {
        String name = normalizeName(ctx.name.getText());
        Long time = decode(ctx.value.getText());
        String unit = ctx.unit.getText();

        m_declarations.addTimerDefinition(name, time, unit);

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
                m_action.setLastState(true);

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
                m_action.setLastState(false);

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
}
