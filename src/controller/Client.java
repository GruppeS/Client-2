package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	/**
	 * Adds actionlisteners to inner classes
	 */
	public Client() {
		jsonCreator = new JsonCreator();
		serverConnection = new ServerConnection();
		screen = new Screen();

		screen.getLoginPanel().addActionListener(new LoginPanelActionListener());
		screen.getMainPanel().addActionListener(new MainPanelActionListener());
		screen.getCalendarPanel().addActionListener(new CalendarPanelActionListener());
		screen.getCalendarDayPanel().addActionListener(new CalendarDayPanelActionListener());
		screen.getCalendarWeekPanel().addActionListener(new CalendarWeekPanelActionListener());
		screen.getCalendarListPanel().addActionListener(new CalendarListPanelActionListener());
		screen.getEventListPanel().addActionListener(new EventListPanelActionListener());
	}

	/**
	 * Shows loginpanel and sets frame visible
	 */
	public void run() {

		screen.show(Screen.LOGINPANEL);
		screen.setVisible(true);
	}

	/**
	 * Sends login information
	 * @return Authentictated int
	 */
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

	/**
	 * Asks server for qotd
	 * @return qotd
	 */
	public String getQuote() {
		String qotd = serverConnection.send(jsonCreator.getQOTD());
		qotd = jsonCreator.setQOTD(qotd);
		return qotd;
	}

	/**
	 * Asks server for forecasts
	 * @return Vector<Vector<Object>> containing forecasts
	 */
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

	/**
	 * Asks server for calendars
	 * @return Vector<Vector<Object>> containing forecasts
	 */
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

	/**
	 * Send create calendar
	 * @param calendar
	 * @param isPublic
	 */
	public void createCalendar(String calendar, boolean isPublic) {
		serverConnection.send(jsonCreator.createCalendar(calendar, isPublic));
	}

	/**
	 * Sends delete calendar
	 * @param selectedCalendar
	 */
	public void deleteCalendar(String selectedCalendar) {
		for(int i = 0; i<this.calendars.calendars.size(); i++) {
			if(this.calendars.calendars.get(i).getCalendarname().equals(selectedCalendar)) {
				String json = jsonCreator.deleteCalendar(this.calendars.calendars.get(i).getCalendarname());
				serverConnection.send(json);
				break;
			}
		}
	}

	/**
	 * Shares calendar
	 * @param calendar
	 * @param username
	 */
	public void shareCalendar(String calendar, String username) {
		serverConnection.send(jsonCreator.shareCalendar(username, calendar));
	}

	/**
	 * Asks for all events
	 * @return Vector<Vector<Object>> containing all events
	 */
	public Vector<Vector<Object>> getEvents() {

		String events = serverConnection.send(jsonCreator.getEvents());

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

	/**
	 * Asks for all events, sorts them into this day
	 * @return Vector<Vector<Object>> containing this days events
	 */
	public Vector<Vector<Object>> getDayEvents() {

		Date todaysDate = new Date();
		String today = todaysDate.toString().substring(0,10);

		String events = serverConnection.send(jsonCreator.getEvents());

		this.events = jsonCreator.setEvents(events);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i = 0; i<this.events.events.size(); i++) {

			String eventStart = this.events.events.get(i).getStartdate().toString().substring(0,10);

			if(eventStart.equals(today)){
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
		}
		return data;
	}

	/**
	 * Asks for all events´, sorts them into the next 7 days
	 * @return ArrayList<Vector<Vector<Object>>> containing vectors containing events
	 */
	public ArrayList<Vector<Vector<Object>>> getWeekEvents() {

		String events = serverConnection.send(jsonCreator.getEvents());
		this.events = jsonCreator.setEvents(events);

		ArrayList<Vector<Vector<Object>>> week = new ArrayList<Vector<Vector<Object>>>();

		long hourInMilliseconds = 60L * 60L * 1000L;

		for(int count = 0; count <= 6; count++) {
			Date todaysDate = new Date();
			Date newDate = new Date(todaysDate.getTime() + (24L * count) * hourInMilliseconds);
			String newDateString = newDate.toString().substring(0,10);

			Vector<Vector<Object>> data = new Vector<Vector<Object>>();

			for(int i = 0; i<this.events.events.size(); i++) {

				String eventStart = this.events.events.get(i).getStartdate().toString().substring(0,10);

				if(eventStart.equals(newDateString)){
					Vector<Object> row = new Vector<Object>();
					row.addElement(this.events.events.get(i).getEventid());
					row.addElement(this.events.events.get(i).getType());
					row.addElement(this.events.events.get(i).getDescription());
					row.addElement((this.events.events.get(i).getStartdate()).toString().substring(0,16));
					row.addElement((this.events.events.get(i).getEnddate()).toString().substring(0,16));
					row.addElement(this.events.events.get(i).getLocation());
					row.addElement(this.events.events.get(i).getNote());
					data.addElement(row);
					week.add(data);
				}	
			}
		}
		return week;
	}

	/**
	 * Asks for custom events from specific calendar
	 * @param fromCalendar
	 * @return Vector<Vector<Object>> containing only custom events
	 */
	public Vector<Vector<Object>> getCustomEvents(String fromCalendar) {
		String events = serverConnection.send(jsonCreator.getCustomEvents(fromCalendar));

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

	/**
	 * Sends create event
	 * @param description
	 * @param start
	 * @param end
	 * @param location
	 */
	public void createEvent(String description, String start, String end, String location) {
		try {
			Date startDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(start);
			Date endDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(end);

			serverConnection.send(jsonCreator.createEvent(description, location, startDate, endDate, eventInCalendar));

		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Sends delete event
	 * @param eventID
	 */
	public void deleteEvent(String eventID) {
		serverConnection.send(jsonCreator.deleteEvent(eventID));
	}

	/**
	 * sends create note
	 * @param eventID
	 * @param note
	 */
	public void createNote(String eventID, String note) {
		serverConnection.send(jsonCreator.createNote(eventID, note));
	}

	/**
	 * sends delete note
	 * @param eventID
	 */
	public void deleteNote(String eventID) {
		serverConnection.send(jsonCreator.deleteNote(eventID));
	}

	/**
	 * Inner class for loginpanel actionlistener
	 */
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
	/**
	 * Inner class for mainpanel actionlistener
	 */
	private class MainPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();

			if(cmd.equals("CalendarBtn"))
			{
				screen.getCalendarDayPanel().setEvents(getDayEvents());
				screen.setFrame(750, 425);
				screen.show(Screen.CALENDARDAYPANEL);
			}

			if(cmd.equals("LogoutBtn"))
			{
				serverConnection.close();
				screen.show(Screen.LOGINPANEL);
			}
		}
	}
	/**
	 * Inner class for calendarpanel actionlistener
	 */
	private class CalendarPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();

			if(cmd.equals("btnCalendars"))
			{
				screen.resetFrame();
				screen.getCalendarListPanel().setCalendars(getCalendars());
				screen.show(Screen.CALENDARLISTPANEL);
			}

			if(cmd.equals("btnBack"))
			{
				screen.resetFrame();
				screen.show(Screen.MAINPANEL);
			}

			if(cmd.equals("btnAddNote")) {
				String selectedEvent = screen.getCalendarPanel().getSelectedEvent();
				String note = screen.getCalendarPanel().getNote();
				createNote(selectedEvent, note);
				screen.getCalendarPanel().setEvents(getEvents());
			}

			if(cmd.equals("btnDeleteNote")) {
				String selectedEvent = screen.getCalendarPanel().getSelectedEvent();
				deleteNote(selectedEvent);
				screen.getCalendarPanel().setEvents(getEvents());
			}
		}
	}
	/**
	 * Inner class for calendardaypanel actionlistener
	 */
	private class CalendarDayPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();

			if(cmd.equals("btnCalendars"))
			{
				screen.resetFrame();
				screen.getCalendarListPanel().setCalendars(getCalendars());
				screen.show(Screen.CALENDARLISTPANEL);
			}

			if(cmd.equals("btnBack"))
			{
				screen.show(Screen.MAINPANEL);
			}

			if(cmd.equals("btnAll"))
			{
				screen.getCalendarPanel().setEvents(getEvents());
				screen.show(Screen.CALENDARPANEL);
			}

			if(cmd.equals("btnWeekDay"))
			{
				screen.setFrame(780, 600);
				screen.getCalendarWeekPanel().setEvents(getWeekEvents());
				screen.show(Screen.CALENDARWEEKPANEL);
			}

			if(cmd.equals("btnAddNote")) {
				String selectedEvent = screen.getCalendarDayPanel().getSelectedEvent();
				String note = screen.getCalendarDayPanel().getNote();
				createNote(selectedEvent, note);
				screen.getCalendarDayPanel().setEvents(getDayEvents());
			}

			if(cmd.equals("btnDeleteNote")) {
				String selectedEvent = screen.getCalendarDayPanel().getSelectedEvent();
				deleteNote(selectedEvent);
				screen.getCalendarDayPanel().setEvents(getDayEvents());
			}
		}
	}
	/**
	 * Inner class for calendarweekpanel actionlistener
	 */
	private class CalendarWeekPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			if(cmd.equals("btnWeekDay"))
			{
				screen.setFrame(750, 425);
				screen.getCalendarDayPanel().setEvents(getDayEvents());
				screen.show(Screen.CALENDARDAYPANEL);
			}
		}
	}
	/**
	 * Inner class for calendarlistpanel actionlistener
	 */
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

				screen.getEventListPanel().setEvents(getCustomEvents(eventInCalendar));

				screen.setFrame(500, 525);
				screen.show(Screen.EVENTLISTPANEL);
			}

			if(cmd.equals("btnBack"))
			{
				screen.show(Screen.MAINPANEL);
			}
		}
	}
	/**
	 * Inner class for eventlistpanel actionlistener
	 */
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

				screen.getEventListPanel().setEvents(getCustomEvents(eventInCalendar));
			}
			if(cmd.equals("btnDelete"))
			{
				String selectedEvent = screen.getEventListPanel().getSelectedEvent();

				deleteEvent(selectedEvent);

				screen.getEventListPanel().setEvents(getCustomEvents(eventInCalendar));
			}
			if(cmd.equals("btnBack"))
			{
				screen.resetFrame();
				screen.show(Screen.CALENDARLISTPANEL);
			}
		}
	}
}
