
/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import nl.lxtreme.libtdl.grammar.*;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface BasicTdlVisitor<T> extends ParseTreeVisitor<T> {
	T visitTermDecl(BasicTdlParser.TermDeclContext ctx);

	T visitProg(BasicTdlParser.ProgContext ctx);

	T visitStageDef(BasicTdlParser.StageDefContext ctx);

	T visitDecNumber(BasicTdlParser.DecNumberContext ctx);

	T visitNumber(BasicTdlParser.NumberContext ctx);

	T visitExpr(BasicTdlParser.ExprContext ctx);

	T visitActiveClause(BasicTdlParser.ActiveClauseContext ctx);

	T visitWhenAction(BasicTdlParser.WhenActionContext ctx);

	T visitDecl(BasicTdlParser.DeclContext ctx);
}