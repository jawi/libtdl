// Generated from BasicTdl.g4 by ANTLR 4.2.2

/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;


import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

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
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2!\u00ff\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\2\3\2\7\2N\n\2\f\2\16\2Q\13\2\3\2\3"+
		"\2\3\2\3\2\3\3\5\3X\n\3\3\3\3\3\3\3\3\3\3\4\6\4_\n\4\r\4\16\4`\3\4\3\4"+
		"\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\3\34\6\34\u00d2\n\34\r\34\16\34\u00d3\3\35\3\35\3\36\3\36\3"+
		"\36\6\36\u00db\n\36\r\36\16\36\u00dc\3\37\3\37\3 \3 \6 \u00e3\n \r \16"+
		" \u00e4\3!\3!\3\"\3\"\3\"\7\"\u00ec\n\"\f\"\16\"\u00ef\13\"\5\"\u00f1"+
		"\n\"\3#\5#\u00f4\n#\3#\3#\3$\3$\3$\3$\5$\u00fc\n$\3$\3$\2\2%\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\2\67\349\2;\35=\2?\36A\2"+
		"C\37E G!\3\2\t\4\2\f\f\17\17\4\2\13\13\"\"\4\2DDdd\5\2\62;CHch\4\2ZZz"+
		"z\4\2opww\4\2CLcl\u0104\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\67\3\2\2\2\2;\3\2"+
		"\2\2\2?\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\3I\3\2\2\2\5W\3\2\2\2"+
		"\7^\3\2\2\2\td\3\2\2\2\13g\3\2\2\2\ri\3\2\2\2\17n\3\2\2\2\21t\3\2\2\2"+
		"\23z\3\2\2\2\25\u0082\3\2\2\2\27\u0087\3\2\2\2\31\u008d\3\2\2\2\33\u0092"+
		"\3\2\2\2\35\u0097\3\2\2\2\37\u009c\3\2\2\2!\u00a5\3\2\2\2#\u00a8\3\2\2"+
		"\2%\u00ae\3\2\2\2\'\u00ba\3\2\2\2)\u00c0\3\2\2\2+\u00c2\3\2\2\2-\u00c4"+
		"\3\2\2\2/\u00c6\3\2\2\2\61\u00c8\3\2\2\2\63\u00ca\3\2\2\2\65\u00cc\3\2"+
		"\2\2\67\u00ce\3\2\2\29\u00d5\3\2\2\2;\u00d7\3\2\2\2=\u00de\3\2\2\2?\u00e0"+
		"\3\2\2\2A\u00e6\3\2\2\2C\u00f0\3\2\2\2E\u00f3\3\2\2\2G\u00fb\3\2\2\2I"+
		"J\7\61\2\2JK\7\61\2\2KO\3\2\2\2LN\n\2\2\2ML\3\2\2\2NQ\3\2\2\2OM\3\2\2"+
		"\2OP\3\2\2\2PR\3\2\2\2QO\3\2\2\2RS\t\2\2\2ST\3\2\2\2TU\b\2\2\2U\4\3\2"+
		"\2\2VX\7\17\2\2WV\3\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\7\f\2\2Z[\3\2\2\2[\\\b"+
		"\3\2\2\\\6\3\2\2\2]_\t\3\2\2^]\3\2\2\2_`\3\2\2\2`^\3\2\2\2`a\3\2\2\2a"+
		"b\3\2\2\2bc\b\4\2\2c\b\3\2\2\2de\7<\2\2ef\7?\2\2f\n\3\2\2\2gh\7?\2\2h"+
		"\f\3\2\2\2ij\7o\2\2jk\7c\2\2kl\7u\2\2lm\7m\2\2m\16\3\2\2\2no\7x\2\2op"+
		"\7c\2\2pq\7n\2\2qr\7w\2\2rs\7g\2\2s\20\3\2\2\2tu\7u\2\2uv\7v\2\2vw\7c"+
		"\2\2wx\7i\2\2xy\7g\2\2y\22\3\2\2\2z{\7e\2\2{|\7c\2\2|}\7r\2\2}~\7v\2\2"+
		"~\177\7w\2\2\177\u0080\7t\2\2\u0080\u0081\7g\2\2\u0081\24\3\2\2\2\u0082"+
		"\u0083\7y\2\2\u0083\u0084\7j\2\2\u0084\u0085\7g\2\2\u0085\u0086\7p\2\2"+
		"\u0086\26\3\2\2\2\u0087\u0088\7u\2\2\u0088\u0089\7v\2\2\u0089\u008a\7"+
		"c\2\2\u008a\u008b\7t\2\2\u008b\u008c\7v\2\2\u008c\30\3\2\2\2\u008d\u008e"+
		"\7u\2\2\u008e\u008f\7v\2\2\u008f\u0090\7q\2\2\u0090\u0091\7r\2\2\u0091"+
		"\32\3\2\2\2\u0092\u0093\7i\2\2\u0093\u0094\7q\2\2\u0094\u0095\7v\2\2\u0095"+
		"\u0096\7q\2\2\u0096\34\3\2\2\2\u0097\u0098\7p\2\2\u0098\u0099\7g\2\2\u0099"+
		"\u009a\7z\2\2\u009a\u009b\7v\2\2\u009b\36\3\2\2\2\u009c\u009d\7c\2\2\u009d"+
		"\u009e\7e\2\2\u009e\u009f\7v\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1\7x\2\2"+
		"\u00a1\u00a2\7c\2\2\u00a2\u00a3\7v\2\2\u00a3\u00a4\7g\2\2\u00a4 \3\2\2"+
		"\2\u00a5\u00a6\7q\2\2\u00a6\u00a7\7p\2\2\u00a7\"\3\2\2\2\u00a8\u00a9\7"+
		"n\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7x\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad"+
		"\7n\2\2\u00ad$\3\2\2\2\u00ae\u00af\7k\2\2\u00af\u00b0\7o\2\2\u00b0\u00b1"+
		"\7o\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3\7f\2\2\u00b3\u00b4\7k\2\2\u00b4"+
		"\u00b5\7c\2\2\u00b5\u00b6\7v\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7n\2\2"+
		"\u00b8\u00b9\7{\2\2\u00b9&\3\2\2\2\u00ba\u00bb\7f\2\2\u00bb\u00bc\7g\2"+
		"\2\u00bc\u00bd\7n\2\2\u00bd\u00be\7c\2\2\u00be\u00bf\7{\2\2\u00bf(\3\2"+
		"\2\2\u00c0\u00c1\7\u0080\2\2\u00c1*\3\2\2\2\u00c2\u00c3\7`\2\2\u00c3,"+
		"\3\2\2\2\u00c4\u00c5\7%\2\2\u00c5.\3\2\2\2\u00c6\u00c7\7.\2\2\u00c7\60"+
		"\3\2\2\2\u00c8\u00c9\7<\2\2\u00c9\62\3\2\2\2\u00ca\u00cb\7B\2\2\u00cb"+
		"\64\3\2\2\2\u00cc\u00cd\4\62\63\2\u00cd\66\3\2\2\2\u00ce\u00cf\7\62\2"+
		"\2\u00cf\u00d1\t\4\2\2\u00d0\u00d2\5\65\33\2\u00d1\u00d0\3\2\2\2\u00d2"+
		"\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d48\3\2\2\2"+
		"\u00d5\u00d6\t\5\2\2\u00d6:\3\2\2\2\u00d7\u00d8\7\62\2\2\u00d8\u00da\t"+
		"\6\2\2\u00d9\u00db\59\35\2\u00da\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd<\3\2\2\2\u00de\u00df\4\629\2"+
		"\u00df>\3\2\2\2\u00e0\u00e2\7\62\2\2\u00e1\u00e3\5=\37\2\u00e2\u00e1\3"+
		"\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"@\3\2\2\2\u00e6\u00e7\4\62;\2\u00e7B\3\2\2\2\u00e8\u00f1\7\62\2\2\u00e9"+
		"\u00ed\4\63;\2\u00ea\u00ec\5A!\2\u00eb\u00ea\3\2\2\2\u00ec\u00ef\3\2\2"+
		"\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed"+
		"\3\2\2\2\u00f0\u00e8\3\2\2\2\u00f0\u00e9\3\2\2\2\u00f1D\3\2\2\2\u00f2"+
		"\u00f4\t\7\2\2\u00f3\u00f2\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\3\2"+
		"\2\2\u00f5\u00f6\7u\2\2\u00f6F\3\2\2\2\u00f7\u00f8\7v\2\2\u00f8\u00f9"+
		"\7g\2\2\u00f9\u00fa\7t\2\2\u00fa\u00fc\7o\2\2\u00fb\u00f7\3\2\2\2\u00fb"+
		"\u00fc\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe\t\b\2\2\u00feH\3\2\2\2"+
		"\r\2OW`\u00d3\u00dc\u00e4\u00ed\u00f0\u00f3\u00fb\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}