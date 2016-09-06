package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import composite.Expression;

/**
 * 
 * @author Antonio Bilotta <br>
 * Implementa un iteratore che effettua una visita simmetrica per expressioni unari <br>
 */
public class SymmetricalUnaryOperatorIterator extends UnaryOperatorIterator {
	
	private Expression expression;
	private Iterator<Expression> iterator;
	
	public SymmetricalUnaryOperatorIterator(Expression expression) {
		this.expression = expression;
	}
	
	public SymmetricalUnaryOperatorIterator(SymmetricalUnaryOperatorIterator iterator) {
		this.expression = iterator.expression;
	}

	@Override
	public boolean hasNext() {
		if(this.iterator == null) return true;
		return this.iterator.hasNext();
	}

	@Override
	public Expression next() {
		if(! this.hasNext()) throw new NoSuchElementException();
		if(this.iterator == null) {
			this.iterator = this.expression.getLeft().iterator();
			return this.expression;
		}
		return this.iterator.next();
	}
	
	@Override
	public SymmetricalUnaryOperatorIterator clone() {
		SymmetricalUnaryOperatorIterator clone = (SymmetricalUnaryOperatorIterator) super.clone();
		clone.expression = this.expression;
		return clone;
	}

}
