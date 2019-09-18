package model.message;

import java.io.Serializable;

public class RegisterMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	public boolean check;
	public String login;
	public String message;
	
	public RegisterMessage() {}
	public RegisterMessage(boolean check,String message, String login) {
		this.check = check;
		this.login = login;
		this.message = message;
	}
}
