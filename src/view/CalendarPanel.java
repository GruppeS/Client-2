package view;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CalendarPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack;
	private JScrollPane scrollPane;
	private JTable table;

	Vector<Object> columnNames = new Vector<Object>();
	private JButton btnCalendars;
	
	public CalendarPanel() {
		setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(20, 359, 97, 23);
		add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 21, 341, 321);
		add(scrollPane);
		
		btnCalendars = new JButton("Calendars");
		btnCalendars.setBounds(264, 359, 97, 23);
		add(btnCalendars);
	}
	
	public void setEvents(Vector<?> data) {
		columnNames = new Vector<Object>();
		columnNames.add("ID");
		columnNames.add("Type");
		columnNames.add("Event");
		columnNames.add("Start");
		columnNames.add("End");
		columnNames.add("Location");
		table = new JTable(data, columnNames);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
	}
	
	public void addActionListener(ActionListener l)
	{
		btnCalendars.addActionListener(l);
		btnCalendars.setActionCommand("CalendarsBtn");
		btnBack.addActionListener(l);
		btnBack.setActionCommand("BackBtn");
	}
}