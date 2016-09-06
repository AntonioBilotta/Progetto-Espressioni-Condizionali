package mvc_controller;

import java.awt.EventQueue;

public class ConditionalExpressionApplicationLauncher {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ConditionalExpressionApplicationController();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
