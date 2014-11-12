package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel
{ 
	private static final long serialVersionUID = 1L;

	private JLabel lblNewLabel;
	private JLabel lblPleaseLoginBelow;
	private JLabel lblUserEmail;
	private JLabel lblPassword;
	private JLabel lblIncorrectMail;
	private JLabel lblIncorrectPassword;
	private JLabel lblIncorrectPlatform;
	private JLabel lblIncorrectActive;
	private JTextField email_Login;
	private JPasswordField password_Login;
	private JButton btnLogin;
	

	public LoginPanel()
	{
		setLayout(null);

		lblNewLabel = new JLabel("Calendar");
		lblNewLabel.setBounds(114, 75, 68, 24);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 19));
		add(lblNewLabel);

		lblPleaseLoginBelow = new JLabel("Please login below:");
		lblPleaseLoginBelow.setBounds(96, 133, 108, 16);
		lblPleaseLoginBelow.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblPleaseLoginBelow);

		lblUserEmail = new JLabel("CBS-Mail:");
		lblUserEmail.setBounds(77, 175, 52, 14);
		lblUserEmail.setFont(new Font("Calibri", Font.PLAIN, 11));
		add(lblUserEmail);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(80, 230, 49, 14);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 11));
		add(lblPassword);

		lblIncorrectMail = new JLabel("Mail is incorrect");
		lblIncorrectMail.setBounds(108, 295, 74, 14);
		lblIncorrectMail.setFont(new Font("Calibri", Font.ITALIC, 11));
		lblIncorrectMail.setForeground(Color.red);
		lblIncorrectMail.setVisible(false);
		add(lblIncorrectMail);
		
		lblIncorrectPassword = new JLabel("Password is incorrect");
		lblIncorrectPassword.setBounds(108, 295, 74, 14);
		lblIncorrectPassword.setFont(new Font("Calibri", Font.ITALIC, 11));
		lblIncorrectPassword.setForeground(Color.red);
		lblIncorrectPassword.setVisible(false);
		add(lblIncorrectPassword);
		
		lblIncorrectPlatform = new JLabel("Platform is incorrect");
		lblIncorrectPlatform.setBounds(108, 295, 74, 14);
		lblIncorrectPlatform.setFont(new Font("Calibri", Font.ITALIC, 11));
		lblIncorrectPlatform.setForeground(Color.red);
		lblIncorrectPlatform.setVisible(false);
		add(lblIncorrectPlatform);
		
		lblIncorrectActive = new JLabel("User is not active");
		lblIncorrectActive.setBounds(108, 295, 74, 14);
		lblIncorrectActive.setFont(new Font("Calibri", Font.ITALIC, 11));
		lblIncorrectActive.setForeground(Color.red);
		lblIncorrectActive.setVisible(false);
		add(lblIncorrectActive);

		email_Login = new JTextField();
		email_Login.setBounds(80, 200, 143, 20);
		email_Login.setColumns(10);
		add(email_Login);

		password_Login = new JPasswordField();
		password_Login.setBounds(80, 254, 143, 20);
		password_Login.setColumns(10);
		add(password_Login);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(115, 331, 89, 23);
		add(btnLogin);
	}

	public void addActionListener(ActionListener l)
	{
		btnLogin.addActionListener(l);
		btnLogin.setActionCommand("LoginBtn");
	}

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

	public String getEmail_Login()
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