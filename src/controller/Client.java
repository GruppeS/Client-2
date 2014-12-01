package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import model.JsonCreator;
import model.ServerConnection;
import model.jsonClasses.Events;
import model.jsonClasses.Forecasts;
import view.Screen;

public class Client implements Runnable {

	private JsonCreator jsonCreator;
	private ServerConnection serverConnection;
	private Screen screen;
	private Events events;
	private Forecasts forecasts;

	public Client() {
		jsonCreator = new JsonCreator();
		serverConnection = new ServerConnection();
		screen = new Screen();

		screen.getLoginPanel().addActionListener(new LoginPanelActionListener());
		screen.getMainPanel().addActionListener(new MainPanelActionListener());
	}

	public void run() {

		screen.show(Screen.LOGINPANEL);
		screen.setVisible(true);
	}

	public String authenticate(){

		String email = null;
		String password = null;

		email = screen.getLoginPanel().getEmail_Login();
		password = screen.getLoginPanel().getPassword_Login();

		return jsonCreator.login(email, password);
	}

	public void getCalendar()
	{
		String calendar = serverConnection.send(jsonCreator.setCalendar());
		events = jsonCreator.getEvents(calendar);
	}

	public String getQuote()
	{
		String qotd = serverConnection.send(jsonCreator.setQOTD());
		qotd = jsonCreator.getQOTD(qotd);
		return qotd;
	}
	
	public Vector<Vector<Object>> getForecast()
	{
		String forecast = serverConnection.send(jsonCreator.setForecast());
		forecasts = jsonCreator.getForecast(forecast);
		
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
//					getCalendar();
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
		}
	}
	private class MainPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();
			
			if(cmd.equals("LogoutBtn"))
			{
				serverConnection.close();
				screen.show(Screen.LOGINPANEL);
			}
		}
	}
}
