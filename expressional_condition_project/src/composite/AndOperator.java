package composite;

import visitor.ExpressionVisitor;
import interpreter.Context;

/**
 * 
 * @author Antonio Bilotta
 *
 */
public class AndOperator extends BooleanOperator {

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * precondition: left != null && right != null
	 */
	@Override
	public boolean evaluate(Context context) {
		if(this.left == null || this.right == null) {
			throw new UnevaluableExpressionException("Operands can't be null");
		}
		return this.left.evaluate(context) && this.right.evaluate(context);
	}

	@Override
	public String toString() {
		return "AND ";
	}

}
