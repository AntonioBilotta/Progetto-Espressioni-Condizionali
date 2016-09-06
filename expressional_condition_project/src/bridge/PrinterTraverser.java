package bridge;

import composite.Expression;

public class PrinterTraverser extends Traverser {
	
	private StringBuilder stringBuilder;
	
	public PrinterTraverser() {
		this.stringBuilder = new StringBuilder();
	}
	
	@Override
	protected boolean processItem(Expression expression) {
		this.stringBuilder.append(expression);
		return true;
	}
	
	public String getString() {
		String expression = this.stringBuilder.toString();
		this.stringBuilder = new StringBuilder();
		return expression;
	}

}
