package model.message;

import java.io.Serializable;

import model.enumerate.UserType;

public class LoginMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	public boolean isauthentication = false;
	public String message = "";
	public UserType userType;
	
	public LoginMessage() {}

	public LoginMessage(boolean isauthentication, String message, UserType userType) {
		this.isauthentication = isauthentication;
		this.message = message;
		this.userType = userType;
	}
	
	
}
