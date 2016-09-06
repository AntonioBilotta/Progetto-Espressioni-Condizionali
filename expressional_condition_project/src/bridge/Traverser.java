package bridge;

import java.util.Iterator;

import composite.Expression;

public abstract class Traverser {
	
	protected Iterator<Expression> iterator;
	
	public boolean traverse(Expression expression) {
		this.iterator = expression.iterator();
		boolean result = false;
		while(this.iterator.hasNext()) {
			result = this.processItem(this.iterator.next());
			if(! result) break;
		}
		return result;
	}
	
	protected abstract boolean processItem(Expression expression);

}
