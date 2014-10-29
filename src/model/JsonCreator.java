package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonCreator {
	private Gson gson;
	private UserInfo userInfo;
	
	public JsonCreator(){
		gson = new GsonBuilder().create();
		userInfo = new UserInfo();
	}
	
	public String login(String email, String password)
	{
		userInfo.setAuthUserEmail(email);
		userInfo.setAuthUserPassword(password);
		userInfo.setAuthUserIsAdmin(false);
		String gsonString = gson.toJson(userInfo);
		return gsonString;
	}
}