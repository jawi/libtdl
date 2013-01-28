
/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import org.antlr.v4.runtime.tree.*;

public class BasicTdlBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements BasicTdlVisitor<T> {
	@Override public T visitTermDecl(BasicTdlParser.TermDeclContext ctx) { return visitChildren(ctx); }

	@Override public T visitProg(BasicTdlParser.ProgContext ctx) { return visitChildren(ctx); }

	@Override public T visitTermExpr(BasicTdlParser.TermExprContext ctx) { return visitChildren(ctx); }

	@Override public T visitStageDef(BasicTdlParser.StageDefContext ctx) { return visitChildren(ctx); }

	@Override public T visitDecNumber(BasicTdlParser.DecNumberContext ctx) { return visitChildren(ctx); }

	@Override public T visitNumber(BasicTdlParser.NumberContext ctx) { return visitChildren(ctx); }

	@Override public T visitExpr(BasicTdlParser.ExprContext ctx) { return visitChildren(ctx); }

	@Override public T visitActiveClause(BasicTdlParser.ActiveClauseContext ctx) { return visitChildren(ctx); }

	@Override public T visitWhenAction(BasicTdlParser.WhenActionContext ctx) { return visitChildren(ctx); }

	@Override public T visitDecl(BasicTdlParser.DeclContext ctx) { return visitChildren(ctx); }
}