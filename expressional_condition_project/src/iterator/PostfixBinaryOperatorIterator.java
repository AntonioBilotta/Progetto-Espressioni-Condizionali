package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import composite.Expression;

/**
 * 
 * @author Antonio Bilotta
 * Implementa un iteratore che effettua una visita postfissa per expressioni binari <br>
 */
public class PostfixBinaryOperatorIterator extends BinaryOperatorIterator {
	
	private Expression expression;
	private Iterator<Expression> iterator;
	private Position position;
	private boolean flag; 

	public PostfixBinaryOperatorIterator(Expression expression) {
		this.expression = expression;
		this.flag = true;
		this.position = Position.LEFT;
	}
	
	public PostfixBinaryOperatorIterator(PostfixBinaryOperatorIterator iterator) {
		this.expression = iterator.expression;
		this.flag = iterator.flag;
		this.position = iterator.position;
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
		if(this.iterator.hasNext()) { // Se l'iteratore ha un prossimo, visitalo...
			return this.iterator.next();
		}	
		else if(this.position == Position.LEFT && this.expression.getRight().iterator().hasNext()) { // ...altrimenti, se sei a sinistra, cambia al nodo destro
			this.iterator = this.expression.getRight().iterator();
			this.position = Position.RIGHT;
			return this.iterator.next();
		}
		else { // ...altrimenti, visita questo nodo
			this.flag = false;
			return this.expression;
		}
	}
	
	@Override
	public PostfixBinaryOperatorIterator clone() {
		PostfixBinaryOperatorIterator clone = (PostfixBinaryOperatorIterator) super.clone();
		clone.expression = this.expression;
		return clone;
	}

}
