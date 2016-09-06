package composite;

import interpreter.Context;
import visitor.ExpressionVisitor;

/**
 * 
 * @author Antonio Bilotta
 * 
 */
public class AddOperator extends MathOperator {

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * precondition: left != null && right != null
	 */
	@Override
	public double evaluate(Context context) {
		if(this.left == null || this.right == null) {
			throw new UnevaluableExpressionException("Operands can't be null");
		}
		return this.left.evaluate(context) + this.right.evaluate(context);
	}

	@Override
	public String toString() {
		return "+ ";
	}

}
