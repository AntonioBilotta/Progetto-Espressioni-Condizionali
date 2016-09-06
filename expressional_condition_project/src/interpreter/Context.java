package interpreter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import composite.Id;

/**
 * 
 * @author Antonio Bilotta <br>
 * Definisce gli oggetti contesto in cui le espressioni condizionali devono essere valutate <br>
 */
public class Context implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> idValues;
	
	public Context() {
		this.idValues = new HashMap<String, Integer>();
	}
	
	public Context(Context context) {
		this.idValues = new HashMap<String, Integer>(context.idValues);
	}
	
	/**
	 * 
	 * @param id la variabile di classe Id <br>
	 * @param value il valore numerico della variabile <br>
	 */
	public void assign(String key, int value) {
		this.idValues.put(key, value);
	}
	
	/**
	 * 
	 * @param id la variabile di classe Id da interpretare <br>
	 * @return il valore della variabile <br>
	 */
	public int lookup(Id id) {
		int value = 0;
		if(this.idValues.containsKey(id.getName())) {
			value = this.idValues.get(id.getName());
		}
		return value;
	}

	@Override
	public String toString() {
		return "Context [idValues=" + idValues + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idValues == null) ? 0 : idValues.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Context other = (Context) obj;
		if (idValues == null) {
			if (other.idValues != null)
				return false;
		} else if (!idValues.equals(other.idValues))
			return false;
		return true;
	}
	
}
