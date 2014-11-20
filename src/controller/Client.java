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

	private boolean authenticated;

	public Client() {
		encrypt = new Encryption();
		jsonCreator = new JsonCreator();
		serverConnection = new ServerConnection();
		screen = new Screen();
		authenticated = false;

		screen.getLoginPanel().addActionListener(new LoginPanelActionListener());	
	}

	public void run() {

		screen.show(Screen.LOGINPANEL);
		screen.setVisible(true);

		try {
			serverConnection.connect();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public String authenticate(){

		String email = null;
		String password = null;

		try {
			email = screen.getLoginPanel().getEmail_Login();
			password = screen.getLoginPanel().getPassword_Login();
//			password = encrypt.aesEncrypt(password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return jsonCreator.login(email, password);
	}

	public void getCalendar() throws ClassNotFoundException, IOException
	{
			String calendar = serverConnection.send(jsonCreator.getCalendar());
			events = jsonCreator.getEvents(calendar);
	}


	private class LoginPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();

			if(cmd.equals("LoginBtn"))
			{
				try {			
					switch(serverConnection.send(authenticate())) {

					default:
						authenticated = false;
						System.exit(0);
						break;
					case "0":
						authenticated = true;
						getCalendar();
						screen.getLoginPanel().reset();
						break;
					case "1":
						authenticated = false;
						screen.getLoginPanel().incorrect(1);
						break;
					case "2":
						authenticated = false;
						screen.getLoginPanel().incorrect(2);
						break;
					case "3":
						authenticated = false;
						screen.getLoginPanel().incorrect(3);
						break;
					case "4":
						authenticated = false;
						screen.getLoginPanel().incorrect(4);
						break;
					}
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}