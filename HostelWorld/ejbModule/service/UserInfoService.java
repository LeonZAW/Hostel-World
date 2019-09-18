package service;

import java.math.BigDecimal;

import javax.ejb.Remote;

import model.Card;
import model.Hostel;

@Remote
public interface UserInfoService {
	public Card getAssociator(String login);
	public Hostel getHostel(String login);
	public BigDecimal getCardMoney(String login);
	public void payByCard(String login,BigDecimal money, int accpoint);
	public void checkAssociatorLevelUp(String alogin);
	public void testState();
	public void modifyCard(Card card);
}
