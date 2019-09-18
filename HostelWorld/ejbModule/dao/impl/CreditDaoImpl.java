package dao.impl;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.CreditDao;
import model.Card;
import model.Credit;
import model.Income;
import model.Reserved;
import model.enumerate.AssociatorState;
import model.enumerate.IncomeState;
import model.enumerate.PayType;
@Stateless
public class CreditDaoImpl implements CreditDao {
	private static final String MANAGER_CREDIT_ID = "1118";
	@PersistenceContext
	protected EntityManager em;
	@Override
	public boolean hasCredit(String credit) {
		Credit card = em.find(Credit.class, credit);
		return card!=null;
	}

	@Override
	public boolean check(String credit, String password) {
		Credit card = em.find(Credit.class, credit);
		return card.getPassword().equals(password);
	}

	@Override
	public BigDecimal getBalance(String credit) {
		Credit card = em.find(Credit.class, credit);
		return card.getMoney();
	}

	@Override
	public void recharge(String credit, String login, BigDecimal money) {
		Credit creditCard = em.find(Credit.class, credit);
		Card card = em.find(Card.class, login);
		creditCard.setMoney(creditCard.getMoney().subtract(money));
		card.setMoney(card.getMoney().add(money));
	}
	
	@Override
	public void relinquish(String credit, String login, BigDecimal rate){
		Credit creditCard = em.find(Credit.class, credit);
		Card card = em.find(Card.class, login);
		BigDecimal moneyAfterRate = card.getMoney().multiply(rate);
		creditCard.setMoney(creditCard.getMoney().add(moneyAfterRate));
		card.setMoney(new BigDecimal(0));
		card.setState(AssociatorState.STOP.ordinal());
	}

	@Override
	public void payForHostel(Income income) {
		IncomeState state = IncomeState.values()[income.getState()];
		if(state==IncomeState.COMPLETE){
			System.out.println("error in CreditDaoImpl#payForHostel 怕是失了智");
		}
		Income newIncome = em.find(Income.class, income.getId());
		newIncome.setState(IncomeState.COMPLETE.ordinal());
		Credit managerCredit = em.find(Credit.class,MANAGER_CREDIT_ID);
		Credit hostelCredit = em.find(Credit.class, income.getRoom().getHostel().getCreditId());
		BigDecimal money = income.getMoney().add(income.getReserveMoney());
		money = money.multiply(new BigDecimal("0.95"));
		managerCredit.setMoney(managerCredit.getMoney().subtract(money));
		hostelCredit.setMoney(hostelCredit.getMoney().add(money));
		System.out.println("哇！！！！！！！！！");
	}

	@Override
	public void payToManager(String alogin,BigDecimal money) {
		Card associator = em.find(Card.class, alogin);
		Credit managerCredit = em.find(Credit.class,MANAGER_CREDIT_ID);
		associator.setMoney(associator.getMoney().subtract(money));
		managerCredit.setMoney(managerCredit.getMoney().add(money));
	}
	
	@Override
	public boolean checkManagerCredit(BigDecimal money) {
		Credit managerCredit = em.find(Credit.class,MANAGER_CREDIT_ID);
		if(managerCredit.getMoney().compareTo(money)>0)
			return true;
		return false;
	}

	@Override
	public BigDecimal getTotalIncome() {
		Query query = em.createQuery("select sum(money) from Income i where payType=?1");
		query.setParameter(1, PayType.CARD.ordinal());
		BigDecimal cardMoney = (BigDecimal) query.getSingleResult();
		query = em.createQuery("select sum(reserveMoney) from Income i");
		BigDecimal reserveMoney = (BigDecimal) query.getSingleResult();
		return cardMoney.add(reserveMoney);
	}

	@Override
	public BigDecimal getTotalOutput() {
		Query query = em.createQuery("select sum(money) from Income i where payType=?1 and state=?2");
		query.setParameter(1, PayType.CARD.ordinal());
		query.setParameter(2, IncomeState.COMPLETE.ordinal());
		BigDecimal cardMoney = (BigDecimal) query.getSingleResult();
		query = em.createQuery("select sum(reserveMoney) from Income i where payType=?1 and state=?2");
		query.setParameter(1, PayType.CARD.ordinal());
		query.setParameter(2, IncomeState.COMPLETE.ordinal());
		BigDecimal reserveMoney = (BigDecimal) query.getSingleResult();
		return cardMoney.add(reserveMoney);
	}

	@Override
	public void payToAssociator(int reserveid) {
		Reserved reserved = em.find(Reserved.class, reserveid);
		Card associator = em.find(Card.class, reserved.getAssociator_login());
		Credit managerCredit = em.find(Credit.class,MANAGER_CREDIT_ID);
		BigDecimal money = reserved.getReserveMoney().divide(new BigDecimal(2));
		associator.setMoney(associator.getMoney().add(money));
		managerCredit.setMoney(managerCredit.getMoney().subtract(money));
	}
}
