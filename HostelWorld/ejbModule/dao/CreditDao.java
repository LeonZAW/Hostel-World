package dao;

import java.math.BigDecimal;

import javax.ejb.Remote;

import model.Income;

@Remote
public interface CreditDao {
	public boolean hasCredit(String credit);
	public boolean check(String credit, String password);
	public BigDecimal getBalance(String credit);
	public void recharge(String credit, String login, BigDecimal money);
	public void relinquish(String credit, String login, BigDecimal rate);
	public void payForHostel(Income income);
	public void payToManager(String alogin,BigDecimal money);
	public boolean checkManagerCredit(BigDecimal money);
	public BigDecimal getTotalIncome();
	public BigDecimal getTotalOutput();
	public void payToAssociator(int reserveid);
}
