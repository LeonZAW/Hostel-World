package model.message;

import java.io.Serializable;

public class RoomManageMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean check;
	public String message;

	
	public RoomManageMessage() {
		super();
	}
	public RoomManageMessage(boolean check, String message) {
		super();
		this.check = check;
		this.message = message;
	}

}
