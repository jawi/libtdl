
/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;

public class AdvTdlBaseListener implements AdvTdlListener {
	@Override public void enterEdgeDecl(AdvTdlParser.EdgeDeclContext ctx) { }
	@Override public void exitEdgeDecl(AdvTdlParser.EdgeDeclContext ctx) { }

	@Override public void enterTermExpr(AdvTdlParser.TermExprContext ctx) { }
	@Override public void exitTermExpr(AdvTdlParser.TermExprContext ctx) { }

	@Override public void enterDecNumber(AdvTdlParser.DecNumberContext ctx) { }
	@Override public void exitDecNumber(AdvTdlParser.DecNumberContext ctx) { }

	@Override public void enterTimerDecl(AdvTdlParser.TimerDeclContext ctx) { }
	@Override public void exitTimerDecl(AdvTdlParser.TimerDeclContext ctx) { }

	@Override public void enterElseClause(AdvTdlParser.ElseClauseContext ctx) { }
	@Override public void exitElseClause(AdvTdlParser.ElseClauseContext ctx) { }

	@Override public void enterNumber(AdvTdlParser.NumberContext ctx) { }
	@Override public void exitNumber(AdvTdlParser.NumberContext ctx) { }

	@Override public void enterExpr(AdvTdlParser.ExprContext ctx) { }
	@Override public void exitExpr(AdvTdlParser.ExprContext ctx) { }

	@Override public void enterRangeDecl(AdvTdlParser.RangeDeclContext ctx) { }
	@Override public void exitRangeDecl(AdvTdlParser.RangeDeclContext ctx) { }

	@Override public void enterProg(AdvTdlParser.ProgContext ctx) { }
	@Override public void exitProg(AdvTdlParser.ProgContext ctx) { }

	@Override public void enterTermDecl(AdvTdlParser.TermDeclContext ctx) { }
	@Override public void exitTermDecl(AdvTdlParser.TermDeclContext ctx) { }

	@Override public void enterWhenClause(AdvTdlParser.WhenClauseContext ctx) { }
	@Override public void exitWhenClause(AdvTdlParser.WhenClauseContext ctx) { }

	@Override public void enterEdgeTermDecl(AdvTdlParser.EdgeTermDeclContext ctx) { }
	@Override public void exitEdgeTermDecl(AdvTdlParser.EdgeTermDeclContext ctx) { }

	@Override public void enterStageDef(AdvTdlParser.StageDefContext ctx) { }
	@Override public void exitStageDef(AdvTdlParser.StageDefContext ctx) { }

	@Override public void enterDecl(AdvTdlParser.DeclContext ctx) { }
	@Override public void exitDecl(AdvTdlParser.DeclContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	@Override public void visitTerminal(TerminalNode node) { }
	@Override public void visitErrorNode(ErrorNode node) { }
}