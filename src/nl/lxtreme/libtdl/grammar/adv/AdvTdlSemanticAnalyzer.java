/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import static nl.lxtreme.libtdl.grammar.Util.*;

import java.util.*;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.ProblemReporter.Category;
import nl.lxtreme.libtdl.ProblemReporter.Marker;
import nl.lxtreme.libtdl.ProblemReporter.MarkerBuilder;
import nl.lxtreme.libtdl.ProblemReporter.Type;
import nl.lxtreme.libtdl.grammar.*;
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

import org.antlr.v4.runtime.*;

/**
 * Verifies whether a parse tree produced by {@link AdvTdlParser} is
 * semantically valid.
 */
public class AdvTdlSemanticAnalyzer extends AdvTdlBaseVisitor<AdvTdlSemanticAnalyzer> implements
        SemanticAnalyzer<AdvTdlSemanticAnalyzer> {
    // CONSTANTS

    private static final long MAX_INT_VALUE = (1L << 32) - 1L;
    private static final long MAX_TIMER_VALUE = 0xFFFFFFFFL;
    private static final long MAX_OCCURRANCE_COUNT = 0xFFFFFL;

    // VARIABLES

    private final TdlConfig m_config;
    private final List<TermDefinitionListener> m_listeners;
    private final Map<String, TermDefinition> m_declarations;
    private final Map<Integer, Object> m_stages;
    private final ProblemReporter m_problemReporter;

    // CONSTRUCTORS

    /**
     * Creates a new {@link AdvTdlSemanticAnalyzer} instance.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>;
     * @param problemReporter
     *            the problem reporter to report any validation problems to,
     *            cannot be <code>null</code>.
     */
    public AdvTdlSemanticAnalyzer(TdlConfig config, ProblemReporter problemReporter) {
        if (config == null) {
            throw new IllegalArgumentException("Config cannot be null!");
        }
        m_config = config;

        if (problemReporter == null) {
            throw new IllegalArgumentException("Problem reporter cannot be null!");
        }
        m_problemReporter = problemReporter;

        m_listeners = new ArrayList<TermDefinitionListener>();

        m_declarations = new HashMap<String, TermDefinition>();
        m_stages = new HashMap<Integer, Object>();
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
    public AdvTdlSemanticAnalyzer visitEdgeDecl(EdgeDeclContext ctx) {
        Map<String, Long> values = new HashMap<String, Long>();
        for (EdgeTermDeclContext term : ctx.terms) {
            Long value = validateValue(term.mask, 0, MAX_INT_VALUE, "invalid edge mask");

            if (value != null) {
                // Valid edge term mask...
                values.put(term.name.getText(), value.longValue());
            }
        }

        if (!values.isEmpty()) {
            declare(ctx, new Edge(ctx.name.getText(), values));
        }

        return super.visitEdgeDecl(ctx);
    }

    @Override
    public AdvTdlSemanticAnalyzer visitElseAction(ElseActionContext ctx) {
        Long levelID = validateValue(ctx.n, 1, m_config.getMaxStages(), "invalid level ID");
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
    public AdvTdlSemanticAnalyzer visitExpr(ExprContext ctx) {
        if (ctx.term != null) {
            validateDeclaredTerm(ctx.term);
        }

        return super.visitExpr(ctx);
    }

    @Override
    public AdvTdlSemanticAnalyzer visitProg(ProgContext ctx) {
        // First traverse the whole tree, then do validations...
        AdvTdlSemanticAnalyzer result = super.visitProg(ctx);

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
    public AdvTdlSemanticAnalyzer visitRangeDecl(RangeDeclContext ctx) {
        Long lower = validateValue(ctx.lowerBound, 0, MAX_INT_VALUE, "invalid lower bound");
        Long upper = validateValue(ctx.upperBound, 0, MAX_INT_VALUE, "invalid upper bound");
        boolean validRange = validateRange(ctx.lowerBound, ctx.upperBound,
                "lower bound should be less than upper bound");

        if (validRange) {
            declare(ctx, new Range(ctx.name.getText(), lower, upper));
        }

        return super.visitRangeDecl(ctx);
    }

    @Override
    public AdvTdlSemanticAnalyzer visitStageDef(StageDefContext ctx) {
        Long stageId = validateValue(ctx.n, 1, m_config.getMaxStages(), "invalid stage ID");
        if (stageId != null) {
            define(ctx, stageId.intValue(), ctx);
        }

        if (ctx.occurrence != null) {
            validateValue(ctx.occurrence, 1, MAX_OCCURRANCE_COUNT, "invalid occurrence count");
        }

        return super.visitStageDef(ctx);
    }

    @Override
    public AdvTdlSemanticAnalyzer visitTermDecl(TermDeclContext ctx) {
        Long mask = validateValue(ctx.mask, 0, MAX_INT_VALUE, "invalid mask");
        Long value = validateValue(ctx.value, 0, MAX_INT_VALUE, "invalid value");

        if ((mask != null) && (value != null)) {
            declare(ctx, new Term(ctx.name.getText(), value.longValue(), mask.longValue()));
        }

        return super.visitTermDecl(ctx);
    }

    @Override
    public AdvTdlSemanticAnalyzer visitTimerDecl(TimerDeclContext ctx) {
        Long value = validateValue(ctx.value, 1, MAX_TIMER_VALUE, "invalid timer value");

        if (value != null) {
            declare(ctx, new Timer(ctx.name.getText(), value.longValue(), ctx.unit.getText()));
        }

        return super.visitTimerDecl(ctx);
    }

    @Override
    public AdvTdlSemanticAnalyzer visitWhenAction(WhenActionContext ctx) {
        if ((ctx.name != null) && (AdvTdlLexer.CAPTURE != ctx.name.getType())) {
            validateDeclaredTerm(ctx.name);
        }

        return super.visitWhenAction(ctx);
    }

    private void declare(ParserRuleContext context, TermDefinition def) {
        if (m_declarations.put(def.getName(), def) != null) {
            String msg = "term " + def.getName() + " already declared";

            Marker marker = new MarkerBuilder().setCategory(Category.SEMANTIC).setType(Type.ERROR) //
                    .setLocation(context).setDescription(msg).build();

            m_problemReporter.report(marker);
        } else {
            for (TermDefinitionListener listener : m_listeners) {
                listener.termDeclared(def.getName(), def.toString());
            }
        }
    }

    private void define(ParserRuleContext context, int stageNo, Object value) {
        Integer stage = Integer.valueOf(stageNo);
        if (m_stages.put(stage, value) != null) {
            String msg = "stage " + stageNo + " already defined";

            Marker marker = new MarkerBuilder().setCategory(Category.SEMANTIC).setType(Type.ERROR) //
                    .setLocation(context).setDescription(msg).build();

            m_problemReporter.report(marker);
        }
    }

    private void validateDeclaredTerm(Token term) {
        String name = normalizeName(term.getText());
        if (!m_declarations.containsKey(name)) {
            int offset = term.getStartIndex();
            int length = term.getStopIndex() - offset;
            String msg = name + " is not declared";

            Marker marker = new MarkerBuilder().setCategory(Category.SEMANTIC).setType(Type.ERROR) //
                    .setLocation(offset, length, term.getLine(), term.getCharPositionInLine()) //
                    .setDescription(msg).build();

            m_problemReporter.report(marker);
        }
    }

    private boolean validateRange(ParserRuleContext lower, ParserRuleContext upper, String msg) {
        Long lowerBound = decode(lower.getText());
        Long upperBound = decode(upper.getText());

        boolean valid = (lowerBound != null) && (upperBound != null) && (lowerBound.compareTo(upperBound) < 0);

        if (!valid) {
            int offset = lower.getStart().getStartIndex();
            int length = lower.getStop().getStopIndex() - offset;

            MarkerBuilder builder = new MarkerBuilder();
            Marker marker = builder.setCategory(Category.SEMANTIC).setType(Type.ERROR)
                    .setLocation(offset, length, lower.getStart().getLine(), lower.getStart().getCharPositionInLine())
                    .setDescription(msg).build();

            m_problemReporter.report(marker);
        }

        return valid;
    }

    private Long validateValue(ParserRuleContext ctx, long lower, long upper, String msg) {
        return Util.validateValue(ctx, lower, upper, msg, m_problemReporter);
    }
}
