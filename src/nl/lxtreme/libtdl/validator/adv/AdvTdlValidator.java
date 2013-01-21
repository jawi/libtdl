/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.validator.adv;

import java.util.*;

import nl.lxtreme.libtdl.grammar.*;
import nl.lxtreme.libtdl.grammar.ProblemReporter.Category;
import nl.lxtreme.libtdl.grammar.ProblemReporter.Marker;
import nl.lxtreme.libtdl.grammar.ProblemReporter.MarkerBuilder;
import nl.lxtreme.libtdl.grammar.ProblemReporter.Type;
import nl.lxtreme.libtdl.grammar.adv.*;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.EdgeDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.EdgeTermDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.ElseActionContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.ExprContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.ProgContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.RangeDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.StageDefContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.TermDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.TimerDeclContext;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.WhenActionContext;
import nl.lxtreme.libtdl.validator.*;

import org.antlr.v4.runtime.*;

/**
 * Validates whether a parse tree produced by {@link AdvTdlParser} is valid.
 */
public class AdvTdlValidator extends AdvTdlBaseVisitor<AdvTdlValidator> {
    // CONSTANTS

    private static final int DEFAULT_STAGE_COUNT = 10;

    private static final long MAX_INT_VALUE = (1L << 32) - 1L;
    private static final long MAX_TIMER_VALUE = 0xFFFFFFFFL;
    private static final long MAX_OCCURRANCE_COUNT = 0xFFFFFL;

    // VARIABLES

    private final Map<String, Object> m_declarations;
    private final Map<Integer, Object> m_stages;
    private final ProblemReporter m_problemReporter;

    private int m_stageCount;

    // CONSTRUCTORS

    /**
     * Creates a new {@link AdvTdlValidator} instance.
     * 
     * @param problemReporter
     *            the problem reporter to report any validation problems to,
     *            cannot be <code>null</code>.
     */
    public AdvTdlValidator(ProblemReporter problemReporter) {
        m_problemReporter = problemReporter;

        m_declarations = new HashMap<String, Object>();
        m_stages = new HashMap<Integer, Object>();

        m_stageCount = DEFAULT_STAGE_COUNT;
    }

    // METHODS

    /**
     * Returns the definition for the term with the given name,
     * 
     * @param name
     *            the name of the term to get the definition for, cannot be
     *            <code>null</code>.
     * @return the definition for the given term, or <code>null</code> if no
     *         definition was found.
     */
    public String getTermDefinition(String name) {
        Object result = m_declarations.get(normalizeName(name));
        if (result instanceof ParserRuleContext) {
            return ((ParserRuleContext) result).getText();
        }
        return null;
    }

    /**
     * Sets the number of stages that this validator should check for.
     * 
     * @param stages
     *            a stage count, > 0.
     */
    public void setStageCount(int stages) {
        m_stageCount = stages;
    }

    @Override
    public AdvTdlValidator visitEdgeDecl(EdgeDeclContext ctx) {
        for (EdgeTermDeclContext term : ctx.terms) {
            validateValue(term.mask, 0, MAX_INT_VALUE, "invalid edge mask");
        }

        declare(ctx, ctx.name.getText(), ctx);
        return super.visitEdgeDecl(ctx);
    }

    @Override
    public AdvTdlValidator visitElseAction(ElseActionContext ctx) {
        Long levelID = validateValue(ctx.n, 1, m_stageCount, "invalid level ID");
        if (levelID != null) {
            Integer level = Integer.valueOf(levelID.intValue());
            if (!m_stages.containsKey(level)) {
                // Forward declaration...
                m_stages.put(level, null);
            }
        }

        return super.visitElseAction(ctx);
    }

    @Override
    public AdvTdlValidator visitExpr(ExprContext ctx) {
        if (ctx.term != null) {
            validateDeclaredTerm(ctx.term);
        }

        return super.visitExpr(ctx);
    }

    @Override
    public AdvTdlValidator visitProg(ProgContext ctx) {
        // First traverse the whole tree, then do validations...
        AdvTdlValidator result = super.visitProg(ctx);

        for (Map.Entry<Integer, Object> entry : m_stages.entrySet()) {
            if (entry.getValue() == null) {
                MarkerBuilder builder = new MarkerBuilder();
                Marker marker = builder.setCategory(Category.SEMANTIC).setType(Type.ERROR).setLocation(-1, -1, -1, -1)
                        .setDescription("undefined stage: " + entry.getKey()).build();

                m_problemReporter.report(marker);
            }
        }

        return result;
    }

    @Override
    public AdvTdlValidator visitRangeDecl(RangeDeclContext ctx) {
        validateValue(ctx.lowerBound, 0, MAX_INT_VALUE, "invalid lower bound");
        validateValue(ctx.upperBound, 0, MAX_INT_VALUE, "invalid upper bound");
        validateRange(ctx.lowerBound, ctx.upperBound, "lower bound should be less than upper bound");

        declare(ctx, ctx.name.getText(), ctx);

        return super.visitRangeDecl(ctx);
    }

