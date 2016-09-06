package test;

import interpreter.Context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import composite.MathOperator;
import composite.NegOperator;
import composite.Num;
import composite.UnevaluableExpressionException;

import flyweight.MathFlyweightFactory;

public class UnaryMathOperatorsJUnitTest {
	
	MathOperator operator;
	Num num1;
	MathFlyweightFactory factory;
	
	@BeforeClass
	public static void runOnceBeforeAllTests() {
		
	}
	
	@AfterClass
	public static void runOnceAfterAllTests() {
		
	}
	
	@Before
	public void runBeforeEachTest() {
		this.factory = new MathFlyweightFactory();
		this.num1 = this.factory.createNum(3);
		this.operator = new NegOperator();
	}
	
	@After
	public void runAfterEachTest() {
		
	}
	
	@Test
	public void setLeftTest() {
		this.operator.setLeft(this.num1);
		this.operator.getLeft().equals(this.num1);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void setRightTest() {
		this.operator.setRight(this.num1);
	}
	
	@Test
	public void evaluatePositiveTest() {
		Context context = new Context();
		this.operator.setLeft(this.num1);
		assert this.operator.evaluate(context) == -3;
		System.out.println(this.operator + "= " + this.operator.evaluate(context));
	}
	
	@Test(expected = UnevaluableExpressionException.class)
	public void evaluateNegativeTest() {
		Context context = new Context();
		this.operator.evaluate(context);
	}

}
