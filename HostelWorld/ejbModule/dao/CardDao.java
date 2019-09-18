package dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import model.Card;

@Remote
public interface CardDao {
	public Card getCard(String login);
	public void save(Card card);
	public BigDecimal getMoney(String login);
	public void activate(String login);
	public void suspend(String login);
	public void recover(String login);
	public void registerNewAssociator(String login);
	public void pay(String login,BigDecimal money);
	public int getNewLevel(String login);
	public void modifyLevel(Card associator);
	public List<Card> getAllAssociator();
	public int getAccpoints(String login);
	public void suspendCard(String login);
	public void killCard(String login);
	public void payPoint(String login, int accpoint);
	public void addPoint(String login,int accpoint);
	public void modify(Card associator);
	public BigDecimal getDiscount(String login);
}
