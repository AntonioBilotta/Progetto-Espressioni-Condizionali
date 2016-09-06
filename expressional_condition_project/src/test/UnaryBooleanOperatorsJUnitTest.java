package test;

import static org.junit.Assert.assertEquals;
import interpreter.Context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import composite.BooleanOperator;
import composite.NotOperator;
import composite.Num;
import composite.RelOperator;
import composite.UnevaluableExpressionException;

import flyweight.MathFlyweightFactory;

public class UnaryBooleanOperatorsJUnitTest {
	
	BooleanOperator operator;
	RelOperator op1;
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
		
		this.num1 = this.factory.createNum(11);
		this.num2 = this.factory.createNum(12);
		
		this.op1 = new RelOperator();
		this.op1.setLeft(num1);
		this.op1.setRight(num2);
		this.op1.setType(RelOperator.Type.EQ);
		
		this.operator = new NotOperator();
	}
	
	@After
	public void runAfterEachTest() {
		
	}
	
	@Test
	public void setLeftTest() {
		this.operator.setLeft(op1);
		this.operator.getLeft().equals(op1);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void setRightNegativeTest() {
		this.operator.setRight(op1);
	}
	
	@Test
	public void evaluatePositiveTest() {
		Context context = new Context();
		this.operator.setLeft(op1);
		assertEquals(this.operator.evaluate(context), true);
		System.out.println(this.operator + "= " + this.operator.evaluate(context));
	}
	
	@Test(expected = UnevaluableExpressionException.class)
	public void evaluateNegativeTest() {
		Context context = new Context();
		this.operator.evaluate(context);
	}
	
}
