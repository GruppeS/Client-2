package view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	private JLabel lblQuoteOfThe;
	private JButton btnLogOut;
	private JScrollPane scrollPane;
	private JTable table;
	
	Vector<Object> columnNames = new Vector<Object>();
	private JLabel lblWeatherForecast;
	private JButton btnCalendar;

	public MainPanel() {
		setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(41, 222, 295, 116);
		textArea.setFont(new Font("Calibri", Font.ITALIC, 12));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		add(textArea);

		lblQuoteOfThe = new JLabel("Quote of the day:");
		lblQuoteOfThe.setBounds(41, 193, 201, 27);
		add(lblQuoteOfThe);

		btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(10, 349, 100, 26);
		add(btnLogOut);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 364, 135);
		add(scrollPane);
		
		lblWeatherForecast = new JLabel("Weather forecast:");
		lblWeatherForecast.setBounds(49, 28, 131, 14);
		add(lblWeatherForecast);
		
		btnCalendar = new JButton("Calendar");
		btnCalendar.setBounds(273, 349, 100, 26);
		add(btnCalendar);
	}

	public void addActionListener(ActionListener l) {
		btnLogOut.addActionListener(l);
		btnLogOut.setActionCommand("LogoutBtn");
		btnCalendar.addActionListener(l);
		btnCalendar.setActionCommand("CalendarBtn");
	}
	
	/**
	 * Sets forecasts table
	 * @param data
	 */
	public void setWeather(Vector<?> data) {
		columnNames = new Vector<Object>();
		columnNames.add("Day");
		columnNames.add("Degrees");
		columnNames.add("Weather");
		table = new JTable(data, columnNames);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
	}
	
	/**
	 * Sets quote
	 * @param quote
	 */
	public void setQuote(String quote) {
		textArea.setText(quote);
	}
}
