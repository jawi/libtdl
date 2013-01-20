// $ANTLR ANTLRVersion> BasicTdlParser.java generatedTimestamp>

/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import nl.lxtreme.libtdl.grammar.*;

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
		COMMA=23, COLON=24, BIN_LITERAL=25, HEX_LITERAL=26, OCT_LITERAL=27, DEC_LITERAL=28, 
		TIME_UNIT=29, TERM_NAME=30;
	public static final String[] tokenNames = {
		"<INVALID>", "COMMENT", "NL", "WS", "':='", "'='", "'mask'", "'value'", 
		"'stage'", "'capture'", "'when'", "'start'", "'stop'", "'goto'", "'next'", 
		"'activate'", "'on'", "'level'", "'immediately'", "'delay'", "'~'", "'^'", 
		"'#'", "','", "':'", "BIN_LITERAL", "HEX_LITERAL", "OCT_LITERAL", "DEC_LITERAL", 
		"TIME_UNIT", "TERM_NAME"
	};
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_termDecl = 2, RULE_stageDef = 3, RULE_activeClause = 4, 
		RULE_whenAction = 5, RULE_expr = 6, RULE_number = 7, RULE_decNumber = 8;
	public static final String[] ruleNames = {
		"prog", "decl", "termDecl", "stageDef", "activeClause", "whenAction", 
		"expr", "number", "decNumber"
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
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STAGE || _la==TERM_NAME) {
				{
				setState(20);
				switch (_input.LA(1)) {
				case TERM_NAME:
					{
					setState(18); decl();
					}
					break;
				case STAGE:
					{
					setState(19); stageDef();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(25); match(EOF);
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
			setState(27); termDecl();
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(28); match(WS);
				}
				}
				setState(33);
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
			setState(34); ((TermDeclContext)_localctx).name = match(TERM_NAME);
			setState(35); match(ASSIGN);
			setState(59);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				{
				setState(36); match(MASK);
				setState(37); match(EQUALS_TO);
				setState(38); ((TermDeclContext)_localctx).mask = number();
				}
				setState(40); match(COMMA);
				{
				setState(41); match(VALUE);
				setState(42); match(EQUALS_TO);
				setState(43); ((TermDeclContext)_localctx).value = number();
				}
				}
				break;

			case 2:
				{
				{
				setState(45); match(MASK);
				setState(46); match(EQUALS_TO);
				setState(47); number();
				}
				 notifyErrorListeners("missing term value"); 
				}
				break;

			case 3:
				{
				{
				setState(51); ((TermDeclContext)_localctx).mask = number();
				setState(52); match(XOR);
				setState(53); ((TermDeclContext)_localctx).value = number();
				}
				}
				break;

			case 4:
				{
				{
				setState(55); number();
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
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(BasicTdlParser.COMMA, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
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
			setState(71);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(61); match(STAGE);
				setState(62); ((StageDefContext)_localctx).n = decNumber();
				setState(63); match(COLON);
				}
				break;

			case 2:
				{
				setState(65); match(STAGE);
				setState(66); decNumber();
				 notifyErrorListeners("missing colon"); 
				}
				break;

			case 3:
				{
				setState(69); match(STAGE);
				 notifyErrorListeners("missing stage ID"); 
				}
				break;
			}
			setState(84);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(73); match(ACTIVATE);
				setState(74); activeClause();
				setState(75); match(COMMA);
				}
				break;

			case 2:
				{
				setState(77); match(ACTIVATE);
				setState(78); activeClause();
				 notifyErrorListeners("missing comma"); 
				}
				break;

			case 3:
				{
				setState(81); match(ACTIVATE);
				 notifyErrorListeners("missing activate clause"); 
				}
				break;

			case 4:
				{
				 notifyErrorListeners("missing activate clause"); 
				}
				break;
			}
			setState(97);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(86); match(WHEN);
				setState(87); expr();
				setState(88); whenAction();
				}
				break;

			case 2:
				{
				setState(90); match(WHEN);
				setState(91); expr();
				 notifyErrorListeners("missing when action"); 
				}
				break;

			case 3:
				{
				setState(94); match(WHEN);
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
			setState(111);
			switch (_input.LA(1)) {
			case EOF:
			case STAGE:
			case WHEN:
			case ON:
			case COMMA:
			case TERM_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(99); match(ON);
					setState(100); match(LEVEL);
					setState(101); ((ActiveClauseContext)_localctx).n = decNumber();
					}
					break;

				case 2:
					{
					setState(102); match(ON);
					setState(103); match(LEVEL);
					 notifyErrorListeners("missing level ID"); 
					}
					break;

				case 3:
					{
					setState(105); match(ON);
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
				setState(110); match(IMMEDIATELY);
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
			setState(131);
			switch (_input.LA(1)) {
			case START:
				enterOuterAlt(_localctx, 1);
				{
				setState(113); match(START);
				setState(114); match(CAPTURE);
				setState(123);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(115); match(DELAY);
					setState(116); ((WhenActionContext)_localctx).n = decNumber();
					setState(117); match(SAMPLES);
					}
					break;

				case 2:
					{
					setState(119); match(DELAY);
					setState(120); decNumber();
					 notifyErrorListeners("missing delay unit"); 
					}
					break;
				}
				}
				break;
			case GOTO:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(125); match(GOTO);
					setState(126); match(NEXT);
					}
					break;

				case 2:
					{
					setState(127); match(GOTO);
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
		enterRule(_localctx, 12, RULE_expr);
		try {
			setState(143);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(133); match(NOT);
					setState(134); expr();
					}
					break;

				case 2:
					{
					setState(135); match(NOT);
					setState(136); match(NOT);
					setState(137); expr();
					 notifyErrorListeners("meaningless operation"); 
					}
					break;
				}
				}
				break;
			case TERM_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(142); ((ExprContext)_localctx).term = match(TERM_NAME);
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
		enterRule(_localctx, 14, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
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
		enterRule(_localctx, 16, RULE_decNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); match(DEC_LITERAL);
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
		"\1\3\36\u0096\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6"+
		"\2\7\7\7\2\b\7\b\1\0\1\0\5\0\25\b\0\n\0\f\0\30\t\0\1\0\1\0\1\1\1\1\5\1"+
		"\36\b\1\n\1\f\1!\t\1\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1"+
		"\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\3\2<\b\2\1\3\1\3\1"+
		"\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\3\3H\b\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1"+
		"\3\1\3\1\3\1\3\3\3U\b\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\3"+
		"\3b\b\3\1\4\1\4\1\4\1\4\1\4\1\4\1\4\1\4\1\4\3\4m\b\4\1\4\3\4p\b\4\1\5"+
		"\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\3\5|\b\5\1\5\1\5\1\5\1\5\3\5\u0082"+
		"\b\5\3\5\u0084\b\5\1\6\1\6\1\6\1\6\1\6\1\6\1\6\3\6\u008d\b\6\1\6\3\6\u0090"+
		"\b\6\1\7\1\7\1\b\1\b\1\b\0\t\0\2\4\6\b\n\f\16\20\0\1\1\31\34\u00a5\0\26"+
		"\1\0\0\0\2\33\1\0\0\0\4\"\1\0\0\0\6G\1\0\0\0\bo\1\0\0\0\n\u0083\1\0\0"+
		"\0\f\u008f\1\0\0\0\16\u0091\1\0\0\0\20\u0093\1\0\0\0\22\25\3\2\1\0\23"+
		"\25\3\6\3\0\24\22\1\0\0\0\24\23\1\0\0\0\25\30\1\0\0\0\26\24\1\0\0\0\26"+
		"\27\1\0\0\0\27\31\1\0\0\0\30\26\1\0\0\0\31\32\5\uffff\0\0\32\1\1\0\0\0"+
		"\33\37\3\4\2\0\34\36\5\3\0\0\35\34\1\0\0\0\36!\1\0\0\0\37\35\1\0\0\0\37"+
		" \1\0\0\0 \3\1\0\0\0!\37\1\0\0\0\"#\5\36\0\0#;\5\4\0\0$%\5\6\0\0%&\5\5"+
		"\0\0&\'\3\16\7\0\'(\1\0\0\0()\5\27\0\0)*\5\7\0\0*+\5\5\0\0+,\3\16\7\0"+
		",<\1\0\0\0-.\5\6\0\0./\5\5\0\0/\60\3\16\7\0\60\61\1\0\0\0\61\62\6\2\uffff"+
		"\0\62<\1\0\0\0\63\64\3\16\7\0\64\65\5\25\0\0\65\66\3\16\7\0\66<\1\0\0"+
		"\0\678\3\16\7\089\6\2\uffff\09<\1\0\0\0:<\6\2\uffff\0;$\1\0\0\0;-\1\0"+
		"\0\0;\63\1\0\0\0;\67\1\0\0\0;:\1\0\0\0<\5\1\0\0\0=>\5\b\0\0>?\3\20\b\0"+
		"?@\5\30\0\0@H\1\0\0\0AB\5\b\0\0BC\3\20\b\0CD\6\3\uffff\0DH\1\0\0\0EF\5"+
		"\b\0\0FH\6\3\uffff\0G=\1\0\0\0GA\1\0\0\0GE\1\0\0\0HT\1\0\0\0IJ\5\17\0"+
		"\0JK\3\b\4\0KL\5\27\0\0LU\1\0\0\0MN\5\17\0\0NO\3\b\4\0OP\6\3\uffff\0P"+
		"U\1\0\0\0QR\5\17\0\0RU\6\3\uffff\0SU\6\3\uffff\0TI\1\0\0\0TM\1\0\0\0T"+
		"Q\1\0\0\0TS\1\0\0\0Ua\1\0\0\0VW\5\n\0\0WX\3\f\6\0XY\3\n\5\0Yb\1\0\0\0"+
		"Z[\5\n\0\0[\\\3\f\6\0\\]\6\3\uffff\0]b\1\0\0\0^_\5\n\0\0_b\6\3\uffff\0"+
		"`b\6\3\uffff\0aV\1\0\0\0aZ\1\0\0\0a^\1\0\0\0a`\1\0\0\0b\7\1\0\0\0cd\5"+
		"\20\0\0de\5\21\0\0em\3\20\b\0fg\5\20\0\0gh\5\21\0\0hm\6\4\uffff\0ij\5"+
		"\20\0\0jm\6\4\uffff\0km\6\4\uffff\0lc\1\0\0\0lf\1\0\0\0li\1\0\0\0lk\1"+
		"\0\0\0mp\1\0\0\0np\5\22\0\0ol\1\0\0\0on\1\0\0\0p\t\1\0\0\0qr\5\13\0\0"+
		"r{\5\t\0\0st\5\23\0\0tu\3\20\b\0uv\5\26\0\0v|\1\0\0\0wx\5\23\0\0xy\3\20"+
		"\b\0yz\6\5\uffff\0z|\1\0\0\0{s\1\0\0\0{w\1\0\0\0{|\1\0\0\0|\u0084\1\0"+
		"\0\0}~\5\r\0\0~\u0082\5\16\0\0\177\u0080\5\r\0\0\u0080\u0082\6\5\uffff"+
		"\0\u0081}\1\0\0\0\u0081\177\1\0\0\0\u0082\u0084\1\0\0\0\u0083q\1\0\0\0"+
		"\u0083\u0081\1\0\0\0\u0084\13\1\0\0\0\u0085\u0086\5\24\0\0\u0086\u008d"+
		"\3\f\6\0\u0087\u0088\5\24\0\0\u0088\u0089\5\24\0\0\u0089\u008a\3\f\6\0"+
		"\u008a\u008b\6\6\uffff\0\u008b\u008d\1\0\0\0\u008c\u0085\1\0\0\0\u008c"+
		"\u0087\1\0\0\0\u008d\u0090\1\0\0\0\u008e\u0090\5\36\0\0\u008f\u008c\1"+
		"\0\0\0\u008f\u008e\1\0\0\0\u0090\r\1\0\0\0\u0091\u0092\7\0\0\0\u0092\17"+
		"\1\0\0\0\u0093\u0094\5\34\0\0\u0094\21\1\0\0\0\16\24\26\37;GTalo{\u0081"+
		"\u0083\u008c\u008f";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}