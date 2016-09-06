package composite;

import iterator.ExpressionIterator;

import visitor.ExpressionVisitor;

/**
 * 
 * @author Antonio Bilotta <br>
 * Interfaccia per i componenti delle espressioni <br>
 */
public interface Expression extends Iterable<Expression> {
	
	/**
	 * 
	 * @param iterator L'iteratore per questa espressione <br>
	 */
	void setIterator(ExpressionIterator iterator);
	
	Expression getLeft();
	
	Expression getRight();
	
	void accept(ExpressionVisitor visitor);

}
