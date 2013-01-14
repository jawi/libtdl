/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.validator.adv;

import java.util.*;

import nl.lxtreme.libtdl.grammar.adv.*;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.*;

/**
 * Validates whether a parse tree produced by {@link AdvTdlParser} is valid.
 */
public class AdvTdlValidator extends AdvTdlBaseVisitor<AdvTdlValidator> {
    // VARIABLES

    private final Map<String, Object> m_declarations = new HashMap<String, Object>();
    private final Map<String, Object> m_stages = new HashMap<String, Object>();

    // METHODS

    @Override
    public AdvTdlValidator visitTermDecl(final TermDeclContext ctx) {
        declare(ctx.name.getText(), ctx);
        return super.visitTermDecl(ctx);
    }

    @Override
    public AdvTdlValidator visitTimerDecl(final TimerDeclContext ctx) {
        declare(ctx.name.getText(), ctx);
        return super.visitTimerDecl(ctx);
    }

    @Override
    public AdvTdlValidator visitEdgeDecl(final EdgeDeclContext ctx) {
        declare(ctx.name.getText(), ctx);
        return super.visitEdgeDecl(ctx);
    }

    @Override
    public AdvTdlValidator visitRangeDecl(final RangeDeclContext ctx) {
        declare(ctx.name.getText(), ctx);
        return super.visitRangeDecl(ctx);
    }

    @Override
    public AdvTdlValidator visitStageDef(final StageDefContext ctx) {
        define(ctx.n.getText(), ctx);
        return super.visitStageDef(ctx);
    }

    private void define(final String stageNo, final Object value) {
        if (this.m_stages.put(stageNo, value) != null) {
            throw new RuntimeException("Stage " + stageNo + " already defined!");
        }
    }

    private void declare(final String name, final Object value) {
        if (this.m_declarations.put(name, value) != null) {
            throw new RuntimeException("Term " + name + " already declared!");
        }
    }
}
