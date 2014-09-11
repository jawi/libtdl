// Generated from AdvTdl.g4 by ANTLR 4.2.2

/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;


import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AdvTdlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AdvTdlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(@NotNull AdvTdlParser.DeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#elseAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseAction(@NotNull AdvTdlParser.ElseActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#termExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermExpr(@NotNull AdvTdlParser.TermExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#timerDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimerDecl(@NotNull AdvTdlParser.TimerDeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#edgeTermDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEdgeTermDecl(@NotNull AdvTdlParser.EdgeTermDeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#rangeDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeDecl(@NotNull AdvTdlParser.RangeDeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(@NotNull AdvTdlParser.ProgContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#termDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermDecl(@NotNull AdvTdlParser.TermDeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull AdvTdlParser.NumberContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#decNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecNumber(@NotNull AdvTdlParser.DecNumberContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#whenAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenAction(@NotNull AdvTdlParser.WhenActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull AdvTdlParser.ExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#stageDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStageDef(@NotNull AdvTdlParser.StageDefContext ctx);

	/**
	 * Visit a parse tree produced by {@link AdvTdlParser#edgeDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEdgeDecl(@NotNull AdvTdlParser.EdgeDeclContext ctx);
}