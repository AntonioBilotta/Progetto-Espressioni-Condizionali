package composite;

import interpreter.Context;
import visitor.ExpressionVisitor;

/**
 * 
 * @author Antonio Bilotta
 *
 */
public class NegOperator extends MathOperator {
	
	@Override
	public void setRight(MathExpression component) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public MathExpression getRight() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * precondition: left != null <br>
	 */
	@Override
	public double evaluate(Context context) {
		if(this.left == null) {
			throw new UnevaluableExpressionException("Operand can't be null");
		}
		return - this.left.evaluate(context);
	}
	
	@Override
	public String toString() {
		return "- ";
	}

}
