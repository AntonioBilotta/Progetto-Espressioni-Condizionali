package test;

import flyweight.MathFlyweightFactory;
import interpreter.Context;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import composite.MathExpression;

public class IdJUnitTest {
	
	MathExpression component;
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
		this.component = this.factory.createId("a");
		this.component.setLeft(this.factory.createAddOperator());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setRightNegativeTest() {
		this.component = this.factory.createId("a");
		this.component.setRight(this.factory.createAddOperator());
	}
	
	@Test
	public void evaluatePositiveTest() {
		this.component = this.factory.createId("a");
		Context context = new Context();
		context.assign("a", 11);
		assert this.component.evaluate(context) == 11;
		System.out.println(this.component);
	}

}
