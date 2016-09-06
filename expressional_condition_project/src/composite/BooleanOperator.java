package composite;

/**
 * 
 * @author Antonio Bilotta <br>
 * Definisce i metodi degli operatori booleani <br>
 * 
 */
public abstract class BooleanOperator extends BooleanExpression {
	
	/*
	 * Poichè la maggior parte degli operatori sono binari, sono state introdotte due variabili
	 * operando, tuttavia le classi eredi sono tenute a usarne almeno una
	*/
	protected BooleanExpression left;
	protected BooleanExpression right;
	
	@Override
	public int getCardinality() {
		return 2;
	}
	
	@Override
	public void setLeft(BooleanExpression expression) {
		this.left = expression;
	}
	
	@Override
	public void setRight(BooleanExpression expression) {
		this.right = expression;
	}
	
	@Override
	public BooleanExpression getLeft() {
		return left;
	}
	
	@Override
	public BooleanExpression getRight() {
		return right;
	}

}
