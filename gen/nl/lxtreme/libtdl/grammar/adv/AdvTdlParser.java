// $ANTLR ANTLRVersion> AdvTdlParser.java generatedTimestamp>

/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AdvTdlParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__8=1, T__7=2, T__6=3, T__5=4, T__4=5, T__3=6, T__2=7, T__1=8, T__0=9, 
		COMMENT=10, WS=11, DEFINE=12, ASSIGN=13, EQUALS_TO=14, MASK=15, VALUE=16, 
		RISING=17, FALLING=18, BOTH=19, NEITHER=20, STAGE=21, CAPTURE=22, NOP=23, 
		ANY=24, WHEN=25, OCCURS=26, START=27, STOP=28, CLEAR=29, GOTO=30, NEXT=31, 
		ELSE=32, BIN_LITERAL=33, HEX_LITERAL=34, OCT_LITERAL=35, DEC_LITERAL=36, 
		TIME_UNIT=37, TERM_NAME=38, TIMER_NAME=39, RANGE_NAME=40, EDGE_NAME=41;
	public static final String[] tokenNames = {
		"<INVALID>", "'^'", "'&'", "')'", "','", "':'", "'('", "'~'", "'|'", "'..'", 
		"COMMENT", "WS", "'define'", "'as'", "'='", "'mask'", "'value'", "'rising'", 
		"'falling'", "'both'", "'neither'", "'stage'", "'capture'", "'nop'", "'any'", 
		"'when'", "'occurs'", "'start'", "'stop'", "'clear'", "'goto'", "'next'", 
		"'else'", "BIN_LITERAL", "HEX_LITERAL", "OCT_LITERAL", "DEC_LITERAL", 
		"TIME_UNIT", "TERM_NAME", "TIMER_NAME", "RANGE_NAME", "EDGE_NAME"
	};
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_termDecl = 2, RULE_timerDecl = 3, RULE_rangeDecl = 4, 
		RULE_edgeTermDecl = 5, RULE_edgeDecl = 6, RULE_stageDef = 7, RULE_whenClause = 8, 
		RULE_elseClause = 9, RULE_termExpr = 10, RULE_expr = 11, RULE_number = 12, 
		RULE_decNumber = 13;
	public static final String[] ruleNames = {
		"prog", "decl", "termDecl", "timerDecl", "rangeDecl", "edgeTermDecl", 
		"edgeDecl", "stageDef", "whenClause", "elseClause", "termExpr", "expr", 
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


	    private int stageCount = 10;

	    private static boolean validRange(String text, long lowerBound, long upperBound) {
	      long result = -1L;
	      try {
	        result = Long.decode(text);
	      } catch (NumberFormatException ignored) {
	        // Ignore invalid numbers...
	      }
	      return ( result >= lowerBound && result <= upperBound );
	    }
	    
	    public void setStageCount(int stages) {
	      stageCount = stages;
	    }

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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitProg(this);
		}
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DEFINE) | (1L << STAGE) | (1L << TERM_NAME) | (1L << TIMER_NAME) | (1L << RANGE_NAME) | (1L << EDGE_NAME))) != 0)) {
				{
				setState(30);
				switch (_input.LA(1)) {
				case DEFINE:
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
		public TerminalNode DEFINE() { return getToken(AdvTdlParser.DEFINE, 0); }
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitDecl(this);
		}
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
			setState(38);
			_la = _input.LA(1);
			if (_la==DEFINE) {
				{
				setState(37); match(DEFINE);
				}
			}

			setState(44);
			switch (_input.LA(1)) {
			case TERM_NAME:
				{
				setState(40); termDecl();
				}
				break;
			case TIMER_NAME:
				{
				setState(41); timerDecl();
				}
				break;
			case RANGE_NAME:
				{
				setState(42); rangeDecl();
				}
				break;
			case EDGE_NAME:
				{
				setState(43); edgeDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(46); match(WS);
				}
				}
				setState(51);
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
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public TerminalNode MASK() { return getToken(AdvTdlParser.MASK, 0); }
		public TerminalNode VALUE() { return getToken(AdvTdlParser.VALUE, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public TerminalNode EQUALS_TO() { return getToken(AdvTdlParser.EQUALS_TO, 0); }
		public TerminalNode ASSIGN() { return getToken(AdvTdlParser.ASSIGN, 0); }
		public TermDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterTermDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitTermDecl(this);
		}
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
			setState(52); ((TermDeclContext)_localctx).name = match(TERM_NAME);
			setState(53); match(ASSIGN);
			setState(67);
			switch (_input.LA(1)) {
			case MASK:
				{
				{
				setState(54); match(MASK);
				setState(55); match(EQUALS_TO);
				setState(56); ((TermDeclContext)_localctx).mask = number();
				}
				setState(58); match(4);
				{
				setState(59); match(VALUE);
				setState(60); match(EQUALS_TO);
				setState(61); ((TermDeclContext)_localctx).value = number();
				}
				}
				break;
			case BIN_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case DEC_LITERAL:
				{
				{
				setState(63); ((TermDeclContext)_localctx).mask = number();
				setState(64); match(1);
				setState(65); ((TermDeclContext)_localctx).value = number();
				}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterTimerDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitTimerDecl(this);
		}
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
			setState(69); ((TimerDeclContext)_localctx).name = match(TIMER_NAME);
			setState(70); match(ASSIGN);
			{
			setState(71); ((TimerDeclContext)_localctx).value = number();
			setState(72);
			if (!( validRange((((TimerDeclContext)_localctx).value!=null?_input.getText(((TimerDeclContext)_localctx).value.start,((TimerDeclContext)_localctx).value.stop):null), 1, 0xFFFFFFFFL) )) throw new FailedPredicateException(this, " validRange($value.text, 1, 0xFFFFFFFFL) ");
			setState(73); ((TimerDeclContext)_localctx).unit = match(TIME_UNIT);
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
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public TerminalNode RANGE_NAME() { return getToken(AdvTdlParser.RANGE_NAME, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public TerminalNode ASSIGN() { return getToken(AdvTdlParser.ASSIGN, 0); }
		public RangeDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterRangeDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitRangeDecl(this);
		}
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
			setState(75); ((RangeDeclContext)_localctx).name = match(RANGE_NAME);
			setState(76); match(ASSIGN);
			{
			setState(77); ((RangeDeclContext)_localctx).lowerBound = number();
			setState(78); match(9);
			setState(79); ((RangeDeclContext)_localctx).upperBound = number();
			}
			setState(80);
			if (!( Long.decode((((RangeDeclContext)_localctx).lowerBound!=null?_input.getText(((RangeDeclContext)_localctx).lowerBound.start,((RangeDeclContext)_localctx).lowerBound.stop):null)) < Long.decode((((RangeDeclContext)_localctx).upperBound!=null?_input.getText(((RangeDeclContext)_localctx).upperBound.start,((RangeDeclContext)_localctx).upperBound.stop):null)) )) throw new FailedPredicateException(this, " Long.decode($lowerBound.text) < Long.decode($upperBound.text) ");
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterEdgeTermDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitEdgeTermDecl(this);
		}
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
			setState(82);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RISING) | (1L << FALLING) | (1L << BOTH) | (1L << NEITHER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(83); match(EQUALS_TO);
			setState(84); number();
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
		public List<EdgeTermDeclContext> edgeTermDecl() {
			return getRuleContexts(EdgeTermDeclContext.class);
		}
		public TerminalNode EDGE_NAME() { return getToken(AdvTdlParser.EDGE_NAME, 0); }
		public EdgeTermDeclContext edgeTermDecl(int i) {
			return getRuleContext(EdgeTermDeclContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(AdvTdlParser.ASSIGN, 0); }
		public EdgeDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_edgeDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterEdgeDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitEdgeDecl(this);
		}
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
			setState(86); ((EdgeDeclContext)_localctx).name = match(EDGE_NAME);
			setState(87); match(ASSIGN);
			setState(88); edgeTermDecl();
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(89); match(4);
				setState(90); edgeTermDecl();
				}
				}
				setState(95);
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

	public static class StageDefContext extends ParserRuleContext {
		public DecNumberContext n;
		public TermExprContext captureExpr;
		public TermExprContext ifExpr;
		public DecNumberContext occurrence;
		public TermExprContext elseExpr;
		public WhenClauseContext whenClause() {
			return getRuleContext(WhenClauseContext.class,0);
		}
		public TerminalNode CAPTURE() { return getToken(AdvTdlParser.CAPTURE, 0); }
		public List<TermExprContext> termExpr() {
			return getRuleContexts(TermExprContext.class);
		}
		public List<DecNumberContext> decNumber() {
			return getRuleContexts(DecNumberContext.class);
		}
		public ElseClauseContext elseClause() {
			return getRuleContext(ElseClauseContext.class,0);
		}
		public TerminalNode WHEN() { return getToken(AdvTdlParser.WHEN, 0); }
		public TerminalNode STAGE() { return getToken(AdvTdlParser.STAGE, 0); }
		public DecNumberContext decNumber(int i) {
			return getRuleContext(DecNumberContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(AdvTdlParser.ELSE, 0); }
		public TerminalNode OCCURS() { return getToken(AdvTdlParser.OCCURS, 0); }
		public TermExprContext termExpr(int i) {
			return getRuleContext(TermExprContext.class,i);
		}
		public StageDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stageDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterStageDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitStageDef(this);
		}
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
			setState(96); match(STAGE);
			setState(97); ((StageDefContext)_localctx).n = decNumber();
			setState(98);
			if (!( validRange((((StageDefContext)_localctx).n!=null?_input.getText(((StageDefContext)_localctx).n.start,((StageDefContext)_localctx).n.stop):null), 1, stageCount) )) throw new FailedPredicateException(this, " validRange($n.text, 1, stageCount) ");
			setState(99); match(5);
			setState(100); match(CAPTURE);
			setState(101); ((StageDefContext)_localctx).captureExpr = termExpr();
			setState(102); match(WHEN);
			setState(103); ((StageDefContext)_localctx).ifExpr = termExpr();
			setState(108);
			_la = _input.LA(1);
			if (_la==OCCURS) {
				{
				setState(104); match(OCCURS);
				setState(105); ((StageDefContext)_localctx).occurrence = decNumber();
				setState(106);
				if (!( validRange((((StageDefContext)_localctx).occurrence!=null?_input.getText(((StageDefContext)_localctx).occurrence.start,((StageDefContext)_localctx).occurrence.stop):null), 1, 0xFFFFF) )) throw new FailedPredicateException(this, " validRange($occurrence.text, 1, 0xFFFFF) ");
				}
			}

			setState(110); whenClause();
			setState(111); match(ELSE);
			setState(112); ((StageDefContext)_localctx).elseExpr = termExpr();
			setState(113); elseClause();
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

	public static class WhenClauseContext extends ParserRuleContext {
		public TerminalNode CAPTURE() { return getToken(AdvTdlParser.CAPTURE, 0); }
		public TerminalNode START() { return getToken(AdvTdlParser.START, 0); }
		public TerminalNode NEXT() { return getToken(AdvTdlParser.NEXT, 0); }
		public TerminalNode STOP() { return getToken(AdvTdlParser.STOP, 0); }
		public TerminalNode TIMER_NAME() { return getToken(AdvTdlParser.TIMER_NAME, 0); }
		public TerminalNode GOTO() { return getToken(AdvTdlParser.GOTO, 0); }
		public TerminalNode CLEAR() { return getToken(AdvTdlParser.CLEAR, 0); }
		public WhenClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterWhenClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitWhenClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitWhenClause(this);
			else return null;
		}
	}

	public final WhenClauseContext whenClause() throws RecognitionException {
		WhenClauseContext _localctx = new WhenClauseContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_whenClause);
		int _la;
		try {
			setState(121);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << START) | (1L << STOP) | (1L << CLEAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(116); match(TIMER_NAME);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				_la = _input.LA(1);
				if ( !(_la==START || _la==STOP) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(118); match(CAPTURE);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(119); match(GOTO);
				setState(120); match(NEXT);
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

	public static class ElseClauseContext extends ParserRuleContext {
		public DecNumberContext n;
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public TerminalNode GOTO() { return getToken(AdvTdlParser.GOTO, 0); }
		public ElseClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterElseClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitElseClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdvTdlVisitor ) return ((AdvTdlVisitor<? extends T>)visitor).visitElseClause(this);
			else return null;
		}
	}

	public final ElseClauseContext elseClause() throws RecognitionException {
		ElseClauseContext _localctx = new ElseClauseContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_elseClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); match(GOTO);
			setState(124); ((ElseClauseContext)_localctx).n = decNumber();
			setState(125);
			if (!( validRange((((ElseClauseContext)_localctx).n!=null?_input.getText(((ElseClauseContext)_localctx).n.start,((ElseClauseContext)_localctx).n.stop):null), 1, stageCount) )) throw new FailedPredicateException(this, " validRange($n.text, 1, stageCount) ");
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterTermExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitTermExpr(this);
		}
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
			setState(130);
			switch (_input.LA(1)) {
			case NOP:
				enterOuterAlt(_localctx, 1);
				{
				setState(127); match(NOP);
				}
				break;
			case ANY:
				enterOuterAlt(_localctx, 2);
				{
				setState(128); match(ANY);
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
				setState(129); expr(0);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitExpr(this);
		}
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
			setState(140);
			switch (_input.LA(1)) {
			case 7:
				{
				setState(133); match(7);
				setState(134); expr(2);
				}
				break;
			case 6:
				{
				setState(135); match(6);
				setState(136); expr(0);
				setState(137); match(3);
				}
				break;
			case TERM_NAME:
			case TIMER_NAME:
			case RANGE_NAME:
			case EDGE_NAME:
				{
				setState(139);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TERM_NAME) | (1L << TIMER_NAME) | (1L << RANGE_NAME) | (1L << EDGE_NAME))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(153);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(151);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(142);
						if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
						setState(143); match(1);
						setState(144); expr(6);
						}
						break;

					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(145);
						if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
						setState(146); match(2);
						setState(147); expr(5);
						}
						break;

					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(148);
						if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
						setState(149); match(8);
						setState(150); expr(4);
						}
						break;
					}
					} 
				}
				setState(155);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitNumber(this);
		}
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
			setState(156);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).enterDecNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdvTdlListener ) ((AdvTdlListener)listener).exitDecNumber(this);
		}
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
			setState(158); match(DEC_LITERAL);
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
		case 3: return timerDecl_sempred((TimerDeclContext)_localctx, predIndex);

		case 4: return rangeDecl_sempred((RangeDeclContext)_localctx, predIndex);

		case 7: return stageDef_sempred((StageDefContext)_localctx, predIndex);

		case 9: return elseClause_sempred((ElseClauseContext)_localctx, predIndex);

		case 11: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean rangeDecl_sempred(RangeDeclContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return  Long.decode((((RangeDeclContext)_localctx).lowerBound!=null?_input.getText(((RangeDeclContext)_localctx).lowerBound.start,((RangeDeclContext)_localctx).lowerBound.stop):null)) < Long.decode((((RangeDeclContext)_localctx).upperBound!=null?_input.getText(((RangeDeclContext)_localctx).upperBound.start,((RangeDeclContext)_localctx).upperBound.stop):null)) ;
		}
		return true;
	}
	private boolean stageDef_sempred(StageDefContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return  validRange((((StageDefContext)_localctx).n!=null?_input.getText(((StageDefContext)_localctx).n.start,((StageDefContext)_localctx).n.stop):null), 1, stageCount) ;

		case 3: return  validRange((((StageDefContext)_localctx).occurrence!=null?_input.getText(((StageDefContext)_localctx).occurrence.start,((StageDefContext)_localctx).occurrence.stop):null), 1, 0xFFFFF) ;
		}
		return true;
	}
	private boolean timerDecl_sempred(TimerDeclContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return  validRange((((TimerDeclContext)_localctx).value!=null?_input.getText(((TimerDeclContext)_localctx).value.start,((TimerDeclContext)_localctx).value.stop):null), 1, 0xFFFFFFFFL) ;
		}
		return true;
	}
	private boolean elseClause_sempred(ElseClauseContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4: return  validRange((((ElseClauseContext)_localctx).n!=null?_input.getText(((ElseClauseContext)_localctx).n.start,((ElseClauseContext)_localctx).n.stop):null), 1, stageCount) ;
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5: return 5 >= _localctx._p;

		case 6: return 4 >= _localctx._p;

		case 7: return 3 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\1\3)\u00a1\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2"+
		"\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\1\0\1\0\5\0\37"+
		"\b\0\n\0\f\0\"\t\0\1\0\1\0\1\1\3\1\'\b\1\1\1\1\1\1\1\1\1\3\1-\b\1\1\1"+
		"\5\1\60\b\1\n\1\f\1\63\t\1\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2"+
		"\1\2\1\2\1\2\1\2\3\2D\b\2\1\3\1\3\1\3\1\3\1\3\1\3\1\4\1\4\1\4\1\4\1\4"+
		"\1\4\1\4\1\5\1\5\1\5\1\5\1\6\1\6\1\6\1\6\1\6\5\6\\\b\6\n\6\f\6_\t\6\1"+
		"\7\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\7\3\7m\b\7\1\7\1\7\1\7\1"+
		"\7\1\7\1\b\1\b\1\b\1\b\1\b\1\b\3\bz\b\b\1\t\1\t\1\t\1\t\1\n\1\n\1\n\3"+
		"\n\u0083\b\n\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\13\3\13\u008d\b\13\1"+
		"\13\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\13\5\13\u0098\b\13\n\13\f\13"+
		"\u009b\t\13\1\f\1\f\1\r\1\r\1\r\0\16\0\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\0\5\1\21\24\1\33\35\1\33\34\1&)\1!$\u00a5\0 \1\0\0\0\2&\1\0\0\0\4\64"+
		"\1\0\0\0\6E\1\0\0\0\bK\1\0\0\0\nR\1\0\0\0\fV\1\0\0\0\16`\1\0\0\0\20y\1"+
		"\0\0\0\22{\1\0\0\0\24\u0082\1\0\0\0\26\u008c\1\0\0\0\30\u009c\1\0\0\0"+
		"\32\u009e\1\0\0\0\34\37\3\2\1\0\35\37\3\16\7\0\36\34\1\0\0\0\36\35\1\0"+
		"\0\0\37\"\1\0\0\0 \36\1\0\0\0 !\1\0\0\0!#\1\0\0\0\" \1\0\0\0#$\5\uffff"+
		"\0\0$\1\1\0\0\0%\'\5\f\0\0&%\1\0\0\0&\'\1\0\0\0\',\1\0\0\0(-\3\4\2\0)"+
		"-\3\6\3\0*-\3\b\4\0+-\3\f\6\0,(\1\0\0\0,)\1\0\0\0,*\1\0\0\0,+\1\0\0\0"+
		"-\61\1\0\0\0.\60\5\13\0\0/.\1\0\0\0\60\63\1\0\0\0\61/\1\0\0\0\61\62\1"+
		"\0\0\0\62\3\1\0\0\0\63\61\1\0\0\0\64\65\5&\0\0\65C\5\r\0\0\66\67\5\17"+
		"\0\0\678\5\16\0\089\3\30\f\09:\1\0\0\0:;\5\4\0\0;<\5\20\0\0<=\5\16\0\0"+
		"=>\3\30\f\0>D\1\0\0\0?@\3\30\f\0@A\5\1\0\0AB\3\30\f\0BD\1\0\0\0C\66\1"+
		"\0\0\0C?\1\0\0\0D\5\1\0\0\0EF\5\'\0\0FG\5\r\0\0GH\3\30\f\0HI\4\3\0\1I"+
		"J\5%\0\0J\7\1\0\0\0KL\5(\0\0LM\5\r\0\0MP\3\30\f\0NO\5\t\0\0Oi\3\30\f\0"+
		"PQ\4\4\1\1Q\t\1\0\0\0RS\7\0\0\0ST\5\16\0\0TU\3\30\f\0U\13\1\0\0\0VW\5"+
		")\0\0WX\5\r\0\0X]\3\n\5\0YZ\5\4\0\0Z\\\3\n\5\0[Y\1\0\0\0\\_\1\0\0\0]["+
		"\1\0\0\0]^\1\0\0\0^\r\1\0\0\0_]\1\0\0\0`a\5\25\0\0ab\3\32\r\0bc\4\7\2"+
		"\1cd\5\5\0\0de\5\26\0\0ef\3\24\n\0fg\5\31\0\0gl\3\24\n\0hi\5\32\0\0ij"+
		"\3\32\r\0jk\4\7\3\1km\1\0\0\0lh\1\0\0\0lm\1\0\0\0mn\1\0\0\0no\3\20\b\0"+
		"op\5 \0\0pq\3\24\n\0qr\3\22\t\0r\17\1\0\0\0st\7\1\0\0tz\5\'\0\0uv\7\2"+
		"\0\0vz\5\26\0\0wx\5\36\0\0xz\5\37\0\0ys\1\0\0\0yu\1\0\0\0yw\1\0\0\0z\21"+
		"\1\0\0\0{|\5\36\0\0|}\3\32\r\0}~\4\t\4\1~\23\1\0\0\0\177\u0083\5\27\0"+
		"\0\u0080\u0083\5\30\0\0\u0081\u0083\3\26\13\0\u0082\177\1\0\0\0\u0082"+
		"\u0080\1\0\0\0\u0082\u0081\1\0\0\0\u0083\25\1\0\0\0\u0084\u0085\6\13\uffff"+
		"\0\u0085\u0086\5\7\0\0\u0086\u008d\3\26\13\0\u0087\u0088\5\6\0\0\u0088"+
		"\u0089\3\26\13\0\u0089\u008a\5\3\0\0\u008a\u008d\1\0\0\0\u008b\u008d\7"+
		"\3\0\0\u008c\u0084\1\0\0\0\u008c\u0087\1\0\0\0\u008c\u008b\1\0\0\0\u008d"+
		"\u0099\1\0\0\0\u008e\u008f\4\13\5\1\u008f\u0090\5\1\0\0\u0090\u0098\3"+
		"\26\13\0\u0091\u0092\4\13\6\1\u0092\u0093\5\2\0\0\u0093\u0098\3\26\13"+
		"\0\u0094\u0095\4\13\7\1\u0095\u0096\5\b\0\0\u0096\u0098\3\26\13\0\u0097"+
		"\u008e\1\0\0\0\u0097\u0091\1\0\0\0\u0097\u0094\1\0\0\0\u0098\u009b\1\0"+
		"\0\0\u0099\u0097\1\0\0\0\u0099\u009a\1\0\0\0\u009a\27\1\0\0\0\u009b\u0099"+
		"\1\0\0\0\u009c\u009d\7\4\0\0\u009d\31\1\0\0\0\u009e\u009f\5$\0\0\u009f"+
		"\33\1\0\0\0\r\36 &,\61C]ly\u0082\u008c\u0097\u0099";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}