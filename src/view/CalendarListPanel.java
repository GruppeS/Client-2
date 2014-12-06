package view;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.Font;

public class CalendarListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnBack;
	private JButton btnDelete;
	private JCheckBox isPublic;
	private JLabel lblCalendarName;
	private JButton btnCreate;
	private JLabel lblShareWith;
	private JTextField textShare;
	private JTextField textCalendar;
	private JLabel lblCalendars;
	private JButton btnShare;
	private JButton btnCalendarEvents;
	private JScrollPane scrollPane;
	private JTable table;

	Vector<Object> columnNames = new Vector<Object>();

	public CalendarListPanel() {
		setLayout(null);

		btnBack = new JButton("Back to Main Menu");
		btnBack.setBounds(112, 350, 150, 23);
		add(btnBack);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(30, 184, 104, 23);
		add(btnDelete);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 33, 209, 138);
		add(scrollPane);
		
		textCalendar = new JTextField();
		textCalendar.setBounds(30, 262, 104, 20);
		add(textCalendar);
		textCalendar.setColumns(10);
		
		isPublic = new JCheckBox("Public");
		isPublic.setBounds(145, 261, 97, 23);
		add(isPublic);
		
		lblCalendarName = new JLabel("Calendar name:");
		lblCalendarName.setBounds(30, 240, 133, 14);
		add(lblCalendarName);
		
		btnCreate = new JButton("Create calendar");
		btnCreate.setBounds(30, 301, 133, 23);
		add(btnCreate);
		
		lblShareWith = new JLabel("Share with:");
		lblShareWith.setBounds(243, 240, 122, 14);
		add(lblShareWith);
		
		textShare = new JTextField();
		textShare.setBounds(243, 262, 122, 20);
		add(textShare);
		textShare.setColumns(10);
		
		lblCalendars = new JLabel("Calendars");
		lblCalendars.setBounds(30, 8, 104, 23);
		add(lblCalendars);
		
		btnShare = new JButton("Share");
		btnShare.setBounds(243, 301, 97, 23);
		add(btnShare);
		
		btnCalendarEvents = new JButton("Calendar events");
		btnCalendarEvents.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCalendarEvents.setBounds(249, 75, 116, 54);
		add(btnCalendarEvents);
	}

	public void setCalendars(Vector<?> data) {
		columnNames = new Vector<Object>();
		columnNames.add("Calendar");
		columnNames.add("Created By");
		table = new JTable(data, columnNames);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}
	
	public String getSelectedCalendar() {
		String selectedCalendar;
		
		int row = table.getSelectedRow();
		
		if(row!=-1)
		{
			selectedCalendar = (table.getValueAt(row, 0)).toString();
		} else {
			selectedCalendar = null;
		}
		
		return selectedCalendar;
	}
	
	public String getCalendar() {
		return textCalendar.getText();
	}
	public boolean getIsPublic() {
		return isPublic.isSelected();
	}
	public String getShareWith() {
		return textShare.getText();
	}
	
	public void addActionListener(ActionListener l)
	{
		btnCalendarEvents.addActionListener(l);
		btnCalendarEvents.setActionCommand("btnEvents");
		btnBack.addActionListener(l);
		btnBack.setActionCommand("btnBack");
		btnDelete.addActionListener(l);
		btnDelete.setActionCommand("btnDelete");
		btnCreate.addActionListener(l);
		btnCreate.setActionCommand("btnCreate");
		btnShare.addActionListener(l);
		btnShare.setActionCommand("btnShare");
	}
}
