// Generated from AdvTdl.g4 by ANTLR 4.4

/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;


import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AdvTdlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

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
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'", "'*'"
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
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2,\u0148\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3g\n\3\f\3\16\3j"+
		"\13\3\3\3\3\3\3\3\3\3\3\4\5\4q\n\4\3\4\3\4\3\4\3\4\3\5\6\5x\n\5\r\5\16"+
		"\5y\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3"+
		"\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3"+
		"\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3$\6"+
		"$\u0104\n$\r$\16$\u0105\3%\3%\3&\3&\3&\6&\u010d\n&\r&\16&\u010e\3\'\3"+
		"\'\3(\3(\6(\u0115\n(\r(\16(\u0116\3)\3)\3*\3*\3*\7*\u011e\n*\f*\16*\u0121"+
		"\13*\5*\u0123\n*\3+\5+\u0126\n+\3+\3+\3,\3,\3,\3,\5,\u012e\n,\3,\3,\3"+
		"-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\2"+
		"\2\60\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E\2G$I\2K%M\2O&Q\2S\'U(W)Y*[+],\3\2\t\4\2\f\f\17\17\4\2\13"+
		"\13\"\"\4\2DDdd\5\2\62;CHch\4\2ZZzz\4\2opww\4\2CLcl\u014d\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2G\3\2\2\2\2K\3\2\2\2\2O\3\2\2\2"+
		"\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\3_"+
		"\3\2\2\2\5b\3\2\2\2\7p\3\2\2\2\tw\3\2\2\2\13}\3\2\2\2\r\u0080\3\2\2\2"+
		"\17\u0082\3\2\2\2\21\u0087\3\2\2\2\23\u008d\3\2\2\2\25\u0094\3\2\2\2\27"+
		"\u009c\3\2\2\2\31\u00a1\3\2\2\2\33\u00a9\3\2\2\2\35\u00af\3\2\2\2\37\u00b7"+
		"\3\2\2\2!\u00bb\3\2\2\2#\u00bf\3\2\2\2%\u00c4\3\2\2\2\'\u00cb\3\2\2\2"+
		")\u00d1\3\2\2\2+\u00d6\3\2\2\2-\u00dc\3\2\2\2/\u00e1\3\2\2\2\61\u00e6"+
		"\3\2\2\2\63\u00eb\3\2\2\2\65\u00ee\3\2\2\2\67\u00f0\3\2\2\29\u00f2\3\2"+
		"\2\2;\u00f4\3\2\2\2=\u00f6\3\2\2\2?\u00f8\3\2\2\2A\u00fa\3\2\2\2C\u00fc"+
		"\3\2\2\2E\u00fe\3\2\2\2G\u0100\3\2\2\2I\u0107\3\2\2\2K\u0109\3\2\2\2M"+
		"\u0110\3\2\2\2O\u0112\3\2\2\2Q\u0118\3\2\2\2S\u0122\3\2\2\2U\u0125\3\2"+
		"\2\2W\u012d\3\2\2\2Y\u0131\3\2\2\2[\u0139\3\2\2\2]\u0141\3\2\2\2_`\7\60"+
		"\2\2`a\7\60\2\2a\4\3\2\2\2bc\7\61\2\2cd\7\61\2\2dh\3\2\2\2eg\n\2\2\2f"+
		"e\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2ik\3\2\2\2jh\3\2\2\2kl\t\2\2\2"+
		"lm\3\2\2\2mn\b\3\2\2n\6\3\2\2\2oq\7\17\2\2po\3\2\2\2pq\3\2\2\2qr\3\2\2"+
		"\2rs\7\f\2\2st\3\2\2\2tu\b\4\2\2u\b\3\2\2\2vx\t\3\2\2wv\3\2\2\2xy\3\2"+
		"\2\2yw\3\2\2\2yz\3\2\2\2z{\3\2\2\2{|\b\5\2\2|\n\3\2\2\2}~\7<\2\2~\177"+
		"\7?\2\2\177\f\3\2\2\2\u0080\u0081\7?\2\2\u0081\16\3\2\2\2\u0082\u0083"+
		"\7o\2\2\u0083\u0084\7c\2\2\u0084\u0085\7u\2\2\u0085\u0086\7m\2\2\u0086"+
		"\20\3\2\2\2\u0087\u0088\7x\2\2\u0088\u0089\7c\2\2\u0089\u008a\7n\2\2\u008a"+
		"\u008b\7w\2\2\u008b\u008c\7g\2\2\u008c\22\3\2\2\2\u008d\u008e\7t\2\2\u008e"+
		"\u008f\7k\2\2\u008f\u0090\7u\2\2\u0090\u0091\7k\2\2\u0091\u0092\7p\2\2"+
		"\u0092\u0093\7i\2\2\u0093\24\3\2\2\2\u0094\u0095\7h\2\2\u0095\u0096\7"+
		"c\2\2\u0096\u0097\7n\2\2\u0097\u0098\7n\2\2\u0098\u0099\7k\2\2\u0099\u009a"+
		"\7p\2\2\u009a\u009b\7i\2\2\u009b\26\3\2\2\2\u009c\u009d\7d\2\2\u009d\u009e"+
		"\7q\2\2\u009e\u009f\7v\2\2\u009f\u00a0\7j\2\2\u00a0\30\3\2\2\2\u00a1\u00a2"+
		"\7p\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7k\2\2\u00a4\u00a5\7v\2\2\u00a5"+
		"\u00a6\7j\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7t\2\2\u00a8\32\3\2\2\2\u00a9"+
		"\u00aa\7u\2\2\u00aa\u00ab\7v\2\2\u00ab\u00ac\7c\2\2\u00ac\u00ad\7i\2\2"+
		"\u00ad\u00ae\7g\2\2\u00ae\34\3\2\2\2\u00af\u00b0\7e\2\2\u00b0\u00b1\7"+
		"c\2\2\u00b1\u00b2\7r\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7w\2\2\u00b4\u00b5"+
		"\7t\2\2\u00b5\u00b6\7g\2\2\u00b6\36\3\2\2\2\u00b7\u00b8\7p\2\2\u00b8\u00b9"+
		"\7q\2\2\u00b9\u00ba\7r\2\2\u00ba \3\2\2\2\u00bb\u00bc\7c\2\2\u00bc\u00bd"+
		"\7p\2\2\u00bd\u00be\7{\2\2\u00be\"\3\2\2\2\u00bf\u00c0\7y\2\2\u00c0\u00c1"+
		"\7j\2\2\u00c1\u00c2\7g\2\2\u00c2\u00c3\7p\2\2\u00c3$\3\2\2\2\u00c4\u00c5"+
		"\7q\2\2\u00c5\u00c6\7e\2\2\u00c6\u00c7\7e\2\2\u00c7\u00c8\7w\2\2\u00c8"+
		"\u00c9\7t\2\2\u00c9\u00ca\7u\2\2\u00ca&\3\2\2\2\u00cb\u00cc\7u\2\2\u00cc"+
		"\u00cd\7v\2\2\u00cd\u00ce\7c\2\2\u00ce\u00cf\7t\2\2\u00cf\u00d0\7v\2\2"+
		"\u00d0(\3\2\2\2\u00d1\u00d2\7u\2\2\u00d2\u00d3\7v\2\2\u00d3\u00d4\7q\2"+
		"\2\u00d4\u00d5\7r\2\2\u00d5*\3\2\2\2\u00d6\u00d7\7e\2\2\u00d7\u00d8\7"+
		"n\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7c\2\2\u00da\u00db\7t\2\2\u00db,"+
		"\3\2\2\2\u00dc\u00dd\7i\2\2\u00dd\u00de\7q\2\2\u00de\u00df\7v\2\2\u00df"+
		"\u00e0\7q\2\2\u00e0.\3\2\2\2\u00e1\u00e2\7p\2\2\u00e2\u00e3\7g\2\2\u00e3"+
		"\u00e4\7z\2\2\u00e4\u00e5\7v\2\2\u00e5\60\3\2\2\2\u00e6\u00e7\7g\2\2\u00e7"+
		"\u00e8\7n\2\2\u00e8\u00e9\7u\2\2\u00e9\u00ea\7g\2\2\u00ea\62\3\2\2\2\u00eb"+
		"\u00ec\7q\2\2\u00ec\u00ed\7p\2\2\u00ed\64\3\2\2\2\u00ee\u00ef\7.\2\2\u00ef"+
		"\66\3\2\2\2\u00f0\u00f1\7<\2\2\u00f18\3\2\2\2\u00f2\u00f3\7\u0080\2\2"+
		"\u00f3:\3\2\2\2\u00f4\u00f5\7`\2\2\u00f5<\3\2\2\2\u00f6\u00f7\7(\2\2\u00f7"+
		">\3\2\2\2\u00f8\u00f9\7~\2\2\u00f9@\3\2\2\2\u00fa\u00fb\7*\2\2\u00fbB"+
		"\3\2\2\2\u00fc\u00fd\7+\2\2\u00fdD\3\2\2\2\u00fe\u00ff\4\62\63\2\u00ff"+
		"F\3\2\2\2\u0100\u0101\7\62\2\2\u0101\u0103\t\4\2\2\u0102\u0104\5E#\2\u0103"+
		"\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2"+
		"\2\2\u0106H\3\2\2\2\u0107\u0108\t\5\2\2\u0108J\3\2\2\2\u0109\u010a\7\62"+
		"\2\2\u010a\u010c\t\6\2\2\u010b\u010d\5I%\2\u010c\u010b\3\2\2\2\u010d\u010e"+
		"\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010fL\3\2\2\2\u0110"+
		"\u0111\4\629\2\u0111N\3\2\2\2\u0112\u0114\7\62\2\2\u0113\u0115\5M\'\2"+
		"\u0114\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117"+
		"\3\2\2\2\u0117P\3\2\2\2\u0118\u0119\4\62;\2\u0119R\3\2\2\2\u011a\u0123"+
		"\7\62\2\2\u011b\u011f\4\63;\2\u011c\u011e\5Q)\2\u011d\u011c\3\2\2\2\u011e"+
		"\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0123\3\2"+
		"\2\2\u0121\u011f\3\2\2\2\u0122\u011a\3\2\2\2\u0122\u011b\3\2\2\2\u0123"+
		"T\3\2\2\2\u0124\u0126\t\7\2\2\u0125\u0124\3\2\2\2\u0125\u0126\3\2\2\2"+
		"\u0126\u0127\3\2\2\2\u0127\u0128\7u\2\2\u0128V\3\2\2\2\u0129\u012a\7v"+
		"\2\2\u012a\u012b\7g\2\2\u012b\u012c\7t\2\2\u012c\u012e\7o\2\2\u012d\u0129"+
		"\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\t\b\2\2\u0130"+
		"X\3\2\2\2\u0131\u0132\7v\2\2\u0132\u0133\7k\2\2\u0133\u0134\7o\2\2\u0134"+
		"\u0135\7g\2\2\u0135\u0136\7t\2\2\u0136\u0137\3\2\2\2\u0137\u0138\4\63"+
		"\64\2\u0138Z\3\2\2\2\u0139\u013a\7t\2\2\u013a\u013b\7c\2\2\u013b\u013c"+
		"\7p\2\2\u013c\u013d\7i\2\2\u013d\u013e\7g\2\2\u013e\u013f\3\2\2\2\u013f"+
		"\u0140\4\63\64\2\u0140\\\3\2\2\2\u0141\u0142\7g\2\2\u0142\u0143\7f\2\2"+
		"\u0143\u0144\7i\2\2\u0144\u0145\7g\2\2\u0145\u0146\3\2\2\2\u0146\u0147"+
		"\4\63\64\2\u0147^\3\2\2\2\r\2hpy\u0105\u010e\u0116\u011f\u0122\u0125\u012d"+
		"\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}