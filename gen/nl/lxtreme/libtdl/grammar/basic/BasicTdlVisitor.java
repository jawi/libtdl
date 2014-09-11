// Generated from BasicTdl.g4 by ANTLR 4.2.2

/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;


import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BasicTdlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BasicTdlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BasicTdlParser#termDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermDecl(@NotNull BasicTdlParser.TermDeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link BasicTdlParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull BasicTdlParser.NumberContext ctx);

	/**
	 * Visit a parse tree produced by {@link BasicTdlParser#decNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecNumber(@NotNull BasicTdlParser.DecNumberContext ctx);

	/**
	 * Visit a parse tree produced by {@link BasicTdlParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(@NotNull BasicTdlParser.DeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link BasicTdlParser#termExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermExpr(@NotNull BasicTdlParser.TermExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link BasicTdlParser#whenAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenAction(@NotNull BasicTdlParser.WhenActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link BasicTdlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull BasicTdlParser.ExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link BasicTdlParser#activeClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActiveClause(@NotNull BasicTdlParser.ActiveClauseContext ctx);

	/**
	 * Visit a parse tree produced by {@link BasicTdlParser#stageDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStageDef(@NotNull BasicTdlParser.StageDefContext ctx);

	/**
	 * Visit a parse tree produced by {@link BasicTdlParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(@NotNull BasicTdlParser.ProgContext ctx);
}