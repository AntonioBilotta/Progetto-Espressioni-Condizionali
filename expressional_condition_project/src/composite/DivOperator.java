package composite;

import interpreter.Context;
import visitor.ExpressionVisitor;

/**
 * 
 * @author Antonio Bilotta
 * 
 */
public class DivOperator extends MathOperator {

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * precondition: left != null && right != null, value of right can't be 0 <br>
	 */
	@Override
	public double evaluate(Context context) {
		if(this.left == null || this.right == null) {
			throw new UnevaluableExpressionException("Operands can't be null");
		}
		double rightValue;
		if((rightValue = this.right.evaluate(context)) == 0) {
			throw new UnevaluableExpressionException("Division by zero is not admitted");
		}
		return this.left.evaluate(context) / rightValue;
	}
	
	@Override
	public String toString() {
		return ": ";
	}

}
