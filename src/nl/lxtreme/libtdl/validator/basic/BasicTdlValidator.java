/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.validator.basic;

import java.util.*;

import nl.lxtreme.libtdl.grammar.basic.*;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.StageDefContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.TermDeclContext;

/**
 * Validates whether a parse tree produced by {@link BasicTdlParser} is valid.
 */
public class BasicTdlValidator extends BasicTdlBaseVisitor<BasicTdlValidator> {
    // VARIABLES

    private final Map<String, Object> m_declarations = new HashMap<String, Object>();
    private final Map<String, Object> m_stages = new HashMap<String, Object>();

    // METHODS

    @Override
    public BasicTdlValidator visitTermDecl(final TermDeclContext ctx) {
        declare(ctx.name.getText(), ctx);
        return super.visitTermDecl(ctx);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BasicTdlValidator visitStageDef(final StageDefContext ctx) {
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
