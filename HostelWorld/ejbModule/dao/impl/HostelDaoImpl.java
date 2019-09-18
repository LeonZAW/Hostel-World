package dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.HostelDao;
import model.Checkin;
import model.Discount;
import model.Hostel;
import model.Income;
import model.Reserved;
import model.enumerate.HostelState;
import model.enumerate.IncomeState;
import model.enumerate.PayType;

@Stateless
public class HostelDaoImpl implements HostelDao{
	@PersistenceContext
	protected EntityManager em;
	@Override
	public Hostel getHostel(String login) {
		return em.find(Hostel.class, login);
	}
	@Override
	public void setHostelState(String login, HostelState state) {
		Hostel hostel = em.find(Hostel.class, login);
		hostel.setState(state.ordinal());
	}
	@Override
	public void registerNewHostel(String login) {
		Hostel hostel = new Hostel();
		hostel.setLogin(login);
		hostel.setState(HostelState.NOT_OPEN.ordinal());
		em.persist(hostel);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Hostel> getHostels() {
		Query query = em.createQuery("select h from Hostel h where state=?1");
		query.setParameter(1, HostelState.OPEN.ordinal());
		List<Hostel> list = query.getResultList();
		return list;
	}
	@Override
	public int checkin(Checkin checkin) {
		em.persist(checkin);
		Query query = em.createQuery("select c.id from Checkin c where rid=?1 and checkintime=?2 and checkouttime=?3");
		query.setParameter(1, checkin.getRid());
		query.setParameter(2, checkin.getCheckintime());
		query.setParameter(3, checkin.getCheckouttime());
		return (int) query.getSingleResult();
	}
	@Override
	public Checkin getCheckin(int checkinid) {
		return em.find(Checkin.class, checkinid);
	}
	@Override
	public void checkout(Income income) {
		em.persist(income);
	}
	
	@Override
	public BigDecimal getDiscount(int level) {
		Discount discount = em.find(Discount.class, level);
		return discount.getDiscount();
	}
	
	@Override
	public void setReserved(Reserved reserved) {
		em.persist(reserved);
	}
	@Override
	public BigDecimal getHostelIncome(String hlogin) {
		Query query = em.createQuery("select sum(money) from Income i where hotel_login=?1 and payType=?2");
		query.setParameter(1, hlogin);
		query.setParameter(2, PayType.CASH.ordinal());
		System.out.println(hlogin);
		BigDecimal totalMoney1 = (BigDecimal) query.getSingleResult();
		if(totalMoney1==null){
			System.out.println("WRYYYYYYYYYYYYYYYYYYYYYYYYYYYY(warning) @HostelDaoImpl#getHostelIncome");
			totalMoney1 = new BigDecimal(0);
		}
		query = em.createQuery("select sum(money) from Income i where hotel_login=?1 and payType=?2 and state=?3");
		query.setParameter(1, hlogin);
		query.setParameter(2, PayType.CARD.ordinal());
		query.setParameter(3, IncomeState.COMPLETE.ordinal());
		System.out.println(hlogin);
		BigDecimal totalMoney2 = (BigDecimal) query.getSingleResult();
		if(totalMoney2==null){
			System.out.println("WRYYYYYYYYYYYYYYYYYYYYYYYYYYYY(warning) @HostelDaoImpl#getHostelIncome");
			totalMoney2 = new BigDecimal(0);
		}
		totalMoney2=totalMoney2.multiply(new BigDecimal("0.95"));
		query = em.createQuery("select sum(reserveMoney) from Income i where hotel_login=?1 and state=?2");
		query.setParameter(1, hlogin);
		query.setParameter(2, IncomeState.COMPLETE.ordinal());
		System.out.println(hlogin);
		BigDecimal totalMoney3 = (BigDecimal) query.getSingleResult();
		if(totalMoney3==null){
			System.out.println("WRYYYYYYYYYYYYYYYYYYYYYYYYYYYY(warning) @HostelDaoImpl#getHostelIncome");
			totalMoney3 = new BigDecimal(0);
		}
		totalMoney3=totalMoney3.multiply(new BigDecimal("0.95"));
		return totalMoney1.add(totalMoney2).add(totalMoney3);
	}
	@Override
	public BigDecimal getAssociatorConsum(String alogin) {
		Query query = em.createQuery("select sum(money) from Income i where associator_login=?1");
		query.setParameter(1, alogin);
		BigDecimal totalMoney = (BigDecimal) query.getSingleResult();
		if(totalMoney==null){
			System.out.println("WRYYYYYYYYYYYYYYYYYYYYYYYYYYYY(warning) @HostelDaoImpl#getAssociatorConsum");
			totalMoney = new BigDecimal(0);
		}
		return totalMoney;
	}
	
	@Override
	public BigDecimal getHostelNotPayIncome(String hlogin) {
		Query query = em.createQuery("select sum(money) from Income i where hotel_login=?1 and state<>?2 and payType=?3");
		query.setParameter(1, hlogin);
		query.setParameter(2, IncomeState.COMPLETE.ordinal());
		query.setParameter(3, PayType.CARD.ordinal());
		BigDecimal totalMoney1 = (BigDecimal) query.getSingleResult();
		if(totalMoney1==null){
			System.out.println("WRYYYYYYYYYYYYYYYYYYYYYYYYYYYY(warning) @HostelDaoImpl#getHostelIncome");
			totalMoney1 = new BigDecimal(0);
		}
		/*query = em.createQuery("select sum(money) from Income i where hotel_login=?1 and state=?2");
		query.setParameter(1, hlogin);
		query.setParameter(2, IncomeState.AUDITING.ordinal());
		BigDecimal totalMoney2 = (BigDecimal) query.getSingleResult();
		if(totalMoney2==null){
			System.out.println("WRYYYYYYYYYYYYYYYYYYYYYYYYYYYY(warning) @HostelDaoImpl#getHostelIncome");
			totalMoney2 = new BigDecimal(0);
		}*/
		query = em.createQuery("select sum(reserveMoney) from Income i where hotel_login=?1 and state=?2");
		query.setParameter(1, hlogin);
		query.setParameter(2, IncomeState.AUDITING_RESERVE.ordinal());
		BigDecimal totalMoney3 = (BigDecimal) query.getSingleResult();
		if(totalMoney3==null){
			System.out.println("WRYYYYYYYYYYYYYYYYYYYYYYYYYYYY(warning) @HostelDaoImpl#getHostelIncome");
			totalMoney3 = new BigDecimal(0);
		}
		BigDecimal totalMoney = totalMoney1.add(totalMoney3);
		return totalMoney;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Hostel> getAllHostels() {
		Query query = em.createQuery("select h from Hostel h");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Income> getAuditingIncomes(String hlogin){
		Query query = em.createQuery("select i from Income i where hotel_login=?1 and state<>?2");
		query.setParameter(1, hlogin);
		query.setParameter(2, IncomeState.COMPLETE.ordinal());
		return query.getResultList();
	}
	@Override
	public int getPeopleTime(String hlogin) {
		Query query = em.createQuery("select count(distinct c.id) from Checkin c where c.room.login=?1");
		query.setParameter(1, hlogin);
		long longRes = (long) query.getSingleResult();
		return (int) longRes;
	}
	@Override
	public int getRentTime(String alogin) {
		Query query = em.createQuery("select count(distinct c.id) from Checkin c where c.associator_login=?1");
		query.setParameter(1, alogin);
		long longRes = (long) query.getSingleResult();
		return (int) longRes;
	}

}
