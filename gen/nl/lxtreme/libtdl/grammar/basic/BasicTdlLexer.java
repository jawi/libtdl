// $ANTLR ANTLRVersion> BasicTdlLexer.java generatedTimestamp>

/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicTdlLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, NL=2, WS=3, ASSIGN=4, EQUALS_TO=5, MASK=6, VALUE=7, STAGE=8, 
		CAPTURE=9, WHEN=10, START=11, STOP=12, GOTO=13, NEXT=14, ACTIVATE=15, 
		ON=16, LEVEL=17, IMMEDIATELY=18, DELAY=19, NOT=20, XOR=21, SAMPLES=22, 
		COMMA=23, COLON=24, AT=25, BIN_LITERAL=26, HEX_LITERAL=27, OCT_LITERAL=28, 
		DEC_LITERAL=29, TIME_UNIT=30, TERM_NAME=31;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT", "NL", "WS", "':='", "'='", "'mask'", "'value'", "'stage'", 
		"'capture'", "'when'", "'start'", "'stop'", "'goto'", "'next'", "'activate'", 
		"'on'", "'level'", "'immediately'", "'delay'", "'~'", "'^'", "'#'", "','", 
		"':'", "'@'", "BIN_LITERAL", "HEX_LITERAL", "OCT_LITERAL", "DEC_LITERAL", 
		"TIME_UNIT", "TERM_NAME"
	};
	public static final String[] ruleNames = {
		"COMMENT", "NL", "WS", "ASSIGN", "EQUALS_TO", "MASK", "VALUE", "STAGE", 
		"CAPTURE", "WHEN", "START", "STOP", "GOTO", "NEXT", "ACTIVATE", "ON", 
		"LEVEL", "IMMEDIATELY", "DELAY", "NOT", "XOR", "SAMPLES", "COMMA", "COLON", 
		"AT", "BIN_DIGIT", "BIN_LITERAL", "HEX_DIGIT", "HEX_LITERAL", "OCT_DIGIT", 
		"OCT_LITERAL", "DEC_DIGIT", "DEC_LITERAL", "TIME_UNIT", "TERM_NAME"
	};


	public BasicTdlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BasicTdl.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 1: NL_action((RuleContext)_localctx, actionIndex); break;

		case 2: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: _channel = HIDDEN;  break;
		}
	}
	private void NL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: _channel = HIDDEN;  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: _channel = HIDDEN;  break;
		}
	}

	public static final String _serializedATN =
		"\1\2\37\u00fd\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5"+
		"\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16"+
		"\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25"+
		"\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34"+
		"\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\1\0\1\0\1\0\1"+
		"\0\5\0L\b\0\n\0\f\0O\t\0\1\0\1\0\1\0\1\0\1\1\3\1V\b\1\1\1\1\1\1\1\1\1"+
		"\1\2\4\2]\b\2\13\2\f\2^\1\2\1\2\1\3\1\3\1\3\1\4\1\4\1\5\1\5\1\5\1\5\1"+
		"\5\1\6\1\6\1\6\1\6\1\6\1\6\1\7\1\7\1\7\1\7\1\7\1\7\1\b\1\b\1\b\1\b\1\b"+
		"\1\b\1\b\1\b\1\t\1\t\1\t\1\t\1\t\1\n\1\n\1\n\1\n\1\n\1\n\1\13\1\13\1\13"+
		"\1\13\1\13\1\f\1\f\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\16\1\16\1\16\1\16"+
		"\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\20\1\20"+
		"\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\22\1\22"+
		"\1\22\1\22\1\22\1\22\1\23\1\23\1\24\1\24\1\25\1\25\1\26\1\26\1\27\1\27"+
		"\1\30\1\30\1\31\1\31\1\32\1\32\1\32\4\32\u00d0\b\32\13\32\f\32\u00d1\1"+
		"\33\1\33\1\34\1\34\1\34\4\34\u00d9\b\34\13\34\f\34\u00da\1\35\1\35\1\36"+
		"\1\36\4\36\u00e1\b\36\13\36\f\36\u00e2\1\37\1\37\1 \1 \1 \5 \u00ea\b "+
		"\n \f \u00ed\t \3 \u00ef\b \1!\3!\u00f2\b!\1!\1!\1\"\1\"\1\"\1\"\3\"\u00fa"+
		"\b\"\1\"\1\"\0#\1\1\0\3\2\1\5\3\2\7\4\uffff\t\5\uffff\13\6\uffff\r\7\uffff"+
		"\17\b\uffff\21\t\uffff\23\n\uffff\25\13\uffff\27\f\uffff\31\r\uffff\33"+
		"\16\uffff\35\17\uffff\37\20\uffff!\21\uffff#\22\uffff%\23\uffff\'\24\uffff"+
		")\25\uffff+\26\uffff-\27\uffff/\30\uffff\61\31\uffff\63\0\uffff\65\32"+
		"\uffff\67\0\uffff9\33\uffff;\0\uffff=\34\uffff?\0\uffffA\35\uffffC\36"+
		"\uffffE\37\uffff\1\0\b\2\n\n\r\r\2\n\n\r\r\2\t\t  \2BBbb\3\609AFaf\2X"+
		"Xxx\2mnuu\2AJaj\u0102\0\1\1\0\0\0\0\3\1\0\0\0\0\5\1\0\0\0\0\7\1\0\0\0"+
		"\0\t\1\0\0\0\0\13\1\0\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1"+
		"\0\0\0\0\25\1\0\0\0\0\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0"+
		"\0\0\37\1\0\0\0\0!\1\0\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0"+
		"\0\0+\1\0\0\0\0-\1\0\0\0\0/\1\0\0\0\0\61\1\0\0\0\0\65\1\0\0\0\09\1\0\0"+
		"\0\0=\1\0\0\0\0A\1\0\0\0\0C\1\0\0\0\0E\1\0\0\0\1G\1\0\0\0\3U\1\0\0\0\5"+
		"\\\1\0\0\0\7b\1\0\0\0\te\1\0\0\0\13g\1\0\0\0\rl\1\0\0\0\17r\1\0\0\0\21"+
		"x\1\0\0\0\23\u0080\1\0\0\0\25\u0085\1\0\0\0\27\u008b\1\0\0\0\31\u0090"+
		"\1\0\0\0\33\u0095\1\0\0\0\35\u009a\1\0\0\0\37\u00a3\1\0\0\0!\u00a6\1\0"+
		"\0\0#\u00ac\1\0\0\0%\u00b8\1\0\0\0\'\u00be\1\0\0\0)\u00c0\1\0\0\0+\u00c2"+
		"\1\0\0\0-\u00c4\1\0\0\0/\u00c6\1\0\0\0\61\u00c8\1\0\0\0\63\u00ca\1\0\0"+
		"\0\65\u00cc\1\0\0\0\67\u00d3\1\0\0\09\u00d5\1\0\0\0;\u00dc\1\0\0\0=\u00de"+
		"\1\0\0\0?\u00e4\1\0\0\0A\u00ee\1\0\0\0C\u00f1\1\0\0\0E\u00f9\1\0\0\0G"+
		"H\5/\0\0HI\5/\0\0IM\1\0\0\0JL\b\0\0\0KJ\1\0\0\0LO\1\0\0\0MK\1\0\0\0MN"+
		"\1\0\0\0NP\1\0\0\0OM\1\0\0\0PQ\7\1\0\0QR\1\0\0\0RS\6\0\0\0S\2\1\0\0\0"+
		"TV\5\r\0\0UT\1\0\0\0UV\1\0\0\0VW\1\0\0\0WX\5\n\0\0XY\1\0\0\0YZ\6\1\1\0"+
		"Z\4\1\0\0\0[]\7\2\0\0\\[\1\0\0\0]^\1\0\0\0^\\\1\0\0\0^_\1\0\0\0_`\1\0"+
		"\0\0`a\6\2\2\0a\6\1\0\0\0bc\5:\0\0cd\5=\0\0d\b\1\0\0\0ef\5=\0\0f\n\1\0"+
		"\0\0gh\5m\0\0hi\5a\0\0ij\5s\0\0jk\5k\0\0k\f\1\0\0\0lm\5v\0\0mn\5a\0\0"+
		"no\5l\0\0op\5u\0\0pq\5e\0\0q\16\1\0\0\0rs\5s\0\0st\5t\0\0tu\5a\0\0uv\5"+
		"g\0\0vw\5e\0\0w\20\1\0\0\0xy\5c\0\0yz\5a\0\0z{\5p\0\0{|\5t\0\0|}\5u\0"+
		"\0}~\5r\0\0~\177\5e\0\0\177\22\1\0\0\0\u0080\u0081\5w\0\0\u0081\u0082"+
		"\5h\0\0\u0082\u0083\5e\0\0\u0083\u0084\5n\0\0\u0084\24\1\0\0\0\u0085\u0086"+
		"\5s\0\0\u0086\u0087\5t\0\0\u0087\u0088\5a\0\0\u0088\u0089\5r\0\0\u0089"+
		"\u008a\5t\0\0\u008a\26\1\0\0\0\u008b\u008c\5s\0\0\u008c\u008d\5t\0\0\u008d"+
		"\u008e\5o\0\0\u008e\u008f\5p\0\0\u008f\30\1\0\0\0\u0090\u0091\5g\0\0\u0091"+
		"\u0092\5o\0\0\u0092\u0093\5t\0\0\u0093\u0094\5o\0\0\u0094\32\1\0\0\0\u0095"+
		"\u0096\5n\0\0\u0096\u0097\5e\0\0\u0097\u0098\5x\0\0\u0098\u0099\5t\0\0"+
		"\u0099\34\1\0\0\0\u009a\u009b\5a\0\0\u009b\u009c\5c\0\0\u009c\u009d\5"+
		"t\0\0\u009d\u009e\5i\0\0\u009e\u009f\5v\0\0\u009f\u00a0\5a\0\0\u00a0\u00a1"+
		"\5t\0\0\u00a1\u00a2\5e\0\0\u00a2\36\1\0\0\0\u00a3\u00a4\5o\0\0\u00a4\u00a5"+
		"\5n\0\0\u00a5 \1\0\0\0\u00a6\u00a7\5l\0\0\u00a7\u00a8\5e\0\0\u00a8\u00a9"+
		"\5v\0\0\u00a9\u00aa\5e\0\0\u00aa\u00ab\5l\0\0\u00ab\"\1\0\0\0\u00ac\u00ad"+
		"\5i\0\0\u00ad\u00ae\5m\0\0\u00ae\u00af\5m\0\0\u00af\u00b0\5e\0\0\u00b0"+
		"\u00b1\5d\0\0\u00b1\u00b2\5i\0\0\u00b2\u00b3\5a\0\0\u00b3\u00b4\5t\0\0"+
		"\u00b4\u00b5\5e\0\0\u00b5\u00b6\5l\0\0\u00b6\u00b7\5y\0\0\u00b7$\1\0\0"+
		"\0\u00b8\u00b9\5d\0\0\u00b9\u00ba\5e\0\0\u00ba\u00bb\5l\0\0\u00bb\u00bc"+
		"\5a\0\0\u00bc\u00bd\5y\0\0\u00bd&\1\0\0\0\u00be\u00bf\5~\0\0\u00bf(\1"+
		"\0\0\0\u00c0\u00c1\5^\0\0\u00c1*\1\0\0\0\u00c2\u00c3\5#\0\0\u00c3,\1\0"+
		"\0\0\u00c4\u00c5\5,\0\0\u00c5.\1\0\0\0\u00c6\u00c7\5:\0\0\u00c7\60\1\0"+
		"\0\0\u00c8\u00c9\5@\0\0\u00c9\62\1\0\0\0\u00ca\u00cb\2\60\61\0\u00cb\64"+
		"\1\0\0\0\u00cc\u00cd\5\60\0\0\u00cd\u00cf\7\3\0\0\u00ce\u00d0\3\63\31"+
		"\0\u00cf\u00ce\1\0\0\0\u00d0\u00d1\1\0\0\0\u00d1\u00cf\1\0\0\0\u00d1\u00d2"+
		"\1\0\0\0\u00d2\66\1\0\0\0\u00d3\u00d4\7\4\0\0\u00d48\1\0\0\0\u00d5\u00d6"+
		"\5\60\0\0\u00d6\u00d8\7\5\0\0\u00d7\u00d9\3\67\33\0\u00d8\u00d7\1\0\0"+
		"\0\u00d9\u00da\1\0\0\0\u00da\u00d8\1\0\0\0\u00da\u00db\1\0\0\0\u00db:"+
		"\1\0\0\0\u00dc\u00dd\2\60\67\0\u00dd<\1\0\0\0\u00de\u00e0\5\60\0\0\u00df"+
		"\u00e1\3;\35\0\u00e0\u00df\1\0\0\0\u00e1\u00e2\1\0\0\0\u00e2\u00e0\1\0"+
		"\0\0\u00e2\u00e3\1\0\0\0\u00e3>\1\0\0\0\u00e4\u00e5\2\609\0\u00e5@\1\0"+
		"\0\0\u00e6\u00ef\5\60\0\0\u00e7\u00eb\2\619\0\u00e8\u00ea\3?\37\0\u00e9"+
		"\u00e8\1\0\0\0\u00ea\u00ed\1\0\0\0\u00eb\u00e9\1\0\0\0\u00eb\u00ec\1\0"+
		"\0\0\u00ec\u00ef\1\0\0\0\u00ed\u00eb\1\0\0\0\u00ee\u00e6\1\0\0\0\u00ee"+
		"\u00e7\1\0\0\0\u00efB\1\0\0\0\u00f0\u00f2\7\6\0\0\u00f1\u00f0\1\0\0\0"+
		"\u00f1\u00f2\1\0\0\0\u00f2\u00f3\1\0\0\0\u00f3\u00f4\5s\0\0\u00f4D\1\0"+
		"\0\0\u00f5\u00f6\5t\0\0\u00f6\u00f7\5e\0\0\u00f7\u00f8\5r\0\0\u00f8\u00fa"+
		"\5m\0\0\u00f9\u00f5\1\0\0\0\u00f9\u00fa\1\0\0\0\u00fa\u00fb\1\0\0\0\u00fb"+
		"\u00fc\7\7\0\0\u00fcF\1\0\0\0\13\0MU^\u00d1\u00da\u00e2\u00eb\u00ee\u00f1"+
		"\u00f9";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}