package test;

import interpreter.Context;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import visitor.InstallIteratorVisitor;

import abstract_factory.IteratorToolkit;
import abstract_factory.SymmetricalIteratorToolkit;
import bridge.PrinterTraverser;
import builder.ConditionalExpressionRDPB;
import composite.BooleanExpression;

public class ConditionalExpressionRDPBJUnitTest {
	
	private ConditionalExpressionRDPB builder;
	private Context context;
	private String expression;
	
	@BeforeClass
	public static void runOnceBeforeAllTests() {
		
	}
	
	@AfterClass
	public static void runOnceAfterAllTests() {
		
	}
	
	@Before
	public void runBeforeEachTest() {
		this.builder = new ConditionalExpressionRDPB();
		this.context = new Context();
		this.context.assign("a", 6);
		this.context.assign("b", 5);
		this.expression = "-a^2*[a+2]/3 > + 3*2%4/2+1-b && !a==5 AND (a==6 || b==6) OR 3 < b";
	}
	
	@After
	public void runAfterEachTest() {
		
	}
	
	/**
	 * Test del metodo build() <br>
	 * Test guidato dalla sintassi <br>
	 */
	@Test
	public void build() {
		BooleanExpression expression = this.builder.build(new ByteArrayInputStream(this.expression.getBytes()));
		IteratorToolkit toolkit = new SymmetricalIteratorToolkit();
		InstallIteratorVisitor visitor = new InstallIteratorVisitor(toolkit);
		visitor.install(expression);
		PrinterTraverser traverser = new PrinterTraverser();
		traverser.traverse(expression);
		System.out.println(traverser.getString());
		traverser.traverse(expression);
		System.out.println(traverser.getString());
	}
	
}
