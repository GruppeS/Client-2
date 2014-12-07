package model;

import java.util.Date;

import model.jsonClasses.Calendar;
import model.jsonClasses.Calendars;
import model.jsonClasses.Event;
import model.jsonClasses.Events;
import model.jsonClasses.Forecasts;
import model.jsonClasses.QOTD;
import model.jsonClasses.UserInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonCreator {
	private Gson gson;
	private UserInfo userInfo;
	private Calendars calendars;
	private Events events;
	private QOTD qotd;
	private Forecasts forecasts;

	public JsonCreator(){
		gson = new GsonBuilder().create();
		userInfo = new UserInfo();
		calendars = new Calendars();
		events = new Events();
		qotd = new QOTD();
		forecasts = new Forecasts();
	}

	public String getLogin(String username, String password)
	{
		userInfo.setUsername(username);
		userInfo.setPassword(password);
		String gsonString = gson.toJson(userInfo);
		return gsonString;
	}
	
	public UserInfo setLogin(String authenticated) {
		this.userInfo = gson.fromJson(authenticated, UserInfo.class);
		return this.userInfo;
	}

	public String getCalendars()
	{
		String gsonString = gson.toJson(calendars);
		return gsonString;
	}

	public Calendars setCalendars(String calendars)
	{
		this.calendars = gson.fromJson(calendars, Calendars.class);
		return this.calendars;
	}

	public String createCalendar(String calendarName, boolean isPublic) {
		Calendar calendar = new Calendar(calendarName, null, isPublic);
		calendar.setOverallID("createCalendar");
		String gsonString = gson.toJson(calendar);
		return gsonString;
	}

	public String deleteCalendar(String selectedCalendar)
	{
		Calendar calendar = new Calendar(selectedCalendar, null, true);
		calendar.setOverallID("deleteCalendar");
		String gsonString = gson.toJson(calendar);
		return gsonString;
	}

	public String shareCalendar(String username, String calendarToShare)
	{
		Calendar calendar = new Calendar(calendarToShare, null, true);
		calendar.setOverallID("shareCalendar");
		calendar.setShareWith(username);
		String gsonString = gson.toJson(calendar);
		return gsonString;
	}

	public String getEvents()
	{
		String gsonString = gson.toJson(events);
		return gsonString;
	}

	public Events setEvents(String events)
	{
		this.events = gson.fromJson(events, Events.class);
		return this.events;
	}
	
	public String getCustomEvents(String calendar)
	{
		Event event = new Event(null, null, null, null, null, null, null);
		event.setCalendar(calendar);
		event.setOverallID("getCustomEvents");
		String gsonString = gson.toJson(event);
		return gsonString;
	}
	
	public String createEvent(String description, String location, Date start, Date end, String calendar)
	{
		Event event = new Event(null, null, null, description, location, null, null);
		event.setStartdate(start);
		event.setEnddate(end);
		event.setCalendar(calendar);
		event.setOverallID("createEvent");
		String gsonString = gson.toJson(event);
		return gsonString;
	}

	public String deleteEvent(String eventID)
	{
		Event event = new Event(null, eventID, null, null, null, null, null);
		event.setOverallID("deleteEvent");
		String gsonString = gson.toJson(event);
		return gsonString;
	}
	
	public String createNote(String eventID, String note)
	{
		Event event = new Event(null, eventID, null, null, null, null, null);
		event.setNote(note);
		event.setOverallID("createNote");
		String gsonString = gson.toJson(event);
		return gsonString;
	}
	
	public String deleteNote(String eventID)
	{
		Event event = new Event(null, eventID, null, null, null, null, null);
		event.setOverallID("deleteNote");
		String gsonString = gson.toJson(event);
		return gsonString;
	}

	public String getQOTD()
	{
		String gsonString = gson.toJson(qotd);
		return gsonString;
	}

	public String setQOTD(String quote)
	{
		qotd = gson.fromJson(quote, QOTD.class);
		return qotd.getQuote();
	}

	public String getForecast()
	{
		String gsonString = gson.toJson(forecasts);
		return gsonString;
	}

	public Forecasts setForecast(String forecasts)
	{
		this.forecasts = gson.fromJson(forecasts, Forecasts.class);
		return this.forecasts;
	}
}