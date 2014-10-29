package model;

import java.io.Serializable;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String overallID = "logIn";
	private String email;
	private String password;
	private boolean isAdmin;

	public void setAuthUserEmail(String email) {
		this.email = email;
	}
	public void setAuthUserPassword(String password) {
		this.password = password;
	}
	public void setAuthUserIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}