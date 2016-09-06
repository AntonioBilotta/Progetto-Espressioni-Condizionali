package visitor;

import abstract_factory.IteratorToolkit;
import composite.AddOperator;
import composite.AndOperator;
import composite.DivOperator;
import composite.Expression;
import composite.Id;
import composite.MulOperator;
import composite.NegOperator;
import composite.NotOperator;
import composite.Num;
import composite.OrOperator;
import composite.PowOperator;
import composite.RelOperator;
import composite.RemOperator;
import composite.SubOperator;

public class InstallIteratorVisitor implements ExpressionVisitor {
	
	private IteratorToolkit toolkit;
	
	public InstallIteratorVisitor(IteratorToolkit toolkit) {
		this.toolkit = toolkit;
	}
	
	public void install(Expression expression) {
		expression.accept(this);
	}

	@Override
	public void visit(OrOperator expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		expression.setIterator(this.toolkit.createBinaryOperatorIterator(expression));
	}

	@Override
	public void visit(AndOperator expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		expression.setIterator(this.toolkit.createBinaryOperatorIterator(expression));
	}

	@Override
	public void visit(NotOperator expression) {
		expression.getLeft().accept(this);
		expression.setIterator(this.toolkit.createUnaryOperatorIterator(expression));
	}

	@Override
	public void visit(RelOperator expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		expression.setIterator(this.toolkit.createBinaryOperatorIterator(expression));
	}

	@Override
	public void visit(AddOperator expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		expression.setIterator(this.toolkit.createBinaryOperatorIterator(expression));
	}

	@Override
	public void visit(SubOperator expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		expression.setIterator(this.toolkit.createBinaryOperatorIterator(expression));
	}

	@Override
	public void visit(NegOperator expression) {
		expression.getLeft().accept(this);
		expression.setIterator(this.toolkit.createUnaryOperatorIterator(expression));
	}

	@Override
	public void visit(MulOperator expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		expression.setIterator(this.toolkit.createBinaryOperatorIterator(expression));
	}

	@Override
	public void visit(DivOperator expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		expression.setIterator(this.toolkit.createBinaryOperatorIterator(expression));
	}

	@Override
	public void visit(RemOperator expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		expression.setIterator(this.toolkit.createBinaryOperatorIterator(expression));
	}

	@Override
	public void visit(PowOperator expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		expression.setIterator(this.toolkit.createBinaryOperatorIterator(expression));
	}

	@Override
	public void visit(Id expression) {
		expression.setIterator(this.toolkit.createOperandIterator(expression));
	}

	@Override
	public void visit(Num expression) {
		expression.setIterator(this.toolkit.createOperandIterator(expression));
	}

}
