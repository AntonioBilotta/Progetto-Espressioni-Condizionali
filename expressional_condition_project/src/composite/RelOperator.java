package composite;

import visitor.ExpressionVisitor;

import interpreter.Context;

/**
 * 
 * @author Antonio Bilotta
 * 
 */
public class RelOperator extends BooleanExpression {
	
	public enum Type {EQ, NE, GT, GE, LT, LE}
	
	private MathExpression left; 
	private MathExpression right;
	private Type type;
	
	@Override
	public int getCardinality() {
		return 2;
	}

	/**
	 * 
	 * @return la sotto-espressione matematica sinistra <br>
	 */
	@Override
	public MathExpression getLeft() {
		return left;
	}
	
	/**
	 * 
	 * @param left la sotto-espressione matematica sinistra <br>
	 */
	public void setLeft(MathExpression left) {
		this.left = left;
	}
	
	/**
	 * 
	 * @return la sotto-espressione matematica destra <br>
	 */
	@Override
	public MathExpression getRight() {
		return right;
	}
	
	/**
	 * 
	 * @param right la sotto-espressione matematica destra <br>
	 */
	public void setRight(MathExpression right) {
		this.right = right;
	}
	
	/**
	 * 
	 * @return il tipo di operatore relazionale <br>
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * 
	 * @param type il tipo di operatore relazionale <br>
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	/**
	 * precondition: left != null && right != null
	 */
	@Override
	public boolean evaluate(Context context) {
		if(this.left == null || this.right == null || this.type == null) {
			throw new UnevaluableExpressionException("Operands can't be null");
		}
		double l = this.left.evaluate(context);
		double r = this.right.evaluate(context);
		switch(type) {
		case EQ : return l == r;
		case NE : return l != r;
		case GT : return l > r;
		case GE : return l >= r;
		case LT	: return l < r;
		default	: return l <= r;
		}
	}
	
	/**
	 * @param visitor un oggetto visitor per questa classe <br>
	 */
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		switch(type) {
		case EQ : return "== ";
		case NE : return "!= ";
		case GT : return "> ";
		case GE : return ">= ";
		case LT	: return "< ";
		default	: return "<= ";
		}
	}

}
