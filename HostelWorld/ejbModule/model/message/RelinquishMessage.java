package model.message;

import java.io.Serializable;

public class RelinquishMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean check;
	public String message;
	public RelinquishMessage() {}
	public RelinquishMessage(boolean check, String message) {
		this.check = check;
		this.message = message;
	}
	
}
