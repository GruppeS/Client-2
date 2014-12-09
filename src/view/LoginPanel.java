package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel{ 
	private static final long serialVersionUID = 1L;

	private JLabel lblCalendar;
	private JLabel lblPleaseLoginBelow;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblIncorrectMail;
	private JLabel lblIncorrectPassword;
	private JLabel lblIncorrectPlatform;
	private JLabel lblIncorrectActive;
	private JTextField email_Login;
	private JPasswordField password_Login;
	private JButton btnLogin;
	private JButton btnExit;
	

	public LoginPanel()
	{
		setLayout(null);

		lblCalendar = new JLabel("Calendar");
		lblCalendar.setBounds(155, 74, 68, 24);
		lblCalendar.setFont(new Font("Calibri", Font.PLAIN, 19));
		add(lblCalendar);

		lblPleaseLoginBelow = new JLabel("Please login below:");
		lblPleaseLoginBelow.setBounds(137, 132, 108, 16);
		lblPleaseLoginBelow.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblPleaseLoginBelow);

		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(121, 174, 102, 14);
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 11));
		add(lblUsername);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(121, 229, 89, 14);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 11));
		add(lblPassword);

		lblIncorrectMail = new JLabel("Mail is incorrect");
		lblIncorrectMail.setBounds(161, 284, 234, 14);
		lblIncorrectMail.setFont(new Font("Calibri", Font.ITALIC, 11));
		lblIncorrectMail.setForeground(Color.red);
		lblIncorrectMail.setVisible(false);
		add(lblIncorrectMail);
		
		lblIncorrectPassword = new JLabel("Password is incorrect");
		lblIncorrectPassword.setBounds(148, 298, 234, 14);
		lblIncorrectPassword.setFont(new Font("Calibri", Font.ITALIC, 11));
		lblIncorrectPassword.setForeground(Color.red);
		lblIncorrectPassword.setVisible(false);
		add(lblIncorrectPassword);
		
		lblIncorrectPlatform = new JLabel("Platform is incorrect");
		lblIncorrectPlatform.setBounds(148, 323, 234, 14);
		lblIncorrectPlatform.setFont(new Font("Calibri", Font.ITALIC, 11));
		lblIncorrectPlatform.setForeground(Color.red);
		lblIncorrectPlatform.setVisible(false);
		add(lblIncorrectPlatform);
		
		lblIncorrectActive = new JLabel("User is not active");
		lblIncorrectActive.setBounds(158, 310, 237, 14);
		lblIncorrectActive.setFont(new Font("Calibri", Font.ITALIC, 11));
		lblIncorrectActive.setForeground(Color.red);
		lblIncorrectActive.setVisible(false);
		add(lblIncorrectActive);

		email_Login = new JTextField();
		email_Login.setBounds(121, 199, 143, 20);
		email_Login.setColumns(10);
		add(email_Login);

		password_Login = new JPasswordField();
		password_Login.setBounds(121, 253, 143, 20);
		password_Login.setColumns(10);
		add(password_Login);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(152, 348, 89, 23);
		add(btnLogin);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(281, 22, 89, 23);
		add(btnExit);
	}

	public void addActionListener(ActionListener l)
	{
		btnLogin.addActionListener(l);
		btnLogin.setActionCommand("LoginBtn");
		btnExit.addActionListener(l);
		btnExit.setActionCommand("ExitBtn");
	}

	/**
	 * Shows error labels
	 * @param action
	 */
	public void incorrect(int action)
	{
		switch (action){
		case 1:
			lblIncorrectMail.setVisible(true);
			break;
		case 2:
			lblIncorrectActive.setVisible(true);
			break;
		case 3:
			lblIncorrectPassword.setVisible(true);
			break;
		case 4:
			lblIncorrectPlatform.setVisible(true);
			break;
		}
	}

	public String getUsername_Login()
	{
		return email_Login.getText();
	}

	@SuppressWarnings("deprecation")
	public String getPassword_Login()
	{
		return password_Login.getText();
	}

	public void reset()
	{
		lblIncorrectMail.setVisible(false);
		lblIncorrectPassword.setVisible(false);
		lblIncorrectPlatform.setVisible(false);
		lblIncorrectActive.setVisible(false);
		password_Login.setText("");
	}
}