package test;

import static org.junit.Assert.assertEquals;

import iterator.ExpressionIterator;
import iterator.OperandIterator;
import iterator.PostfixBinaryOperatorIterator;
import iterator.PostfixUnaryOperatorIterator;
import iterator.SymmetricalBinaryOperatorIterator;
import iterator.SymmetricalUnaryOperatorIterator;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import composite.AddOperator;
import composite.Expression;
import composite.Id;
import composite.NegOperator;

/**
 * 
 * @author Antonio Bilotta <br>
 * Test per la gerarchia di classi di iteratori per i expressioni composite <br>
 * test guidato dalla logica formale delle specifiche <br>
 */
public class ExpressionIteratorJUnitTest {
	
	ExpressionIterator operandIterator;
	ExpressionIterator unaryOperatorIterator;
	ExpressionIterator binaryOperatorIterator;
	
	Id id;
	NegOperator neg;
	AddOperator add;
	
	@BeforeClass
	public static void runOnceBeforeAllTests() {
		
	}
	
	@AfterClass
	public static void runOnceAfterAllTests() {
		
	}
	
	@Before
	public void runBeforeEachTest() {
		this.id = new Id();
		id.setName("a");
		
		this.neg = new NegOperator();
		this.neg.setLeft(this.id);
		
		this.add = new AddOperator();
		this.add.setLeft(this.id);
		this.add.setRight(this.neg);
	}
	
	@After
	public void runAfterEachTest() {
		this.operandIterator = null;
		this.unaryOperatorIterator = null;
		this.binaryOperatorIterator = null;
		this.id = null;
		this.neg = null;
		this.add = null;
	}
	
	/**
	 * Test positivo del metodo next per iteratore di operandi <br>
	 */
	@Test
	public void operandIterator_positive_next() {
		this.operandIterator = new OperandIterator(this.id);
		this.id.setIterator(this.operandIterator);
		
		assert this.operandIterator.hasNext();
		Expression next = this.operandIterator.next();
		assertEquals(next, id);
	}
	
	/**
	 * Test negativo del metodo next per iteratore di operandi <br>
	 */
	@Test(expected = NoSuchElementException.class)
	public void operandIterator_negative_next() {
		this.operandIterator = new OperandIterator(this.id);
		this.id.setIterator(this.operandIterator);
		
		while(this.operandIterator.hasNext()) {
			this.operandIterator.next();
		}
		assert ! this.operandIterator.hasNext();
		this.operandIterator.next();
	}
	
	/**
	 * Test positivo del metodo next per iteratori postfissi di operatori unari <br>
	 */
	@Test
	public void unaryPostfixOperatorIterator_positive_next() {
		this.operandIterator = new OperandIterator(this.id);
		this.id.setIterator(this.operandIterator);
		this.unaryOperatorIterator = new PostfixUnaryOperatorIterator(this.neg);
		this.neg.setIterator(this.unaryOperatorIterator);
		
		assert this.unaryOperatorIterator.hasNext();
		Expression next = this.unaryOperatorIterator.next();
		assertEquals(next, id);
		assert this.unaryOperatorIterator.hasNext();
		next = this.unaryOperatorIterator.next();
		assertEquals(next, neg);
	}
	
	/**
	 * Test negativo del metodo next per iteratori postfissi di operatori unari <br>
	 */
	@Test(expected = NoSuchElementException.class)
	public void unaryPostfixOperatorIterator_negative_next() {
		this.operandIterator = new OperandIterator(this.id);
		this.id.setIterator(this.operandIterator);
		this.unaryOperatorIterator = new PostfixUnaryOperatorIterator(this.neg);
		this.neg.setIterator(this.unaryOperatorIterator);
		
		while(this.unaryOperatorIterator.hasNext()) {
			this.unaryOperatorIterator.next();
		}
		assert ! this.unaryOperatorIterator.hasNext();
		this.unaryOperatorIterator.next();
	}
	
	/**
	 * Test positivo del metodo next per iteratori simmetrici di operatori unari <br>
	 */
	@Test
	public void unarySymmetricalOperatorIterator_positive_next() {
		this.operandIterator = new OperandIterator(this.id);
		this.id.setIterator(this.operandIterator);
		this.unaryOperatorIterator = new SymmetricalUnaryOperatorIterator(this.neg);
		this.neg.setIterator(this.unaryOperatorIterator);
		
		assert this.unaryOperatorIterator.hasNext();
		Expression next = this.unaryOperatorIterator.next();
		assertEquals(next, neg);
		assert this.unaryOperatorIterator.hasNext();
		next = this.unaryOperatorIterator.next();
		assertEquals(next, id);
	}
	
