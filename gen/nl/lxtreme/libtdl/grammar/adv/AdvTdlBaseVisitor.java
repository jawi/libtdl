
/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

public class AdvTdlBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements AdvTdlVisitor<T> {
	@Override public T visitEdgeDecl(AdvTdlParser.EdgeDeclContext ctx) { return visitChildren(ctx); }

	@Override public T visitTermExpr(AdvTdlParser.TermExprContext ctx) { return visitChildren(ctx); }

	@Override public T visitDecNumber(AdvTdlParser.DecNumberContext ctx) { return visitChildren(ctx); }

	@Override public T visitTimerDecl(AdvTdlParser.TimerDeclContext ctx) { return visitChildren(ctx); }

	@Override public T visitElseClause(AdvTdlParser.ElseClauseContext ctx) { return visitChildren(ctx); }

	@Override public T visitNumber(AdvTdlParser.NumberContext ctx) { return visitChildren(ctx); }

	@Override public T visitExpr(AdvTdlParser.ExprContext ctx) { return visitChildren(ctx); }

	@Override public T visitRangeDecl(AdvTdlParser.RangeDeclContext ctx) { return visitChildren(ctx); }

	@Override public T visitProg(AdvTdlParser.ProgContext ctx) { return visitChildren(ctx); }

	@Override public T visitTermDecl(AdvTdlParser.TermDeclContext ctx) { return visitChildren(ctx); }

	@Override public T visitWhenClause(AdvTdlParser.WhenClauseContext ctx) { return visitChildren(ctx); }

	@Override public T visitEdgeTermDecl(AdvTdlParser.EdgeTermDeclContext ctx) { return visitChildren(ctx); }

	@Override public T visitStageDef(AdvTdlParser.StageDefContext ctx) { return visitChildren(ctx); }

	@Override public T visitDecl(AdvTdlParser.DeclContext ctx) { return visitChildren(ctx); }
}