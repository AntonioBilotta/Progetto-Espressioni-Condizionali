package composite;

import interpreter.Context;
import visitor.ExpressionVisitor;

public class Num extends MathExpression {
	
	private Integer value;
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * precondition: value != null <br>
	 */
	@Override
	public double evaluate(Context context) {
		if(this.value == null) {
			throw new UnevaluableExpressionException("Value can't be null");
		}
		return this.value;
	}

	@Override
	public String toString() {
		return this.value + " ";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
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
		Num other = (Num) obj;
		if (value != other.value)
			return false;
		return true;
	}

}
