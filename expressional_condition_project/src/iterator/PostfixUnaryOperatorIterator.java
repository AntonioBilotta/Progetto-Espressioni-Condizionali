package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import composite.Expression;

/**
 * 
 * @author Antonio Bilotta
 * Implementa un iteratore che effettua una visita postfissa per expressioni unari <br>
 */
public class PostfixUnaryOperatorIterator extends UnaryOperatorIterator {
	
	private Expression expression;
	private Iterator<Expression> iterator;
	private boolean flag; 
	
	public PostfixUnaryOperatorIterator(Expression expression) {
		this.expression = expression;
		this.flag = true;
	}
	
	public PostfixUnaryOperatorIterator(PostfixUnaryOperatorIterator iterator) {
		this.expression = iterator.expression;
		this.flag = iterator.flag;
	}
	
	@Override
	public boolean hasNext() {
		return this.flag;
	}

	@Override
	public Expression next() {
		if(! this.hasNext()) throw new NoSuchElementException();
		if(this.iterator == null) {
			this.iterator = this.expression.getLeft().iterator();
		}
		if(this.iterator.hasNext()) {
			return this.iterator.next();
		}
		else {
			this.flag = false;
			return this.expression;
		}
	}
	
	@Override
	public PostfixUnaryOperatorIterator clone() {
		PostfixUnaryOperatorIterator clone = (PostfixUnaryOperatorIterator) super.clone();
		clone.expression = this.expression;
		return clone;
	}

}
