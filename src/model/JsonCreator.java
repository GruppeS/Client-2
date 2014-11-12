package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonCreator {
	private Gson gson;
	private UserInfo userInfo;
	private GetCalendar getCalendar;
	
	public JsonCreator(){
		gson = new GsonBuilder().create();
		userInfo = new UserInfo();
		getCalendar = new GetCalendar();
	}
	
	public String login(String email, String password)
	{
		userInfo.setAuthUserEmail(email);
		userInfo.setAuthUserPassword(password);
		String gsonString = gson.toJson(userInfo);
		return gsonString;
	}
	
	public String getCalendar()
	{
		String gsonString = gson.toJson(getCalendar);
		return gsonString;
	}
}