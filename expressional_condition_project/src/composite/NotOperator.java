package composite;

import interpreter.Context;
import visitor.ExpressionVisitor;

/**
 * 
 * @author Antonio Bilotta <br>
 *
 */
public class NotOperator extends BooleanOperator {

	@Override
	public void setRight(BooleanExpression Expression) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public BooleanExpression getRight() {
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
	public boolean evaluate(Context context) {
		if(this.left == null) {
			throw new UnevaluableExpressionException("Operand can't be null");
		}
		return ! this.left.evaluate(context);
	}
	
	@Override
	public String toString() {
		return "NOT ";
	}
}
