package model.message;

import java.io.Serializable;

public class ActivateMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean check;
	public String message;

	
	public ActivateMessage() {
		super();
	}
	public ActivateMessage(boolean check, String message) {
		super();
		this.check = check;
		this.message = message;
	}

}
