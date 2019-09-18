package model.message;

import java.io.Serializable;
import java.math.BigDecimal;

public class RechargeMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	public boolean check;
	public String message;
	public BigDecimal money;
	
	public RechargeMessage() {}
	public RechargeMessage(boolean check, String message, BigDecimal money) {
		this.check = check;
		this.message = message;
		this.money = money;
	}
	
}
