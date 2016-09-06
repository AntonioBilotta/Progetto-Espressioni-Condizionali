package composite;

import visitor.ExpressionVisitor;
import interpreter.Context;

/**
 * 
 * @author Antonio Bilotta
 * 
 */
public class RemOperator extends MathOperator {
	
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
		double rightValue;
		if((rightValue = this.right.evaluate(context)) == 0) {
			throw new UnevaluableExpressionException("Division by zero is not admitted");
		}
		return this.left.evaluate(context) % rightValue;
	}
	
	@Override
	public String toString() {
		return "% ";
	}

}
