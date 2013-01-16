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
		COMMA=23, COLON=24, BIN_LITERAL=25, HEX_LITERAL=26, OCT_LITERAL=27, DEC_LITERAL=28, 
		TIME_UNIT=29, TERM_NAME=30;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT", "NL", "WS", "':='", "'='", "'mask'", "'value'", "'stage'", 
		"'capture'", "'when'", "'start'", "'stop'", "'goto'", "'next'", "'activate'", 
		"'on'", "'level'", "'immediately'", "'delay'", "'~'", "'^'", "'#'", "','", 
		"':'", "BIN_LITERAL", "HEX_LITERAL", "OCT_LITERAL", "DEC_LITERAL", "TIME_UNIT", 
		"TERM_NAME"
	};
	public static final String[] ruleNames = {
		"COMMENT", "NL", "WS", "ASSIGN", "EQUALS_TO", "MASK", "VALUE", "STAGE", 
		"CAPTURE", "WHEN", "START", "STOP", "GOTO", "NEXT", "ACTIVATE", "ON", 
		"LEVEL", "IMMEDIATELY", "DELAY", "NOT", "XOR", "SAMPLES", "COMMA", "COLON", 
		"BIN_DIGIT", "BIN_LITERAL", "HEX_DIGIT", "HEX_LITERAL", "OCT_DIGIT", "OCT_LITERAL", 
		"DEC_DIGIT", "DEC_LITERAL", "TIME_UNIT", "TERM_NAME"
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
		"\1\2\36\u00f9\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5"+
		"\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16"+
		"\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25"+
		"\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34"+
		"\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\1\0\1\0\1\0\1\0\5\0J\b"+
		"\0\n\0\f\0M\t\0\1\0\1\0\1\0\1\0\1\1\3\1T\b\1\1\1\1\1\1\1\1\1\1\2\4\2["+
		"\b\2\13\2\f\2\\\1\2\1\2\1\3\1\3\1\3\1\4\1\4\1\5\1\5\1\5\1\5\1\5\1\6\1"+
		"\6\1\6\1\6\1\6\1\6\1\7\1\7\1\7\1\7\1\7\1\7\1\b\1\b\1\b\1\b\1\b\1\b\1\b"+
		"\1\b\1\t\1\t\1\t\1\t\1\t\1\n\1\n\1\n\1\n\1\n\1\n\1\13\1\13\1\13\1\13\1"+
		"\13\1\f\1\f\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\16\1\16\1\16\1\16\1\16\1"+
		"\16\1\16\1\16\1\16\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\20\1\20\1\21\1"+
		"\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\22\1\22\1\22\1"+
		"\22\1\22\1\22\1\23\1\23\1\24\1\24\1\25\1\25\1\26\1\26\1\27\1\27\1\30\1"+
		"\30\1\31\1\31\1\31\4\31\u00cc\b\31\13\31\f\31\u00cd\1\32\1\32\1\33\1\33"+
		"\1\33\4\33\u00d5\b\33\13\33\f\33\u00d6\1\34\1\34\1\35\1\35\4\35\u00dd"+
		"\b\35\13\35\f\35\u00de\1\36\1\36\1\37\1\37\1\37\5\37\u00e6\b\37\n\37\f"+
		"\37\u00e9\t\37\3\37\u00eb\b\37\1 \3 \u00ee\b \1 \1 \1!\1!\1!\1!\3!\u00f6"+
		"\b!\1!\1!\0\"\1\1\0\3\2\1\5\3\2\7\4\uffff\t\5\uffff\13\6\uffff\r\7\uffff"+
		"\17\b\uffff\21\t\uffff\23\n\uffff\25\13\uffff\27\f\uffff\31\r\uffff\33"+
		"\16\uffff\35\17\uffff\37\20\uffff!\21\uffff#\22\uffff%\23\uffff\'\24\uffff"+
		")\25\uffff+\26\uffff-\27\uffff/\30\uffff\61\0\uffff\63\31\uffff\65\0\uffff"+
		"\67\32\uffff9\0\uffff;\33\uffff=\0\uffff?\34\uffffA\35\uffffC\36\uffff"+
		"\1\0\b\2\n\n\r\r\2\n\n\r\r\2\t\t  \2BBbb\3\609AFaf\2XXxx\2mnuu\2AJaj\u00fe"+
		"\0\1\1\0\0\0\0\3\1\0\0\0\0\5\1\0\0\0\0\7\1\0\0\0\0\t\1\0\0\0\0\13\1\0"+
		"\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25\1\0\0\0\0"+
		"\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0\0\0!\1\0"+
		"\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0\0-\1\0\0"+
		"\0\0/\1\0\0\0\0\63\1\0\0\0\0\67\1\0\0\0\0;\1\0\0\0\0?\1\0\0\0\0A\1\0\0"+
		"\0\0C\1\0\0\0\1E\1\0\0\0\3S\1\0\0\0\5Z\1\0\0\0\7`\1\0\0\0\tc\1\0\0\0\13"+
		"e\1\0\0\0\rj\1\0\0\0\17p\1\0\0\0\21v\1\0\0\0\23~\1\0\0\0\25\u0083\1\0"+
		"\0\0\27\u0089\1\0\0\0\31\u008e\1\0\0\0\33\u0093\1\0\0\0\35\u0098\1\0\0"+
		"\0\37\u00a1\1\0\0\0!\u00a4\1\0\0\0#\u00aa\1\0\0\0%\u00b6\1\0\0\0\'\u00bc"+
		"\1\0\0\0)\u00be\1\0\0\0+\u00c0\1\0\0\0-\u00c2\1\0\0\0/\u00c4\1\0\0\0\61"+
		"\u00c6\1\0\0\0\63\u00c8\1\0\0\0\65\u00cf\1\0\0\0\67\u00d1\1\0\0\09\u00d8"+
		"\1\0\0\0;\u00da\1\0\0\0=\u00e0\1\0\0\0?\u00ea\1\0\0\0A\u00ed\1\0\0\0C"+
		"\u00f5\1\0\0\0EF\5/\0\0FG\5/\0\0GK\1\0\0\0HJ\b\0\0\0IH\1\0\0\0JM\1\0\0"+
		"\0KI\1\0\0\0KL\1\0\0\0LN\1\0\0\0MK\1\0\0\0NO\7\1\0\0OP\1\0\0\0PQ\6\0\0"+
		"\0Q\2\1\0\0\0RT\5\r\0\0SR\1\0\0\0ST\1\0\0\0TU\1\0\0\0UV\5\n\0\0VW\1\0"+
		"\0\0WX\6\1\1\0X\4\1\0\0\0Y[\7\2\0\0ZY\1\0\0\0[\\\1\0\0\0\\Z\1\0\0\0\\"+
		"]\1\0\0\0]^\1\0\0\0^_\6\2\2\0_\6\1\0\0\0`a\5:\0\0ab\5=\0\0b\b\1\0\0\0"+
		"cd\5=\0\0d\n\1\0\0\0ef\5m\0\0fg\5a\0\0gh\5s\0\0hi\5k\0\0i\f\1\0\0\0jk"+
		"\5v\0\0kl\5a\0\0lm\5l\0\0mn\5u\0\0no\5e\0\0o\16\1\0\0\0pq\5s\0\0qr\5t"+
		"\0\0rs\5a\0\0st\5g\0\0tu\5e\0\0u\20\1\0\0\0vw\5c\0\0wx\5a\0\0xy\5p\0\0"+
		"yz\5t\0\0z{\5u\0\0{|\5r\0\0|}\5e\0\0}\22\1\0\0\0~\177\5w\0\0\177\u0080"+
		"\5h\0\0\u0080\u0081\5e\0\0\u0081\u0082\5n\0\0\u0082\24\1\0\0\0\u0083\u0084"+
		"\5s\0\0\u0084\u0085\5t\0\0\u0085\u0086\5a\0\0\u0086\u0087\5r\0\0\u0087"+
		"\u0088\5t\0\0\u0088\26\1\0\0\0\u0089\u008a\5s\0\0\u008a\u008b\5t\0\0\u008b"+
		"\u008c\5o\0\0\u008c\u008d\5p\0\0\u008d\30\1\0\0\0\u008e\u008f\5g\0\0\u008f"+
		"\u0090\5o\0\0\u0090\u0091\5t\0\0\u0091\u0092\5o\0\0\u0092\32\1\0\0\0\u0093"+
		"\u0094\5n\0\0\u0094\u0095\5e\0\0\u0095\u0096\5x\0\0\u0096\u0097\5t\0\0"+
		"\u0097\34\1\0\0\0\u0098\u0099\5a\0\0\u0099\u009a\5c\0\0\u009a\u009b\5"+
		"t\0\0\u009b\u009c\5i\0\0\u009c\u009d\5v\0\0\u009d\u009e\5a\0\0\u009e\u009f"+
		"\5t\0\0\u009f\u00a0\5e\0\0\u00a0\36\1\0\0\0\u00a1\u00a2\5o\0\0\u00a2\u00a3"+
		"\5n\0\0\u00a3 \1\0\0\0\u00a4\u00a5\5l\0\0\u00a5\u00a6\5e\0\0\u00a6\u00a7"+
		"\5v\0\0\u00a7\u00a8\5e\0\0\u00a8\u00a9\5l\0\0\u00a9\"\1\0\0\0\u00aa\u00ab"+
		"\5i\0\0\u00ab\u00ac\5m\0\0\u00ac\u00ad\5m\0\0\u00ad\u00ae\5e\0\0\u00ae"+
		"\u00af\5d\0\0\u00af\u00b0\5i\0\0\u00b0\u00b1\5a\0\0\u00b1\u00b2\5t\0\0"+
		"\u00b2\u00b3\5e\0\0\u00b3\u00b4\5l\0\0\u00b4\u00b5\5y\0\0\u00b5$\1\0\0"+
		"\0\u00b6\u00b7\5d\0\0\u00b7\u00b8\5e\0\0\u00b8\u00b9\5l\0\0\u00b9\u00ba"+
		"\5a\0\0\u00ba\u00bb\5y\0\0\u00bb&\1\0\0\0\u00bc\u00bd\5~\0\0\u00bd(\1"+
		"\0\0\0\u00be\u00bf\5^\0\0\u00bf*\1\0\0\0\u00c0\u00c1\5#\0\0\u00c1,\1\0"+
		"\0\0\u00c2\u00c3\5,\0\0\u00c3.\1\0\0\0\u00c4\u00c5\5:\0\0\u00c5\60\1\0"+
		"\0\0\u00c6\u00c7\2\60\61\0\u00c7\62\1\0\0\0\u00c8\u00c9\5\60\0\0\u00c9"+
		"\u00cb\7\3\0\0\u00ca\u00cc\3\61\30\0\u00cb\u00ca\1\0\0\0\u00cc\u00cd\1"+
		"\0\0\0\u00cd\u00cb\1\0\0\0\u00cd\u00ce\1\0\0\0\u00ce\64\1\0\0\0\u00cf"+
		"\u00d0\7\4\0\0\u00d0\66\1\0\0\0\u00d1\u00d2\5\60\0\0\u00d2\u00d4\7\5\0"+
		"\0\u00d3\u00d5\3\65\32\0\u00d4\u00d3\1\0\0\0\u00d5\u00d6\1\0\0\0\u00d6"+
		"\u00d4\1\0\0\0\u00d6\u00d7\1\0\0\0\u00d78\1\0\0\0\u00d8\u00d9\2\60\67"+
		"\0\u00d9:\1\0\0\0\u00da\u00dc\5\60\0\0\u00db\u00dd\39\34\0\u00dc\u00db"+
		"\1\0\0\0\u00dd\u00de\1\0\0\0\u00de\u00dc\1\0\0\0\u00de\u00df\1\0\0\0\u00df"+
		"<\1\0\0\0\u00e0\u00e1\2\609\0\u00e1>\1\0\0\0\u00e2\u00eb\5\60\0\0\u00e3"+
		"\u00e7\2\619\0\u00e4\u00e6\3=\36\0\u00e5\u00e4\1\0\0\0\u00e6\u00e9\1\0"+
		"\0\0\u00e7\u00e5\1\0\0\0\u00e7\u00e8\1\0\0\0\u00e8\u00eb\1\0\0\0\u00e9"+
		"\u00e7\1\0\0\0\u00ea\u00e2\1\0\0\0\u00ea\u00e3\1\0\0\0\u00eb@\1\0\0\0"+
		"\u00ec\u00ee\7\6\0\0\u00ed\u00ec\1\0\0\0\u00ed\u00ee\1\0\0\0\u00ee\u00ef"+
		"\1\0\0\0\u00ef\u00f0\5s\0\0\u00f0B\1\0\0\0\u00f1\u00f2\5t\0\0\u00f2\u00f3"+
		"\5e\0\0\u00f3\u00f4\5r\0\0\u00f4\u00f6\5m\0\0\u00f5\u00f1\1\0\0\0\u00f5"+
		"\u00f6\1\0\0\0\u00f6\u00f7\1\0\0\0\u00f7\u00f8\7\7\0\0\u00f8D\1\0\0\0"+
		"\13\0KS\\\u00cd\u00d6\u00de\u00e7\u00ea\u00ed\u00f5";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}