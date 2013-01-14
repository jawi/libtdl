
/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface BasicTdlListener extends ParseTreeListener {
	void enterWhenClause(BasicTdlParser.WhenClauseContext ctx);
	void exitWhenClause(BasicTdlParser.WhenClauseContext ctx);

	void enterTermDecl(BasicTdlParser.TermDeclContext ctx);
	void exitTermDecl(BasicTdlParser.TermDeclContext ctx);

	void enterProg(BasicTdlParser.ProgContext ctx);
	void exitProg(BasicTdlParser.ProgContext ctx);

	void enterStageDef(BasicTdlParser.StageDefContext ctx);
	void exitStageDef(BasicTdlParser.StageDefContext ctx);

	void enterDecNumber(BasicTdlParser.DecNumberContext ctx);
	void exitDecNumber(BasicTdlParser.DecNumberContext ctx);

	void enterNumber(BasicTdlParser.NumberContext ctx);
	void exitNumber(BasicTdlParser.NumberContext ctx);

	void enterExpr(BasicTdlParser.ExprContext ctx);
	void exitExpr(BasicTdlParser.ExprContext ctx);

	void enterActiveClause(BasicTdlParser.ActiveClauseContext ctx);
	void exitActiveClause(BasicTdlParser.ActiveClauseContext ctx);

	void enterDecl(BasicTdlParser.DeclContext ctx);
	void exitDecl(BasicTdlParser.DeclContext ctx);
}