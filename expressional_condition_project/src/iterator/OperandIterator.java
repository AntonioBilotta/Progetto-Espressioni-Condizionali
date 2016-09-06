package iterator;

import java.util.NoSuchElementException;

import composite.Expression;

/**
 * 
 * @author Antonio Bilotta <br>
 * Classe che implementa l'iteratore per i componenti terminali o operandi <br>
 */
public class OperandIterator extends ExpressionIterator {
	
	private Expression expression;
	private boolean flag;
	
	public OperandIterator(Expression expression) {
		this.expression = expression;
		this.flag = true;
	}
	
	public OperandIterator(OperandIterator iterator) {
		this.expression = iterator.expression;
		this.flag = iterator.flag;
	}
	
	/**
	 * precondition: true <br>
	 */
	@Override
	public boolean hasNext() {
		return this.flag;
	}
	
	/**
	 * precondition: esiste un prossimo elemento <br>
	 */
	@Override
	public Expression next() {
		if(this.hasNext()) {
			this.flag = false;
			return this.expression;
		}
		throw new NoSuchElementException();
	}
	
	@Override
	public OperandIterator clone() {
		OperandIterator clone = (OperandIterator) super.clone();
		clone.flag = this.flag;
		return clone;
	}

}
