package view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class EventListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JScrollPane scrollPane;
	private JButton btnDelete;
	private JLabel lblEventName;
	private JTextField textDescription;
	private JTextField textStart;
	private JLabel lblStart;
	private JTextField textEnd;
	private JLabel lblEnd;
	private JTextField textLocation;
	private JLabel lblLocation;
	private JTable table;

	Vector<Object> columnNames = new Vector<Object>();
	private JButton btnAdd;
	private JLabel lblNewLabel;
	private JLabel label;
	private JButton btnBack;

	public EventListPanel() {
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 28, 431, 225);
		add(scrollPane);

		btnDelete = new JButton("Delete event");
		btnDelete.setBounds(308, 264, 160, 30);
		add(btnDelete);

		lblEventName = new JLabel("Event description:");
		lblEventName.setBounds(37, 275, 175, 30);
		add(lblEventName);

		textDescription = new JTextField();
		textDescription.setBounds(37, 311, 210, 30);
		add(textDescription);
		textDescription.setColumns(10);

		textStart = new JTextField();
		textStart.setBounds(308, 311, 160, 30);
		add(textStart);
		textStart.setColumns(10);

		lblStart = new JLabel("Start:");
		lblStart.setBounds(268, 314, 65, 24);
		add(lblStart);

		textEnd = new JTextField();
		textEnd.setColumns(10);
		textEnd.setBounds(308, 381, 160, 30);
		add(textEnd);

		lblEnd = new JLabel("End:");
		lblEnd.setBounds(268, 384, 65, 24);
		add(lblEnd);

		textLocation = new JTextField();
		textLocation.setColumns(10);
		textLocation.setBounds(37, 381, 210, 30);
		add(textLocation);

		lblLocation = new JLabel("Location:");
		lblLocation.setBounds(37, 356, 88, 14);
		add(lblLocation);

		btnAdd = new JButton("Add event");
		btnAdd.setBounds(187, 422, 123, 30);
		add(btnAdd);

		lblNewLabel = new JLabel("dd-MM-yyyy HH:mm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(335, 345, 202, 20);
		add(lblNewLabel);

		label = new JLabel("dd-MM-yyyy HH:mm");
		label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label.setBounds(335, 416, 202, 20);
		add(label);

		btnBack = new JButton("Back");
		btnBack.setBounds(23, 442, 89, 23);
		add(btnBack);
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

	public String getDescription() {
		return textDescription.getText();
	}
	public String getStart() {
		return textStart.getText();
	}
	public String getEnd() {
		return textEnd.getText();
	}
	public String getEventLocation() {
		return textLocation.getText();
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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}

	public void addActionListener(ActionListener l)
	{
		btnAdd.addActionListener(l);
		btnAdd.setActionCommand("btnAdd");
		btnDelete.addActionListener(l);
		btnDelete.setActionCommand("btnDelete");
		btnBack.addActionListener(l);
		btnBack.setActionCommand("btnBack");
	}
}
