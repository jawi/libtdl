/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import static nl.lxtreme.libtdl.grammar.Util.*;

import java.util.*;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.ProblemReporter.Category;
import nl.lxtreme.libtdl.ProblemReporter.Marker;
import nl.lxtreme.libtdl.ProblemReporter.MarkerBuilder;
import nl.lxtreme.libtdl.ProblemReporter.Type;
import nl.lxtreme.libtdl.grammar.*;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.ActiveClauseContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.ExprContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.StageDefContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.TermDeclContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.TermExprContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.WhenActionContext;

import org.antlr.v4.runtime.*;

/**
 * Verifies whether a parse tree produced by {@link BasicTdlParser} is
 * semantically valid.
 */
public class BasicTdlSemanticAnalyzer extends BasicTdlBaseVisitor<BasicTdlSemanticAnalyzer> implements
        SemanticAnalyzer<BasicTdlSemanticAnalyzer> {
    // CONSTANTS

    private static final long MAX_INT_VALUE = (1L << 32) - 1L;
    private static final long MAX_DELAY_VALUE = 0xFFFFL;

    // VARIABLES

    private final TdlConfig m_config;
    private final List<TermDefinitionListener> m_listeners;
    private final Map<String, Term> m_declarations;
    private final Map<Integer, Boolean> m_stages;
    private final ProblemReporter m_problemReporter;

    // CONSTRUCTORS

    /**
     * Creates a new {@link BasicTdlSemanticAnalyzer} instance.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>;
     * @param problemReporter
     *            the problem reporter to report any validation problems to,
     *            cannot be <code>null</code>.
     */
    public BasicTdlSemanticAnalyzer(TdlConfig config, ProblemReporter problemReporter) {
        if (config == null) {
            throw new IllegalArgumentException("Config cannot be null!");
        }
        m_config = config;

        if (problemReporter == null) {
            throw new IllegalArgumentException("Problem reporter cannot be null!");
        }
        m_problemReporter = problemReporter;

        m_listeners = new ArrayList<TermDefinitionListener>();

        m_declarations = new HashMap<String, Term>();
        m_stages = new HashMap<Integer, Boolean>();
    }

    // METHODS

    @Override
    public void addTermDefinitionListener(TermDefinitionListener listener) {
        if (!m_listeners.contains(listener)) {
            m_listeners.add(listener);
        }
    }

    @Override
    public void removeTermDefinitionListener(TermDefinitionListener listener) {
        m_listeners.remove(listener);
    }

    @Override
    public void reset() {
        m_declarations.clear();
        m_stages.clear();
    }

    @Override
    public BasicTdlSemanticAnalyzer visitActiveClause(ActiveClauseContext ctx) {
        if (ctx.n != null) {
            validateValue(ctx.n, 1, m_config.getMaxStages(), "invalid level ID");
        }
        return super.visitActiveClause(ctx);
    }

    @Override
    public BasicTdlSemanticAnalyzer visitExpr(ExprContext ctx) {
        if (ctx.term != null) {
            Token term = ctx.term;
            String name = normalizeName(term.getText());
            if (!m_declarations.containsKey(name)) {
                String msg = name + " is not declared";

                Marker marker = new MarkerBuilder().setCategory(Category.SEMANTIC).setType(Type.ERROR) //
                        .setLocation(ctx).setDescription(msg).build();

                m_problemReporter.report(marker);
            }
        }
        return super.visitExpr(ctx);
    }

    @Override
    public BasicTdlSemanticAnalyzer visitStageDef(StageDefContext ctx) {
        Long stageId = validateValue(ctx.n, 1, m_config.getMaxStages(), "invalid stage ID");
        if (stageId != null) {
            define(ctx, stageId.intValue());
        }
        return super.visitStageDef(ctx);
    }

    @Override
    public BasicTdlSemanticAnalyzer visitTermDecl(TermDeclContext ctx) {
        Long mask = validateValue(ctx.mask, 0, MAX_INT_VALUE, "invalid mask");
        Long value = validateValue(ctx.value, 0, MAX_INT_VALUE, "invalid value");

        if ((mask != null) && (value != null)) {
            declare(ctx, new Term(ctx.name.getText(), value.longValue(), mask.longValue()));
        }

        return super.visitTermDecl(ctx);
    }

    @Override
    public BasicTdlSemanticAnalyzer visitTermExpr(TermExprContext ctx) {
        if ((ctx.AT() != null) && (ctx.n != null)) {
            validateValue(ctx.n, 0, m_config.getMaxChannels() - 1, "invalid channel: " + ctx.n.getText());
        }
        return super.visitTermExpr(ctx);
    }

    @Override
    public BasicTdlSemanticAnalyzer visitWhenAction(WhenActionContext ctx) {
        if (ctx.n != null) {
            validateValue(ctx.n, 1, MAX_DELAY_VALUE, "invalid delay value");
        }
        return super.visitWhenAction(ctx);
    }

    private void declare(ParserRuleContext context, Term term) {
        if (m_declarations.put(term.getName(), term) != null) {
            String msg = "term " + term.getName() + " already declared";

            Marker marker = new MarkerBuilder().setCategory(Category.SEMANTIC).setType(Type.ERROR) //
                    .setLocation(context).setDescription(msg).build();

            m_problemReporter.report(marker);
        } else {
            for (TermDefinitionListener listener : m_listeners) {
                listener.termDeclared(term.getName(), term.toString());
            }
        }
    }

    private void define(ParserRuleContext context, int stageNo) {
        Integer stage = Integer.valueOf(stageNo);
        if (m_stages.put(stage, Boolean.TRUE) != null) {
            String msg = "stage " + stageNo + " already defined";

            Marker marker = new MarkerBuilder().setCategory(Category.SEMANTIC).setType(Type.ERROR) //
                    .setLocation(context).setDescription(msg).build();

            m_problemReporter.report(marker);
        }
    }

    private Long validateValue(ParserRuleContext ctx, long lower, long upper, String msg) {
        return Util.validateValue(ctx, lower, upper, msg, m_problemReporter);
    }
}
