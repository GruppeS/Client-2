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
	
	public CalendarPanel() {
		setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(20, 359, 89, 23);
		add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 21, 341, 321);
		add(scrollPane);

	}
	
	public void addActionListener(ActionListener l)
	{
		btnBack.addActionListener(l);
		btnBack.setActionCommand("BackBtn");
	}
	
	public void setCalendar(Vector<?> data) {
		columnNames = new Vector<Object>();
		columnNames.add("Type");
		columnNames.add("Course");
		columnNames.add("Start");
		columnNames.add("End");
		columnNames.add("Location");
		table = new JTable(data, columnNames);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
	}
}