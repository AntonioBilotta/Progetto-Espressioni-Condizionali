package iterator;

import java.util.Iterator;

import composite.Expression;

/**
 * 
 * @author Antonio Bilotta <br>
 * Implementa i metodi più generali per l'iterazione sui componenti di un albero di componenti <br>
 */
public abstract class ExpressionIterator implements Iterator<Expression>, Cloneable {
	
	protected Expression expression;
	
	/**
	 * 
	 * @param component il componente su cui si vuole svolgere l'iterazione <br>
	 */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	/**
	 * 
	 * @return il componente su cui si vuole svolgere l'iterazione <br>
	 */
	public Expression getExpression() {
		return this.expression;
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public ExpressionIterator clone() {
		try {
			ExpressionIterator clone = (ExpressionIterator) super.clone();
			clone.setExpression(this.expression);
			return clone;
		} catch(CloneNotSupportedException cnsexc) {
			throw new Error(cnsexc);
		}
	}

}
