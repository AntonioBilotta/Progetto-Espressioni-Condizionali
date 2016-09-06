package test;

import interpreter.Context;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import composite.MathExpression;
import composite.Num;
import composite.UnevaluableExpressionException;

import flyweight.MathFlyweightFactory;

public class NumJUnitTest {

	MathExpression expression;
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
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setLeftNegativeTest() {
		this.expression = this.factory.createNum(11);
		this.expression.setLeft(this.factory.createAddOperator());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setRightNegativeTest() {
		this.expression = this.factory.createNum(11);
		this.expression.setRight(this.factory.createAddOperator());
	}
	
	@Test
	public void evaluatePositiveTest() {
		this.expression = this.factory.createNum(11);
		Context context = new Context();
		assert this.expression.evaluate(context) == 11;
		System.out.println(this.expression);
	}
	
	@Test(expected = UnevaluableExpressionException.class)
	public void evaluateNegativeTest() {
		this.expression = new Num();
		Context context = new Context();
		this.expression.evaluate(context);
	}

}
