package view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MainPanel extends JPanel {
	private JTextArea textArea;
	private JLabel lblQuoteOfThe;
	private JButton btnLogOut;

	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(62, 218, 201, 99);
		add(textArea);
		
		lblQuoteOfThe = new JLabel("Quote of the day:");
		lblQuoteOfThe.setBounds(62, 180, 201, 27);
		add(lblQuoteOfThe);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(222, 328, 99, 26);
		add(btnLogOut);

	}
}
