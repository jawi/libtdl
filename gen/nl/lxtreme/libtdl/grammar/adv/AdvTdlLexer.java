// $ANTLR ANTLRVersion> AdvTdlLexer.java generatedTimestamp>

/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AdvTdlLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, COMMENT=2, NL=3, WS=4, ASSIGN=5, EQUALS_TO=6, MASK=7, VALUE=8, 
		RISING=9, FALLING=10, BOTH=11, NEITHER=12, STAGE=13, CAPTURE=14, NOP=15, 
		ANY=16, WHEN=17, OCCURS=18, START=19, STOP=20, CLEAR=21, GOTO=22, NEXT=23, 
		ELSE=24, ON=25, COMMA=26, COLON=27, NOT=28, XOR=29, AND=30, OR=31, LPAREN=32, 
		RPAREN=33, BIN_LITERAL=34, HEX_LITERAL=35, OCT_LITERAL=36, DEC_LITERAL=37, 
		TIME_UNIT=38, TERM_NAME=39, TIMER_NAME=40, RANGE_NAME=41, EDGE_NAME=42;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'..'", "COMMENT", "NL", "WS", "':='", "'='", "'mask'", "'value'", "'rising'", 
		"'falling'", "'both'", "'neither'", "'stage'", "'capture'", "'nop'", "'any'", 
		"'when'", "'occurs'", "'start'", "'stop'", "'clear'", "'goto'", "'next'", 
		"'else'", "'on'", "','", "':'", "'~'", "'^'", "'&'", "'|'", "'('", "')'", 
		"BIN_LITERAL", "HEX_LITERAL", "OCT_LITERAL", "DEC_LITERAL", "TIME_UNIT", 
		"TERM_NAME", "TIMER_NAME", "RANGE_NAME", "EDGE_NAME"
	};
	public static final String[] ruleNames = {
		"T__0", "COMMENT", "NL", "WS", "ASSIGN", "EQUALS_TO", "MASK", "VALUE", 
		"RISING", "FALLING", "BOTH", "NEITHER", "STAGE", "CAPTURE", "NOP", "ANY", 
		"WHEN", "OCCURS", "START", "STOP", "CLEAR", "GOTO", "NEXT", "ELSE", "ON", 
		"COMMA", "COLON", "NOT", "XOR", "AND", "OR", "LPAREN", "RPAREN", "BIN_DIGIT", 
		"BIN_LITERAL", "HEX_DIGIT", "HEX_LITERAL", "OCT_DIGIT", "OCT_LITERAL", 
		"DEC_DIGIT", "DEC_LITERAL", "TIME_UNIT", "TERM_NAME", "TIMER_NAME", "RANGE_NAME", 
		"EDGE_NAME"
	};


	public AdvTdlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AdvTdl.g4"; }

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
		case 1: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 2: NL_action((RuleContext)_localctx, actionIndex); break;

		case 3: WS_action((RuleContext)_localctx, actionIndex); break;
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
		"\1\2*\u0146\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2"+
		"\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16"+
		"\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25"+
		"\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34"+
		"\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2"+
		"%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\1\0\1\0\1\0\1\1"+
		"\1\1\1\1\1\1\5\1e\b\1\n\1\f\1h\t\1\1\1\1\1\1\1\1\1\1\2\3\2o\b\2\1\2\1"+
		"\2\1\2\1\2\1\3\4\3v\b\3\13\3\f\3w\1\3\1\3\1\4\1\4\1\4\1\5\1\5\1\6\1\6"+
		"\1\6\1\6\1\6\1\7\1\7\1\7\1\7\1\7\1\7\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\t\1"+
		"\t\1\t\1\t\1\t\1\t\1\t\1\t\1\n\1\n\1\n\1\n\1\n\1\13\1\13\1\13\1\13\1\13"+
		"\1\13\1\13\1\13\1\f\1\f\1\f\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1"+
		"\r\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\20\1"+
		"\21\1\21\1\21\1\21\1\21\1\21\1\21\1\22\1\22\1\22\1\22\1\22\1\22\1\23\1"+
		"\23\1\23\1\23\1\23\1\24\1\24\1\24\1\24\1\24\1\24\1\25\1\25\1\25\1\25\1"+
		"\25\1\26\1\26\1\26\1\26\1\26\1\27\1\27\1\27\1\27\1\27\1\30\1\30\1\30\1"+
		"\31\1\31\1\32\1\32\1\33\1\33\1\34\1\34\1\35\1\35\1\36\1\36\1\37\1\37\1"+
		" \1 \1!\1!\1\"\1\"\1\"\4\"\u0102\b\"\13\"\f\"\u0103\1#\1#\1$\1$\1$\4$"+
		"\u010b\b$\13$\f$\u010c\1%\1%\1&\1&\4&\u0113\b&\13&\f&\u0114\1\'\1\'\1"+
		"(\1(\1(\5(\u011c\b(\n(\f(\u011f\t(\3(\u0121\b(\1)\3)\u0124\b)\1)\1)\1"+
		"*\1*\1*\1*\3*\u012c\b*\1*\1*\1+\1+\1+\1+\1+\1+\1+\1+\1,\1,\1,\1,\1,\1"+
		",\1,\1,\1-\1-\1-\1-\1-\1-\1-\0.\1\1\uffff\3\2\0\5\3\1\7\4\2\t\5\uffff"+
		"\13\6\uffff\r\7\uffff\17\b\uffff\21\t\uffff\23\n\uffff\25\13\uffff\27"+
		"\f\uffff\31\r\uffff\33\16\uffff\35\17\uffff\37\20\uffff!\21\uffff#\22"+
		"\uffff%\23\uffff\'\24\uffff)\25\uffff+\26\uffff-\27\uffff/\30\uffff\61"+
		"\31\uffff\63\32\uffff\65\33\uffff\67\34\uffff9\35\uffff;\36\uffff=\37"+
		"\uffff? \uffffA!\uffffC\0\uffffE\"\uffffG\0\uffffI#\uffffK\0\uffffM$\uffff"+
		"O\0\uffffQ%\uffffS&\uffffU\'\uffffW(\uffffY)\uffff[*\uffff\1\0\b\2\n\n"+
		"\r\r\2\n\n\r\r\2\t\t  \2BBbb\3\609AFaf\2XXxx\2mnuu\2AJaj\u014b\0\1\1\0"+
		"\0\0\0\3\1\0\0\0\0\5\1\0\0\0\0\7\1\0\0\0\0\t\1\0\0\0\0\13\1\0\0\0\0\r"+
		"\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25\1\0\0\0\0\27\1\0"+
		"\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0\0\0!\1\0\0\0\0"+
		"#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0\0-\1\0\0\0\0/\1"+
		"\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1\0\0\0\0\67\1\0\0\0\09\1\0\0\0"+
		"\0;\1\0\0\0\0=\1\0\0\0\0?\1\0\0\0\0A\1\0\0\0\0E\1\0\0\0\0I\1\0\0\0\0M"+
		"\1\0\0\0\0Q\1\0\0\0\0S\1\0\0\0\0U\1\0\0\0\0W\1\0\0\0\0Y\1\0\0\0\0[\1\0"+
		"\0\0\1]\1\0\0\0\3`\1\0\0\0\5n\1\0\0\0\7u\1\0\0\0\t{\1\0\0\0\13~\1\0\0"+
		"\0\r\u0080\1\0\0\0\17\u0085\1\0\0\0\21\u008b\1\0\0\0\23\u0092\1\0\0\0"+
		"\25\u009a\1\0\0\0\27\u009f\1\0\0\0\31\u00a7\1\0\0\0\33\u00ad\1\0\0\0\35"+
		"\u00b5\1\0\0\0\37\u00b9\1\0\0\0!\u00bd\1\0\0\0#\u00c2\1\0\0\0%\u00c9\1"+
		"\0\0\0\'\u00cf\1\0\0\0)\u00d4\1\0\0\0+\u00da\1\0\0\0-\u00df\1\0\0\0/\u00e4"+
		"\1\0\0\0\61\u00e9\1\0\0\0\63\u00ec\1\0\0\0\65\u00ee\1\0\0\0\67\u00f0\1"+
		"\0\0\09\u00f2\1\0\0\0;\u00f4\1\0\0\0=\u00f6\1\0\0\0?\u00f8\1\0\0\0A\u00fa"+
		"\1\0\0\0C\u00fc\1\0\0\0E\u00fe\1\0\0\0G\u0105\1\0\0\0I\u0107\1\0\0\0K"+
		"\u010e\1\0\0\0M\u0110\1\0\0\0O\u0116\1\0\0\0Q\u0120\1\0\0\0S\u0123\1\0"+
		"\0\0U\u012b\1\0\0\0W\u012f\1\0\0\0Y\u0137\1\0\0\0[\u013f\1\0\0\0]^\5."+
		"\0\0^_\5.\0\0_\2\1\0\0\0`a\5/\0\0ab\5/\0\0bf\1\0\0\0ce\b\0\0\0dc\1\0\0"+
		"\0eh\1\0\0\0fd\1\0\0\0fg\1\0\0\0gi\1\0\0\0hf\1\0\0\0ij\7\1\0\0jk\1\0\0"+
		"\0kl\6\1\0\0l\4\1\0\0\0mo\5\r\0\0nm\1\0\0\0no\1\0\0\0op\1\0\0\0pq\5\n"+
		"\0\0qr\1\0\0\0rs\6\2\1\0s\6\1\0\0\0tv\7\2\0\0ut\1\0\0\0vw\1\0\0\0wu\1"+
		"\0\0\0wx\1\0\0\0xy\1\0\0\0yz\6\3\2\0z\b\1\0\0\0{|\5:\0\0|}\5=\0\0}\n\1"+
		"\0\0\0~\177\5=\0\0\177\f\1\0\0\0\u0080\u0081\5m\0\0\u0081\u0082\5a\0\0"+
		"\u0082\u0083\5s\0\0\u0083\u0084\5k\0\0\u0084\16\1\0\0\0\u0085\u0086\5"+
		"v\0\0\u0086\u0087\5a\0\0\u0087\u0088\5l\0\0\u0088\u0089\5u\0\0\u0089\u008a"+
		"\5e\0\0\u008a\20\1\0\0\0\u008b\u008c\5r\0\0\u008c\u008d\5i\0\0\u008d\u008e"+
		"\5s\0\0\u008e\u008f\5i\0\0\u008f\u0090\5n\0\0\u0090\u0091\5g\0\0\u0091"+
		"\22\1\0\0\0\u0092\u0093\5f\0\0\u0093\u0094\5a\0\0\u0094\u0095\5l\0\0\u0095"+
		"\u0096\5l\0\0\u0096\u0097\5i\0\0\u0097\u0098\5n\0\0\u0098\u0099\5g\0\0"+
		"\u0099\24\1\0\0\0\u009a\u009b\5b\0\0\u009b\u009c\5o\0\0\u009c\u009d\5"+
		"t\0\0\u009d\u009e\5h\0\0\u009e\26\1\0\0\0\u009f\u00a0\5n\0\0\u00a0\u00a1"+
		"\5e\0\0\u00a1\u00a2\5i\0\0\u00a2\u00a3\5t\0\0\u00a3\u00a4\5h\0\0\u00a4"+
		"\u00a5\5e\0\0\u00a5\u00a6\5r\0\0\u00a6\30\1\0\0\0\u00a7\u00a8\5s\0\0\u00a8"+
		"\u00a9\5t\0\0\u00a9\u00aa\5a\0\0\u00aa\u00ab\5g\0\0\u00ab\u00ac\5e\0\0"+
		"\u00ac\32\1\0\0\0\u00ad\u00ae\5c\0\0\u00ae\u00af\5a\0\0\u00af\u00b0\5"+
		"p\0\0\u00b0\u00b1\5t\0\0\u00b1\u00b2\5u\0\0\u00b2\u00b3\5r\0\0\u00b3\u00b4"+
		"\5e\0\0\u00b4\34\1\0\0\0\u00b5\u00b6\5n\0\0\u00b6\u00b7\5o\0\0\u00b7\u00b8"+
		"\5p\0\0\u00b8\36\1\0\0\0\u00b9\u00ba\5a\0\0\u00ba\u00bb\5n\0\0\u00bb\u00bc"+
		"\5y\0\0\u00bc \1\0\0\0\u00bd\u00be\5w\0\0\u00be\u00bf\5h\0\0\u00bf\u00c0"+
		"\5e\0\0\u00c0\u00c1\5n\0\0\u00c1\"\1\0\0\0\u00c2\u00c3\5o\0\0\u00c3\u00c4"+
		"\5c\0\0\u00c4\u00c5\5c\0\0\u00c5\u00c6\5u\0\0\u00c6\u00c7\5r\0\0\u00c7"+
		"\u00c8\5s\0\0\u00c8$\1\0\0\0\u00c9\u00ca\5s\0\0\u00ca\u00cb\5t\0\0\u00cb"+
		"\u00cc\5a\0\0\u00cc\u00cd\5r\0\0\u00cd\u00ce\5t\0\0\u00ce&\1\0\0\0\u00cf"+
		"\u00d0\5s\0\0\u00d0\u00d1\5t\0\0\u00d1\u00d2\5o\0\0\u00d2\u00d3\5p\0\0"+
		"\u00d3(\1\0\0\0\u00d4\u00d5\5c\0\0\u00d5\u00d6\5l\0\0\u00d6\u00d7\5e\0"+
		"\0\u00d7\u00d8\5a\0\0\u00d8\u00d9\5r\0\0\u00d9*\1\0\0\0\u00da\u00db\5"+
		"g\0\0\u00db\u00dc\5o\0\0\u00dc\u00dd\5t\0\0\u00dd\u00de\5o\0\0\u00de,"+
		"\1\0\0\0\u00df\u00e0\5n\0\0\u00e0\u00e1\5e\0\0\u00e1\u00e2\5x\0\0\u00e2"+
		"\u00e3\5t\0\0\u00e3.\1\0\0\0\u00e4\u00e5\5e\0\0\u00e5\u00e6\5l\0\0\u00e6"+
		"\u00e7\5s\0\0\u00e7\u00e8\5e\0\0\u00e8\60\1\0\0\0\u00e9\u00ea\5o\0\0\u00ea"+
		"\u00eb\5n\0\0\u00eb\62\1\0\0\0\u00ec\u00ed\5,\0\0\u00ed\64\1\0\0\0\u00ee"+
		"\u00ef\5:\0\0\u00ef\66\1\0\0\0\u00f0\u00f1\5~\0\0\u00f18\1\0\0\0\u00f2"+
		"\u00f3\5^\0\0\u00f3:\1\0\0\0\u00f4\u00f5\5&\0\0\u00f5<\1\0\0\0\u00f6\u00f7"+
		"\5|\0\0\u00f7>\1\0\0\0\u00f8\u00f9\5(\0\0\u00f9@\1\0\0\0\u00fa\u00fb\5"+
		")\0\0\u00fbB\1\0\0\0\u00fc\u00fd\2\60\61\0\u00fdD\1\0\0\0\u00fe\u00ff"+
		"\5\60\0\0\u00ff\u0101\7\3\0\0\u0100\u0102\3C!\0\u0101\u0100\1\0\0\0\u0102"+
		"\u0103\1\0\0\0\u0103\u0101\1\0\0\0\u0103\u0104\1\0\0\0\u0104F\1\0\0\0"+
		"\u0105\u0106\7\4\0\0\u0106H\1\0\0\0\u0107\u0108\5\60\0\0\u0108\u010a\7"+
		"\5\0\0\u0109\u010b\3G#\0\u010a\u0109\1\0\0\0\u010b\u010c\1\0\0\0\u010c"+
		"\u010a\1\0\0\0\u010c\u010d\1\0\0\0\u010dJ\1\0\0\0\u010e\u010f\2\60\67"+
		"\0\u010fL\1\0\0\0\u0110\u0112\5\60\0\0\u0111\u0113\3K%\0\u0112\u0111\1"+
		"\0\0\0\u0113\u0114\1\0\0\0\u0114\u0112\1\0\0\0\u0114\u0115\1\0\0\0\u0115"+
		"N\1\0\0\0\u0116\u0117\2\609\0\u0117P\1\0\0\0\u0118\u0121\5\60\0\0\u0119"+
		"\u011d\2\619\0\u011a\u011c\3O\'\0\u011b\u011a\1\0\0\0\u011c\u011f\1\0"+
		"\0\0\u011d\u011b\1\0\0\0\u011d\u011e\1\0\0\0\u011e\u0121\1\0\0\0\u011f"+
		"\u011d\1\0\0\0\u0120\u0118\1\0\0\0\u0120\u0119\1\0\0\0\u0121R\1\0\0\0"+
		"\u0122\u0124\7\6\0\0\u0123\u0122\1\0\0\0\u0123\u0124\1\0\0\0\u0124\u0125"+
		"\1\0\0\0\u0125\u0126\5s\0\0\u0126T\1\0\0\0\u0127\u0128\5t\0\0\u0128\u0129"+
		"\5e\0\0\u0129\u012a\5r\0\0\u012a\u012c\5m\0\0\u012b\u0127\1\0\0\0\u012b"+
		"\u012c\1\0\0\0\u012c\u012d\1\0\0\0\u012d\u012e\7\7\0\0\u012eV\1\0\0\0"+
		"\u012f\u0130\5t\0\0\u0130\u0131\5i\0\0\u0131\u0132\5m\0\0\u0132\u0133"+
		"\5e\0\0\u0133\u0134\5r\0\0\u0134\u0135\1\0\0\0\u0135\u0136\2\61\62\0\u0136"+
		"X\1\0\0\0\u0137\u0138\5r\0\0\u0138\u0139\5a\0\0\u0139\u013a\5n\0\0\u013a"+
		"\u013b\5g\0\0\u013b\u013c\5e\0\0\u013c\u013d\1\0\0\0\u013d\u013e\2\61"+
		"\62\0\u013eZ\1\0\0\0\u013f\u0140\5e\0\0\u0140\u0141\5d\0\0\u0141\u0142"+
		"\5g\0\0\u0142\u0143\5e\0\0\u0143\u0144\1\0\0\0\u0144\u0145\2\61\62\0\u0145"+
		"\\\1\0\0\0\13\0fnw\u0103\u010c\u0114\u011d\u0120\u0123\u012b";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}