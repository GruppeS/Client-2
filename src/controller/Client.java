package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import model.Encryption;
import model.Events;
import model.JsonCreator;
import model.ServerConnection;
import view.Screen;

public class Client implements Runnable {

	private Encryption encrypt;
	private JsonCreator jsonCreator;
	private ServerConnection serverConnection;
	private Screen screen;
	private Events events;

	public Client() {
		encrypt = new Encryption();
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
		String calendar = serverConnection.send(jsonCreator.getCalendar());
		events = jsonCreator.getEvents(calendar);
	}

	public String getQuote()
	{
		String qotd = serverConnection.send(jsonCreator.getQOTD());
		qotd = jsonCreator.getQuote(qotd);
		return qotd;
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
