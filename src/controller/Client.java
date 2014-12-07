package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import model.JsonCreator;
import model.ServerConnection;
import model.jsonClasses.Calendars;
import model.jsonClasses.Events;
import model.jsonClasses.Forecasts;
import model.jsonClasses.UserInfo;
import view.Screen;

public class Client implements Runnable {

	private JsonCreator jsonCreator;
	private ServerConnection serverConnection;
	private Screen screen;
	private Calendars calendars;
	private Events events;
	private Forecasts forecasts;
	private UserInfo userInfo;
	
	private String eventInCalendar;

	public Client() {
		jsonCreator = new JsonCreator();
		serverConnection = new ServerConnection();
		screen = new Screen();

		screen.getLoginPanel().addActionListener(new LoginPanelActionListener());
		screen.getMainPanel().addActionListener(new MainPanelActionListener());
		screen.getCalendarPanel().addActionListener(new CalendarPanelActionListener());
		screen.getCalendarListPanel().addActionListener(new CalendarListPanelActionListener());
		screen.getEventListPanel().addActionListener(new EventListPanelActionListener());
	}

	public void run() {
		
		screen.show(Screen.LOGINPANEL);
		screen.setVisible(true);
	}

	public String authenticate(){
		String username = null;
		String password = null;
		
		serverConnection.connect();

		username = screen.getLoginPanel().getUsername_Login();
		password = screen.getLoginPanel().getPassword_Login();

		String authenticated = serverConnection.send(jsonCreator.getLogin(username, password));
		
		userInfo = jsonCreator.setLogin(authenticated);
		
		return userInfo.getAuthenticated();
	}

	public String getQuote() {
		String qotd = serverConnection.send(jsonCreator.getQOTD());
		qotd = jsonCreator.setQOTD(qotd);
		return qotd;
	}

	public Vector<Vector<Object>> getForecast() {
		String forecast = serverConnection.send(jsonCreator.getForecast());
		forecasts = jsonCreator.setForecast(forecast);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i = 0; i<forecasts.forecasts.size(); i++) {
			Vector<Object> row = new Vector<Object>();
			row.addElement(forecasts.forecasts.get(i).getDate().substring(0,10));
			row.addElement(forecasts.forecasts.get(i).getCelsius());
			row.addElement(forecasts.forecasts.get(i).getDesc());
			data.addElement(row);
		}
		return data;
	}

	public Vector<Vector<Object>> getCalendars() {
		String calendars = serverConnection.send(jsonCreator.getCalendars());
		this.calendars = jsonCreator.setCalendars(calendars);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i = 0; i<this.calendars.calendars.size(); i++) {
			Vector<Object> row = new Vector<Object>();
			row.addElement(this.calendars.calendars.get(i).getCalendarname());
			row.addElement(this.calendars.calendars.get(i).getUsername());
			data.addElement(row);
		}
		return data;
	}

	public void createCalendar(String calendar, boolean isPublic) {
		serverConnection.send(jsonCreator.createCalendar(calendar, isPublic));
	}

	public void deleteCalendar(String selectedCalendar) {
		for(int i = 0; i<this.calendars.calendars.size(); i++) {
			if(this.calendars.calendars.get(i).getCalendarname().equals(selectedCalendar)) {
				String json = jsonCreator.deleteCalendar(this.calendars.calendars.get(i).getCalendarname());
				serverConnection.send(json);
				break;
			}
		}
	}

	public void shareCalendar(String calendar, String username) {
		serverConnection.send(jsonCreator.shareCalendar(username, calendar));
	}

	public Vector<Vector<Object>> getEvents(boolean allEvents, String fromCalendar) {
		String events;

		if(allEvents){
			events = serverConnection.send(jsonCreator.getEvents());
		} else {
			events = serverConnection.send(jsonCreator.getCustomEvents(fromCalendar));
		}
		this.events = jsonCreator.setEvents(events);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i = 0; i<this.events.events.size(); i++) {
			Vector<Object> row = new Vector<Object>();
			row.addElement(this.events.events.get(i).getEventid());
			row.addElement(this.events.events.get(i).getType());
			row.addElement(this.events.events.get(i).getDescription());
			row.addElement((this.events.events.get(i).getStartdate()).toString().substring(0,16));
			row.addElement((this.events.events.get(i).getEnddate()).toString().substring(0,16));
			row.addElement(this.events.events.get(i).getLocation());
			row.addElement(this.events.events.get(i).getNote());
			data.addElement(row);
		}
		return data;
	}

	public void createEvent(String description, String start, String end, String location) {
		try {
			Date startDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(start);
			Date endDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(end);

			serverConnection.send(jsonCreator.createEvent(description, location, startDate, endDate, eventInCalendar));

		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
	
	public void deleteEvent(String eventID) {
		serverConnection.send(jsonCreator.deleteEvent(eventID));
	}
	
	public void createNote(String eventID, String note) {
		serverConnection.send(jsonCreator.createNote(eventID, note));
	}
	
	public void deleteNote(String eventID) {
		serverConnection.send(jsonCreator.deleteNote(eventID));
	}

	private class LoginPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();

			if(cmd.equals("LoginBtn"))
			{
				switch(authenticate()) {
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
				screen.getCalendarPanel().setEvents(getEvents(true, null));
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

			if(cmd.equals("btnCalendars"))
			{
				screen.getCalendarListPanel().setCalendars(getCalendars());
				screen.show(Screen.CALENDARLISTPANEL);
			}

			if(cmd.equals("btnBack"))
			{
				screen.show(Screen.MAINPANEL);
			}
			
			if(cmd.equals("btnAddNote")) {
				String selectedEvent = screen.getCalendarPanel().getSelectedEvent();
				String note = screen.getCalendarPanel().getNote();
				createNote(selectedEvent, note);
				screen.getCalendarPanel().setEvents(getEvents(true, null));
			}
			
			if(cmd.equals("btnDeleteNote")) {
				String selectedEvent = screen.getCalendarPanel().getSelectedEvent();
				deleteNote(selectedEvent);
				screen.getCalendarPanel().setEvents(getEvents(true, null));
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

			if(cmd.equals("btnEvents"))
			{
				eventInCalendar = screen.getCalendarListPanel().getSelectedCalendar();

				screen.getEventListPanel().setEvents(getEvents(false, eventInCalendar));

				screen.setFrame(500, 525);
				screen.show(Screen.EVENTLISTPANEL);
			}

			if(cmd.equals("btnBack"))
			{
				screen.show(Screen.MAINPANEL);
			}
		}
	}
	private class EventListPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();

			if(cmd.equals("btnAdd"))
			{
				String description = screen.getEventListPanel().getDescription();
				String start = screen.getEventListPanel().getStart();
				String end = screen.getEventListPanel().getEnd();
				String location = screen.getEventListPanel().getEventLocation();
				
				createEvent(description, start, end, location);
				
				screen.getEventListPanel().setEvents(getEvents(false, eventInCalendar));
			}
			if(cmd.equals("btnDelete"))
			{
				String selectedEvent = screen.getEventListPanel().getSelectedEvent();
				
				deleteEvent(selectedEvent);
				
				screen.getEventListPanel().setEvents(getEvents(false, eventInCalendar));
			}
			if(cmd.equals("btnBack"))
			{
				screen.show(Screen.CALENDARLISTPANEL);
			}
		}
	}
}
