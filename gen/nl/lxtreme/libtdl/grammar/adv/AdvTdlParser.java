// $ANTLR ANTLRVersion> AdvTdlParser.java generatedTimestamp>

/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.*;
import org.antlr.v4.runtime.tree.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AdvTdlParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__8=1, T__7=2, T__6=3, T__5=4, T__4=5, T__3=6, T__2=7, T__1=8, T__0=9, 
		COMMENT=10, WS=11, ASSIGN=12, EQUALS_TO=13, MASK=14, VALUE=15, RISING=16, 
		FALLING=17, BOTH=18, NEITHER=19, STAGE=20, CAPTURE=21, NOP=22, ANY=23, 
		WHEN=24, OCCURS=25, START=26, STOP=27, CLEAR=28, GOTO=29, NEXT=30, ELSE=31, 
		ON=32, BIN_LITERAL=33, HEX_LITERAL=34, OCT_LITERAL=35, DEC_LITERAL=36, 
		TIME_UNIT=37, TERM_NAME=38, TIMER_NAME=39, RANGE_NAME=40, EDGE_NAME=41;
	public static final String[] tokenNames = {
		"<INVALID>", "'^'", "'&'", "')'", "','", "':'", "'('", "'~'", "'|'", "'..'", 
		"COMMENT", "WS", "':='", "'='", "'mask'", "'value'", "'rising'", "'falling'", 
		"'both'", "'neither'", "'stage'", "'capture'", "'nop'", "'any'", "'when'", 
		"'occurs'", "'start'", "'stop'", "'clear'", "'goto'", "'next'", "'else'", 
		"'on'", "BIN_LITERAL", "HEX_LITERAL", "OCT_LITERAL", "DEC_LITERAL", "TIME_UNIT", 
		"TERM_NAME", "TIMER_NAME", "RANGE_NAME", "EDGE_NAME"
	};
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_termDecl = 2, RULE_timerDecl = 3, RULE_rangeDecl = 4, 
		RULE_edgeTermDecl = 5, RULE_edgeDecl = 6, RULE_stageDef = 7, RULE_whenAction = 8, 
		RULE_elseAction = 9, RULE_termExpr = 10, RULE_expr = 11, RULE_number = 12, 
		RULE_decNumber = 13;
	public static final String[] ruleNames = {
		"prog", "decl", "termDecl", "timerDecl", "rangeDecl", "edgeTermDecl", 
		"edgeDecl", "stageDef", "whenAction", "elseAction", "termExpr", "expr", 
		"number", "decNumber"
	};

	@Override
	public String getGrammarFileName() { return "AdvTdl.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public AdvTdlParser(TokenStream input) {
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
		public TerminalNode EOF() { return getToken(AdvTdlParser.EOF, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitProg(this);
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
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAGE) | (1L << TERM_NAME) | (1L << TIMER_NAME) | (1L << RANGE_NAME) | (1L << EDGE_NAME))) != 0)) {
				{
				setState(30);
				switch (_input.LA(1)) {
				case TERM_NAME:
				case TIMER_NAME:
				case RANGE_NAME:
				case EDGE_NAME:
					{
					setState(28); decl();
					}
					break;
				case STAGE:
					{
					setState(29); stageDef();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(35); match(EOF);
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
		public EdgeDeclContext edgeDecl() {
			return getRuleContext(EdgeDeclContext.class,0);
		}
		public RangeDeclContext rangeDecl() {
			return getRuleContext(RangeDeclContext.class,0);
		}
		public TermDeclContext termDecl() {
			return getRuleContext(TermDeclContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(AdvTdlParser.WS); }
		public TimerDeclContext timerDecl() {
			return getRuleContext(TimerDeclContext.class,0);
		}
		public TerminalNode WS(int i) {
			return getToken(AdvTdlParser.WS, i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitDecl(this);
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
			setState(41);
			switch (_input.LA(1)) {
			case TERM_NAME:
				{
				setState(37); termDecl();
				}
				break;
			case TIMER_NAME:
				{
				setState(38); timerDecl();
				}
				break;
			case RANGE_NAME:
				{
				setState(39); rangeDecl();
				}
				break;
			case EDGE_NAME:
				{
				setState(40); edgeDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(43); match(WS);
				}
				}
				setState(48);
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
		public TerminalNode TERM_NAME() { return getToken(AdvTdlParser.TERM_NAME, 0); }
		public TerminalNode MASK() { return getToken(AdvTdlParser.MASK, 0); }
		public TerminalNode VALUE() { return getToken(AdvTdlParser.VALUE, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode EQUALS_TO() { return getToken(AdvTdlParser.EQUALS_TO, 0); }
		public TerminalNode ASSIGN() { return getToken(AdvTdlParser.ASSIGN, 0); }
		public TermDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitTermDecl(this);
			else return null;
		}
	}

	public final TermDeclContext termDecl() throws RecognitionException {
		TermDeclContext _localctx = new TermDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_termDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); ((TermDeclContext)_localctx).name = match(TERM_NAME);
			setState(50); match(ASSIGN);
			setState(74);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				{
				setState(51); match(MASK);
				setState(52); match(EQUALS_TO);
				setState(53); ((TermDeclContext)_localctx).mask = number();
				}
				setState(55); match(4);
				{
				setState(56); match(VALUE);
				setState(57); match(EQUALS_TO);
				setState(58); ((TermDeclContext)_localctx).value = number();
				}
				}
				break;

			case 2:
				{
				{
				setState(60); match(MASK);
				setState(61); match(EQUALS_TO);
				setState(62); number();
				}
				 notifyErrorListeners("missing term value"); 
				}
				break;

			case 3:
				{
				{
				setState(66); ((TermDeclContext)_localctx).mask = number();
				setState(67); match(1);
				setState(68); ((TermDeclContext)_localctx).value = number();
				}
				}
				break;

			case 4:
				{
				{
				setState(70); number();
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

	public static class TimerDeclContext extends ParserRuleContext {
		public Token name;
		public NumberContext value;
		public Token unit;
		public TerminalNode TIMER_NAME() { return getToken(AdvTdlParser.TIMER_NAME, 0); }
		public TerminalNode TIME_UNIT() { return getToken(AdvTdlParser.TIME_UNIT, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(AdvTdlParser.ASSIGN, 0); }
		public TimerDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timerDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitTimerDecl(this);
			else return null;
		}
	}

	public final TimerDeclContext timerDecl() throws RecognitionException {
		TimerDeclContext _localctx = new TimerDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_timerDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); ((TimerDeclContext)_localctx).name = match(TIMER_NAME);
			setState(77); match(ASSIGN);
			setState(85);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(78); ((TimerDeclContext)_localctx).value = number();
				setState(79); ((TimerDeclContext)_localctx).unit = match(TIME_UNIT);
				}
				break;

			case 2:
				{
				setState(81); number();
				 notifyErrorListeners("missing time unit"); 
				}
				break;

			case 3:
				{
				 notifyErrorListeners("missing time value and unit"); 
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

	public static class RangeDeclContext extends ParserRuleContext {
		public Token name;
		public NumberContext lowerBound;
		public NumberContext upperBound;
		public TerminalNode RANGE_NAME() { return getToken(AdvTdlParser.RANGE_NAME, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(AdvTdlParser.ASSIGN, 0); }
		public RangeDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitRangeDecl(this);
			else return null;
		}
	}

	public final RangeDeclContext rangeDecl() throws RecognitionException {
		RangeDeclContext _localctx = new RangeDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_rangeDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87); ((RangeDeclContext)_localctx).name = match(RANGE_NAME);
			setState(88); match(ASSIGN);
			setState(101);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(89); ((RangeDeclContext)_localctx).lowerBound = number();
				setState(90); match(9);
				setState(91); ((RangeDeclContext)_localctx).upperBound = number();
				}
				break;

			case 2:
				{
				setState(93); number();
				setState(94); match(9);
				 notifyErrorListeners("missing upper bound"); 
				}
				break;

			case 3:
				{
				setState(97); number();
				 notifyErrorListeners("missing upper bound"); 
				}
				break;

			case 4:
				{
				 notifyErrorListeners("invalid range definition, needs lower and upper bound"); 
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

	public static class EdgeTermDeclContext extends ParserRuleContext {
		public NumberContext mask;
		public TerminalNode BOTH() { return getToken(AdvTdlParser.BOTH, 0); }
		public TerminalNode NEITHER() { return getToken(AdvTdlParser.NEITHER, 0); }
		public TerminalNode FALLING() { return getToken(AdvTdlParser.FALLING, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode EQUALS_TO() { return getToken(AdvTdlParser.EQUALS_TO, 0); }
		public TerminalNode RISING() { return getToken(AdvTdlParser.RISING, 0); }
		public EdgeTermDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_edgeTermDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitEdgeTermDecl(this);
			else return null;
		}
	}

	public final EdgeTermDeclContext edgeTermDecl() throws RecognitionException {
		EdgeTermDeclContext _localctx = new EdgeTermDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_edgeTermDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RISING) | (1L << FALLING) | (1L << BOTH) | (1L << NEITHER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(104); match(EQUALS_TO);
			setState(107);
			switch (_input.LA(1)) {
			case BIN_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case DEC_LITERAL:
				{
				setState(105); ((EdgeTermDeclContext)_localctx).mask = number();
				}
				break;
			case EOF:
			case 4:
			case WS:
			case STAGE:
			case TERM_NAME:
			case TIMER_NAME:
			case RANGE_NAME:
			case EDGE_NAME:
				{
				 notifyErrorListeners("missing edge value"); 
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class EdgeDeclContext extends ParserRuleContext {
		public Token name;
		public EdgeTermDeclContext edgeTermDecl;
		public List<EdgeTermDeclContext> terms = new ArrayList<EdgeTermDeclContext>();
		public EdgeTermDeclContext edgeTermDecl() {
			return getRuleContext(EdgeTermDeclContext.class,0);
		}
		public TerminalNode EDGE_NAME() { return getToken(AdvTdlParser.EDGE_NAME, 0); }
		public TerminalNode ASSIGN() { return getToken(AdvTdlParser.ASSIGN, 0); }
		public EdgeDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_edgeDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitEdgeDecl(this);
			else return null;
		}
	}

	public final EdgeDeclContext edgeDecl() throws RecognitionException {
		EdgeDeclContext _localctx = new EdgeDeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_edgeDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109); ((EdgeDeclContext)_localctx).name = match(EDGE_NAME);
			setState(110); match(ASSIGN);
			setState(120);
			switch (_input.LA(1)) {
			case RISING:
			case FALLING:
			case BOTH:
			case NEITHER:
				{
				{
				setState(111); ((EdgeDeclContext)_localctx).edgeTermDecl = edgeTermDecl();
				((EdgeDeclContext)_localctx).terms.add(((EdgeDeclContext)_localctx).edgeTermDecl);
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==4) {
					{
					{
					setState(112); match(4);
					setState(113); ((EdgeDeclContext)_localctx).edgeTermDecl = edgeTermDecl();
					((EdgeDeclContext)_localctx).terms.add(((EdgeDeclContext)_localctx).edgeTermDecl);
					}
					}
					setState(118);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case EOF:
			case WS:
			case STAGE:
			case TERM_NAME:
			case TIMER_NAME:
			case RANGE_NAME:
			case EDGE_NAME:
				{
				 notifyErrorListeners("invalid edge definition, needs at least one edge term declaration"); 
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		public TermExprContext captureExpr;
		public TermExprContext ifExpr;
		public DecNumberContext occurrence;
		public TermExprContext elseExpr;
		public ElseActionContext elseAction() {
			return getRuleContext(ElseActionContext.class,0);
		}
		public TerminalNode ON() { return getToken(AdvTdlParser.ON, 0); }
		public TerminalNode CAPTURE() { return getToken(AdvTdlParser.CAPTURE, 0); }
		public List<TermExprContext> termExpr() {
			return getRuleContexts(TermExprContext.class);
		}
		public List<DecNumberContext> decNumber() {
			return getRuleContexts(DecNumberContext.class);
		}
		public TerminalNode WHEN() { return getToken(AdvTdlParser.WHEN, 0); }
		public TerminalNode STAGE() { return getToken(AdvTdlParser.STAGE, 0); }
		public TerminalNode ELSE() { return getToken(AdvTdlParser.ELSE, 0); }
		public WhenActionContext whenAction() {
			return getRuleContext(WhenActionContext.class,0);
		}
		public DecNumberContext decNumber(int i) {
			return getRuleContext(DecNumberContext.class,i);
		}
		public TerminalNode OCCURS() { return getToken(AdvTdlParser.OCCURS, 0); }
		public TermExprContext termExpr(int i) {
			return getRuleContext(TermExprContext.class,i);
		}
		public StageDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stageDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitStageDef(this);
			else return null;
		}
	}

	public final StageDefContext stageDef() throws RecognitionException {
		StageDefContext _localctx = new StageDefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stageDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(122); match(STAGE);
				setState(123); ((StageDefContext)_localctx).n = decNumber();
				setState(124); match(5);
				}
				break;

			case 2:
				{
				setState(126); match(STAGE);
				setState(127); decNumber();
				 notifyErrorListeners("missing colon"); 
				}
				break;

			case 3:
				{
				setState(130); match(STAGE);
				 notifyErrorListeners("missing stage ID"); 
				}
				break;
			}
			setState(139);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(134); match(CAPTURE);
				setState(135); ((StageDefContext)_localctx).captureExpr = termExpr();
				}
				break;

			case 2:
				{
				setState(136); match(CAPTURE);
				 notifyErrorListeners("missing capture expression"); 
				}
				break;

			case 3:
				{
				 notifyErrorListeners("missing capture clause"); 
				}
				break;
			}
			setState(164);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(141); match(WHEN);
				setState(142); ((StageDefContext)_localctx).ifExpr = termExpr();
				setState(145);
				_la = _input.LA(1);
				if (_la==OCCURS) {
					{
					setState(143); match(OCCURS);
					setState(144); ((StageDefContext)_localctx).occurrence = decNumber();
					}
				}

				setState(147); whenAction();
				}
				break;

			case 2:
				{
				setState(149); match(WHEN);
				setState(150); termExpr();
				setState(153);
				_la = _input.LA(1);
				if (_la==OCCURS) {
					{
					setState(151); match(OCCURS);
					setState(152); decNumber();
					}
				}

				 notifyErrorListeners("missing when action"); 
				}
				break;

			case 3:
				{
				setState(157); match(WHEN);
				setState(160);
				_la = _input.LA(1);
				if (_la==OCCURS) {
					{
					setState(158); match(OCCURS);
					setState(159); decNumber();
					}
				}

				 notifyErrorListeners("missing when expression"); 
				}
				break;

			case 4:
				{
				 notifyErrorListeners("missing when clause"); 
				}
				break;
			}
			setState(182);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(166); match(ELSE);
				setState(167); match(ON);
				setState(168); ((StageDefContext)_localctx).elseExpr = termExpr();
				setState(169); elseAction();
				}
				break;

			case 2:
				{
				setState(171); match(ELSE);
				setState(172); match(ON);
				setState(173); termExpr();
				 notifyErrorListeners("missing else action"); 
				}
				break;

			case 3:
				{
				setState(176); match(ELSE);
				setState(177); match(ON);
				 notifyErrorListeners("missing else expression"); 
				}
				break;

			case 4:
				{
				setState(179); match(ELSE);
				 notifyErrorListeners("missing on"); 
				}
				break;

			case 5:
				{
				 notifyErrorListeners("missing else clause"); 
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

	public static class WhenActionContext extends ParserRuleContext {
		public Token timer;
		public TerminalNode CAPTURE() { return getToken(AdvTdlParser.CAPTURE, 0); }
		public TerminalNode START() { return getToken(AdvTdlParser.START, 0); }
		public TerminalNode NEXT() { return getToken(AdvTdlParser.NEXT, 0); }
		public TerminalNode STOP() { return getToken(AdvTdlParser.STOP, 0); }
		public TerminalNode TIMER_NAME() { return getToken(AdvTdlParser.TIMER_NAME, 0); }
		public TerminalNode GOTO() { return getToken(AdvTdlParser.GOTO, 0); }
		public TerminalNode CLEAR() { return getToken(AdvTdlParser.CLEAR, 0); }
		public WhenActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenAction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitWhenAction(this);
			else return null;
		}
	}

	public final WhenActionContext whenAction() throws RecognitionException {
		WhenActionContext _localctx = new WhenActionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_whenAction);
		int _la;
		try {
			setState(194);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(184);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << START) | (1L << STOP) | (1L << CLEAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(185); ((WhenActionContext)_localctx).timer = match(TIMER_NAME);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				_la = _input.LA(1);
				if ( !(_la==START || _la==STOP) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(187); match(CAPTURE);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(192);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(188); match(GOTO);
					setState(189); match(NEXT);
					}
					break;

				case 2:
					{
					setState(190); match(GOTO);
					 notifyErrorListeners("missing next"); 
					}
					break;
				}
				}
				break;
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

	public static class ElseActionContext extends ParserRuleContext {
		public DecNumberContext n;
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public TerminalNode GOTO() { return getToken(AdvTdlParser.GOTO, 0); }
		public ElseActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseAction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitElseAction(this);
			else return null;
		}
	}

	public final ElseActionContext elseAction() throws RecognitionException {
		ElseActionContext _localctx = new ElseActionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_elseAction);
		try {
			setState(200);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(196); match(GOTO);
				setState(197); ((ElseActionContext)_localctx).n = decNumber();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(198); match(GOTO);
				 notifyErrorListeners("missing level ID"); 
				}
				break;
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
		public TerminalNode ANY() { return getToken(AdvTdlParser.ANY, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NOP() { return getToken(AdvTdlParser.NOP, 0); }
		public TermExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitTermExpr(this);
			else return null;
		}
	}

	public final TermExprContext termExpr() throws RecognitionException {
		TermExprContext _localctx = new TermExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_termExpr);
		try {
			setState(205);
			switch (_input.LA(1)) {
			case NOP:
				enterOuterAlt(_localctx, 1);
				{
				setState(202); match(NOP);
				}
				break;
			case ANY:
				enterOuterAlt(_localctx, 2);
				{
				setState(203); match(ANY);
				}
				break;
			case 6:
			case 7:
			case TERM_NAME:
			case TIMER_NAME:
			case RANGE_NAME:
			case EDGE_NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(204); expr(0);
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
		public int _p;
		public Token term;
		public TerminalNode TERM_NAME() { return getToken(AdvTdlParser.TERM_NAME, 0); }
		public TerminalNode RANGE_NAME() { return getToken(AdvTdlParser.RANGE_NAME, 0); }
		public TerminalNode TIMER_NAME() { return getToken(AdvTdlParser.TIMER_NAME, 0); }
		public TerminalNode EDGE_NAME() { return getToken(AdvTdlParser.EDGE_NAME, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExprContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitExpr(this);
			else return null;
		}
	}

	public final ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
		ExprContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			switch (_input.LA(1)) {
			case 7:
				{
				setState(208); match(7);
				setState(209); expr(2);
				}
				break;
			case 6:
				{
				setState(210); match(6);
				setState(211); expr(0);
				setState(212); match(3);
				}
				break;
			case TERM_NAME:
			case TIMER_NAME:
			case RANGE_NAME:
			case EDGE_NAME:
				{
				setState(214);
				((ExprContext)_localctx).term = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TERM_NAME) | (1L << TIMER_NAME) | (1L << RANGE_NAME) | (1L << EDGE_NAME))) != 0)) ) {
					((ExprContext)_localctx).term = (Token)_errHandler.recoverInline(this);
				}
				consume();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(228);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(226);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(217);
						if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
						setState(218); match(1);
						setState(219); expr(6);
						}
						break;

					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(220);
						if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
						setState(221); match(2);
						setState(222); expr(5);
						}
						break;

					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(223);
						if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
						setState(224); match(8);
						setState(225); expr(4);
						}
						break;
					}
					} 
				}
				setState(230);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode BIN_LITERAL() { return getToken(AdvTdlParser.BIN_LITERAL, 0); }
		public TerminalNode HEX_LITERAL() { return getToken(AdvTdlParser.HEX_LITERAL, 0); }
		public TerminalNode OCT_LITERAL() { return getToken(AdvTdlParser.OCT_LITERAL, 0); }
		public TerminalNode DEC_LITERAL() { return getToken(AdvTdlParser.DEC_LITERAL, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitNumber(this);
			else return null;
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
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
		public TerminalNode DEC_LITERAL() { return getToken(AdvTdlParser.DEC_LITERAL, 0); }
		public DecNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decNumber; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitDecNumber(this);
			else return null;
		}
	}

	public final DecNumberContext decNumber() throws RecognitionException {
		DecNumberContext _localctx = new DecNumberContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_decNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233); match(DEC_LITERAL);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 5 >= _localctx._p;

		case 1: return 4 >= _localctx._p;

		case 2: return 3 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\1\3)\u00ec\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2"+
		"\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\1\0\1\0\5\0\37"+
		"\b\0\n\0\f\0\"\t\0\1\0\1\0\1\1\1\1\1\1\1\1\3\1*\b\1\1\1\5\1-\b\1\n\1\f"+
		"\1\60\t\1\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2"+
		"\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\3\2K\b\2\1\3\1\3\1\3\1\3\1\3"+
		"\1\3\1\3\1\3\1\3\3\3V\b\3\1\4\1\4\1\4\1\4\1\4\1\4\1\4\1\4\1\4\1\4\1\4"+
		"\1\4\1\4\1\4\3\4f\b\4\1\5\1\5\1\5\1\5\3\5l\b\5\1\6\1\6\1\6\1\6\1\6\5\6"+
		"s\b\6\n\6\f\6v\t\6\1\6\3\6y\b\6\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1"+
		"\7\3\7\u0085\b\7\1\7\1\7\1\7\1\7\1\7\3\7\u008c\b\7\1\7\1\7\1\7\1\7\3\7"+
		"\u0092\b\7\1\7\1\7\1\7\1\7\1\7\1\7\3\7\u009a\b\7\1\7\1\7\1\7\1\7\1\7\3"+
		"\7\u00a1\b\7\1\7\1\7\3\7\u00a5\b\7\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\7"+
		"\1\7\1\7\1\7\1\7\1\7\1\7\1\7\3\7\u00b7\b\7\1\b\1\b\1\b\1\b\1\b\1\b\1\b"+
		"\1\b\3\b\u00c1\b\b\3\b\u00c3\b\b\1\t\1\t\1\t\1\t\3\t\u00c9\b\t\1\n\1\n"+
		"\1\n\3\n\u00ce\b\n\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\13\3\13\u00d8"+
		"\b\13\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\13\5\13\u00e3\b\13\n\13"+
		"\f\13\u00e6\t\13\1\f\1\f\1\r\1\r\1\r\0\16\0\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\0\5\1\20\23\1\32\34\1\32\33\1&)\1!$\u0108\0 \1\0\0\0\2)\1\0\0\0"+
		"\4\61\1\0\0\0\6L\1\0\0\0\bW\1\0\0\0\ng\1\0\0\0\fm\1\0\0\0\16\u0084\1\0"+
		"\0\0\20\u00c2\1\0\0\0\22\u00c8\1\0\0\0\24\u00cd\1\0\0\0\26\u00d7\1\0\0"+
		"\0\30\u00e7\1\0\0\0\32\u00e9\1\0\0\0\34\37\3\2\1\0\35\37\3\16\7\0\36\34"+
		"\1\0\0\0\36\35\1\0\0\0\37\"\1\0\0\0 \36\1\0\0\0 !\1\0\0\0!#\1\0\0\0\""+
		" \1\0\0\0#$\5\uffff\0\0$\1\1\0\0\0%*\3\4\2\0&*\3\6\3\0\'*\3\b\4\0(*\3"+
		"\f\6\0)%\1\0\0\0)&\1\0\0\0)\'\1\0\0\0)(\1\0\0\0*.\1\0\0\0+-\5\13\0\0,"+
		"+\1\0\0\0-\60\1\0\0\0.,\1\0\0\0./\1\0\0\0/\3\1\0\0\0\60.\1\0\0\0\61\62"+
		"\5&\0\0\62J\5\f\0\0\63\64\5\16\0\0\64\65\5\r\0\0\65\66\3\30\f\0\66\67"+
		"\1\0\0\0\678\5\4\0\089\5\17\0\09:\5\r\0\0:;\3\30\f\0;K\1\0\0\0<=\5\16"+
		"\0\0=>\5\r\0\0>?\3\30\f\0?@\1\0\0\0@A\6\2\uffff\0AK\1\0\0\0BC\3\30\f\0"+
		"CD\5\1\0\0DE\3\30\f\0EK\1\0\0\0FG\3\30\f\0GH\6\2\uffff\0HK\1\0\0\0IK\6"+
		"\2\uffff\0J\63\1\0\0\0J<\1\0\0\0JB\1\0\0\0JF\1\0\0\0JI\1\0\0\0K\5\1\0"+
		"\0\0LM\5\'\0\0MU\5\f\0\0NO\3\30\f\0OP\5%\0\0PV\1\0\0\0QR\3\30\f\0RS\6"+
		"\3\uffff\0SV\1\0\0\0TV\6\3\uffff\0UN\1\0\0\0UQ\1\0\0\0UT\1\0\0\0V\7\1"+
		"\0\0\0WX\5(\0\0Xe\5\f\0\0YZ\3\30\f\0Z[\5\t\0\0[\\\3\30\f\0\\f\1\0\0\0"+
		"]^\3\30\f\0^_\5\t\0\0_`\6\4\uffff\0`f\1\0\0\0ab\3\30\f\0bc\6\4\uffff\0"+
		"cf\1\0\0\0df\6\4\uffff\0eY\1\0\0\0e]\1\0\0\0ea\1\0\0\0ed\1\0\0\0f\t\1"+
		"\0\0\0gh\7\0\0\0hk\5\r\0\0il\3\30\f\0jl\6\5\uffff\0ki\1\0\0\0kj\1\0\0"+
		"\0l\13\1\0\0\0mn\5)\0\0nx\5\f\0\0ot\3\n\5\0pq\5\4\0\0qs\3\n\5\0rp\1\0"+
		"\0\0sv\1\0\0\0tr\1\0\0\0tu\1\0\0\0uy\1\0\0\0vt\1\0\0\0wy\6\6\uffff\0x"+
		"o\1\0\0\0xw\1\0\0\0y\r\1\0\0\0z{\5\24\0\0{|\3\32\r\0|}\5\5\0\0}\u0085"+
		"\1\0\0\0~\177\5\24\0\0\177\u0080\3\32\r\0\u0080\u0081\6\7\uffff\0\u0081"+
		"\u0085\1\0\0\0\u0082\u0083\5\24\0\0\u0083\u0085\6\7\uffff\0\u0084z\1\0"+
		"\0\0\u0084~\1\0\0\0\u0084\u0082\1\0\0\0\u0085\u008b\1\0\0\0\u0086\u0087"+
		"\5\25\0\0\u0087\u008c\3\24\n\0\u0088\u0089\5\25\0\0\u0089\u008c\6\7\uffff"+
		"\0\u008a\u008c\6\7\uffff\0\u008b\u0086\1\0\0\0\u008b\u0088\1\0\0\0\u008b"+
		"\u008a\1\0\0\0\u008c\u00a4\1\0\0\0\u008d\u008e\5\30\0\0\u008e\u0091\3"+
		"\24\n\0\u008f\u0090\5\31\0\0\u0090\u0092\3\32\r\0\u0091\u008f\1\0\0\0"+
		"\u0091\u0092\1\0\0\0\u0092\u0093\1\0\0\0\u0093\u0094\3\20\b\0\u0094\u00a5"+
		"\1\0\0\0\u0095\u0096\5\30\0\0\u0096\u0099\3\24\n\0\u0097\u0098\5\31\0"+
		"\0\u0098\u009a\3\32\r\0\u0099\u0097\1\0\0\0\u0099\u009a\1\0\0\0\u009a"+
		"\u009b\1\0\0\0\u009b\u009c\6\7\uffff\0\u009c\u00a5\1\0\0\0\u009d\u00a0"+
		"\5\30\0\0\u009e\u009f\5\31\0\0\u009f\u00a1\3\32\r\0\u00a0\u009e\1\0\0"+
		"\0\u00a0\u00a1\1\0\0\0\u00a1\u00a2\1\0\0\0\u00a2\u00a5\6\7\uffff\0\u00a3"+
		"\u00a5\6\7\uffff\0\u00a4\u008d\1\0\0\0\u00a4\u0095\1\0\0\0\u00a4\u009d"+
		"\1\0\0\0\u00a4\u00a3\1\0\0\0\u00a5\u00b6\1\0\0\0\u00a6\u00a7\5\37\0\0"+
		"\u00a7\u00a8\5 \0\0\u00a8\u00a9\3\24\n\0\u00a9\u00aa\3\22\t\0\u00aa\u00b7"+
		"\1\0\0\0\u00ab\u00ac\5\37\0\0\u00ac\u00ad\5 \0\0\u00ad\u00ae\3\24\n\0"+
		"\u00ae\u00af\6\7\uffff\0\u00af\u00b7\1\0\0\0\u00b0\u00b1\5\37\0\0\u00b1"+
		"\u00b2\5 \0\0\u00b2\u00b7\6\7\uffff\0\u00b3\u00b4\5\37\0\0\u00b4\u00b7"+
		"\6\7\uffff\0\u00b5\u00b7\6\7\uffff\0\u00b6\u00a6\1\0\0\0\u00b6\u00ab\1"+
		"\0\0\0\u00b6\u00b0\1\0\0\0\u00b6\u00b3\1\0\0\0\u00b6\u00b5\1\0\0\0\u00b7"+
		"\17\1\0\0\0\u00b8\u00b9\7\1\0\0\u00b9\u00c3\5\'\0\0\u00ba\u00bb\7\2\0"+
		"\0\u00bb\u00c3\5\25\0\0\u00bc\u00bd\5\35\0\0\u00bd\u00c1\5\36\0\0\u00be"+
		"\u00bf\5\35\0\0\u00bf\u00c1\6\b\uffff\0\u00c0\u00bc\1\0\0\0\u00c0\u00be"+
		"\1\0\0\0\u00c1\u00c3\1\0\0\0\u00c2\u00b8\1\0\0\0\u00c2\u00ba\1\0\0\0\u00c2"+
		"\u00c0\1\0\0\0\u00c3\21\1\0\0\0\u00c4\u00c5\5\35\0\0\u00c5\u00c9\3\32"+
		"\r\0\u00c6\u00c7\5\35\0\0\u00c7\u00c9\6\t\uffff\0\u00c8\u00c4\1\0\0\0"+
		"\u00c8\u00c6\1\0\0\0\u00c9\23\1\0\0\0\u00ca\u00ce\5\26\0\0\u00cb\u00ce"+
		"\5\27\0\0\u00cc\u00ce\3\26\13\0\u00cd\u00ca\1\0\0\0\u00cd\u00cb\1\0\0"+
		"\0\u00cd\u00cc\1\0\0\0\u00ce\25\1\0\0\0\u00cf\u00d0\6\13\uffff\0\u00d0"+
		"\u00d1\5\7\0\0\u00d1\u00d8\3\26\13\0\u00d2\u00d3\5\6\0\0\u00d3\u00d4\3"+
		"\26\13\0\u00d4\u00d5\5\3\0\0\u00d5\u00d8\1\0\0\0\u00d6\u00d8\7\3\0\0\u00d7"+
		"\u00cf\1\0\0\0\u00d7\u00d2\1\0\0\0\u00d7\u00d6\1\0\0\0\u00d8\u00e4\1\0"+
		"\0\0\u00d9\u00da\4\13\0\1\u00da\u00db\5\1\0\0\u00db\u00e3\3\26\13\0\u00dc"+
		"\u00dd\4\13\1\1\u00dd\u00de\5\2\0\0\u00de\u00e3\3\26\13\0\u00df\u00e0"+
		"\4\13\2\1\u00e0\u00e1\5\b\0\0\u00e1\u00e3\3\26\13\0\u00e2\u00d9\1\0\0"+
		"\0\u00e2\u00dc\1\0\0\0\u00e2\u00df\1\0\0\0\u00e3\u00e6\1\0\0\0\u00e4\u00e2"+
		"\1\0\0\0\u00e4\u00e5\1\0\0\0\u00e5\27\1\0\0\0\u00e6\u00e4\1\0\0\0\u00e7"+
		"\u00e8\7\4\0\0\u00e8\31\1\0\0\0\u00e9\u00ea\5$\0\0\u00ea\33\1\0\0\0\30"+
		"\36 ).JUektx\u0084\u008b\u0091\u0099\u00a0\u00a4\u00b6\u00c0\u00c2\u00c8"+
		"\u00cd\u00d7\u00e2\u00e4";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}