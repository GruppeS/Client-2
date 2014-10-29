package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Screen extends JFrame
{
	private static final long serialVersionUID = 1L;

	public static final String LOGINPANEL = "1";

	private JPanel contentPane;
	private LoginPanel loginPanel;

	CardLayout c;

	public Screen()
	{
		setTitle("Calendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		loginPanel = new LoginPanel();
		contentPane.add(loginPanel, LOGINPANEL);

		c = (CardLayout) getContentPane().getLayout();
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public void show(String card)
	{
		c.show(getContentPane(), card);
	}
}