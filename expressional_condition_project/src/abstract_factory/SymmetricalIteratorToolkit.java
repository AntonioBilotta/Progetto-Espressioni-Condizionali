package abstract_factory;

import iterator.BinaryOperatorIterator;
import iterator.OperandIterator;
import iterator.SymmetricalBinaryOperatorIterator;
import iterator.SymmetricalUnaryOperatorIterator;
import iterator.UnaryOperatorIterator;
import composite.Expression;

/**
 * 
 * @author Antonio Bilotta <br>
 * Classe factory degli iteratori che effettuano visita postfissa <br>
 */
public class SymmetricalIteratorToolkit implements IteratorToolkit {
	
	@Override
	public OperandIterator createOperandIterator(Expression expression) {
		return new OperandIterator(expression);
	}

	@Override
	public BinaryOperatorIterator createBinaryOperatorIterator(Expression expression) {
		return new SymmetricalBinaryOperatorIterator(expression);
	}

	@Override
	public UnaryOperatorIterator createUnaryOperatorIterator(Expression expression) {
		return new SymmetricalUnaryOperatorIterator(expression);
	}

}
