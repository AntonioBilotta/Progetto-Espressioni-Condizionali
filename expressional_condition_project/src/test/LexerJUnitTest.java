package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import builder.ExpressionLexer;
import builder.Lexer;
import builder.Lexer.Symbol;

public class LexerJUnitTest {
	
	private Lexer lexer;
	
	@BeforeClass
	public static void runOnceBeforeAllTests() {
		
	}
	
	@AfterClass
	public static void runOnceAfterAllTests() {
		
	}
	
	@Before
	public void runBeforeEachTest() {
		
	}
	
	@After
	public void runAfterEachTest() {
		
	}
	
	/**
	 * Test positivo del metodo nextSymbol <br>
	 * 		test per ogni possibile simbolo ammissibile <br>
	 * 
	 */
	@Test
	public void nextSymbol() {
		String symbols = "OR or AND and NOT not aA0 Aa00 1 123 && || ! () [] = < > + - * / % ^";
		lexer = new ExpressionLexer(new ByteArrayInputStream(symbols.getBytes()));
		System.out.println(symbols);
		while(true) {
			Symbol s = lexer.nextSymbol();
			if(s == Symbol.EOF) break;
			System.out.print(s + " : ");
			System.out.println(lexer.getString());
		}
	}
	
	/**
	 * Test negativo del metodo nextSymbol <br>
	 * 		test per simboli non riconosciuti <br>
	 * 
	 */
	@Test
	public void nextSymbol_neg() {
		String symbols = "1 < 2 & a2 | 2>1 $ {}";
		lexer = new ExpressionLexer(new ByteArrayInputStream(symbols.getBytes()));
		while(true) {
			Symbol s = lexer.nextSymbol();
			if(s == Symbol.EOF) break;
			System.out.print(s + " : ");
			System.out.println(lexer.getString());
		}
	}
	
	/**
	 * Test del metodo getString <br>
	 * 2 classi di test <br>
	 * 		1) chiamata del metodo senza aver mai invocato nextSymbol() <br>
	 * 		2) chiamata del metodo dopo un'invocazione di nextSymbol() <br>
	 */
	@Test
	public void getString() {
		String symbols = "OR or AND and NOT not aA0 Aa00 1 123 && || ! () [] = < > + - * / % ^";
		lexer = new ExpressionLexer(new ByteArrayInputStream(symbols.getBytes()));
		assertEquals(lexer.getString(),null);
		while(true) {
			Symbol s = lexer.nextSymbol();
			if(s == Symbol.EOF) break;
			System.out.print(s + " : ");
			System.out.println(lexer.getString());
		}
	}

}
