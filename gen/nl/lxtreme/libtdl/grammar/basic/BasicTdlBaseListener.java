
/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;

public class BasicTdlBaseListener implements BasicTdlListener {
	@Override public void enterWhenClause(BasicTdlParser.WhenClauseContext ctx) { }
	@Override public void exitWhenClause(BasicTdlParser.WhenClauseContext ctx) { }

	@Override public void enterTermDecl(BasicTdlParser.TermDeclContext ctx) { }
	@Override public void exitTermDecl(BasicTdlParser.TermDeclContext ctx) { }

	@Override public void enterProg(BasicTdlParser.ProgContext ctx) { }
	@Override public void exitProg(BasicTdlParser.ProgContext ctx) { }

	@Override public void enterStageDef(BasicTdlParser.StageDefContext ctx) { }
	@Override public void exitStageDef(BasicTdlParser.StageDefContext ctx) { }

	@Override public void enterDecNumber(BasicTdlParser.DecNumberContext ctx) { }
	@Override public void exitDecNumber(BasicTdlParser.DecNumberContext ctx) { }

	@Override public void enterNumber(BasicTdlParser.NumberContext ctx) { }
	@Override public void exitNumber(BasicTdlParser.NumberContext ctx) { }

	@Override public void enterExpr(BasicTdlParser.ExprContext ctx) { }
	@Override public void exitExpr(BasicTdlParser.ExprContext ctx) { }

	@Override public void enterActiveClause(BasicTdlParser.ActiveClauseContext ctx) { }
	@Override public void exitActiveClause(BasicTdlParser.ActiveClauseContext ctx) { }

	@Override public void enterDecl(BasicTdlParser.DeclContext ctx) { }
	@Override public void exitDecl(BasicTdlParser.DeclContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	@Override public void visitTerminal(TerminalNode node) { }
	@Override public void visitErrorNode(ErrorNode node) { }
}