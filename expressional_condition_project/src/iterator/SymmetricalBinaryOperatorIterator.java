package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import composite.Expression;

/**
 * 
 * @author Antonio Bilotta
 * Implementa un iteratore che effettua una visita simmetrica per expressioni binari <br>
 */
public class SymmetricalBinaryOperatorIterator extends BinaryOperatorIterator {
	
	private Expression expression;
	private Iterator<Expression> iterator;
	private Position position;
	
	public SymmetricalBinaryOperatorIterator(Expression expression) {
		this.expression = expression;
		this.position = Position.LEFT;
	}
	
	public SymmetricalBinaryOperatorIterator(SymmetricalBinaryOperatorIterator iterator) {
		this.expression = iterator.expression;
		this.position = iterator.position;
	}

	@Override
	public boolean hasNext() {
		if(this.position == Position.RIGHT) {
			return this.iterator.hasNext();
		}
		return true;
	}

	@Override
	public Expression next() {
		if(! this.hasNext()) throw new NoSuchElementException();
		if(this.iterator == null) {
			this.iterator = this.expression.getLeft().iterator();
		}
		if(this.position == Position.LEFT && ! this.iterator.hasNext()) {
			this.iterator = this.expression.getRight().iterator();
			this.position = Position.RIGHT;
			return this.expression;
		}
		return this.iterator.next();
	}
	
	@Override
	public SymmetricalBinaryOperatorIterator clone() {
		SymmetricalBinaryOperatorIterator clone = (SymmetricalBinaryOperatorIterator) super.clone();
		clone.expression = this.expression;
		return clone;
	}

}
