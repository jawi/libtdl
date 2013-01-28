// $ANTLR ANTLRVersion> BasicTdlParser.java generatedTimestamp>

/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.*;
import org.antlr.v4.runtime.tree.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicTdlParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, NL=2, WS=3, ASSIGN=4, EQUALS_TO=5, MASK=6, VALUE=7, STAGE=8, 
		CAPTURE=9, WHEN=10, START=11, STOP=12, GOTO=13, NEXT=14, ACTIVATE=15, 
		ON=16, LEVEL=17, IMMEDIATELY=18, DELAY=19, NOT=20, XOR=21, SAMPLES=22, 
		COMMA=23, COLON=24, AT=25, BIN_LITERAL=26, HEX_LITERAL=27, OCT_LITERAL=28, 
		DEC_LITERAL=29, TIME_UNIT=30, TERM_NAME=31;
	public static final String[] tokenNames = {
		"<INVALID>", "COMMENT", "NL", "WS", "':='", "'='", "'mask'", "'value'", 
		"'stage'", "'capture'", "'when'", "'start'", "'stop'", "'goto'", "'next'", 
		"'activate'", "'on'", "'level'", "'immediately'", "'delay'", "'~'", "'^'", 
		"'#'", "','", "':'", "'@'", "BIN_LITERAL", "HEX_LITERAL", "OCT_LITERAL", 
		"DEC_LITERAL", "TIME_UNIT", "TERM_NAME"
	};
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_termDecl = 2, RULE_stageDef = 3, RULE_activeClause = 4, 
		RULE_whenAction = 5, RULE_termExpr = 6, RULE_expr = 7, RULE_number = 8, 
		RULE_decNumber = 9;
	public static final String[] ruleNames = {
		"prog", "decl", "termDecl", "stageDef", "activeClause", "whenAction", 
		"termExpr", "expr", "number", "decNumber"
	};

	@Override
	public String getGrammarFileName() { return "BasicTdl.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }



	public BasicTdlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public List<StageDefContext> stageDef() {
			return getRuleContexts(StageDefContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public StageDefContext stageDef(int i) {
			return getRuleContext(StageDefContext.class,i);
		}
		public TerminalNode EOF() { return getToken(BasicTdlParser.EOF, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitProg(this);
			else return null;
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STAGE || _la==TERM_NAME) {
				{
				setState(22);
				switch (_input.LA(1)) {
				case TERM_NAME:
					{
					setState(20); decl();
					}
					break;
				case STAGE:
					{
					setState(21); stageDef();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(27); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public TermDeclContext termDecl() {
			return getRuleContext(TermDeclContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(BasicTdlParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(BasicTdlParser.WS, i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitDecl(this);
			else return null;
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); termDecl();
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(30); match(WS);
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermDeclContext extends ParserRuleContext {
		public Token name;
		public NumberContext mask;
		public NumberContext value;
		public TerminalNode TERM_NAME() { return getToken(BasicTdlParser.TERM_NAME, 0); }
		public TerminalNode XOR() { return getToken(BasicTdlParser.XOR, 0); }
		public TerminalNode MASK() { return getToken(BasicTdlParser.MASK, 0); }
		public TerminalNode VALUE() { return getToken(BasicTdlParser.VALUE, 0); }
		public TerminalNode COMMA() { return getToken(BasicTdlParser.COMMA, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode EQUALS_TO() { return getToken(BasicTdlParser.EQUALS_TO, 0); }
		public TerminalNode ASSIGN() { return getToken(BasicTdlParser.ASSIGN, 0); }
		public TermDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitTermDecl(this);
			else return null;
		}
	}

	public final TermDeclContext termDecl() throws RecognitionException {
		TermDeclContext _localctx = new TermDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_termDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); ((TermDeclContext)_localctx).name = match(TERM_NAME);
			setState(37); match(ASSIGN);
			setState(61);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				{
				setState(38); match(MASK);
				setState(39); match(EQUALS_TO);
				setState(40); ((TermDeclContext)_localctx).mask = number();
				}
				setState(42); match(COMMA);
				{
				setState(43); match(VALUE);
				setState(44); match(EQUALS_TO);
				setState(45); ((TermDeclContext)_localctx).value = number();
				}
				}
				break;

			case 2:
				{
				{
				setState(47); match(MASK);
				setState(48); match(EQUALS_TO);
				setState(49); number();
				}
				 notifyErrorListeners("missing term value"); 
				}
				break;

			case 3:
				{
				{
				setState(53); ((TermDeclContext)_localctx).mask = number();
				setState(54); match(XOR);
				setState(55); ((TermDeclContext)_localctx).value = number();
				}
				}
				break;

			case 4:
				{
				{
				setState(57); number();
				}
				 notifyErrorListeners("missing term value"); 
				}
				break;

			case 5:
				{
				 notifyErrorListeners("missing mask and value"); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StageDefContext extends ParserRuleContext {
		public DecNumberContext n;
		public TerminalNode COLON() { return getToken(BasicTdlParser.COLON, 0); }
		public TermExprContext termExpr() {
			return getRuleContext(TermExprContext.class,0);
		}
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(BasicTdlParser.COMMA, 0); }
		public TerminalNode WHEN() { return getToken(BasicTdlParser.WHEN, 0); }
		public ActiveClauseContext activeClause() {
			return getRuleContext(ActiveClauseContext.class,0);
		}
		public TerminalNode STAGE() { return getToken(BasicTdlParser.STAGE, 0); }
		public TerminalNode ACTIVATE() { return getToken(BasicTdlParser.ACTIVATE, 0); }
		public WhenActionContext whenAction() {
			return getRuleContext(WhenActionContext.class,0);
		}
		public StageDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stageDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitStageDef(this);
			else return null;
		}
	}

	public final StageDefContext stageDef() throws RecognitionException {
		StageDefContext _localctx = new StageDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stageDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(63); match(STAGE);
				setState(64); ((StageDefContext)_localctx).n = decNumber();
				setState(65); match(COLON);
				}
				break;

			case 2:
				{
				setState(67); match(STAGE);
				setState(68); decNumber();
				 notifyErrorListeners("missing colon"); 
				}
				break;

			case 3:
				{
				setState(71); match(STAGE);
				 notifyErrorListeners("missing stage ID"); 
				}
				break;
			}
			setState(86);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(75); match(ACTIVATE);
				setState(76); activeClause();
				setState(77); match(COMMA);
				}
				break;

			case 2:
				{
				setState(79); match(ACTIVATE);
				setState(80); activeClause();
				 notifyErrorListeners("missing comma"); 
				}
				break;

			case 3:
				{
				setState(83); match(ACTIVATE);
				 notifyErrorListeners("missing activate clause"); 
				}
				break;

			case 4:
				{
				 notifyErrorListeners("missing activate clause"); 
				}
				break;
			}
			setState(99);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(88); match(WHEN);
				setState(89); termExpr();
				setState(90); whenAction();
				}
				break;

			case 2:
				{
				setState(92); match(WHEN);
				setState(93); termExpr();
				 notifyErrorListeners("missing when action"); 
				}
				break;

			case 3:
				{
				setState(96); match(WHEN);
				 notifyErrorListeners("missing when expression"); 
				}
				break;

			case 4:
				{
				 notifyErrorListeners("missing when clause"); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActiveClauseContext extends ParserRuleContext {
		public DecNumberContext n;
		public TerminalNode ON() { return getToken(BasicTdlParser.ON, 0); }
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public TerminalNode LEVEL() { return getToken(BasicTdlParser.LEVEL, 0); }
		public TerminalNode IMMEDIATELY() { return getToken(BasicTdlParser.IMMEDIATELY, 0); }
		public ActiveClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_activeClause; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitActiveClause(this);
			else return null;
		}
	}

	public final ActiveClauseContext activeClause() throws RecognitionException {
		ActiveClauseContext _localctx = new ActiveClauseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_activeClause);
		try {
			setState(113);
			switch (_input.LA(1)) {
			case EOF:
			case STAGE:
			case WHEN:
			case ON:
			case COMMA:
			case TERM_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(101); match(ON);
					setState(102); match(LEVEL);
					setState(103); ((ActiveClauseContext)_localctx).n = decNumber();
					}
					break;

				case 2:
					{
					setState(104); match(ON);
					setState(105); match(LEVEL);
					 notifyErrorListeners("missing level ID"); 
					}
					break;

				case 3:
					{
					setState(107); match(ON);
					 notifyErrorListeners("missing level"); 
					}
					break;

				case 4:
					{
					 notifyErrorListeners("missing on level"); 
					}
					break;
				}
				}
				break;
			case IMMEDIATELY:
				enterOuterAlt(_localctx, 2);
				{
				setState(112); match(IMMEDIATELY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhenActionContext extends ParserRuleContext {
		public DecNumberContext n;
		public TerminalNode DELAY() { return getToken(BasicTdlParser.DELAY, 0); }
		public TerminalNode CAPTURE() { return getToken(BasicTdlParser.CAPTURE, 0); }
		public TerminalNode START() { return getToken(BasicTdlParser.START, 0); }
		public TerminalNode NEXT() { return getToken(BasicTdlParser.NEXT, 0); }
		public TerminalNode SAMPLES() { return getToken(BasicTdlParser.SAMPLES, 0); }
		public TerminalNode GOTO() { return getToken(BasicTdlParser.GOTO, 0); }
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public WhenActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenAction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitWhenAction(this);
			else return null;
		}
	}

	public final WhenActionContext whenAction() throws RecognitionException {
		WhenActionContext _localctx = new WhenActionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_whenAction);
		try {
			setState(133);
			switch (_input.LA(1)) {
			case START:
				enterOuterAlt(_localctx, 1);
				{
				setState(115); match(START);
				setState(116); match(CAPTURE);
				setState(125);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(117); match(DELAY);
					setState(118); ((WhenActionContext)_localctx).n = decNumber();
					setState(119); match(SAMPLES);
					}
					break;

				case 2:
					{
					setState(121); match(DELAY);
					setState(122); decNumber();
					 notifyErrorListeners("missing delay unit"); 
					}
					break;
				}
				}
				break;
			case GOTO:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(127); match(GOTO);
					setState(128); match(NEXT);
					}
					break;

				case 2:
					{
					setState(129); match(GOTO);
					 notifyErrorListeners("missing next"); 
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermExprContext extends ParserRuleContext {
		public DecNumberContext n;
		public TerminalNode AT() { return getToken(BasicTdlParser.AT, 0); }
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TermExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitTermExpr(this);
			else return null;
		}
	}

	public final TermExprContext termExpr() throws RecognitionException {
		TermExprContext _localctx = new TermExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_termExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135); expr();
			setState(138);
			_la = _input.LA(1);
			if (_la==AT) {
				{
				setState(136); match(AT);
				setState(137); ((TermExprContext)_localctx).n = decNumber();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Token term;
		public TerminalNode TERM_NAME() { return getToken(BasicTdlParser.TERM_NAME, 0); }
		public List<TerminalNode> NOT() { return getTokens(BasicTdlParser.NOT); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NOT(int i) {
			return getToken(BasicTdlParser.NOT, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitExpr(this);
			else return null;
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expr);
		try {
			setState(150);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(140); match(NOT);
					setState(141); expr();
					}
					break;

				case 2:
					{
					setState(142); match(NOT);
					setState(143); match(NOT);
					setState(144); expr();
					 notifyErrorListeners("meaningless operation"); 
					}
					break;
				}
				}
				break;
			case TERM_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(149); ((ExprContext)_localctx).term = match(TERM_NAME);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode BIN_LITERAL() { return getToken(BasicTdlParser.BIN_LITERAL, 0); }
		public TerminalNode HEX_LITERAL() { return getToken(BasicTdlParser.HEX_LITERAL, 0); }
		public TerminalNode OCT_LITERAL() { return getToken(BasicTdlParser.OCT_LITERAL, 0); }
		public TerminalNode DEC_LITERAL() { return getToken(BasicTdlParser.DEC_LITERAL, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitNumber(this);
			else return null;
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BIN_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << DEC_LITERAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecNumberContext extends ParserRuleContext {
		public TerminalNode DEC_LITERAL() { return getToken(BasicTdlParser.DEC_LITERAL, 0); }
		public DecNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decNumber; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitDecNumber(this);
			else return null;
		}
	}

	public final DecNumberContext decNumber() throws RecognitionException {
		DecNumberContext _localctx = new DecNumberContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_decNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); match(DEC_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\1\3\37\u009d\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6"+
		"\2\7\7\7\2\b\7\b\2\t\7\t\1\0\1\0\5\0\27\b\0\n\0\f\0\32\t\0\1\0\1\0\1\1"+
		"\1\1\5\1 \b\1\n\1\f\1#\t\1\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2"+
		"\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\3\2>\b\2\1\3"+
		"\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\3\3J\b\3\1\3\1\3\1\3\1\3\1\3\1\3"+
		"\1\3\1\3\1\3\1\3\1\3\3\3W\b\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3"+
		"\1\3\3\3d\b\3\1\4\1\4\1\4\1\4\1\4\1\4\1\4\1\4\1\4\3\4o\b\4\1\4\3\4r\b"+
		"\4\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\3\5~\b\5\1\5\1\5\1\5\1\5\3"+
		"\5\u0084\b\5\3\5\u0086\b\5\1\6\1\6\1\6\3\6\u008b\b\6\1\7\1\7\1\7\1\7\1"+
		"\7\1\7\1\7\3\7\u0094\b\7\1\7\3\7\u0097\b\7\1\b\1\b\1\t\1\t\1\t\0\n\0\2"+
		"\4\6\b\n\f\16\20\22\0\1\1\32\35\u00ac\0\30\1\0\0\0\2\35\1\0\0\0\4$\1\0"+
		"\0\0\6I\1\0\0\0\bq\1\0\0\0\n\u0085\1\0\0\0\f\u0087\1\0\0\0\16\u0096\1"+
		"\0\0\0\20\u0098\1\0\0\0\22\u009a\1\0\0\0\24\27\3\2\1\0\25\27\3\6\3\0\26"+
		"\24\1\0\0\0\26\25\1\0\0\0\27\32\1\0\0\0\30\26\1\0\0\0\30\31\1\0\0\0\31"+
		"\33\1\0\0\0\32\30\1\0\0\0\33\34\5\uffff\0\0\34\1\1\0\0\0\35!\3\4\2\0\36"+
		" \5\3\0\0\37\36\1\0\0\0 #\1\0\0\0!\37\1\0\0\0!\"\1\0\0\0\"\3\1\0\0\0#"+
		"!\1\0\0\0$%\5\37\0\0%=\5\4\0\0&\'\5\6\0\0\'(\5\5\0\0()\3\20\b\0)*\1\0"+
		"\0\0*+\5\27\0\0+,\5\7\0\0,-\5\5\0\0-.\3\20\b\0.>\1\0\0\0/\60\5\6\0\0\60"+
		"\61\5\5\0\0\61\62\3\20\b\0\62\63\1\0\0\0\63\64\6\2\uffff\0\64>\1\0\0\0"+
		"\65\66\3\20\b\0\66\67\5\25\0\0\678\3\20\b\08>\1\0\0\09:\3\20\b\0:;\6\2"+
		"\uffff\0;>\1\0\0\0<>\6\2\uffff\0=&\1\0\0\0=/\1\0\0\0=\65\1\0\0\0=9\1\0"+
		"\0\0=<\1\0\0\0>\5\1\0\0\0?@\5\b\0\0@A\3\22\t\0AB\5\30\0\0BJ\1\0\0\0CD"+
		"\5\b\0\0DE\3\22\t\0EF\6\3\uffff\0FJ\1\0\0\0GH\5\b\0\0HJ\6\3\uffff\0I?"+
		"\1\0\0\0IC\1\0\0\0IG\1\0\0\0JV\1\0\0\0KL\5\17\0\0LM\3\b\4\0MN\5\27\0\0"+
		"NW\1\0\0\0OP\5\17\0\0PQ\3\b\4\0QR\6\3\uffff\0RW\1\0\0\0ST\5\17\0\0TW\6"+
		"\3\uffff\0UW\6\3\uffff\0VK\1\0\0\0VO\1\0\0\0VS\1\0\0\0VU\1\0\0\0Wc\1\0"+
		"\0\0XY\5\n\0\0YZ\3\f\6\0Z[\3\n\5\0[d\1\0\0\0\\]\5\n\0\0]^\3\f\6\0^_\6"+
		"\3\uffff\0_d\1\0\0\0`a\5\n\0\0ad\6\3\uffff\0bd\6\3\uffff\0cX\1\0\0\0c"+
		"\\\1\0\0\0c`\1\0\0\0cb\1\0\0\0d\7\1\0\0\0ef\5\20\0\0fg\5\21\0\0go\3\22"+
		"\t\0hi\5\20\0\0ij\5\21\0\0jo\6\4\uffff\0kl\5\20\0\0lo\6\4\uffff\0mo\6"+
		"\4\uffff\0ne\1\0\0\0nh\1\0\0\0nk\1\0\0\0nm\1\0\0\0or\1\0\0\0pr\5\22\0"+
		"\0qn\1\0\0\0qp\1\0\0\0r\t\1\0\0\0st\5\13\0\0t}\5\t\0\0uv\5\23\0\0vw\3"+
		"\22\t\0wx\5\26\0\0x~\1\0\0\0yz\5\23\0\0z{\3\22\t\0{|\6\5\uffff\0|~\1\0"+
		"\0\0}u\1\0\0\0}y\1\0\0\0}~\1\0\0\0~\u0086\1\0\0\0\177\u0080\5\r\0\0\u0080"+
		"\u0084\5\16\0\0\u0081\u0082\5\r\0\0\u0082\u0084\6\5\uffff\0\u0083\177"+
		"\1\0\0\0\u0083\u0081\1\0\0\0\u0084\u0086\1\0\0\0\u0085s\1\0\0\0\u0085"+
		"\u0083\1\0\0\0\u0086\13\1\0\0\0\u0087\u008a\3\16\7\0\u0088\u0089\5\31"+
		"\0\0\u0089\u008b\3\22\t\0\u008a\u0088\1\0\0\0\u008a\u008b\1\0\0\0\u008b"+
		"\r\1\0\0\0\u008c\u008d\5\24\0\0\u008d\u0094\3\16\7\0\u008e\u008f\5\24"+
		"\0\0\u008f\u0090\5\24\0\0\u0090\u0091\3\16\7\0\u0091\u0092\6\7\uffff\0"+
		"\u0092\u0094\1\0\0\0\u0093\u008c\1\0\0\0\u0093\u008e\1\0\0\0\u0094\u0097"+
		"\1\0\0\0\u0095\u0097\5\37\0\0\u0096\u0093\1\0\0\0\u0096\u0095\1\0\0\0"+
		"\u0097\17\1\0\0\0\u0098\u0099\7\0\0\0\u0099\21\1\0\0\0\u009a\u009b\5\35"+
		"\0\0\u009b\23\1\0\0\0\17\26\30!=IVcnq}\u0083\u0085\u008a\u0093\u0096";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}