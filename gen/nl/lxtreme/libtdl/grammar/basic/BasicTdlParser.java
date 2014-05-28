// Generated from BasicTdl.g4 by ANTLR 4.2.2

/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

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
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BasicTdlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public StageDefContext stageDef(int i) {
			return getRuleContext(StageDefContext.class,i);
		}
		public TerminalNode EOF() { return getToken(BasicTdlParser.EOF, 0); }
		public List<StageDefContext> stageDef() {
			return getRuleContexts(StageDefContext.class);
		}
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
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
		public List<TerminalNode> WS() { return getTokens(BasicTdlParser.WS); }
		public TermDeclContext termDecl() {
			return getRuleContext(TermDeclContext.class,0);
		}
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
			else return visitor.visitChildren(this);
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
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public List<TerminalNode> EQUALS_TO() { return getTokens(BasicTdlParser.EQUALS_TO); }
		public TerminalNode TERM_NAME() { return getToken(BasicTdlParser.TERM_NAME, 0); }
		public TerminalNode VALUE() { return getToken(BasicTdlParser.VALUE, 0); }
		public TerminalNode XOR() { return getToken(BasicTdlParser.XOR, 0); }
		public TerminalNode ASSIGN() { return getToken(BasicTdlParser.ASSIGN, 0); }
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(BasicTdlParser.COMMA, 0); }
		public TerminalNode MASK() { return getToken(BasicTdlParser.MASK, 0); }
		public TerminalNode EQUALS_TO(int i) {
			return getToken(BasicTdlParser.EQUALS_TO, i);
		}
		public TermDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitTermDecl(this);
			else return visitor.visitChildren(this);
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
		public WhenActionContext whenAction() {
			return getRuleContext(WhenActionContext.class,0);
		}
		public TerminalNode STAGE() { return getToken(BasicTdlParser.STAGE, 0); }
		public TerminalNode COMMA() { return getToken(BasicTdlParser.COMMA, 0); }
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public TermExprContext termExpr() {
			return getRuleContext(TermExprContext.class,0);
		}
		public ActiveClauseContext activeClause() {
			return getRuleContext(ActiveClauseContext.class,0);
		}
		public TerminalNode COLON() { return getToken(BasicTdlParser.COLON, 0); }
		public TerminalNode WHEN() { return getToken(BasicTdlParser.WHEN, 0); }
		public TerminalNode ACTIVATE() { return getToken(BasicTdlParser.ACTIVATE, 0); }
		public StageDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stageDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitStageDef(this);
			else return visitor.visitChildren(this);
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
		public TerminalNode LEVEL() { return getToken(BasicTdlParser.LEVEL, 0); }
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public TerminalNode IMMEDIATELY() { return getToken(BasicTdlParser.IMMEDIATELY, 0); }
		public ActiveClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_activeClause; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitActiveClause(this);
			else return visitor.visitChildren(this);
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
		public TerminalNode GOTO() { return getToken(BasicTdlParser.GOTO, 0); }
		public TerminalNode NEXT() { return getToken(BasicTdlParser.NEXT, 0); }
		public TerminalNode DELAY() { return getToken(BasicTdlParser.DELAY, 0); }
		public TerminalNode START() { return getToken(BasicTdlParser.START, 0); }
		public TerminalNode SAMPLES() { return getToken(BasicTdlParser.SAMPLES, 0); }
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public TerminalNode CAPTURE() { return getToken(BasicTdlParser.CAPTURE, 0); }
		public WhenActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenAction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitWhenAction(this);
			else return visitor.visitChildren(this);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public TerminalNode AT() { return getToken(BasicTdlParser.AT, 0); }
		public TermExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitTermExpr(this);
			else return visitor.visitChildren(this);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NOT(int i) {
			return getToken(BasicTdlParser.NOT, i);
		}
		public List<TerminalNode> NOT() { return getTokens(BasicTdlParser.NOT); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
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
		public TerminalNode OCT_LITERAL() { return getToken(BasicTdlParser.OCT_LITERAL, 0); }
		public TerminalNode BIN_LITERAL() { return getToken(BasicTdlParser.BIN_LITERAL, 0); }
		public TerminalNode DEC_LITERAL() { return getToken(BasicTdlParser.DEC_LITERAL, 0); }
		public TerminalNode HEX_LITERAL() { return getToken(BasicTdlParser.HEX_LITERAL, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
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
			else return visitor.visitChildren(this);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u009f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\7\2\31\n\2\f\2\16\2\34\13\2\3\2\3\2\3\3\3\3\7\3\"\n\3\f\3"+
		"\16\3%\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4@\n\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\5\5L\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5Y\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5f\n\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6q\n\6\3\6\5\6t\n\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0080\n\7\3\7\3\7\3\7\3\7\5\7\u0086\n\7"+
		"\5\7\u0088\n\7\3\b\3\b\3\b\5\b\u008d\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5"+
		"\t\u0096\n\t\3\t\5\t\u0099\n\t\3\n\3\n\3\13\3\13\3\13\2\2\f\2\4\6\b\n"+
		"\f\16\20\22\24\2\3\3\2\34\37\u00ae\2\32\3\2\2\2\4\37\3\2\2\2\6&\3\2\2"+
		"\2\bK\3\2\2\2\ns\3\2\2\2\f\u0087\3\2\2\2\16\u0089\3\2\2\2\20\u0098\3\2"+
		"\2\2\22\u009a\3\2\2\2\24\u009c\3\2\2\2\26\31\5\4\3\2\27\31\5\b\5\2\30"+
		"\26\3\2\2\2\30\27\3\2\2\2\31\34\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33"+
		"\35\3\2\2\2\34\32\3\2\2\2\35\36\7\2\2\3\36\3\3\2\2\2\37#\5\6\4\2 \"\7"+
		"\5\2\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\5\3\2\2\2%#\3\2\2\2&"+
		"\'\7!\2\2\'?\7\6\2\2()\7\b\2\2)*\7\7\2\2*+\5\22\n\2+,\3\2\2\2,-\7\31\2"+
		"\2-.\7\t\2\2./\7\7\2\2/\60\5\22\n\2\60@\3\2\2\2\61\62\7\b\2\2\62\63\7"+
		"\7\2\2\63\64\5\22\n\2\64\65\3\2\2\2\65\66\b\4\1\2\66@\3\2\2\2\678\5\22"+
		"\n\289\7\27\2\29:\5\22\n\2:@\3\2\2\2;<\5\22\n\2<=\b\4\1\2=@\3\2\2\2>@"+
		"\b\4\1\2?(\3\2\2\2?\61\3\2\2\2?\67\3\2\2\2?;\3\2\2\2?>\3\2\2\2@\7\3\2"+
		"\2\2AB\7\n\2\2BC\5\24\13\2CD\7\32\2\2DL\3\2\2\2EF\7\n\2\2FG\5\24\13\2"+
		"GH\b\5\1\2HL\3\2\2\2IJ\7\n\2\2JL\b\5\1\2KA\3\2\2\2KE\3\2\2\2KI\3\2\2\2"+
		"LX\3\2\2\2MN\7\21\2\2NO\5\n\6\2OP\7\31\2\2PY\3\2\2\2QR\7\21\2\2RS\5\n"+
		"\6\2ST\b\5\1\2TY\3\2\2\2UV\7\21\2\2VY\b\5\1\2WY\b\5\1\2XM\3\2\2\2XQ\3"+
		"\2\2\2XU\3\2\2\2XW\3\2\2\2Ye\3\2\2\2Z[\7\f\2\2[\\\5\16\b\2\\]\5\f\7\2"+
		"]f\3\2\2\2^_\7\f\2\2_`\5\16\b\2`a\b\5\1\2af\3\2\2\2bc\7\f\2\2cf\b\5\1"+
		"\2df\b\5\1\2eZ\3\2\2\2e^\3\2\2\2eb\3\2\2\2ed\3\2\2\2f\t\3\2\2\2gh\7\22"+
		"\2\2hi\7\23\2\2iq\5\24\13\2jk\7\22\2\2kl\7\23\2\2lq\b\6\1\2mn\7\22\2\2"+
		"nq\b\6\1\2oq\b\6\1\2pg\3\2\2\2pj\3\2\2\2pm\3\2\2\2po\3\2\2\2qt\3\2\2\2"+
		"rt\7\24\2\2sp\3\2\2\2sr\3\2\2\2t\13\3\2\2\2uv\7\r\2\2v\177\7\13\2\2wx"+
		"\7\25\2\2xy\5\24\13\2yz\7\30\2\2z\u0080\3\2\2\2{|\7\25\2\2|}\5\24\13\2"+
		"}~\b\7\1\2~\u0080\3\2\2\2\177w\3\2\2\2\177{\3\2\2\2\177\u0080\3\2\2\2"+
		"\u0080\u0088\3\2\2\2\u0081\u0082\7\17\2\2\u0082\u0086\7\20\2\2\u0083\u0084"+
		"\7\17\2\2\u0084\u0086\b\7\1\2\u0085\u0081\3\2\2\2\u0085\u0083\3\2\2\2"+
		"\u0086\u0088\3\2\2\2\u0087u\3\2\2\2\u0087\u0085\3\2\2\2\u0088\r\3\2\2"+
		"\2\u0089\u008c\5\20\t\2\u008a\u008b\7\33\2\2\u008b\u008d\5\24\13\2\u008c"+
		"\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\17\3\2\2\2\u008e\u008f\7\26\2"+
		"\2\u008f\u0096\5\20\t\2\u0090\u0091\7\26\2\2\u0091\u0092\7\26\2\2\u0092"+
		"\u0093\5\20\t\2\u0093\u0094\b\t\1\2\u0094\u0096\3\2\2\2\u0095\u008e\3"+
		"\2\2\2\u0095\u0090\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0099\7!\2\2\u0098"+
		"\u0095\3\2\2\2\u0098\u0097\3\2\2\2\u0099\21\3\2\2\2\u009a\u009b\t\2\2"+
		"\2\u009b\23\3\2\2\2\u009c\u009d\7\37\2\2\u009d\25\3\2\2\2\21\30\32#?K"+
		"Xeps\177\u0085\u0087\u008c\u0095\u0098";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}