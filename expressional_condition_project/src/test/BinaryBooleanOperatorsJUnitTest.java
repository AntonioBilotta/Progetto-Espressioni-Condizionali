package test;

import static org.junit.Assert.assertEquals;
import interpreter.Context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import composite.AndOperator;
import composite.BooleanOperator;
import composite.Num;
import composite.RelOperator;
import composite.UnevaluableExpressionException;

import flyweight.MathFlyweightFactory;

public class BinaryBooleanOperatorsJUnitTest {
	
	BooleanOperator operator;
	RelOperator op1;
	RelOperator op2;
	Num num1;
	Num num2;
	Num num3;
	Num num4;
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
		this.num3 = this.factory.createNum(12);
		this.num4 = this.factory.createNum(13);
		
		this.op1 = new RelOperator();
		this.op1.setLeft(num1);
		this.op1.setRight(num2);
		this.op1.setType(RelOperator.Type.EQ);
		
		this.op2 = new RelOperator();
		this.op2.setLeft(num3);
		this.op2.setRight(num4);
		this.op2.setType(RelOperator.Type.LT);
		
		this.operator = new AndOperator();
	}
	
	@After
	public void runAfterEachTest() {
		
	}
	
	@Test
	public void setLeftTest() {
		this.operator.setLeft(op1);
		this.operator.getLeft().equals(op1);
	}
	
	@Test
	public void setRightTest() {
		this.operator.setRight(op2);
		this.operator.getRight().equals(op2);
	}
	
	@Test
	public void evaluatePositiveTest() {
		Context context = new Context();
		this.operator.setLeft(op1);
		this.operator.setRight(op2);
		assertEquals(this.operator.evaluate(context), false);
		System.out.println(this.operator + "= " + this.operator.evaluate(context));
	}
	
	@Test(expected = UnevaluableExpressionException.class)
	public void evaluateNegativeTest() {
		Context context = new Context();
		this.operator.evaluate(context);
	}

}
