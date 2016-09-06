package composite;

import java.util.Iterator;

import interpreter.Context;
import iterator.ExpressionIterator;

/**
 * 
 * @author Antonio Bilotta <br>
 * Definisce i metodi delle espressioni aritmetiche <br>
 * INV: il numero di sotto-espressioni i deve rispettare la condizione 0=<i<=2   <br>
 */
public abstract class MathExpression implements Expression {
	
	protected ExpressionIterator iterator;
	
	/**
	 * 
	 * @return il numero di sotto-Expressioni <br>
	 */
	public int getCardinality() {
		return 0;
	}
	
	/**
	 * 
	 * @param expression la sotto-espressione da aggiungere come operando sinistro <br>
	 * precondition: false <br>
	 * postcondition: la cardinalità è maggiore di 0, il sotto-espressione è aggiunta come operando sinistro <br>
	 */
	public void setLeft(MathExpression Expression) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @param Expression la sotto-espressione da aggiungere come operando destro <br>
	 * precondition: false <br>
	 * postcondition: la cardinalità è maggiore di 0, il sotto-espressione è aggiunto come operando destro <br>
	 */
	public void setRight(MathExpression expression) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @return la sotto-espressione operando sinistro <br>
	 */
	public MathExpression getLeft() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @return la sotto-espressione operando destro <br>
	 */
	public MathExpression getRight() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @param context il contesto in cui effettuare la valutazione <br>
	 * @return il risultato della valutazione <br>
	 */
	public abstract double evaluate(Context context);
	
	@Override
	public Iterator<Expression> iterator() {
		return this.iterator.clone();
	}
	
	@Override
	public void setIterator(ExpressionIterator iterator) {
		this.iterator = iterator;
	}

}
