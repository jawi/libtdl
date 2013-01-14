
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

public interface AdvTdlVisitor<T> extends ParseTreeVisitor<T> {
	T visitEdgeDecl(AdvTdlParser.EdgeDeclContext ctx);

	T visitTermExpr(AdvTdlParser.TermExprContext ctx);

	T visitDecNumber(AdvTdlParser.DecNumberContext ctx);

	T visitTimerDecl(AdvTdlParser.TimerDeclContext ctx);

	T visitElseClause(AdvTdlParser.ElseClauseContext ctx);

	T visitNumber(AdvTdlParser.NumberContext ctx);

	T visitExpr(AdvTdlParser.ExprContext ctx);

	T visitRangeDecl(AdvTdlParser.RangeDeclContext ctx);

	T visitProg(AdvTdlParser.ProgContext ctx);

	T visitTermDecl(AdvTdlParser.TermDeclContext ctx);

	T visitWhenClause(AdvTdlParser.WhenClauseContext ctx);

	T visitEdgeTermDecl(AdvTdlParser.EdgeTermDeclContext ctx);

	T visitStageDef(AdvTdlParser.StageDefContext ctx);

	T visitDecl(AdvTdlParser.DeclContext ctx);
}