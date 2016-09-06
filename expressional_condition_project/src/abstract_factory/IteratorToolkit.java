package abstract_factory;

import iterator.BinaryOperatorIterator;
import iterator.OperandIterator;
import iterator.UnaryOperatorIterator;
import composite.Expression;

/**
 * 
 * @author Antonio Bilotta <br>
 * Interfaccia per classi factory dei ExpressionIterator <br>
 */
public interface IteratorToolkit {
	
	OperandIterator createOperandIterator(Expression expression);
	
	/**
	 * 
	 * @param expression
	 * @return
	 */
	BinaryOperatorIterator createBinaryOperatorIterator(Expression expression);
	
	/**
	 * 
	 * @param expression
	 * @return
	 */
	UnaryOperatorIterator createUnaryOperatorIterator(Expression expression);

}
