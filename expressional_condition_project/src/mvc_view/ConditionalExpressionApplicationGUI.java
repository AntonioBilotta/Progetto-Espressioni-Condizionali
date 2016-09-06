package mvc_view;

import interpreter.Context;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import mvc_controller.ConditionalExpressionApplicationController;
import mvc_controller.ConditionalExpressionApplicationException;
import mvc_model.ConditionalExpressionApplicationModel;
import bridge.PrinterTraverser;

import composite.BooleanExpression;

public class ConditionalExpressionApplicationGUI implements Observer {

	private JFrame frmConditionalExpressionsEvaluator;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JTextPane contextPane;
	private JTextField valueTextField;
	private JTextField nameTextField;
	private JEditorPane expressionEditorPane;
	private JTextPane expressionPane;
	private JRadioButton rdbtnSymmetricalView;
	private JRadioButton rdbtnPostfixView;
	
	private ConditionalExpressionApplicationModel model;
	private ConditionalExpressionApplicationController controller;
	private PrinterTraverser printer;
	
	class NewContextListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				controller.newContext();
			} catch(ConditionalExpressionApplicationException aexc) {
	        	  JOptionPane.showMessageDialog(frmConditionalExpressionsEvaluator, aexc.getMessage());
	        }	
		}		
	}
	
	class OpenContextListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			int n = fileChooser.showOpenDialog(frmConditionalExpressionsEvaluator);
			if (n == JFileChooser.APPROVE_OPTION) {
		          File file = fileChooser.getSelectedFile();
		          try {
		        	  controller.loadContext(file);
		          } catch(ConditionalExpressionApplicationException aexc) {
		        	  JOptionPane.showMessageDialog(frmConditionalExpressionsEvaluator, aexc.getMessage());
		          }
			} 
		}
	}
	
	class SaveContextListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			int n = fileChooser.showSaveDialog(frmConditionalExpressionsEvaluator);
			if (n == JFileChooser.APPROVE_OPTION) {
		          File file = fileChooser.getSelectedFile();
		          try {
		        	  controller.saveContext(file);
		          } catch(ConditionalExpressionApplicationException aexc) {
		        	  JOptionPane.showMessageDialog(frmConditionalExpressionsEvaluator, aexc.getMessage());
		          }
			} 
		}
	}
	
	class InsertVariableListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String key = nameTextField.getText();
				String value = valueTextField.getText();
				controller.assignToContext(key, value);
			} catch(ConditionalExpressionApplicationException ceaexc) {
	        	  JOptionPane.showMessageDialog(frmConditionalExpressionsEvaluator, ceaexc.getMessage());
			}
		}
	} 
	
	class EvaluateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String input = expressionEditorPane.getText();
			input.trim();
			try {
				controller.evaluateExpression(input);
				if(rdbtnPostfixView.isSelected()) {
					controller.setPostfixView();
				} else {
					controller.setSymmetricalView();
				}
			} catch(ConditionalExpressionApplicationException ceaexc) {
	        	  JOptionPane.showMessageDialog(frmConditionalExpressionsEvaluator, ceaexc.getMessage());
			}
		}	
	}
	
	class PostfixViewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			rdbtnSymmetricalView.setSelected(false);
			try {
				controller.setPostfixView();
			} catch(ConditionalExpressionApplicationException ceaexc) {
	        	  JOptionPane.showMessageDialog(frmConditionalExpressionsEvaluator, ceaexc.getMessage());
			}
		}
	}
	
	class SymmetricalViewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			rdbtnPostfixView.setSelected(false);
			try {
				controller.setSymmetricalView();
			} catch(ConditionalExpressionApplicationException ceaexc) {
	        	  JOptionPane.showMessageDialog(frmConditionalExpressionsEvaluator, ceaexc.getMessage());
			}
		}
	}

	/**
	 * Create the application.
	 */
	public ConditionalExpressionApplicationGUI(ConditionalExpressionApplicationModel model, 
			ConditionalExpressionApplicationController controller) {
		this.controller = controller;
		this.model = model;
		initialize();
		this.frmConditionalExpressionsEvaluator.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConditionalExpressionsEvaluator = new JFrame();
		frmConditionalExpressionsEvaluator.setResizable(false);
		frmConditionalExpressionsEvaluator.setTitle("Espressioni Condizionali");
		frmConditionalExpressionsEvaluator.setBounds(100, 100, 755, 350);
		frmConditionalExpressionsEvaluator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmConditionalExpressionsEvaluator.getContentPane().setLayout(springLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmConditionalExpressionsEvaluator.getContentPane().add(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nuovo");
		mnFileMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new NewContextListener());
		
		JMenuItem mntmOpenMenuItem = new JMenuItem("Apri");
		mnFileMenu.add(mntmOpenMenuItem);
		mntmOpenMenuItem.addActionListener(new OpenContextListener());
		
		JMenuItem mntmSaveMenuItem = new JMenuItem("Salva");
		mnFileMenu.add(mntmSaveMenuItem);
		mntmSaveMenuItem.addActionListener(new SaveContextListener());
		
		JMenu mnHelpMenu = new JMenu("Aiuto");
		menuBar.add(mnHelpMenu);
		
		leftPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, leftPanel, 20, SpringLayout.NORTH, frmConditionalExpressionsEvaluator.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, leftPanel, 10, SpringLayout.WEST, frmConditionalExpressionsEvaluator.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, leftPanel, -10, SpringLayout.SOUTH, frmConditionalExpressionsEvaluator.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, leftPanel, 230, SpringLayout.WEST, frmConditionalExpressionsEvaluator.getContentPane());
		frmConditionalExpressionsEvaluator.getContentPane().add(leftPanel);
		
		rightPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, rightPanel, 20, SpringLayout.NORTH, frmConditionalExpressionsEvaluator.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, rightPanel, -507, SpringLayout.EAST, frmConditionalExpressionsEvaluator.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, rightPanel, -10, SpringLayout.SOUTH, frmConditionalExpressionsEvaluator.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, rightPanel, -23, SpringLayout.EAST, frmConditionalExpressionsEvaluator.getContentPane());
		SpringLayout sl_leftPanel = new SpringLayout();
		leftPanel.setLayout(sl_leftPanel);
		
		contextPane = new JTextPane();
		contextPane.setEditable(false);
		sl_leftPanel.putConstraint(SpringLayout.NORTH, contextPane, 10, SpringLayout.NORTH, leftPanel);
		sl_leftPanel.putConstraint(SpringLayout.WEST, contextPane, 10, SpringLayout.WEST, leftPanel);
		leftPanel.add(contextPane);
		
		JLabel lblName = new JLabel("Nome");
		leftPanel.add(lblName);
		
		JLabel lblValue = new JLabel("Valore");
		leftPanel.add(lblValue);
		
		valueTextField = new JTextField();
		sl_leftPanel.putConstraint(SpringLayout.NORTH, lblValue, 0, SpringLayout.NORTH, valueTextField);
		sl_leftPanel.putConstraint(SpringLayout.EAST, lblValue, -6, SpringLayout.WEST, valueTextField);
		sl_leftPanel.putConstraint(SpringLayout.EAST, valueTextField, -10, SpringLayout.EAST, leftPanel);
		leftPanel.add(valueTextField);
		valueTextField.setColumns(10);
		
		nameTextField = new JTextField();
		sl_leftPanel.putConstraint(SpringLayout.SOUTH, contextPane, -16, SpringLayout.NORTH, nameTextField);
		sl_leftPanel.putConstraint(SpringLayout.WEST, nameTextField, 0, SpringLayout.WEST, valueTextField);
		sl_leftPanel.putConstraint(SpringLayout.SOUTH, nameTextField, -6, SpringLayout.NORTH, valueTextField);
		sl_leftPanel.putConstraint(SpringLayout.NORTH, lblName, 0, SpringLayout.NORTH, nameTextField);
		sl_leftPanel.putConstraint(SpringLayout.EAST, lblName, -6, SpringLayout.WEST, nameTextField);
		leftPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JButton btnInsert = new JButton("Inserisci");
		btnInsert.addActionListener(new InsertVariableListener());
		sl_leftPanel.putConstraint(SpringLayout.EAST, contextPane, 0, SpringLayout.EAST, btnInsert);
		sl_leftPanel.putConstraint(SpringLayout.SOUTH, valueTextField, -6, SpringLayout.NORTH, btnInsert);
		sl_leftPanel.putConstraint(SpringLayout.SOUTH, btnInsert, -10, SpringLayout.SOUTH, leftPanel);
		sl_leftPanel.putConstraint(SpringLayout.EAST, btnInsert, -10, SpringLayout.EAST, leftPanel);
		leftPanel.add(btnInsert);
		frmConditionalExpressionsEvaluator.getContentPane().add(rightPanel);
		SpringLayout sl_rightPanel = new SpringLayout();
		rightPanel.setLayout(sl_rightPanel);
		
		expressionEditorPane = new JEditorPane();
		sl_rightPanel.putConstraint(SpringLayout.NORTH, expressionEditorPane, 10, SpringLayout.NORTH, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.WEST, expressionEditorPane, 10, SpringLayout.WEST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.SOUTH, expressionEditorPane, -208, SpringLayout.SOUTH, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.EAST, expressionEditorPane, -10, SpringLayout.EAST, rightPanel);
		rightPanel.add(expressionEditorPane);
		
		JButton btnEvaluate = new JButton("Valuta");
		btnEvaluate.addActionListener(new EvaluateListener());
		sl_rightPanel.putConstraint(SpringLayout.NORTH, btnEvaluate, 6, SpringLayout.SOUTH, expressionEditorPane);
		rightPanel.add(btnEvaluate);
		
		expressionPane = new JTextPane();
		sl_rightPanel.putConstraint(SpringLayout.WEST, expressionPane, 10, SpringLayout.WEST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.EAST, expressionPane, 0, SpringLayout.EAST, expressionEditorPane);
		expressionPane.setEditable(false);
		sl_rightPanel.putConstraint(SpringLayout.NORTH, expressionPane, 14, SpringLayout.SOUTH, btnEvaluate);
		sl_rightPanel.putConstraint(SpringLayout.SOUTH, expressionPane, -46, SpringLayout.SOUTH, rightPanel);
		rightPanel.add(expressionPane);
		
		rdbtnSymmetricalView = new JRadioButton("Simmetrica");
		rdbtnSymmetricalView.setSelected(true);
		rdbtnSymmetricalView.addActionListener(new SymmetricalViewListener());
		sl_rightPanel.putConstraint(SpringLayout.NORTH, rdbtnSymmetricalView, 6, SpringLayout.SOUTH, expressionPane);
		sl_rightPanel.putConstraint(SpringLayout.SOUTH, rdbtnSymmetricalView, 0, SpringLayout.SOUTH, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.WEST, btnEvaluate, 0, SpringLayout.WEST, rdbtnSymmetricalView);
		sl_rightPanel.putConstraint(SpringLayout.WEST, rdbtnSymmetricalView, 10, SpringLayout.WEST, rightPanel);
		rightPanel.add(rdbtnSymmetricalView);
		
		rdbtnPostfixView = new JRadioButton("Postfissa");
		rdbtnPostfixView.addActionListener(new PostfixViewListener());
		sl_rightPanel.putConstraint(SpringLayout.NORTH, rdbtnPostfixView, 9, SpringLayout.NORTH, rdbtnSymmetricalView);
		sl_rightPanel.putConstraint(SpringLayout.WEST, rdbtnPostfixView, 7, SpringLayout.EAST, rdbtnSymmetricalView);
		rightPanel.add(rdbtnPostfixView);
	} 

	@Override
	public void update(Observable observable, Object object) {
		if(this.contextIsObsolete()) {
			String contextText = this.model.getContext().toString();
			this.contextPane.setText(contextText);
		}
		if(this.expressionIsObsolete()) {
			this.printer = new PrinterTraverser();
			this.printer.traverse(this.model.getExpression());
			String expressionText = this.printer.getString();
			String resultText = this.model.getResult() + "";
			this.expressionPane.setText(expressionText + " = " + resultText);
		}
	}
	
	private boolean contextIsObsolete() {
		Context context = this.model.getContext();
		if(context == null) return false;
		return ! context.toString().equals(this.contextPane.getText());
	}
	
	private boolean expressionIsObsolete() {
		BooleanExpression expression = this.model.getExpression();
		Boolean result = this.model.getResult();
		if(expression == null || result == null) return false;
		this.printer = new PrinterTraverser();
		this.printer.traverse(this.model.getExpression());
		return ! this.printer.getString().equals(this.expressionPane.getText());
	}
	
}
