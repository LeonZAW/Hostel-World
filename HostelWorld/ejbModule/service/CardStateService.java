package service;

import java.math.BigDecimal;

import javax.ejb.Remote;

import model.message.ActivateMessage;
import model.message.RechargeMessage;
import model.message.RelinquishMessage;
import model.message.SuspendMessage;

@Remote
public interface CardStateService {
	public RechargeMessage recharge(String login, String credit, String password, BigDecimal money);
	public ActivateMessage activate(String login, String credit, String password, BigDecimal money);
	public SuspendMessage suspendCheck(String login);
	public RelinquishMessage relinquish(String login, String credit);
//	public ContinueMessage continueUse(String login);
	public void decreaseMoneyForReserve(String login,BigDecimal money);
	public int getAccPoint(String login);
}
