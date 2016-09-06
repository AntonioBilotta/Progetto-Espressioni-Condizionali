package builder;

import java.io.InputStream;

import builder.Lexer.Symbol;

import composite.AddOperator;
import composite.AndOperator;
import composite.BooleanExpression;
import composite.DivOperator;
import composite.MathExpression;
import composite.MulOperator;
import composite.NegOperator;
import composite.NotOperator;
import composite.OrOperator;
import composite.PowOperator;
import composite.RelOperator;
import composite.RemOperator;
import composite.SubOperator;

import flyweight.MathFlyweightFactory;

public class ConditionalExpressionRDPB {
	
	private ExpressionLexer lexer;
	private Symbol symbol;
	private MathFlyweightFactory factory;
	
	public BooleanExpression build(InputStream in) {
		this.lexer = new ExpressionLexer(in);
		this.factory = new MathFlyweightFactory();
		return this.buildCond();
	}

	private BooleanExpression buildCond() {
		BooleanExpression root = this.buildTermb();
		while(this.symbol == Symbol.OR) {
			BooleanExpression termb = this.buildTermb();
			BooleanExpression cond = new OrOperator();
			cond.setLeft(root);
			cond.setRight(termb);
			root = cond;
		}
		return root;
	}

	private BooleanExpression buildTermb() {
		BooleanExpression root = this.buildFactb();
		while(this.symbol == Symbol.AND) {
			BooleanExpression factb = this.buildFactb();
			BooleanExpression termb = new AndOperator();
			termb.setLeft(root);
			termb.setRight(factb);
			root = termb;
		}
		return root;
	}

	private BooleanExpression buildFactb() {
		this.symbol = this.lexer.nextSymbol();
		if(this.symbol == Symbol.NOT) {
			BooleanExpression root = new NotOperator();
			BooleanExpression factb = this.buildFactb();
			root.setLeft(factb);
			return root;
		} 
		else if(this.symbol == Symbol.OPAR) {
			BooleanExpression root = this.buildCond();
			this.expected(Symbol.CPAR);
			return root;
		} 
		else {
			MathExpression leftExpr = this.buildExpr(); 
			RelOperator.Type type = null;
			if(this.symbol == Symbol.EQ) {
				this.symbol = this.lexer.nextSymbol();
				this.expected(Symbol.EQ);
				type = RelOperator.Type.EQ;
			}	
			else if(this.symbol == Symbol.NOT) { 
				this.symbol = this.lexer.nextSymbol();
				this.expected(Symbol.EQ);
				type = RelOperator.Type.NE;
			}	
			else if(this.symbol == Symbol.GT) {  
				this.symbol = this.lexer.nextSymbol();
				if(this.symbol == Symbol.EQ) {
					type = RelOperator.Type.GE;
					this.symbol = this.lexer.nextSymbol();
				}
				else {
					type = RelOperator.Type.GT;
				}
			}	
			else if(this.symbol == Symbol.LT) { 
				this.symbol = this.lexer.nextSymbol();
				if(this.symbol == Symbol.EQ) {
					type = RelOperator.Type.LE;
					this.symbol = this.lexer.nextSymbol();
				}
				else {
					type = RelOperator.Type.LT;
				}
			}	
			else {
				throw new SyntaxException("Relational operator expected instead of " + this.symbol + " " + this.lexer.getString());
			}
			MathExpression rightExpr = this.buildExpr();
			RelOperator root = new RelOperator();
			root.setLeft(leftExpr);
			root.setRight(rightExpr);
			root.setType(type);
			return root;
		}
	}

	private MathExpression buildExpr() {
		MathExpression root;
		if(this.symbol == Symbol.MINUS) {
			this.symbol = this.lexer.nextSymbol();
			MathExpression term = this.buildTerm(); 
			root = new NegOperator();
			root.setLeft(term);
		}
		else if(this.symbol == Symbol.PLUS) {
			this.symbol = this.lexer.nextSymbol();
			root = this.buildTerm();
		}
		else {
			root = this.buildTerm();
		}
		while(this.symbol == Symbol.PLUS || this.symbol == Symbol.MINUS) {
			MathExpression expr;
			if(this.symbol == Symbol.PLUS) {
				expr = new AddOperator();
			}
			else {
				expr = new SubOperator();
			}
			this.symbol = this.lexer.nextSymbol();
			MathExpression term = this.buildTerm();
			expr.setLeft(root);
			expr.setRight(term);
			root = expr;
		}
		return root;
	}

	private MathExpression buildTerm() {
		MathExpression root = this.buildTermp();
		while(this.symbol == Symbol.MUL || this.symbol == Symbol.DIV || this.symbol == Symbol.REM ) {
			MathExpression term;
			if(this.symbol == Symbol.MUL) {
				term = new MulOperator();
			}
			else if(this.symbol == Symbol.DIV) {
				term = new DivOperator();
			}
			else {
				term = new RemOperator();
			}
			this.symbol = this.lexer.nextSymbol();
			MathExpression termp = this.buildTermp();
			term.setLeft(root);
			term.setRight(termp);
			root = term;
		}
		return root;
	}

	private MathExpression buildTermp() {
		MathExpression root = this.buildFact();
		while(this.symbol == Symbol.POW) {
			this.symbol = this.lexer.nextSymbol();
			MathExpression fact = this.buildFact();
			MathExpression termp = new PowOperator();
			termp.setLeft(root);
			termp.setRight(fact);
			root = termp;
		}
		return root;
	}

	private MathExpression buildFact() {
		MathExpression root;
		if(this.symbol == Symbol.OBRACK) {
			this.symbol = this.lexer.nextSymbol();
			root = this.buildExpr();
			this.expected(Symbol.CBRACK);
		}
		else if(this.symbol == Symbol.NUM) {
			int value = Integer.parseInt(this.lexer.getString());
			root = this.factory.createNum(value);
			this.symbol = this.lexer.nextSymbol();
		}
		else if(this.symbol == Symbol.ID) {
			String name = this.lexer.getString();
			root = this.factory.createId(name);
			this.symbol = this.lexer.nextSymbol();
		}
		else {
			throw new SyntaxException("Id, Num or open brack expected instead of " + this.symbol + " " + this.lexer.getString());
		}
		return root;
	}

	private void expected(Symbol symbol) {
		if(this.symbol != symbol) {
			String msg="Found " + symbol + " while waiting for " + this.symbol;
			throw new SyntaxException(msg);
		}
		this.symbol = this.lexer.nextSymbol();
	}

}
