// $ANTLR ANTLRVersion> BasicTdlLexer.java generatedTimestamp>

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
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, COMMENT=6, WS=7, DEFINE=8, ASSIGN=9, 
		EQUALS_TO=10, MASK=11, VALUE=12, STAGE=13, CAPTURE=14, WHEN=15, START=16, 
		STOP=17, GOTO=18, NEXT=19, ACTIVATE=20, ON=21, IMMEDIATELY=22, DELAY=23, 
		BIN_LITERAL=24, HEX_LITERAL=25, OCT_LITERAL=26, DEC_LITERAL=27, TIME_UNIT=28, 
		TERM_NAME=29;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'^'", "','", "':'", "'~'", "'#'", "COMMENT", "WS", "'define'", "'as'", 
		"'='", "'mask'", "'value'", "'stage'", "'capture'", "'when'", "'start'", 
		"'stop'", "'goto'", "'next'", "'activate'", "'on'", "'immediately'", "'delay'", 
		"BIN_LITERAL", "HEX_LITERAL", "OCT_LITERAL", "DEC_LITERAL", "TIME_UNIT", 
		"TERM_NAME"
	};
	public static final String[] ruleNames = {
		"T__4", "T__3", "T__2", "T__1", "T__0", "COMMENT", "WS", "DEFINE", "ASSIGN", 
		"EQUALS_TO", "MASK", "VALUE", "STAGE", "CAPTURE", "WHEN", "START", "STOP", 
		"GOTO", "NEXT", "ACTIVATE", "ON", "IMMEDIATELY", "DELAY", "BIN_DIGIT", 
		"BIN_LITERAL", "HEX_DIGIT", "HEX_LITERAL", "OCT_DIGIT", "OCT_LITERAL", 
		"DEC_DIGIT", "DEC_LITERAL", "TIME_UNIT", "TERM_NAME"
	};


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
		case 5: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 6: WS_action((RuleContext)_localctx, actionIndex); break;
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
		"\1\2\35\u00f1\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5"+
		"\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16"+
		"\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25"+
		"\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34"+
		"\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \1\0\1\0\1\1\1\1\1\2\1\2\1\3"+
		"\1\3\1\4\1\4\1\5\1\5\1\5\1\5\5\5R\b\5\n\5\f\5U\t\5\1\5\1\5\1\5\1\5\1\6"+
		"\4\6\\\b\6\13\6\f\6]\1\6\1\6\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\b\1\b\1\b\1"+
		"\t\1\t\1\n\1\n\1\n\1\n\1\n\1\13\1\13\1\13\1\13\1\13\1\13\1\f\1\f\1\f\1"+
		"\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\16\1\16\1\16\1\16\1\16\1"+
		"\17\1\17\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\20\1\21\1\21\1\21\1"+
		"\21\1\21\1\22\1\22\1\22\1\22\1\22\1\23\1\23\1\23\1\23\1\23\1\23\1\23\1"+
		"\23\1\23\1\24\1\24\1\24\1\25\1\25\1\25\1\25\1\25\1\25\1\25\1\25\1\25\1"+
		"\25\1\25\1\25\1\26\1\26\1\26\1\26\1\26\1\26\1\27\1\27\1\30\1\30\1\30\4"+
		"\30\u00c4\b\30\13\30\f\30\u00c5\1\31\1\31\1\32\1\32\1\32\4\32\u00cd\b"+
		"\32\13\32\f\32\u00ce\1\33\1\33\1\34\1\34\4\34\u00d5\b\34\13\34\f\34\u00d6"+
		"\1\35\1\35\1\36\1\36\1\36\5\36\u00de\b\36\n\36\f\36\u00e1\t\36\3\36\u00e3"+
		"\b\36\1\37\3\37\u00e6\b\37\1\37\1\37\1 \1 \1 \1 \3 \u00ee\b \1 \1 \0!"+
		"\1\1\uffff\3\2\uffff\5\3\uffff\7\4\uffff\t\5\uffff\13\6\0\r\7\1\17\b\uffff"+
		"\21\t\uffff\23\n\uffff\25\13\uffff\27\f\uffff\31\r\uffff\33\16\uffff\35"+
		"\17\uffff\37\20\uffff!\21\uffff#\22\uffff%\23\uffff\'\24\uffff)\25\uffff"+
		"+\26\uffff-\27\uffff/\0\uffff\61\30\uffff\63\0\uffff\65\31\uffff\67\0"+
		"\uffff9\32\uffff;\0\uffff=\33\uffff?\34\uffffA\35\uffff\1\0\b\2\n\n\r"+
		"\r\2\n\n\r\r\3\t\n\r\r  \2BBbb\3\609AFaf\2XXxx\2mnuu\2AJaj\u00f5\0\1\1"+
		"\0\0\0\0\3\1\0\0\0\0\5\1\0\0\0\0\7\1\0\0\0\0\t\1\0\0\0\0\13\1\0\0\0\0"+
		"\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25\1\0\0\0\0\27\1"+
		"\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0\0\0!\1\0\0\0"+
		"\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0\0-\1\0\0\0\0"+
		"\61\1\0\0\0\0\65\1\0\0\0\09\1\0\0\0\0=\1\0\0\0\0?\1\0\0\0\0A\1\0\0\0\1"+
		"C\1\0\0\0\3E\1\0\0\0\5G\1\0\0\0\7I\1\0\0\0\tK\1\0\0\0\13M\1\0\0\0\r[\1"+
		"\0\0\0\17a\1\0\0\0\21h\1\0\0\0\23k\1\0\0\0\25m\1\0\0\0\27r\1\0\0\0\31"+
		"x\1\0\0\0\33~\1\0\0\0\35\u0086\1\0\0\0\37\u008b\1\0\0\0!\u0091\1\0\0\0"+
		"#\u0096\1\0\0\0%\u009b\1\0\0\0\'\u00a0\1\0\0\0)\u00a9\1\0\0\0+\u00ac\1"+
		"\0\0\0-\u00b8\1\0\0\0/\u00be\1\0\0\0\61\u00c0\1\0\0\0\63\u00c7\1\0\0\0"+
		"\65\u00c9\1\0\0\0\67\u00d0\1\0\0\09\u00d2\1\0\0\0;\u00d8\1\0\0\0=\u00e2"+
		"\1\0\0\0?\u00e5\1\0\0\0A\u00ed\1\0\0\0CD\5^\0\0D\2\1\0\0\0EF\5,\0\0F\4"+
		"\1\0\0\0GH\5:\0\0H\6\1\0\0\0IJ\5~\0\0J\b\1\0\0\0KL\5#\0\0L\n\1\0\0\0M"+
		"N\5/\0\0NO\5/\0\0OS\1\0\0\0PR\b\0\0\0QP\1\0\0\0RU\1\0\0\0SQ\1\0\0\0ST"+
		"\1\0\0\0TV\1\0\0\0US\1\0\0\0VW\7\1\0\0WX\1\0\0\0XY\6\5\0\0Y\f\1\0\0\0"+
		"Z\\\7\2\0\0[Z\1\0\0\0\\]\1\0\0\0][\1\0\0\0]^\1\0\0\0^_\1\0\0\0_`\6\6\1"+
		"\0`\16\1\0\0\0ab\5d\0\0bc\5e\0\0cd\5f\0\0de\5i\0\0ef\5n\0\0fg\5e\0\0g"+
		"\20\1\0\0\0hi\5a\0\0ij\5s\0\0j\22\1\0\0\0kl\5=\0\0l\24\1\0\0\0mn\5m\0"+
		"\0no\5a\0\0op\5s\0\0pq\5k\0\0q\26\1\0\0\0rs\5v\0\0st\5a\0\0tu\5l\0\0u"+
		"v\5u\0\0vw\5e\0\0w\30\1\0\0\0xy\5s\0\0yz\5t\0\0z{\5a\0\0{|\5g\0\0|}\5"+
		"e\0\0}\32\1\0\0\0~\177\5c\0\0\177\u0080\5a\0\0\u0080\u0081\5p\0\0\u0081"+
		"\u0082\5t\0\0\u0082\u0083\5u\0\0\u0083\u0084\5r\0\0\u0084\u0085\5e\0\0"+
		"\u0085\34\1\0\0\0\u0086\u0087\5w\0\0\u0087\u0088\5h\0\0\u0088\u0089\5"+
		"e\0\0\u0089\u008a\5n\0\0\u008a\36\1\0\0\0\u008b\u008c\5s\0\0\u008c\u008d"+
		"\5t\0\0\u008d\u008e\5a\0\0\u008e\u008f\5r\0\0\u008f\u0090\5t\0\0\u0090"+
		" \1\0\0\0\u0091\u0092\5s\0\0\u0092\u0093\5t\0\0\u0093\u0094\5o\0\0\u0094"+
		"\u0095\5p\0\0\u0095\"\1\0\0\0\u0096\u0097\5g\0\0\u0097\u0098\5o\0\0\u0098"+
		"\u0099\5t\0\0\u0099\u009a\5o\0\0\u009a$\1\0\0\0\u009b\u009c\5n\0\0\u009c"+
		"\u009d\5e\0\0\u009d\u009e\5x\0\0\u009e\u009f\5t\0\0\u009f&\1\0\0\0\u00a0"+
		"\u00a1\5a\0\0\u00a1\u00a2\5c\0\0\u00a2\u00a3\5t\0\0\u00a3\u00a4\5i\0\0"+
		"\u00a4\u00a5\5v\0\0\u00a5\u00a6\5a\0\0\u00a6\u00a7\5t\0\0\u00a7\u00a8"+
		"\5e\0\0\u00a8(\1\0\0\0\u00a9\u00aa\5o\0\0\u00aa\u00ab\5n\0\0\u00ab*\1"+
		"\0\0\0\u00ac\u00ad\5i\0\0\u00ad\u00ae\5m\0\0\u00ae\u00af\5m\0\0\u00af"+
		"\u00b0\5e\0\0\u00b0\u00b1\5d\0\0\u00b1\u00b2\5i\0\0\u00b2\u00b3\5a\0\0"+
		"\u00b3\u00b4\5t\0\0\u00b4\u00b5\5e\0\0\u00b5\u00b6\5l\0\0\u00b6\u00b7"+
		"\5y\0\0\u00b7,\1\0\0\0\u00b8\u00b9\5d\0\0\u00b9\u00ba\5e\0\0\u00ba\u00bb"+
		"\5l\0\0\u00bb\u00bc\5a\0\0\u00bc\u00bd\5y\0\0\u00bd.\1\0\0\0\u00be\u00bf"+
		"\2\60\61\0\u00bf\60\1\0\0\0\u00c0\u00c1\5\60\0\0\u00c1\u00c3\7\3\0\0\u00c2"+
		"\u00c4\3/\27\0\u00c3\u00c2\1\0\0\0\u00c4\u00c5\1\0\0\0\u00c5\u00c3\1\0"+
		"\0\0\u00c5\u00c6\1\0\0\0\u00c6\62\1\0\0\0\u00c7\u00c8\7\4\0\0\u00c8\64"+
		"\1\0\0\0\u00c9\u00ca\5\60\0\0\u00ca\u00cc\7\5\0\0\u00cb\u00cd\3\63\31"+
		"\0\u00cc\u00cb\1\0\0\0\u00cd\u00ce\1\0\0\0\u00ce\u00cc\1\0\0\0\u00ce\u00cf"+
		"\1\0\0\0\u00cf\66\1\0\0\0\u00d0\u00d1\2\60\67\0\u00d18\1\0\0\0\u00d2\u00d4"+
		"\5\60\0\0\u00d3\u00d5\3\67\33\0\u00d4\u00d3\1\0\0\0\u00d5\u00d6\1\0\0"+
		"\0\u00d6\u00d4\1\0\0\0\u00d6\u00d7\1\0\0\0\u00d7:\1\0\0\0\u00d8\u00d9"+
		"\2\609\0\u00d9<\1\0\0\0\u00da\u00e3\5\60\0\0\u00db\u00df\2\619\0\u00dc"+
		"\u00de\3;\35\0\u00dd\u00dc\1\0\0\0\u00de\u00e1\1\0\0\0\u00df\u00dd\1\0"+
		"\0\0\u00df\u00e0\1\0\0\0\u00e0\u00e3\1\0\0\0\u00e1\u00df\1\0\0\0\u00e2"+
		"\u00da\1\0\0\0\u00e2\u00db\1\0\0\0\u00e3>\1\0\0\0\u00e4\u00e6\7\6\0\0"+
		"\u00e5\u00e4\1\0\0\0\u00e5\u00e6\1\0\0\0\u00e6\u00e7\1\0\0\0\u00e7\u00e8"+
		"\5s\0\0\u00e8@\1\0\0\0\u00e9\u00ea\5t\0\0\u00ea\u00eb\5e\0\0\u00eb\u00ec"+
		"\5r\0\0\u00ec\u00ee\5m\0\0\u00ed\u00e9\1\0\0\0\u00ed\u00ee\1\0\0\0\u00ee"+
		"\u00ef\1\0\0\0\u00ef\u00f0\7\7\0\0\u00f0B\1\0\0\0\n\0S]\u00c5\u00ce\u00d6"+
		"\u00df\u00e2\u00e5\u00ed";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}