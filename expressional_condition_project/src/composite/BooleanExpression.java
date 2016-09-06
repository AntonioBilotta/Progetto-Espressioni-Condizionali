package composite;

import java.util.Iterator;

import interpreter.Context;
import iterator.ExpressionIterator;

/**
 * 
 * @author Antonio Bilotta <br>
 * Definisce i metodi delle espressioni booleane <br>
 * 
 */
public abstract class BooleanExpression implements Expression {
	
	protected ExpressionIterator iterator;
	
	/**
	 * 
	 * @return il numero di sotto-espressioni o operandi ammessi <br>
	 */
	public int getCardinality() {
		return 0;
	}
	
	/**
	 * 
	 * @param expression la sotto-espressione da aggiungere come operando sinistro <br>
	 * precondition: false <br>
	 * postcondition: la sotto-espressione è aggiunta come operando sinistro <br>
	 */
	public void setLeft(BooleanExpression expression) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @param expression la sotto-espressione da aggiungere come operando destro <br>
	 * precondition: false <br>
	 * postcondition: la sotto-espressione è aggiunta come operando destro <br>
	 */
	public void setRight(BooleanExpression Expression) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @return la sotto-espressione operando sinistro <br>
	 */
	@Override
	public Expression getLeft() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @return la sotto-espressione operando destro <br>
	 */
	@Override
	public Expression getRight() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @param context il contesto in cui effettuare la valutazione <br>
	 * @return il risultato della valutazione <br>
	 */
	public abstract boolean evaluate(Context context);
	
	@Override
	public Iterator<Expression> iterator() {
		return this.iterator.clone();
	}
	
	@Override
	public void setIterator(ExpressionIterator iterator) {
		this.iterator = iterator;
	}

}
