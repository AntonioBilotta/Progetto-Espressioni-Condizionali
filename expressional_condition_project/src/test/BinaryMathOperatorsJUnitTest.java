package test;

import interpreter.Context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import composite.MathOperator;
import composite.Num;
import composite.RemOperator;
import composite.UnevaluableExpressionException;

import flyweight.MathFlyweightFactory;

public class BinaryMathOperatorsJUnitTest {
	
	MathOperator operator;
	Num num1;
	Num num2;
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
		this.num2 = this.factory.createNum(2);
		
		this.operator = new RemOperator();
	}
	
	@After
	public void runAfterEachTest() {
		
	}
	
	@Test
	public void setLeftTest() {
		this.operator.setLeft(this.num1);
		this.operator.getLeft().equals(this.num1);
	}
	
	@Test
	public void setRightTest() {
		this.operator.setRight(this.num1);
		this.operator.getRight().equals(num1);
	}
	
	@Test
	public void evaluatePositiveTest() {
		Context context = new Context();
		this.operator.setLeft(this.num1);
		this.operator.setRight(this.num2);
		assert this.operator.evaluate(context) == 1;
		System.out.println(this.operator + "= " + this.operator.evaluate(context));
	}
	
	@Test(expected = UnevaluableExpressionException.class)
	public void evaluateNegativeTest() {
		Context context = new Context();
		this.operator.evaluate(context);
	}
	
}
