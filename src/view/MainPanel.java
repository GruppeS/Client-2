package view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	private JLabel lblQuoteOfThe;
	private JButton btnLogOut;

	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(41, 222, 295, 116);
		textArea.setFont(new Font("Calibri", Font.ITALIC, 12));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		add(textArea);

		lblQuoteOfThe = new JLabel("Quote of the day:");
		lblQuoteOfThe.setBounds(41, 179, 201, 27);
		add(lblQuoteOfThe);

		btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(237, 349, 99, 26);
		add(btnLogOut);
	}

	public void addActionListener(ActionListener l) {
		btnLogOut.addActionListener(l);
		btnLogOut.setActionCommand("LogoutBtn");
	}

	public void setQuote(String quote) {
		textArea.setText(quote);
	}
}
