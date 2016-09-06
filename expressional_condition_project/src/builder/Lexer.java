package builder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;

/**
 * 
 * @author Antonio Bilotta <br>
 *
 */
public abstract class Lexer {
	
	public static enum Symbol {
		OR, AND, NOT, EQ, LT, GT, OPAR, CPAR, PLUS, MINUS, 
		MUL, DIV, REM, POW, ID, NUM, OBRACK, CBRACK, EOF, INVALID
	}
	
	protected StreamTokenizer input;
	protected Symbol symbol;
	
	public Lexer(InputStream in) {
		Reader r = new BufferedReader(new InputStreamReader(in));
		this.input = new StreamTokenizer(r);
	}
	
	/**
	 * 
	 * @return Symbol il prossimo simbolo nello stream in input <br>
	 */
	public abstract Symbol nextSymbol();
	
	/**
	 * 
	 * @return String il valore dell'ultima "word" letta nello stream in input, <br> 
	 * se non è stato ancora invocato il metodo nextSymbol(), ritorna null <br>
	 */
	public String getString() {
		return this.input.sval;
	}

}
