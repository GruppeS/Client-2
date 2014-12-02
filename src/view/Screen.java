package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Screen extends JFrame
{
	private static final long serialVersionUID = 1L;

	public static final String LOGINPANEL = "1";
	public static final String MAINPANEL = "2";
	public static final String CALENDARPANEL = "3";

	private JPanel contentPane;
	private LoginPanel loginPanel;
	private MainPanel mainPanel;
	private CalendarPanel calendarPanel;

	CardLayout c;

	public Screen()
	{
		setTitle("Calendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 400, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		loginPanel = new LoginPanel();
		contentPane.add(loginPanel, LOGINPANEL);
		
		mainPanel = new MainPanel();
		contentPane.add(mainPanel, MAINPANEL);
		
		calendarPanel = new CalendarPanel();
		contentPane.add(calendarPanel, CALENDARPANEL);

		c = (CardLayout) getContentPane().getLayout();
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}
	
	public MainPanel getMainPanel() {
		return mainPanel;
	}
	
	public CalendarPanel getCalendarPanel() {
		return calendarPanel;
	}

	public void show(String card)
	{
		c.show(getContentPane(), card);
	}
}