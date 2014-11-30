package model.jsonClasses;

import java.util.ArrayList;

public class Events {
	
	private String overallID = "getCalendar";
	ArrayList<Event> events = new ArrayList<Event>();
 
    public ArrayList<Event> getEvents() {
        return events;
    }
    public void setEvents(ArrayList<Event> event) {
        this.events = event;
    }
}