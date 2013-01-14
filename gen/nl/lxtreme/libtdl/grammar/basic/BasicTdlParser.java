// $ANTLR ANTLRVersion> BasicTdlParser.java generatedTimestamp>

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
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, COMMENT=6, WS=7, DEFINE=8, ASSIGN=9, 
		EQUALS_TO=10, MASK=11, VALUE=12, STAGE=13, CAPTURE=14, WHEN=15, START=16, 
		STOP=17, GOTO=18, NEXT=19, ACTIVATE=20, ON=21, IMMEDIATELY=22, DELAY=23, 
		BIN_LITERAL=24, HEX_LITERAL=25, OCT_LITERAL=26, DEC_LITERAL=27, TIME_UNIT=28, 
		TERM_NAME=29;
	public static final String[] tokenNames = {
		"<INVALID>", "'^'", "','", "':'", "'~'", "'#'", "COMMENT", "WS", "'define'", 
		"'as'", "'='", "'mask'", "'value'", "'stage'", "'capture'", "'when'", 
		"'start'", "'stop'", "'goto'", "'next'", "'activate'", "'on'", "'immediately'", 
		"'delay'", "BIN_LITERAL", "HEX_LITERAL", "OCT_LITERAL", "DEC_LITERAL", 
		"TIME_UNIT", "TERM_NAME"
	};
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_termDecl = 2, RULE_stageDef = 3, RULE_activeClause = 4, 
		RULE_whenClause = 5, RULE_expr = 6, RULE_number = 7, RULE_decNumber = 8;
	public static final String[] ruleNames = {
		"prog", "decl", "termDecl", "stageDef", "activeClause", "whenClause", 
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


	    private int stageCount = 4;

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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).exitProg(this);
		}
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DEFINE) | (1L << STAGE) | (1L << TERM_NAME))) != 0)) {
				{
				setState(20);
				switch (_input.LA(1)) {
				case DEFINE:
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
		public TerminalNode DEFINE() { return getToken(BasicTdlParser.DEFINE, 0); }
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).exitDecl(this);
		}
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
			setState(28);
			_la = _input.LA(1);
			if (_la==DEFINE) {
				{
				setState(27); match(DEFINE);
				}
			}

			{
			setState(30); termDecl();
			}
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(31); match(WS);
				}
				}
				setState(36);
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
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public TerminalNode MASK() { return getToken(BasicTdlParser.MASK, 0); }
		public TerminalNode VALUE() { return getToken(BasicTdlParser.VALUE, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public TerminalNode EQUALS_TO() { return getToken(BasicTdlParser.EQUALS_TO, 0); }
		public TerminalNode ASSIGN() { return getToken(BasicTdlParser.ASSIGN, 0); }
		public TermDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).enterTermDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).exitTermDecl(this);
		}
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
			setState(37); ((TermDeclContext)_localctx).name = match(TERM_NAME);
			setState(38); match(ASSIGN);
			setState(52);
			switch (_input.LA(1)) {
			case MASK:
				{
				{
				setState(39); match(MASK);
				setState(40); match(EQUALS_TO);
				setState(41); ((TermDeclContext)_localctx).mask = number();
				}
				setState(43); match(2);
				{
				setState(44); match(VALUE);
				setState(45); match(EQUALS_TO);
				setState(46); ((TermDeclContext)_localctx).value = number();
				}
				}
				break;
			case BIN_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case DEC_LITERAL:
				{
				{
				setState(48); ((TermDeclContext)_localctx).mask = number();
				setState(49); match(1);
				setState(50); ((TermDeclContext)_localctx).value = number();
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

	public static class StageDefContext extends ParserRuleContext {
		public DecNumberContext n;
		public WhenClauseContext whenClause() {
			return getRuleContext(WhenClauseContext.class,0);
		}
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode WHEN() { return getToken(BasicTdlParser.WHEN, 0); }
		public ActiveClauseContext activeClause() {
			return getRuleContext(ActiveClauseContext.class,0);
		}
		public TerminalNode STAGE() { return getToken(BasicTdlParser.STAGE, 0); }
		public TerminalNode ACTIVATE() { return getToken(BasicTdlParser.ACTIVATE, 0); }
		public StageDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stageDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).enterStageDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).exitStageDef(this);
		}
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
			setState(54); match(STAGE);
			setState(55); ((StageDefContext)_localctx).n = decNumber();
			setState(56);
			if (!( validRange((((StageDefContext)_localctx).n!=null?_input.getText(((StageDefContext)_localctx).n.start,((StageDefContext)_localctx).n.stop):null), 1, stageCount) )) throw new FailedPredicateException(this, " validRange($n.text, 1, stageCount) ");
			setState(57); match(3);
			setState(58); match(ACTIVATE);
			setState(59); activeClause();
			setState(60); match(2);
			setState(61); match(WHEN);
			setState(62); expr();
			setState(63); whenClause();
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
		public TerminalNode IMMEDIATELY() { return getToken(BasicTdlParser.IMMEDIATELY, 0); }
		public ActiveClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_activeClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).enterActiveClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).exitActiveClause(this);
		}
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
			setState(70);
			switch (_input.LA(1)) {
			case ON:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(65); match(ON);
				setState(66); ((ActiveClauseContext)_localctx).n = decNumber();
				setState(67);
				if (!( validRange((((ActiveClauseContext)_localctx).n!=null?_input.getText(((ActiveClauseContext)_localctx).n.start,((ActiveClauseContext)_localctx).n.stop):null), 1, stageCount-1) )) throw new FailedPredicateException(this, " validRange($n.text, 1, stageCount-1) ");
				}
				}
				break;
			case IMMEDIATELY:
				enterOuterAlt(_localctx, 2);
				{
				setState(69); match(IMMEDIATELY);
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

	public static class WhenClauseContext extends ParserRuleContext {
		public DecNumberContext n;
		public TerminalNode DELAY() { return getToken(BasicTdlParser.DELAY, 0); }
		public TerminalNode CAPTURE() { return getToken(BasicTdlParser.CAPTURE, 0); }
		public TerminalNode START() { return getToken(BasicTdlParser.START, 0); }
		public TerminalNode NEXT() { return getToken(BasicTdlParser.NEXT, 0); }
		public TerminalNode GOTO() { return getToken(BasicTdlParser.GOTO, 0); }
		public DecNumberContext decNumber() {
			return getRuleContext(DecNumberContext.class,0);
		}
		public WhenClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).enterWhenClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).exitWhenClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicTdlVisitor ) return ((BasicTdlVisitor<? extends T>)visitor).visitWhenClause(this);
			else return null;
		}
	}

	public final WhenClauseContext whenClause() throws RecognitionException {
		WhenClauseContext _localctx = new WhenClauseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_whenClause);
		int _la;
		try {
			setState(83);
			switch (_input.LA(1)) {
			case START:
				enterOuterAlt(_localctx, 1);
				{
				setState(72); match(START);
				setState(73); match(CAPTURE);
				setState(79);
				_la = _input.LA(1);
				if (_la==DELAY) {
					{
					setState(74); match(DELAY);
					setState(75); ((WhenClauseContext)_localctx).n = decNumber();
					setState(76);
					if (!( validRange((((WhenClauseContext)_localctx).n!=null?_input.getText(((WhenClauseContext)_localctx).n.start,((WhenClauseContext)_localctx).n.stop):null), 1, 0xFFFF) )) throw new FailedPredicateException(this, " validRange($n.text, 1, 0xFFFF) ");
					setState(77); match(5);
					}
				}

				}
				break;
			case GOTO:
				enterOuterAlt(_localctx, 2);
				{
				setState(81); match(GOTO);
				setState(82); match(NEXT);
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
		public TerminalNode TERM_NAME() { return getToken(BasicTdlParser.TERM_NAME, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).exitExpr(this);
		}
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
			setState(88);
			switch (_input.LA(1)) {
			case 4:
				enterOuterAlt(_localctx, 1);
				{
				setState(85); match(4);
				setState(86); expr();
				}
				break;
			case TERM_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(87); match(TERM_NAME);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).exitNumber(this);
		}
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
			setState(90);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).enterDecNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicTdlListener ) ((BasicTdlListener)listener).exitDecNumber(this);
		}
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
			setState(92); match(DEC_LITERAL);
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
		case 3: return stageDef_sempred((StageDefContext)_localctx, predIndex);

		case 4: return activeClause_sempred((ActiveClauseContext)_localctx, predIndex);

		case 5: return whenClause_sempred((WhenClauseContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean whenClause_sempred(WhenClauseContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return  validRange((((WhenClauseContext)_localctx).n!=null?_input.getText(((WhenClauseContext)_localctx).n.start,((WhenClauseContext)_localctx).n.stop):null), 1, 0xFFFF) ;
		}
		return true;
	}
	private boolean stageDef_sempred(StageDefContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return  validRange((((StageDefContext)_localctx).n!=null?_input.getText(((StageDefContext)_localctx).n.start,((StageDefContext)_localctx).n.stop):null), 1, stageCount) ;
		}
		return true;
	}
	private boolean activeClause_sempred(ActiveClauseContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return  validRange((((ActiveClauseContext)_localctx).n!=null?_input.getText(((ActiveClauseContext)_localctx).n.start,((ActiveClauseContext)_localctx).n.stop):null), 1, stageCount-1) ;
		}
		return true;
	}

	public static final String _serializedATN =
		"\1\3\35_\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7\7"+
		"\7\2\b\7\b\1\0\1\0\5\0\25\b\0\n\0\f\0\30\t\0\1\0\1\0\1\1\3\1\35\b\1\1"+
		"\1\1\1\5\1!\b\1\n\1\f\1$\t\1\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1"+
		"\2\1\2\1\2\1\2\1\2\3\2\65\b\2\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3"+
		"\1\3\1\4\1\4\1\4\1\4\1\4\3\4G\b\4\1\5\1\5\1\5\1\5\1\5\1\5\1\5\3\5P\b\5"+
		"\1\5\1\5\3\5T\b\5\1\6\1\6\1\6\3\6Y\b\6\1\7\1\7\1\b\1\b\1\b\0\t\0\2\4\6"+
		"\b\n\f\16\20\0\1\1\30\33^\0\26\1\0\0\0\2\34\1\0\0\0\4%\1\0\0\0\6\66\1"+
		"\0\0\0\bF\1\0\0\0\nS\1\0\0\0\fX\1\0\0\0\16Z\1\0\0\0\20\\\1\0\0\0\22\25"+
		"\3\2\1\0\23\25\3\6\3\0\24\22\1\0\0\0\24\23\1\0\0\0\25\30\1\0\0\0\26\24"+
		"\1\0\0\0\26\27\1\0\0\0\27\31\1\0\0\0\30\26\1\0\0\0\31\32\5\uffff\0\0\32"+
		"\1\1\0\0\0\33\35\5\b\0\0\34\33\1\0\0\0\34\35\1\0\0\0\35\36\1\0\0\0\36"+
		"\"\3\4\2\0\37!\5\7\0\0 \37\1\0\0\0!$\1\0\0\0\" \1\0\0\0\"#\1\0\0\0#\3"+
		"\1\0\0\0$\"\1\0\0\0%&\5\35\0\0&\64\5\t\0\0\'(\5\13\0\0()\5\n\0\0)*\3\16"+
		"\7\0*+\1\0\0\0+,\5\2\0\0,-\5\f\0\0-.\5\n\0\0./\3\16\7\0/\65\1\0\0\0\60"+
		"\61\3\16\7\0\61\62\5\1\0\0\62\63\3\16\7\0\63\65\1\0\0\0\64\'\1\0\0\0\64"+
		"\60\1\0\0\0\65\5\1\0\0\0\66\67\5\r\0\0\678\3\20\b\089\4\3\0\19:\5\3\0"+
		"\0:;\5\24\0\0;<\3\b\4\0<=\5\2\0\0=>\5\17\0\0>?\3\f\6\0?@\3\n\5\0@\7\1"+
		"\0\0\0AB\5\25\0\0BC\3\20\b\0CD\4\4\1\1DG\1\0\0\0EG\5\26\0\0FA\1\0\0\0"+
		"FE\1\0\0\0G\t\1\0\0\0HI\5\20\0\0IO\5\16\0\0JK\5\27\0\0KL\3\20\b\0LM\4"+
		"\5\2\1MN\5\5\0\0NP\1\0\0\0OJ\1\0\0\0OP\1\0\0\0PT\1\0\0\0QR\5\22\0\0RT"+
		"\5\23\0\0SH\1\0\0\0SQ\1\0\0\0T\13\1\0\0\0UV\5\4\0\0VY\3\f\6\0WY\5\35\0"+
		"\0XU\1\0\0\0XW\1\0\0\0Y\r\1\0\0\0Z[\7\0\0\0[\17\1\0\0\0\\]\5\33\0\0]\21"+
		"\1\0\0\0\t\24\26\34\"\64FOSX";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}