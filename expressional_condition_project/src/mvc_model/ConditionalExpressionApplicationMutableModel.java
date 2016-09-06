package mvc_model;

import interpreter.Context;

import composite.BooleanExpression;

public interface ConditionalExpressionApplicationMutableModel extends 
	    ConditionalExpressionApplicationModel {
	
	void setExpression(BooleanExpression expression);
	
	void setResult(boolean result);
	
	void setContext(Context context);

}
