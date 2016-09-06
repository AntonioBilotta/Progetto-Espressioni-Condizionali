package visitor;

import composite.AndOperator;
import composite.DivOperator;
import composite.Id;
import composite.NegOperator;
import composite.SubOperator;
import composite.MulOperator;
import composite.NotOperator;
import composite.Num;
import composite.OrOperator;
import composite.AddOperator;
import composite.PowOperator;
import composite.RelOperator;
import composite.RemOperator;

/**
 * 
 * @author Antonio Bilotta <br>
 * Interfaccia per i visitor delle espressioni <br>
 */
public interface ExpressionVisitor {
	
	void visit(OrOperator component);
	
	void visit(AndOperator component);
	
	void visit(NotOperator component);
	
	void visit(RelOperator component);
	
	void visit(AddOperator component);
	
	void visit(SubOperator component);
	
	void visit(NegOperator component);
	
	void visit(MulOperator component);
	
	void visit(DivOperator component);
	
	void visit(RemOperator component);
	
	void visit(PowOperator component);
	
	void visit(Id component);
	
	void visit(Num component);
	
}
