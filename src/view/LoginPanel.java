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
	private JLabel lblIncorrect;
	private JTextField email_Login;
	private JPasswordField password_Login;
	private JButton btnLogin;

	public LoginPanel()
	{
		setLayout(null);

		lblNewLabel = new JLabel("Calendar");
		lblNewLabel.setBounds(60, 98, 194, 24);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 19));
		add(lblNewLabel);

		lblPleaseLoginBelow = new JLabel("Please login below:");
		lblPleaseLoginBelow.setBounds(99, 145, 108, 16);
		lblPleaseLoginBelow.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblPleaseLoginBelow);

		lblUserEmail = new JLabel("CBS-Mail:");
		lblUserEmail.setBounds(83, 197, 52, 14);
		lblUserEmail.setFont(new Font("Calibri", Font.PLAIN, 11));
		add(lblUserEmail);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(86, 252, 49, 14);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 11));
		add(lblPassword);

		lblIncorrect = new JLabel("Username or password is incorrect");
		lblIncorrect.setBounds(77, 320, 165, 14);
		lblIncorrect.setFont(new Font("Calibri", Font.ITALIC, 11));
		lblIncorrect.setForeground(Color.red);
		lblIncorrect.setVisible(false);
		add(lblIncorrect);

		email_Login = new JTextField();
		email_Login.setBounds(86, 222, 143, 20);
		email_Login.setColumns(10);
		add(email_Login);

		password_Login = new JPasswordField();
		password_Login.setBounds(86, 276, 143, 20);
		password_Login.setColumns(10);
		add(password_Login);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(118, 446, 89, 23);
		add(btnLogin);
	}

	public void addActionListener(ActionListener l)
	{
		btnLogin.addActionListener(l);
		btnLogin.setActionCommand("LoginBtn");
	}

	public void incorrect()
	{
		lblIncorrect.setVisible(true);
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
		lblIncorrect.setVisible(false);
		password_Login.setText("");
	}
}