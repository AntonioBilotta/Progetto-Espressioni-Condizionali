package mvc_model;

import interpreter.Context;

import composite.BooleanExpression;

public interface ConditionalExpressionApplicationModel {
	
	BooleanExpression getExpression();
	
	boolean getResult();
	
	Context getContext();

}
