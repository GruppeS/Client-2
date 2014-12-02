package model;

import model.jsonClasses.Events;
import model.jsonClasses.Forecasts;
import model.jsonClasses.QOTD;
import model.jsonClasses.UserInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonCreator {
	private Gson gson;
	private UserInfo userInfo;
	private Events events;
	private QOTD qotd;
	private Forecasts forecasts;
	
	public JsonCreator(){
		gson = new GsonBuilder().create();
		userInfo = new UserInfo();
		events = new Events();
		qotd = new QOTD();
		forecasts = new Forecasts();
	}
	
	public String login(String email, String password)
	{
		userInfo.setAuthUserEmail(email);
		userInfo.setAuthUserPassword(password);
		String gsonString = gson.toJson(userInfo);
		return gsonString;
	}
	
	public String getEvents()
	{
		String gsonString = gson.toJson(events);
		return gsonString;
	}
	
	public Events setEvents(String calendar)
	{
		events = gson.fromJson(calendar, Events.class);
		return events;
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