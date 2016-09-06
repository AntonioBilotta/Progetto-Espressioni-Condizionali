package test;

import static org.junit.Assert.assertEquals;
import interpreter.Context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import composite.Id;

/**
 * 
 * @author Antonio Bilotta
 * Test BlackBox della classe Context;
 */
public class ContextJUnitTest {
	
	private Context context;
	
	@BeforeClass
	public static void runOnceBeforeAllTests() {
		
	}
	
	@AfterClass
	public static void runOnceAfterAllTests() {
		
	}
	
	@Before
	public void runBeforeEachTest() {
		this.context = new Context();
	}
	
	@After
	public void runAfterEachTest() {
		
	}
	
	/**
	 * 
	 * Test positivo del metodo assign <br>
	 * 2 classi di test: 
	 * 		1) inserimento di una coppia chiave-valore la cui chiave è inesistente <br>
	 *      2) inserimento di una coppia chiave-valore la cui chiave è stata gia precedentemente inserita <br>
	 */
	@Test
	public void assign() {
		Id id1 = new Id();
		id1.setName("a");
		Id id2 = new Id();
		id2.setName("b");
		this.context.assign("a", 1);
		this.context.assign("b", 5);
		this.context.assign("b", 3);
		assertEquals(this.context.lookup(id1), 1);
		assertEquals(this.context.lookup(id2), 3);
	}
	
	/**
	 * 
	 * Test positivo del metodo lookup <br>
	 * 2 classi di test:
	 * 		1) ricerca di un valore la cui chiave è presente nel contesto <br>
	 * 		2) ricerca di un valore la cui chiave non è presente nel contesto <br>
	 */
	@Test
	public void lookup() {
		Id id1 = new Id();
		id1.setName("a");
		Id id2 = new Id();
		id2.setName("b");
		this.context.assign("a", 1);
		assertEquals(this.context.lookup(id1), 1);
		assertEquals(this.context.lookup(id2), 0);
	}
	
}
