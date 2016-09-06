package mvc_model;

import interpreter.Context;

import java.util.Observable;

import composite.BooleanExpression;

public class ObservableModel extends Observable {
	
	private ConditionalExpressionApplicationMutableModel model;
	
	public ObservableModel(ConditionalExpressionApplicationMutableModel model) {
		this.model = model;
		this.setChanged();
	}
	
	public BooleanExpression getExpression() {
		return this.model.getExpression();
	}

	public void setExpression(BooleanExpression expression) {
		this.model.setExpression(expression);
		this.setChanged();
	}
	
	public void setResult(boolean result) {
		this.model.setResult(result);
	}
	
	public boolean getResult() {
		return this.model.getResult();
	}
	
	public Context getContext() {
		return this.model.getContext();
	}

	public void setContext(Context context) {
		this.model.setContext(context);
		this.setChanged();
	}
	
}
