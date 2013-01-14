
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

public interface AdvTdlListener extends ParseTreeListener {
	void enterEdgeDecl(AdvTdlParser.EdgeDeclContext ctx);
	void exitEdgeDecl(AdvTdlParser.EdgeDeclContext ctx);

	void enterTermExpr(AdvTdlParser.TermExprContext ctx);
	void exitTermExpr(AdvTdlParser.TermExprContext ctx);

	void enterDecNumber(AdvTdlParser.DecNumberContext ctx);
	void exitDecNumber(AdvTdlParser.DecNumberContext ctx);

	void enterTimerDecl(AdvTdlParser.TimerDeclContext ctx);
	void exitTimerDecl(AdvTdlParser.TimerDeclContext ctx);

	void enterElseClause(AdvTdlParser.ElseClauseContext ctx);
	void exitElseClause(AdvTdlParser.ElseClauseContext ctx);

	void enterNumber(AdvTdlParser.NumberContext ctx);
	void exitNumber(AdvTdlParser.NumberContext ctx);

	void enterExpr(AdvTdlParser.ExprContext ctx);
	void exitExpr(AdvTdlParser.ExprContext ctx);

	void enterRangeDecl(AdvTdlParser.RangeDeclContext ctx);
	void exitRangeDecl(AdvTdlParser.RangeDeclContext ctx);

	void enterProg(AdvTdlParser.ProgContext ctx);
	void exitProg(AdvTdlParser.ProgContext ctx);

	void enterTermDecl(AdvTdlParser.TermDeclContext ctx);
	void exitTermDecl(AdvTdlParser.TermDeclContext ctx);

	void enterWhenClause(AdvTdlParser.WhenClauseContext ctx);
	void exitWhenClause(AdvTdlParser.WhenClauseContext ctx);

	void enterEdgeTermDecl(AdvTdlParser.EdgeTermDeclContext ctx);
	void exitEdgeTermDecl(AdvTdlParser.EdgeTermDeclContext ctx);

	void enterStageDef(AdvTdlParser.StageDefContext ctx);
	void exitStageDef(AdvTdlParser.StageDefContext ctx);

	void enterDecl(AdvTdlParser.DeclContext ctx);
	void exitDecl(AdvTdlParser.DeclContext ctx);
}