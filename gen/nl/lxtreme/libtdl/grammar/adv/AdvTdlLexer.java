// $ANTLR ANTLRVersion> AdvTdlLexer.java generatedTimestamp>

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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'^'", "'&'", "')'", "','", "':'", "'('", "'~'", "'|'", "'..'", "COMMENT", 
		"WS", "'define'", "'as'", "'='", "'mask'", "'value'", "'rising'", "'falling'", 
		"'both'", "'neither'", "'stage'", "'capture'", "'nop'", "'any'", "'when'", 
		"'occurs'", "'start'", "'stop'", "'clear'", "'goto'", "'next'", "'else'", 
		"BIN_LITERAL", "HEX_LITERAL", "OCT_LITERAL", "DEC_LITERAL", "TIME_UNIT", 
		"TERM_NAME", "TIMER_NAME", "RANGE_NAME", "EDGE_NAME"
	};
	public static final String[] ruleNames = {
		"T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", 
		"COMMENT", "WS", "DEFINE", "ASSIGN", "EQUALS_TO", "MASK", "VALUE", "RISING", 
		"FALLING", "BOTH", "NEITHER", "STAGE", "CAPTURE", "NOP", "ANY", "WHEN", 
		"OCCURS", "START", "STOP", "CLEAR", "GOTO", "NEXT", "ELSE", "BIN_DIGIT", 
		"BIN_LITERAL", "HEX_DIGIT", "HEX_LITERAL", "OCT_DIGIT", "OCT_LITERAL", 
		"DEC_DIGIT", "DEC_LITERAL", "TIME_UNIT", "TERM_NAME", "TIMER_NAME", "RANGE_NAME", 
		"EDGE_NAME"
	};


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
		case 9: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 10: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\1\2)\u0141\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2"+
		"\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16"+
		"\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25"+
		"\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34"+
		"\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2"+
		"%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\1\0\1\0\1\1\1\1\1\2\1"+
		"\2\1\3\1\3\1\4\1\4\1\5\1\5\1\6\1\6\1\7\1\7\1\b\1\b\1\b\1\t\1\t\1\t\1\t"+
		"\5\ts\b\t\n\t\f\tv\t\t\1\t\1\t\1\t\1\t\1\n\4\n}\b\n\13\n\f\n~\1\n\1\n"+
		"\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\f\1\f\1\f\1\r\1\r\1\16\1\16\1\16"+
		"\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\20\1\20"+
		"\1\20\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\22\1\22\1\22\1\22\1\22"+
		"\1\23\1\23\1\23\1\23\1\23\1\23\1\23\1\23\1\24\1\24\1\24\1\24\1\24\1\24"+
		"\1\25\1\25\1\25\1\25\1\25\1\25\1\25\1\25\1\26\1\26\1\26\1\26\1\27\1\27"+
		"\1\27\1\27\1\30\1\30\1\30\1\30\1\30\1\31\1\31\1\31\1\31\1\31\1\31\1\31"+
		"\1\32\1\32\1\32\1\32\1\32\1\32\1\33\1\33\1\33\1\33\1\33\1\34\1\34\1\34"+
		"\1\34\1\34\1\34\1\35\1\35\1\35\1\35\1\35\1\36\1\36\1\36\1\36\1\36\1\37"+
		"\1\37\1\37\1\37\1\37\1 \1 \1!\1!\1!\4!\u00fd\b!\13!\f!\u00fe\1\"\1\"\1"+
		"#\1#\1#\4#\u0106\b#\13#\f#\u0107\1$\1$\1%\1%\4%\u010e\b%\13%\f%\u010f"+
		"\1&\1&\1\'\1\'\1\'\5\'\u0117\b\'\n\'\f\'\u011a\t\'\3\'\u011c\b\'\1(\3"+
		"(\u011f\b(\1(\1(\1)\1)\1)\1)\3)\u0127\b)\1)\1)\1*\1*\1*\1*\1*\1*\1*\1"+
		"*\1+\1+\1+\1+\1+\1+\1+\1+\1,\1,\1,\1,\1,\1,\1,\0-\1\1\uffff\3\2\uffff"+
		"\5\3\uffff\7\4\uffff\t\5\uffff\13\6\uffff\r\7\uffff\17\b\uffff\21\t\uffff"+
		"\23\n\0\25\13\1\27\f\uffff\31\r\uffff\33\16\uffff\35\17\uffff\37\20\uffff"+
		"!\21\uffff#\22\uffff%\23\uffff\'\24\uffff)\25\uffff+\26\uffff-\27\uffff"+
		"/\30\uffff\61\31\uffff\63\32\uffff\65\33\uffff\67\34\uffff9\35\uffff;"+
		"\36\uffff=\37\uffff? \uffffA\0\uffffC!\uffffE\0\uffffG\"\uffffI\0\uffff"+
		"K#\uffffM\0\uffffO$\uffffQ%\uffffS&\uffffU\'\uffffW(\uffffY)\uffff\1\0"+
		"\b\2\n\n\r\r\2\n\n\r\r\3\t\n\r\r  \2BBbb\3\609AFaf\2XXxx\2mnuu\2AJaj\u0145"+
		"\0\1\1\0\0\0\0\3\1\0\0\0\0\5\1\0\0\0\0\7\1\0\0\0\0\t\1\0\0\0\0\13\1\0"+
		"\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25\1\0\0\0\0"+
		"\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0\0\0!\1\0"+
		"\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0\0-\1\0\0"+
		"\0\0/\1\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1\0\0\0\0\67\1\0\0\0\09\1"+
		"\0\0\0\0;\1\0\0\0\0=\1\0\0\0\0?\1\0\0\0\0C\1\0\0\0\0G\1\0\0\0\0K\1\0\0"+
		"\0\0O\1\0\0\0\0Q\1\0\0\0\0S\1\0\0\0\0U\1\0\0\0\0W\1\0\0\0\0Y\1\0\0\0\1"+
		"[\1\0\0\0\3]\1\0\0\0\5_\1\0\0\0\7a\1\0\0\0\tc\1\0\0\0\13e\1\0\0\0\rg\1"+
		"\0\0\0\17i\1\0\0\0\21k\1\0\0\0\23n\1\0\0\0\25|\1\0\0\0\27\u0082\1\0\0"+
		"\0\31\u0089\1\0\0\0\33\u008c\1\0\0\0\35\u008e\1\0\0\0\37\u0093\1\0\0\0"+
		"!\u0099\1\0\0\0#\u00a0\1\0\0\0%\u00a8\1\0\0\0\'\u00ad\1\0\0\0)\u00b5\1"+
		"\0\0\0+\u00bb\1\0\0\0-\u00c3\1\0\0\0/\u00c7\1\0\0\0\61\u00cb\1\0\0\0\63"+
		"\u00d0\1\0\0\0\65\u00d7\1\0\0\0\67\u00dd\1\0\0\09\u00e2\1\0\0\0;\u00e8"+
		"\1\0\0\0=\u00ed\1\0\0\0?\u00f2\1\0\0\0A\u00f7\1\0\0\0C\u00f9\1\0\0\0E"+
		"\u0100\1\0\0\0G\u0102\1\0\0\0I\u0109\1\0\0\0K\u010b\1\0\0\0M\u0111\1\0"+
		"\0\0O\u011b\1\0\0\0Q\u011e\1\0\0\0S\u0126\1\0\0\0U\u012a\1\0\0\0W\u0132"+
		"\1\0\0\0Y\u013a\1\0\0\0[\\\5^\0\0\\\2\1\0\0\0]^\5&\0\0^\4\1\0\0\0_`\5"+
		")\0\0`\6\1\0\0\0ab\5,\0\0b\b\1\0\0\0cd\5:\0\0d\n\1\0\0\0ef\5(\0\0f\f\1"+
		"\0\0\0gh\5~\0\0h\16\1\0\0\0ij\5|\0\0j\20\1\0\0\0kl\5.\0\0lm\5.\0\0m\22"+
		"\1\0\0\0no\5/\0\0op\5/\0\0pt\1\0\0\0qs\b\0\0\0rq\1\0\0\0sv\1\0\0\0tr\1"+
		"\0\0\0tu\1\0\0\0uw\1\0\0\0vt\1\0\0\0wx\7\1\0\0xy\1\0\0\0yz\6\t\0\0z\24"+
		"\1\0\0\0{}\7\2\0\0|{\1\0\0\0}~\1\0\0\0~|\1\0\0\0~\177\1\0\0\0\177\u0080"+
		"\1\0\0\0\u0080\u0081\6\n\1\0\u0081\26\1\0\0\0\u0082\u0083\5d\0\0\u0083"+
		"\u0084\5e\0\0\u0084\u0085\5f\0\0\u0085\u0086\5i\0\0\u0086\u0087\5n\0\0"+
		"\u0087\u0088\5e\0\0\u0088\30\1\0\0\0\u0089\u008a\5a\0\0\u008a\u008b\5"+
		"s\0\0\u008b\32\1\0\0\0\u008c\u008d\5=\0\0\u008d\34\1\0\0\0\u008e\u008f"+
		"\5m\0\0\u008f\u0090\5a\0\0\u0090\u0091\5s\0\0\u0091\u0092\5k\0\0\u0092"+
		"\36\1\0\0\0\u0093\u0094\5v\0\0\u0094\u0095\5a\0\0\u0095\u0096\5l\0\0\u0096"+
		"\u0097\5u\0\0\u0097\u0098\5e\0\0\u0098 \1\0\0\0\u0099\u009a\5r\0\0\u009a"+
		"\u009b\5i\0\0\u009b\u009c\5s\0\0\u009c\u009d\5i\0\0\u009d\u009e\5n\0\0"+
		"\u009e\u009f\5g\0\0\u009f\"\1\0\0\0\u00a0\u00a1\5f\0\0\u00a1\u00a2\5a"+
		"\0\0\u00a2\u00a3\5l\0\0\u00a3\u00a4\5l\0\0\u00a4\u00a5\5i\0\0\u00a5\u00a6"+
		"\5n\0\0\u00a6\u00a7\5g\0\0\u00a7$\1\0\0\0\u00a8\u00a9\5b\0\0\u00a9\u00aa"+
		"\5o\0\0\u00aa\u00ab\5t\0\0\u00ab\u00ac\5h\0\0\u00ac&\1\0\0\0\u00ad\u00ae"+
		"\5n\0\0\u00ae\u00af\5e\0\0\u00af\u00b0\5i\0\0\u00b0\u00b1\5t\0\0\u00b1"+
		"\u00b2\5h\0\0\u00b2\u00b3\5e\0\0\u00b3\u00b4\5r\0\0\u00b4(\1\0\0\0\u00b5"+
		"\u00b6\5s\0\0\u00b6\u00b7\5t\0\0\u00b7\u00b8\5a\0\0\u00b8\u00b9\5g\0\0"+
		"\u00b9\u00ba\5e\0\0\u00ba*\1\0\0\0\u00bb\u00bc\5c\0\0\u00bc\u00bd\5a\0"+
		"\0\u00bd\u00be\5p\0\0\u00be\u00bf\5t\0\0\u00bf\u00c0\5u\0\0\u00c0\u00c1"+
		"\5r\0\0\u00c1\u00c2\5e\0\0\u00c2,\1\0\0\0\u00c3\u00c4\5n\0\0\u00c4\u00c5"+
		"\5o\0\0\u00c5\u00c6\5p\0\0\u00c6.\1\0\0\0\u00c7\u00c8\5a\0\0\u00c8\u00c9"+
		"\5n\0\0\u00c9\u00ca\5y\0\0\u00ca\60\1\0\0\0\u00cb\u00cc\5w\0\0\u00cc\u00cd"+
		"\5h\0\0\u00cd\u00ce\5e\0\0\u00ce\u00cf\5n\0\0\u00cf\62\1\0\0\0\u00d0\u00d1"+
		"\5o\0\0\u00d1\u00d2\5c\0\0\u00d2\u00d3\5c\0\0\u00d3\u00d4\5u\0\0\u00d4"+
		"\u00d5\5r\0\0\u00d5\u00d6\5s\0\0\u00d6\64\1\0\0\0\u00d7\u00d8\5s\0\0\u00d8"+
		"\u00d9\5t\0\0\u00d9\u00da\5a\0\0\u00da\u00db\5r\0\0\u00db\u00dc\5t\0\0"+
		"\u00dc\66\1\0\0\0\u00dd\u00de\5s\0\0\u00de\u00df\5t\0\0\u00df\u00e0\5"+
		"o\0\0\u00e0\u00e1\5p\0\0\u00e18\1\0\0\0\u00e2\u00e3\5c\0\0\u00e3\u00e4"+
		"\5l\0\0\u00e4\u00e5\5e\0\0\u00e5\u00e6\5a\0\0\u00e6\u00e7\5r\0\0\u00e7"+
		":\1\0\0\0\u00e8\u00e9\5g\0\0\u00e9\u00ea\5o\0\0\u00ea\u00eb\5t\0\0\u00eb"+
		"\u00ec\5o\0\0\u00ec<\1\0\0\0\u00ed\u00ee\5n\0\0\u00ee\u00ef\5e\0\0\u00ef"+
		"\u00f0\5x\0\0\u00f0\u00f1\5t\0\0\u00f1>\1\0\0\0\u00f2\u00f3\5e\0\0\u00f3"+
		"\u00f4\5l\0\0\u00f4\u00f5\5s\0\0\u00f5\u00f6\5e\0\0\u00f6@\1\0\0\0\u00f7"+
		"\u00f8\2\60\61\0\u00f8B\1\0\0\0\u00f9\u00fa\5\60\0\0\u00fa\u00fc\7\3\0"+
		"\0\u00fb\u00fd\3A \0\u00fc\u00fb\1\0\0\0\u00fd\u00fe\1\0\0\0\u00fe\u00fc"+
		"\1\0\0\0\u00fe\u00ff\1\0\0\0\u00ffD\1\0\0\0\u0100\u0101\7\4\0\0\u0101"+
		"F\1\0\0\0\u0102\u0103\5\60\0\0\u0103\u0105\7\5\0\0\u0104\u0106\3E\"\0"+
		"\u0105\u0104\1\0\0\0\u0106\u0107\1\0\0\0\u0107\u0105\1\0\0\0\u0107\u0108"+
		"\1\0\0\0\u0108H\1\0\0\0\u0109\u010a\2\60\67\0\u010aJ\1\0\0\0\u010b\u010d"+
		"\5\60\0\0\u010c\u010e\3I$\0\u010d\u010c\1\0\0\0\u010e\u010f\1\0\0\0\u010f"+
		"\u010d\1\0\0\0\u010f\u0110\1\0\0\0\u0110L\1\0\0\0\u0111\u0112\2\609\0"+
		"\u0112N\1\0\0\0\u0113\u011c\5\60\0\0\u0114\u0118\2\619\0\u0115\u0117\3"+
		"M&\0\u0116\u0115\1\0\0\0\u0117\u011a\1\0\0\0\u0118\u0116\1\0\0\0\u0118"+
		"\u0119\1\0\0\0\u0119\u011c\1\0\0\0\u011a\u0118\1\0\0\0\u011b\u0113\1\0"+
		"\0\0\u011b\u0114\1\0\0\0\u011cP\1\0\0\0\u011d\u011f\7\6\0\0\u011e\u011d"+
		"\1\0\0\0\u011e\u011f\1\0\0\0\u011f\u0120\1\0\0\0\u0120\u0121\5s\0\0\u0121"+
		"R\1\0\0\0\u0122\u0123\5t\0\0\u0123\u0124\5e\0\0\u0124\u0125\5r\0\0\u0125"+
		"\u0127\5m\0\0\u0126\u0122\1\0\0\0\u0126\u0127\1\0\0\0\u0127\u0128\1\0"+
		"\0\0\u0128\u0129\7\7\0\0\u0129T\1\0\0\0\u012a\u012b\5t\0\0\u012b\u012c"+
		"\5i\0\0\u012c\u012d\5m\0\0\u012d\u012e\5e\0\0\u012e\u012f\5r\0\0\u012f"+
		"\u0130\1\0\0\0\u0130\u0131\2\61\62\0\u0131V\1\0\0\0\u0132\u0133\5r\0\0"+
		"\u0133\u0134\5a\0\0\u0134\u0135\5n\0\0\u0135\u0136\5g\0\0\u0136\u0137"+
		"\5e\0\0\u0137\u0138\1\0\0\0\u0138\u0139\2\61\62\0\u0139X\1\0\0\0\u013a"+
		"\u013b\5e\0\0\u013b\u013c\5d\0\0\u013c\u013d\5g\0\0\u013d\u013e\5e\0\0"+
		"\u013e\u013f\1\0\0\0\u013f\u0140\2\61\62\0\u0140Z\1\0\0\0\n\0t~\u00fe"+
		"\u0107\u010f\u0118\u011b\u011e\u0126";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}