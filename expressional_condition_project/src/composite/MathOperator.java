package composite;

/**
 * 
 * @author Antonio Bilotta <br>
 * Definisce i metodi degli operatori matematici <br>
 */
public abstract class MathOperator extends MathExpression {
	
	/*
	 * Poichè la maggior parte degli operatori sono binari, sono state introdotte due variabili
	 * operando, tuttavia le classi eredi sono tenute a usarne almeno una
	*/
	protected MathExpression left;
	protected MathExpression right;
	
	@Override
	public int getCardinality() {
		return 2;
	}
	
	@Override
	public void setLeft(MathExpression expression) {
		this.left = expression;
	}
	
	@Override
	public void setRight(MathExpression expression) {
		this.right = expression;
	}
	
	@Override
	public MathExpression getLeft() {
		return left;
	}
	
	@Override
	public MathExpression getRight() {
		return right;
	}

}
