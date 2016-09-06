package mvc_controller;

import interpreter.Context;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import mvc_model.ConditionalExpressionApplicationModelImpl;
import mvc_model.ConditionalExpressionApplicationMutableModel;
import mvc_model.ObservableModel;
import mvc_view.ConditionalExpressionApplicationGUI;
import visitor.InstallIteratorVisitor;
import abstract_factory.IteratorToolkit;
import abstract_factory.PostfixIteratorToolkit;
import abstract_factory.SymmetricalIteratorToolkit;
import builder.ConditionalExpressionRDPB;
import builder.SyntaxException;

import composite.BooleanExpression;
import composite.UnevaluableExpressionException;
import data_access_object.ContextFDAO;
import data_access_object.DefaultContextFDAO;

/**
 * 
 * @author Antonio Bilotta
 *
 */
public class ConditionalExpressionApplicationController {
	
	private ObservableModel model;
	private ConditionalExpressionApplicationGUI window;
	
	public ConditionalExpressionApplicationController() {
		ConditionalExpressionApplicationMutableModel model = new ConditionalExpressionApplicationModelImpl();
		this.model = new ObservableModel(model);
		this.window = new ConditionalExpressionApplicationGUI(model, this);
		this.model.addObserver(window);
	}
	
	/**
	 * 
	 * @param input
	 * @throws ConditionalExpressionApplicationException
	 */
	public void evaluateExpression(String input) throws ConditionalExpressionApplicationException {
		try {
			// verifica precondizioni (contesto non nullo)
			Context context = this.model.getContext();
			if(context == null) {
				throw new ConditionalExpressionApplicationException("Context can't be null");
			}
			// calcola i nuovi risultati
			ConditionalExpressionRDPB builder = new ConditionalExpressionRDPB();
			BooleanExpression expression;
			boolean result;
			try {
				expression = builder.build(new ByteArrayInputStream(input.getBytes()));
			} catch(SyntaxException sexc) {
				throw new ConditionalExpressionApplicationException(sexc.getMessage());
			}	
			try {
				result = expression.evaluate(context);
			} catch(UnevaluableExpressionException ueexc) {
				throw new ConditionalExpressionApplicationException(ueexc.getMessage());
			}
			// modifica i campi nel modello
			this.model.setExpression(expression);
			this.model.setResult(result);
		} catch(RuntimeException exc) {
			throw new ConditionalExpressionApplicationException("Error while the application try to evaluate an expression");
		}
	}
	
	/**
	 * 
	 * @throws ConditionalExpressionApplicationException
	 */
	public void setPostfixView() throws ConditionalExpressionApplicationException {
		try {
			if(this.model.getExpression() == null) {
				throw new ConditionalExpressionApplicationException("Expression can't be null");
			}
			BooleanExpression expression = this.model.getExpression();
			IteratorToolkit toolkit = new PostfixIteratorToolkit();
			InstallIteratorVisitor visitor = new InstallIteratorVisitor(toolkit);
			visitor.install(expression);
			this.model.setExpression(expression);
			this.model.notifyObservers();
		} catch(RuntimeException exc) {
			throw new ConditionalExpressionApplicationException("Error while the application try to set a new view");
		}
	}
	
	/**
	 * 
	 * @throws ConditionalExpressionApplicationException
	 */
	public void setSymmetricalView() throws ConditionalExpressionApplicationException {
		try {
			if(this.model.getExpression() == null) {
				throw new ConditionalExpressionApplicationException("Expression can't be null");
			}
			BooleanExpression expression = this.model.getExpression();
			IteratorToolkit toolkit = new SymmetricalIteratorToolkit();
			InstallIteratorVisitor visitor = new InstallIteratorVisitor(toolkit);
			visitor.install(expression);
			this.model.setExpression(expression);
			this.model.notifyObservers();
		} catch(RuntimeException exc) {
			throw new ConditionalExpressionApplicationException("Error while the application try to set a new view");
		}	
	}
	
	/**
	 * 
	 */
	public void newContext() throws ConditionalExpressionApplicationException {
		try {
			Context context = new Context();
			this.model.setContext(context);
			this.model.notifyObservers();
		} catch(RuntimeException e) {
			throw new ConditionalExpressionApplicationException("Error while the application try to create a new context");
		}	
	}
	
	/**
	 * 
	 * @param name
	 * @param value
	 * @throws ConditionalExpressionApplicationException
	 */
	public void assignToContext(String name, String value) throws ConditionalExpressionApplicationException {
		try {
			Context context = this.model.getContext();
			if(context == null) {
				throw new ConditionalExpressionApplicationException("Context can't be null");
			}
			context.assign(name, Integer.parseInt(value));
			this.model.setContext(context);
			this.model.notifyObservers();
		} catch(RuntimeException exc) {
			throw new ConditionalExpressionApplicationException("Error while the application try to create a new context");
		}		
	}
	
	/**
	 * 
	 * @param file
	 * @throws ConditionalExpressionApplicationException
	 */
	public void loadContext(File file) throws ConditionalExpressionApplicationException {
		try {
			ContextFDAO dao = new DefaultContextFDAO();
			Context context;
			try {
				context = dao.load(file);
			} catch(IOException ioexc) {
				throw new ConditionalExpressionApplicationException(ioexc.getMessage());
			} catch(ClassNotFoundException cnfexc) {
				throw new ConditionalExpressionApplicationException(cnfexc.getMessage());
			}	
			this.model.setContext(context);
			this.model.notifyObservers();
		} catch(RuntimeException exc) {
			throw new ConditionalExpressionApplicationException("Error while the application try to load a context");
		}
	}
	
	/**
	 * 
	 * @param file
	 * @throws ConditionalExpressionApplicationException
	 */
	public void saveContext(File file) throws ConditionalExpressionApplicationException {
		try {
			ContextFDAO dao = new DefaultContextFDAO();
			Context context = this.model.getContext();
			if(context == null) {
				throw new ConditionalExpressionApplicationException("Context can't be null");
			}
			try {
				dao.save(context, file);
			} catch(IOException ioexc) {
				throw new ConditionalExpressionApplicationException(ioexc.getMessage());
			}
		} catch(RuntimeException exc) {
			throw new ConditionalExpressionApplicationException("Error while the application try to save a context");
		}
	}

}
