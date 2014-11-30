package model.jsonClasses;

import java.util.ArrayList;

public class Forecasts {
	
	private String overallID = "getForecast";
	ArrayList<Forecast> forecasts = new ArrayList<Forecast>();
	
	public ArrayList<Forecast> getForecasts() {
		return forecasts;
	}
	public void setForcasts(ArrayList<Forecast> forecast) {
		this.forecasts = forecast;
	}
}
