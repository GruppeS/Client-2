package model.jsonClasses;

import java.util.ArrayList;

public class Events {
	
	@SuppressWarnings("unused")
	private String overallID = "getEvents";
	public ArrayList<Event> events = new ArrayList<Event>();
 
    public ArrayList<Event> getEvents() {
        return events;
    }
}