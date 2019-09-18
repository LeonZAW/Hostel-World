package dao.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.CardDao;
import model.Card;
import model.Discount;
import model.enumerate.AssociatorState;

@Stateless
public class CardDaoImpl implements CardDao{
	@PersistenceContext
	protected EntityManager em;
	@Override
	public Card getCard(String login) {
		return em.find(Card.class, login);
	}
	@Override
	public void save(Card card) {
		em.persist(card);
	}
	@Override
	public BigDecimal getMoney(String login) {
		return em.find(Card.class, login).getMoney();
	}
	@Override
	public void activate(String login) {
		Card card = em.find(Card.class, login);
		Date activateDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(activateDate);
		cal.add(Calendar.YEAR,1);
		Date stopDate =cal.getTime();
		card.setActivateDate(activateDate);
		card.setStopDate(stopDate);
		card.setState(AssociatorState.ACTIVATED.ordinal());
	}
	@Override
	public void suspend(String login) {
		Card card = em.find(Card.class, login);
		Date suspendDate = new Date();
		card.setSuspendDate(suspendDate);
		card.setState(AssociatorState.SUSPENDED.ordinal());
	}
	@Override
	public void recover(String login) {
		Card card = em.find(Card.class, login);
		Date suspendDate = card.getSuspendDate();
		Date stopDate = card.getStopDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(suspendDate);
		long suspendMili = cal.getTimeInMillis();
		cal.setTime(stopDate);
		long stopMili = cal.getTimeInMillis();
		cal.setTime(new Date());
		long nowMili = cal.getTimeInMillis();
		cal.setTimeInMillis(nowMili+stopMili-suspendMili);
		Date newStopDate = cal.getTime();
		card.setStopDate(newStopDate);
		card.setState(AssociatorState.ACTIVATED.ordinal());
	}
	@Override
	public void registerNewAssociator(String login) {
		Card card = new Card();
		card.setLogin(login);
		card.setLevel(1);
		card.setMoney(new BigDecimal(0));
		card.setState(AssociatorState.NOT_ACTIVATED.ordinal());
		em.persist(card);
	}
	@Override
	public void pay(String login,BigDecimal money) {
		Card card = em.find(Card.class, login);
		card.setMoney(card.getMoney().subtract(money));
	}
	@Override
	public int getNewLevel(String login) {
		Query query = em.createQuery("select sum(money) from Income i where associator_login=?1");
		query.setParameter(1, login);
		BigDecimal money = (BigDecimal) query.getSingleResult();
		query = em.createQuery("select max(level) from Discount d where money<=?1");
		query.setParameter(1, money);
		int newlevel = (int) query.getSingleResult();
		return newlevel;
	}
	@Override
	public void modifyLevel(Card associator) {
		Card card = em.find(Card.class, associator.getLogin());
		card.setLevel(associator.getLevel());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Card> getAllAssociator() {
		Query query = em.createQuery("select a from Card a where state=?1");
		query.setParameter(1, AssociatorState.ACTIVATED.ordinal());
		return query.getResultList();
	}
	@Override
	public int getAccpoints(String login) {
		return em.find(Card.class, login).getAccpoint();
	}
	
	@Override
	public void suspendCard(String login) {
		Card associator = em.find(Card.class, login);
		Calendar cal = Calendar.getInstance();
		cal.setTime(associator.getStopDate());
		cal.add(Calendar.YEAR,1);
		if(associator.getMoney().compareTo(new BigDecimal(1000))<0){
			associator.setDeadDate(cal.getTime());
			associator.setState(AssociatorState.SUSPENDED.ordinal());
		}else{
			associator.setStopDate(cal.getTime());
		}
	}
	@Override
	public void killCard(String login) {
		Card associator = em.find(Card.class, login);
		associator.setState(AssociatorState.STOP.ordinal());
	}
	
	@Override
	public void payPoint(String login, int accpoint) {
		Card associator = em.find(Card.class, login);
		int nowPoint = associator.getAccpoint();
		if(nowPoint-accpoint<0){
			System.out.println("WRYYYYYYYYYYYYYYYYYYYYYYYYYYYYY wrong in CardDaoImpl#payPoint");
		}
		associator.setAccpoint(nowPoint-accpoint);
	}
	@Override
	public void addPoint(String login, int accpoint) {
		Card associator = em.find(Card.class, login);
		int nowPoint = associator.getAccpoint();
		associator.setAccpoint(nowPoint+accpoint);
	}
	@Override
	public BigDecimal getDiscount(String login) {
		Card associator = em.find(Card.class, login);
		Discount discount = em.find(Discount.class, associator.getLevel());
		return discount.getDiscount();
	}
	@Override
	public void modify(Card associator) {
		Card card = em.find(Card.class, associator.getLogin());
		card.setName(associator.getName());
		card.setPin(associator.getPin());
	}

}
