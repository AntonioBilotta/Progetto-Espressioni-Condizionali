package builder;

import java.io.IOException;
import java.io.InputStream;
import java.io.StreamTokenizer;

public class ExpressionLexer extends Lexer {
	
	private static final String IDregex = "[A-Z|a-z][A-Z|a-z|0-9]*";
	private static final String NUMregex = "[0-9][0-9]*";
	
	public ExpressionLexer(InputStream in) {
		super(in);
		input.resetSyntax();
		input.eolIsSignificant(false);
		input.wordChars('a', 'z');
		input.wordChars('A', 'Z');
		input.wordChars('0', '9');
		input.whitespaceChars('\u0000', ' ');
		input.ordinaryChar('(');
		input.ordinaryChar(')');
		input.ordinaryChar('[');
		input.ordinaryChar(']');
	}

	@Override
	public Symbol nextSymbol() {
		try {
			switch(this.input.nextToken()) {
			case StreamTokenizer.TT_EOF :
				this.symbol = Symbol.EOF; break;
			case StreamTokenizer.TT_WORD :
				if(this.input.sval.equalsIgnoreCase("or")) 
					this.symbol = Symbol.OR; 
				else if(this.input.sval.equalsIgnoreCase("and"))
					this.symbol = Symbol.AND; 
				else if(this.input.sval.equalsIgnoreCase("not"))
					this.symbol = Symbol.NOT; 
				else if(this.input.sval.matches(IDregex))
					this.symbol = Symbol.ID; 
				else if(this.input.sval.matches(NUMregex)) 
					this.symbol = Symbol.NUM;
				else 
					this.symbol = Symbol.INVALID; 
				break;
			case '&':
				if(this.input.nextToken() == '&') {
					this.symbol = Symbol.AND;
					break;
				} else {
					this.symbol = Symbol.INVALID;
					break;
				}
			case '|':
				if(this.input.nextToken() == '|') {
					this.symbol = Symbol.OR;
					break;
				} else {
					this.symbol = Symbol.INVALID;
					break;
				}
			case '!':
				this.symbol = Symbol.NOT;
				break;
			case '(': 
				this.symbol = Symbol.OPAR;
				break;
			case ')':
				this.symbol = Symbol.CPAR;
				break;
			case '[':
				this.symbol = Symbol.OBRACK;
				break;
			case ']':
				this.symbol = Symbol.CBRACK;
				break;
			case '=':
				this.symbol = Symbol.EQ;
				break;
			case '<':
				this.symbol = Symbol.LT;
				break;
			case '>':
				this.symbol = Symbol.GT;
				break;
			case '+':
				this.symbol = Symbol.PLUS;
				break;
			case '-':
				this.symbol = Symbol.MINUS;
				break;
			case '*':
				this.symbol = Symbol.MUL;
				break;
			case '/':
				this.symbol = Symbol.DIV;
				break;
			case '%':
				this.symbol = Symbol.REM;
				break;
			case '^':
				this.symbol = Symbol.POW;
				break;
			default: 
				this.symbol = Symbol.INVALID;
			}	
		} catch(IOException ioe) {
			this.symbol = Symbol.EOF;
		}
		return this.symbol;
	}

}
