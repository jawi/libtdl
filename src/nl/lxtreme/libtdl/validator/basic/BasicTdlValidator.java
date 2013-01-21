/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.validator.basic;

import static nl.lxtreme.libtdl.validator.ValidationUtil.*;

import java.util.*;

import nl.lxtreme.libtdl.grammar.*;
import nl.lxtreme.libtdl.grammar.ProblemReporter.Category;
import nl.lxtreme.libtdl.grammar.ProblemReporter.Marker;
import nl.lxtreme.libtdl.grammar.ProblemReporter.MarkerBuilder;
import nl.lxtreme.libtdl.grammar.ProblemReporter.Type;
import nl.lxtreme.libtdl.grammar.basic.*;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.ActiveClauseContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.ExprContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.StageDefContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.TermDeclContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.WhenActionContext;

import org.antlr.v4.runtime.*;

/**
 * Validates whether a parse tree produced by {@link BasicTdlParser} is valid.
 */
public class BasicTdlValidator extends BasicTdlBaseVisitor<BasicTdlValidator> {
    // CONSTANTS

    private static final int DEFAULT_STAGE_COUNT = 4;
    private static final long MAX_INT_VALUE = (1L << 32) - 1L;
    private static final long MAX_DELAY_VALUE = 0xFFFFL;

    // VARIABLES

    private final Map<String, Object> m_declarations;
    private final Map<Integer, Object> m_stages;
    private final ProblemReporter m_problemReporter;

    private int m_stageCount;

    // CONSTRUCTORS

    /**
     * Creates a new {@link BasicTdlValidator} instance.
     * 
     * @param problemReporter
     *            the problem reporter to report any validation problems to,
     *            cannot be <code>null</code>.
     */
    public BasicTdlValidator(ProblemReporter problemReporter) {
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
    public BasicTdlValidator visitActiveClause(ActiveClauseContext ctx) {
        if (ctx.n != null) {
            validateValue(ctx.n, 1, m_stageCount, "invalid level ID", m_problemReporter);
        }
        return super.visitActiveClause(ctx);
    }

    @Override
    public BasicTdlValidator visitExpr(ExprContext ctx) {
        if (ctx.term != null) {
            Token term = ctx.term;
            String name = normalizeName(term.getText());
            if (!m_declarations.containsKey(name)) {
                int offset = ctx.getStart().getStartIndex();
                int length = ctx.getStop().getStopIndex() - offset;
                String msg = name + " is not declared";

                MarkerBuilder builder = new MarkerBuilder();
                Marker marker = builder.setCategory(Category.SEMANTIC).setType(Type.ERROR)
                        .setLocation(offset, length, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine())
                        .setDescription(msg).build();

                m_problemReporter.report(marker);
            }
        }
        return super.visitExpr(ctx);
    }

    @Override
    public BasicTdlValidator visitStageDef(StageDefContext ctx) {
        Long stageId = validateValue(ctx.n, 1, m_stageCount, "invalid stage ID", m_problemReporter);
        if (stageId != null) {
            define(ctx, stageId.intValue(), ctx);
        }
        return super.visitStageDef(ctx);
    }

    @Override
    public BasicTdlValidator visitTermDecl(TermDeclContext ctx) {
        validateValue(ctx.mask, 0, MAX_INT_VALUE, "invalid mask", m_problemReporter);
        validateValue(ctx.value, 0, MAX_INT_VALUE, "invalid value", m_problemReporter);

        declare(ctx, ctx.name.getText(), ctx);
        return super.visitTermDecl(ctx);
    }

    @Override
    public BasicTdlValidator visitWhenAction(WhenActionContext ctx) {
        if (ctx.n != null) {
            validateValue(ctx.n, 1, MAX_DELAY_VALUE, "invalid delay value", m_problemReporter);
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
}
