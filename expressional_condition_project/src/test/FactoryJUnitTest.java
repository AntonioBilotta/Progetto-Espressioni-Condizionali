package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import composite.Id;
import composite.Num;
import flyweight.MathFlyweightFactory;

public class FactoryJUnitTest {
	
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
	
	@After
	public void runAfterEachTest() {
		
	}
	
	/**
	 * Test del metodo createId
	 * 2 classi di test
	 * 		1) richiesta di un nuovo componente Id <br>
	 * 		2) richiesta di un componente Id già creato <br>
	 */
	@Test
	public void createId() {
		String key = "a";
		Id id = this.factory.createId(key);
		assert id == this.factory.createId(key);
		System.out.println(id);
	}
	
	/**
	 * Test del metodo createNum
	 * 2 classi di test
	 * 		1) richiesta di un nuovo componente Num <br>
	 * 		2) richiesta di un componente Num già creato <br>
	 */
	@Test
	public void createNum() {
		int key = 1;
		Num num = this.factory.createNum(key);
		assert num == this.factory.createNum(key);
		System.out.println(num);
	}
	
}
