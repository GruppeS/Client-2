package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import model.JsonCreator;
import model.ServerConnection;
import model.jsonClasses.Calendars;
import model.jsonClasses.Events;
import model.jsonClasses.Forecasts;
import view.Screen;

public class Client implements Runnable {

	private JsonCreator jsonCreator;
	private ServerConnection serverConnection;
	private Screen screen;
	private Calendars calendars;
	private Events events;
	//	private Notes notes;
	private Forecasts forecasts;

	public Client() {
		jsonCreator = new JsonCreator();
		serverConnection = new ServerConnection();
		screen = new Screen();

		screen.getLoginPanel().addActionListener(new LoginPanelActionListener());
		screen.getMainPanel().addActionListener(new MainPanelActionListener());
		screen.getCalendarPanel().addActionListener(new CalendarPanelActionListener());
		screen.getCalendarListPanel().addActionListener(new CalendarListPanelActionListener());
	}

	public void run() {

		screen.show(Screen.LOGINPANEL);
		screen.setVisible(true);
	}

	public String authenticate(){

		String username = null;
		String password = null;

		username = screen.getLoginPanel().getUsername_Login();
		password = screen.getLoginPanel().getPassword_Login();

		return jsonCreator.login(username, password);
	}

	public String getQuote()
	{
		String qotd = serverConnection.send(jsonCreator.getQOTD());
		qotd = jsonCreator.setQOTD(qotd);
		return qotd;
	}

	public Vector<Vector<Object>> getForecast()
	{
		String forecast = serverConnection.send(jsonCreator.getForecast());
		forecasts = jsonCreator.setForecast(forecast);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i = 0; i<7; i++) {
			Vector<Object> row = new Vector<Object>();
			row.addElement(forecasts.getForecasts().get(i).getDate().substring(0,10));
			row.addElement(forecasts.getForecasts().get(i).getCelsius());
			row.addElement(forecasts.getForecasts().get(i).getDesc());
			data.addElement(row);
		}
		return data;
	}

	public Vector<Vector<Object>> getCalendars()
	{
		String calendars = serverConnection.send(jsonCreator.getCalendars());
		this.calendars = jsonCreator.setCalendars(calendars);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i = 0; i<this.calendars.getCalendars().size(); i++) {
			Vector<Object> row = new Vector<Object>();
			row.addElement(this.calendars.getCalendars().get(i).getCalendarname());
			row.addElement(this.calendars.getCalendars().get(i).getUsername());
			data.addElement(row);
		}
		return data;
	}

	public void createCalendar(String calendar, boolean isPublic) {
		serverConnection.send(jsonCreator.createCalendar(calendar, isPublic));
	}

	public void deleteCalendar(String selectedCalendar)
	{
		for(int i = 0; i<this.calendars.getCalendars().size(); i++) {
			if(this.calendars.getCalendars().get(i).getCalendarname().equals(selectedCalendar)) {
				String json = jsonCreator.deleteCalendar(this.calendars.getCalendars().get(i).getCalendarname());
				serverConnection.send(json);
				break;
			}
		}
	}

	public void shareCalendar(String calendar, String username) {
		serverConnection.send(jsonCreator.shareCalendar(username, calendar));
	}

	public Vector<Vector<Object>> getEvents()
	{
		String events = serverConnection.send(jsonCreator.getEvents());
		this.events = jsonCreator.setEvents(events);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i = 0; i<this.events.getEvents().size(); i++) {
			Vector<Object> row = new Vector<Object>();
			row.addElement(this.events.getEvents().get(i).getType());
			row.addElement(this.events.getEvents().get(i).getDescription());
			row.addElement((this.events.getEvents().get(i).getStartdate()).toString().substring(0,16));
			row.addElement((this.events.getEvents().get(i).getEnddate()).toString().substring(0,16));
			row.addElement(this.events.getEvents().get(i).getLocation());
			data.addElement(row);
		}
		return data;
	}

	//	public Vector<Vector<Object>> getNotes()
	//	{
	//		String notes = serverConnection.send(jsonCreator.getNotes());
	//		this.notes = jsonCreator.getNotes(notes);
	//
	//		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	//
	//		for(int i = 0; i<this.notes.getNotes().size(); i++) {
	//			Vector<Object> row = new Vector<Object>();
	//			row.addElement(this.notes.getNotes().get(i).getType()); // TO DO
	//			row.addElement(this.notes.getNotes().get(i).getDescription()); // TO DO
	//			data.addElement(row);
	//		}
	//		return data;
	//	}

	private class LoginPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();

			if(cmd.equals("LoginBtn"))
			{
				serverConnection.connect();

				switch(serverConnection.send(authenticate())) {
				default:
					System.exit(0);
					break;
				case "0":
					screen.getLoginPanel().reset();
					screen.show(Screen.MAINPANEL);
					screen.getMainPanel().setQuote(getQuote());
					screen.getMainPanel().setWeather(getForecast());
					break;
				case "1":
					screen.getLoginPanel().incorrect(1);
					break;
				case "2":
					screen.getLoginPanel().incorrect(2);
					break;
				case "3":
					screen.getLoginPanel().incorrect(3);
					break;
				case "4":
					screen.getLoginPanel().incorrect(4);
					break;
				}
			}
			if(cmd.equals("ExitBtn"))
			{
				System.exit(0);
			}
		}
	}
	private class MainPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();

			if(cmd.equals("CalendarBtn"))
			{
				screen.getCalendarPanel().setEvents(getEvents());
				screen.show(Screen.CALENDARPANEL);
			}

			if(cmd.equals("LogoutBtn"))
			{
				serverConnection.close();
				screen.show(Screen.LOGINPANEL);
			}
		}
	}
	private class CalendarPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();

			if(cmd.equals("CalendarsBtn"))
			{
				screen.getCalendarListPanel().setCalendars(getCalendars());
				screen.show(Screen.CALENDARLISTPANEL);
			}

			if(cmd.equals("BackBtn"))
			{
				screen.show(Screen.MAINPANEL);
			}
		}
	}
	private class CalendarListPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();

			if(cmd.equals("btnCreate"))
			{
				String calendar = screen.getCalendarListPanel().getCalendar();
				boolean isPublic = screen.getCalendarListPanel().getIsPublic();
				createCalendar(calendar, isPublic);
				screen.getCalendarListPanel().setCalendars(getCalendars());
			}

			if(cmd.equals("btnShare"))
			{
				String selectedCalendar = screen.getCalendarListPanel().getSelectedCalendar();
				String username = screen.getCalendarListPanel().getShareWith();
				shareCalendar(selectedCalendar, username);
			}

			if(cmd.equals("btnDelete"))
			{
				String selectedCalendar = screen.getCalendarListPanel().getSelectedCalendar();
				deleteCalendar(selectedCalendar);
				screen.getCalendarListPanel().setCalendars(getCalendars());
			}

			if(cmd.equals("btnBack"))
			{
				screen.show(Screen.MAINPANEL);
			}
		}
	}
}
