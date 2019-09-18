package model.message;

import java.io.Serializable;

public class SuspendMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean check;
	public String message;
	public SuspendMessage() {}
	public SuspendMessage(boolean check, String message) {
		this.check = check;
		this.message = message;
	}
	
}
