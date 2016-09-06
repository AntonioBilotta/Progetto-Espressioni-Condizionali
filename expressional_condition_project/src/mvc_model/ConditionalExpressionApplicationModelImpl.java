package mvc_model;

import interpreter.Context;

import composite.BooleanExpression;

public class ConditionalExpressionApplicationModelImpl implements
		ConditionalExpressionApplicationMutableModel {
	
	private BooleanExpression expression;
	private boolean result;
	private Context context;
	
	@Override
	public BooleanExpression getExpression() {
		return this.expression;
	}

	@Override
	public Context getContext() {
		return this.context;
	}

	@Override
	public void setExpression(BooleanExpression expression) {
		this.expression = expression;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public boolean getResult() {
		return this.result;
	}

	@Override
	public void setResult(boolean result) {
		this.result = result;
	}

}
