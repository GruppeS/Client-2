package model;

import model.jsonClasses.GetCalendar;
import model.jsonClasses.QOTD;
import model.jsonClasses.UserInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonCreator {
	private Gson gson;
	private UserInfo userInfo;
	private GetCalendar getCalendar;
	private Events events;
	private QOTD qotd;
	
	public JsonCreator(){
		gson = new GsonBuilder().create();
		userInfo = new UserInfo();
		getCalendar = new GetCalendar();
		events = new Events();
		qotd = new QOTD();
	}
	
	public String login(String email, String password)
	{
		userInfo.setAuthUserEmail(email);
		userInfo.setAuthUserPassword(password);
		String gsonString = gson.toJson(userInfo);
		return gsonString;
	}
	
	public String getCalendar()
	{
		String gsonString = gson.toJson(getCalendar);
		return gsonString;
	}
	
	public Events getEvents(String calendar)
	{
		events = gson.fromJson(calendar, Events.class);
		return events;
	}
	
	public String getQOTD()
	{
		String gsonString = gson.toJson(qotd);
		return gsonString;
	}
	
	public String getQuote(String quote)
	{
		qotd = gson.fromJson(quote, QOTD.class);
		return qotd.getQuote();
	}
}