    @Override
    public AdvTdlValidator visitStageDef(StageDefContext ctx) {
        Long stageId = validateValue(ctx.n, 1, m_stageCount, "invalid stage ID");
        if (stageId != null) {
            define(ctx, stageId.intValue(), ctx);
        }

        int occurrence = 1;
        if (ctx.occurrence != null) {
            Long value = validateValue(ctx.occurrence, 1, MAX_OCCURRANCE_COUNT, "invalid occurrence count");
            if (value != null) {
                occurrence = value.intValue();
            }
        }
        System.out.println("Occerrence = " + occurrence);

        return super.visitStageDef(ctx);
    }

    @Override
    public AdvTdlValidator visitTermDecl(TermDeclContext ctx) {
        validateValue(ctx.mask, 0, MAX_INT_VALUE, "invalid mask");
        validateValue(ctx.value, 0, MAX_INT_VALUE, "invalid value");

        declare(ctx, ctx.name.getText(), ctx);

        return super.visitTermDecl(ctx);
    }

    @Override
    public AdvTdlValidator visitTimerDecl(TimerDeclContext ctx) {
        validateValue(ctx.value, 1, MAX_TIMER_VALUE, "invalid timer value");

        declare(ctx, ctx.name.getText(), ctx);

        return super.visitTimerDecl(ctx);
    }

    @Override
    public AdvTdlValidator visitWhenAction(WhenActionContext ctx) {
        if (ctx.timer != null) {
            validateDeclaredTerm(ctx.timer);
        }

        return super.visitWhenAction(ctx);
    }

    private void declare(ParserRuleContext context, String name, Object value) {
        name = normalizeName(name);
        if (m_declarations.put(name, value) != null) {
            int offset = context.getStart().getStartIndex();
            int length = context.getStop().getStopIndex() - offset;
            String msg = "term " + name + " already declared";

            MarkerBuilder builder = new MarkerBuilder();
            Marker marker = builder
                    .setCategory(Category.SEMANTIC)
                    .setType(Type.ERROR)
                    .setLocation(offset, length, context.getStart().getLine(),
                            context.getStart().getCharPositionInLine()).setDescription(msg).build();

            m_problemReporter.report(marker);
        }
    }

    private void define(ParserRuleContext context, int stageNo, Object value) {
        Integer stage = Integer.valueOf(stageNo);
        if (m_stages.put(stage, value) != null) {
            int offset = context.getStart().getStartIndex();
            int length = context.getStop().getStopIndex() - offset;
            String msg = "stage " + stageNo + " already defined";

            MarkerBuilder builder = new MarkerBuilder();
            Marker marker = builder
                    .setCategory(Category.SEMANTIC)
                    .setType(Type.ERROR)
                    .setLocation(offset, length, context.getStart().getLine(),
                            context.getStart().getCharPositionInLine()).setDescription(msg).build();

            m_problemReporter.report(marker);
        }
    }

    private String normalizeName(String name) {
        if (name.length() == 1) {
            return "term" + name.toUpperCase();
        } else if (name.startsWith("term") && (name.length() > 4)) {
            return "term" + name.substring(4).toUpperCase();
        }
        return name.toLowerCase();
    }

    private void validateDeclaredTerm(Token term) {
        String name = normalizeName(term.getText());
        if (!m_declarations.containsKey(name)) {
            int offset = term.getStartIndex();
            int length = term.getStopIndex() - offset;
            String msg = name + " is not declared";

            MarkerBuilder builder = new MarkerBuilder();
            Marker marker = builder.setCategory(Category.SEMANTIC).setType(Type.ERROR)
                    .setLocation(offset, length, term.getLine(), term.getCharPositionInLine()).setDescription(msg)
                    .build();

            m_problemReporter.report(marker);
        }
    }

    private void validateRange(ParserRuleContext lower, ParserRuleContext upper, String msg) {
        Long lowerBound = ValidationUtil.decode(lower.getText());
        Long upperBound = ValidationUtil.decode(upper.getText());
        if (lowerBound.compareTo(upperBound) >= 0) {
            int offset = lower.getStart().getStartIndex();
            int length = lower.getStop().getStopIndex() - offset;

            MarkerBuilder builder = new MarkerBuilder();
            Marker marker = builder.setCategory(Category.SEMANTIC).setType(Type.ERROR)
                    .setLocation(offset, length, lower.getStart().getLine(), lower.getStart().getCharPositionInLine())
                    .setDescription(msg).build();

            m_problemReporter.report(marker);
        }
    }

    private Long validateValue(ParserRuleContext ctx, long lower, long upper, String msg) {
        return ValidationUtil.validateValue(ctx, lower, upper, msg, m_problemReporter);
    }
}
