package iterator;

/**
 * 
 * @author Antonio Bilotta <br>
 * Classe astratta per iteratori dei componenti binari <br>
 */
public abstract class BinaryOperatorIterator extends ExpressionIterator {
	
	protected static enum Position { LEFT, RIGHT }
	protected ExpressionIterator iterator;
	protected Position position;
	
	@Override
	public BinaryOperatorIterator clone() {
		BinaryOperatorIterator clone = (BinaryOperatorIterator) super.clone();
		clone.position = this.position;
		return clone;
	}

}