	/**
	 * Test negativo del metodo next per iteratori simmetrici di operatori unari <br>
	 */
	@Test(expected = NoSuchElementException.class)
	public void unarySymmetricalOperatorIterator_negative_next() {
		this.operandIterator = new OperandIterator(this.id);
		this.id.setIterator(this.operandIterator);
		this.unaryOperatorIterator = new SymmetricalUnaryOperatorIterator(this.neg);
		this.neg.setIterator(this.unaryOperatorIterator);
		
		while(this.unaryOperatorIterator.hasNext()) {
			this.unaryOperatorIterator.next();
		}
		assert ! this.unaryOperatorIterator.hasNext();
		this.unaryOperatorIterator.next();
	}
	
	/**
	 * Test positivo del metodo next per iteratori postfissi di operatori binari <br>
	 */
	@Test
	public void binaryPostfixOperatorIterator_positive_next() {
		this.operandIterator = new OperandIterator(this.id);
		this.id.setIterator(this.operandIterator);
		this.unaryOperatorIterator = new PostfixUnaryOperatorIterator(this.neg);
		this.neg.setIterator(this.unaryOperatorIterator);
		this.binaryOperatorIterator = new PostfixBinaryOperatorIterator(this.add);
		this.add.setIterator(this.binaryOperatorIterator);
		
		assert this.binaryOperatorIterator.hasNext();
		Expression next = this.binaryOperatorIterator.next();
		assertEquals(next, this.id);
		
		assert this.binaryOperatorIterator.hasNext();
		next = this.binaryOperatorIterator.next();
		assertEquals(next, this.id);
		
		assert this.binaryOperatorIterator.hasNext();
		next = this.binaryOperatorIterator.next();
		assertEquals(next, this.neg);
		
		assert this.binaryOperatorIterator.hasNext();
		next = this.binaryOperatorIterator.next();
		assertEquals(next, this.add);
	}
	
	/**
	 * Test negativo del metodo next per iteratori postfissi di operatori binari <br>
	 */
	@Test(expected = NoSuchElementException.class)
	public void binaryPostfixOperatorIterator_negative_next() {
		this.operandIterator = new OperandIterator(this.id);
		this.id.setIterator(this.operandIterator);
		this.unaryOperatorIterator = new PostfixUnaryOperatorIterator(this.neg);
		this.neg.setIterator(this.unaryOperatorIterator);
		this.binaryOperatorIterator = new PostfixBinaryOperatorIterator(this.add);
		this.add.setIterator(this.binaryOperatorIterator);
		
		while(this.binaryOperatorIterator.hasNext()) {
			this.binaryOperatorIterator.next();
		}
		assert ! this.binaryOperatorIterator.hasNext();
		this.binaryOperatorIterator.next();
	}
	
	/**
	 * Test positivo del metodo next per iteratori simmetrici di operatori binari <br>
	 */
	@Test
	public void binarySymmetricalOperatorIterator_positive_next() {
		this.operandIterator = new OperandIterator(this.id);
		this.id.setIterator(this.operandIterator);
		this.unaryOperatorIterator = new SymmetricalUnaryOperatorIterator(this.neg);
		this.neg.setIterator(this.unaryOperatorIterator);
		this.binaryOperatorIterator = new SymmetricalBinaryOperatorIterator(this.add);
		this.add.setIterator(this.binaryOperatorIterator);
		
		assert this.binaryOperatorIterator.hasNext();
		Expression next = this.binaryOperatorIterator.next();
		assertEquals(next, this.id);
		
		assert this.binaryOperatorIterator.hasNext();
		next = this.binaryOperatorIterator.next();
		assertEquals(next, this.add);
		
		assert this.binaryOperatorIterator.hasNext();
		next = this.binaryOperatorIterator.next();
		assertEquals(next, this.neg);
		
		assert this.binaryOperatorIterator.hasNext();
		next = this.binaryOperatorIterator.next();
		assertEquals(next, this.id);
	}
	
	/**
	 * Test negativo del metodo next per iteratori postfissi di operatori binari <br>
	 */
	@Test(expected = NoSuchElementException.class)
	public void binarySymmetricalOperatorIterator_negative_next() {
		this.operandIterator = new OperandIterator(this.id);
		this.id.setIterator(this.operandIterator);
		this.unaryOperatorIterator = new SymmetricalUnaryOperatorIterator(this.neg);
		this.neg.setIterator(this.unaryOperatorIterator);
		this.binaryOperatorIterator = new SymmetricalBinaryOperatorIterator(this.add);
		this.add.setIterator(this.binaryOperatorIterator);
		
		while(this.binaryOperatorIterator.hasNext()) {
			this.binaryOperatorIterator.next();
		}
		assert ! this.binaryOperatorIterator.hasNext();
		this.binaryOperatorIterator.next();
	}

}
