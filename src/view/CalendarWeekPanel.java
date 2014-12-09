package view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class CalendarWeekPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane0;
	private JTable table;

	Vector<Object> columnNames = new Vector<Object>();
	private JButton btnWeekDay;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane3;
	private JScrollPane scrollPane4;
	private JScrollPane scrollPane5;
	private JScrollPane scrollPane6;

	public CalendarWeekPanel() {
		setLayout(null);

		btnWeekDay = new JButton("WEEK");
		btnWeekDay.setBounds(659, 11, 89, 23);
		add(btnWeekDay);

		scrollPane0 = new JScrollPane();
		scrollPane0.setBounds(10, 50, 364, 118);
		add(scrollPane0);

		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(10, 179, 364, 118);
		add(scrollPane1);

		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 308, 364, 118);
		add(scrollPane2);

		scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(384, 50, 364, 118);
		add(scrollPane3);

		scrollPane4 = new JScrollPane();
		scrollPane4.setBounds(384, 179, 364, 118);
		add(scrollPane4);

		scrollPane5 = new JScrollPane();
		scrollPane5.setBounds(384, 308, 364, 118);
		add(scrollPane5);

		scrollPane6 = new JScrollPane();
		scrollPane6.setBounds(197, 440, 364, 117);
		add(scrollPane6);
	}

	/**
	 * Sets events table
	 * @param data
	 */
	public void setEvents(ArrayList<Vector<Vector<Object>>> data) {
		for(int i = 0; i<data.size(); i++) {
			columnNames = new Vector<Object>();
			columnNames.add("ID");
			columnNames.add("Type");
			columnNames.add("Event");
			columnNames.add("Start");
			columnNames.add("End");
			columnNames.add("Location");
			columnNames.add("Note");
			table = new JTable(data.get(i), columnNames);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			switch(i) {
			case 0:
				scrollPane0.setViewportView(table);
				break;
			case 1:
				scrollPane1.setViewportView(table);
				break;
			case 2:
				scrollPane2.setViewportView(table);
				break;
			case 3:
				scrollPane3.setViewportView(table);
				break;
			case 4:
				scrollPane4.setViewportView(table);
				break;
			case 5:
				scrollPane5.setViewportView(table);
				break;
			case 6:
				scrollPane6.setViewportView(table);
				break;
			}
		}
	}

	public void addActionListener(ActionListener l)
	{
		btnWeekDay.addActionListener(l);
		btnWeekDay.setActionCommand("btnWeekDay");
	}
}
