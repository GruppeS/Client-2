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

	public Client() {
		encrypt = new Encryption();
		jsonCreator = new JsonCreator();
		serverConnection = new ServerConnection();
		screen = new Screen();
		
		screen.getLoginPanel().addActionListener(new LoginPanelActionListener());		
	}

	public void run() {
		screen.show(Screen.LOGINPANEL);
		screen.setVisible(true);
	}
	
	public String authenticate(){
		
		String email = null;
		String password = null;
		
		try {
			email = screen.getLoginPanel().getEmail_Login();
			password = encrypt.aesEncrypt(screen.getLoginPanel().getPassword_Login());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

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
					serverConnection.connect(authenticate());
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}