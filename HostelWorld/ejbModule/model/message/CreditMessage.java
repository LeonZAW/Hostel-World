package model.message;

import java.io.Serializable;

public class CreditMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean check;
	public String message;

	
	public CreditMessage() {
		super();
	}
	public CreditMessage(boolean check, String message) {
		super();
		this.check = check;
		this.message = message;
	}

}
