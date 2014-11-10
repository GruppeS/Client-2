package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import model.Encryption;
import model.JsonCreator;
import model.ServerConnection;
import view.Screen;

public class Client implements Runnable {

	private Encryption encrypt;
	private JsonCreator jsonCreator;
	private ServerConnection serverConnection;
	private Screen screen;
	
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
		
		try {
			serverConnection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		screen.show(Screen.LOGINPANEL);
		screen.setVisible(true);
		
		while(true)
		{
			if(authenticated)
			{
//				screen.show(Screen.MAINMENU);
				break;
			}
		}
	}
	
	public String authenticate(){
		
		String email = null;
		String password = null;
		
		try {
			email = screen.getLoginPanel().getEmail_Login();
			password = encrypt.aesEncrypt(screen.getLoginPanel().getPassword_Login());
			System.out.println("Password AES encrypted");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Creating json");
		return jsonCreator.login(email, password);
	}

	private class LoginPanelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();

			if(cmd.equals("LoginBtn"))
			{
				try {			
					switch(serverConnection.login(authenticate())) {
					
					default:
						authenticated = false;
						break;
					case "0":
						authenticated = true;
						break;
					case "1":
						authenticated = false;
						break;
					case "2":
						authenticated = false;
						break;
					case "3":
						authenticated = false;
						break;
					case "4":
						authenticated = false;
						break;
					}
					
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}