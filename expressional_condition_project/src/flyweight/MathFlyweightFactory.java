package flyweight;

import java.util.HashMap;
import java.util.Map;

import composite.AddOperator;
import composite.DivOperator;
import composite.Id;
import composite.NegOperator;
import composite.SubOperator;
import composite.MulOperator;
import composite.Num;
import composite.PowOperator;
import composite.RemOperator;


/**
 * 
 * @author Antonio Bilotta <br>
 *
 */
public class MathFlyweightFactory {
	
	private Map<String, Id> idComponents;
	private Map<Integer, Num> numComponents;
	
	public MathFlyweightFactory() {
		this.idComponents = new HashMap<String, Id>();
		this.numComponents = new HashMap<Integer, Num>();
	}
	
	/**
	 * 
	 * @param key
	 * @return Id
	 */
	public Id createId(String key) {
		Id id = this.idComponents.get(key);
		if(id == null) {
			id = new Id();
			id.setName(key);
			this.idComponents.put(key, id);
		}
		return id;
	}
	
	/**
	 * 
	 * @param key
	 * @return Num
	 */
	public Num createNum(int key) {
		Num num = this.numComponents.get(key);
		if(num == null) {
			num = new Num();
			num.setValue(key);
			this.numComponents.put(key, num);
		}
		return num;
	}
	
	public PowOperator createPowOperator() {
		return new PowOperator();
	}
	
	public MulOperator createMulOperator() {
		return new MulOperator();
	}
	
	public DivOperator createDivOperator() {
		return new DivOperator();
	}
	
	public RemOperator createRemOperator() {
		return new RemOperator();
	}
	
	public SubOperator createSubOperator() {
		return new SubOperator();
	}
	
	public AddOperator createAddOperator() {
		return new AddOperator();
	}
	
	public NegOperator createNegOperator() {
		return new NegOperator();
	}

}
