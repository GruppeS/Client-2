package view;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

public class CalendarPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack;
	private JScrollPane scrollPane;
	private JTable table;

	Vector<Object> columnNames = new Vector<Object>();
	private JButton btnCalendars;
	private JButton btnAddNote;
	private JTextField textNote;
	private JButton btnDeleteNote;
	
	public CalendarPanel() {
		setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(20, 359, 97, 23);
		add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 21, 341, 281);
		add(scrollPane);
		
		btnCalendars = new JButton("Calendars");
		btnCalendars.setBounds(264, 359, 97, 23);
		add(btnCalendars);
		
		btnAddNote = new JButton("Add note");
		btnAddNote.setBounds(272, 313, 89, 23);
		add(btnAddNote);
		
		textNote = new JTextField();
		textNote.setBounds(20, 313, 230, 23);
		add(textNote);
		textNote.setColumns(10);
		
		btnDeleteNote = new JButton("Delete note");
		btnDeleteNote.setBounds(141, 345, 109, 23);
		add(btnDeleteNote);
	}
	
	public void setEvents(Vector<?> data) {
		columnNames = new Vector<Object>();
		columnNames.add("ID");
		columnNames.add("Type");
		columnNames.add("Event");
		columnNames.add("Start");
		columnNames.add("End");
		columnNames.add("Location");
		columnNames.add("Note");
		table = new JTable(data, columnNames);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}
	
	public String getSelectedEvent() {
		String selectedEvent;
		
		int row = table.getSelectedRow();
		
		if(row!=-1)
		{
			selectedEvent = (table.getValueAt(row, 0)).toString();
		} else {
			selectedEvent = null;
		}
		
		return selectedEvent;
	}
	
	public String getNote() {
		return textNote.getText();
	}
	
	public void addActionListener(ActionListener l)
	{
		btnCalendars.addActionListener(l);
		btnCalendars.setActionCommand("btnCalendars");
		btnBack.addActionListener(l);
		btnBack.setActionCommand("btnBack");
		btnAddNote.addActionListener(l);
		btnAddNote.setActionCommand("btnAddNote");
		btnDeleteNote.addActionListener(l);
		btnDeleteNote.setActionCommand("btnDeleteNote");
	}
}