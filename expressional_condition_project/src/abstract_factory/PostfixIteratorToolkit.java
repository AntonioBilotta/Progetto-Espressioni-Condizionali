package abstract_factory;

import iterator.BinaryOperatorIterator;
import iterator.OperandIterator;
import iterator.PostfixBinaryOperatorIterator;
import iterator.PostfixUnaryOperatorIterator;
import iterator.UnaryOperatorIterator;
import composite.Expression;

/**
 * 
 * @author Antonio Bilotta <br>
 * Classe factory degli iteratori che effettuano visita postfissa <br>
 */
public class PostfixIteratorToolkit implements IteratorToolkit {
	
	@Override
	public OperandIterator createOperandIterator(Expression expression) {
		return new OperandIterator(expression);
	}

	@Override
	public BinaryOperatorIterator createBinaryOperatorIterator(Expression expression) {
		return new PostfixBinaryOperatorIterator(expression);
	}

	@Override
	public UnaryOperatorIterator createUnaryOperatorIterator(Expression expression) {
		return new PostfixUnaryOperatorIterator(expression);
	}

}
