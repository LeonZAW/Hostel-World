package service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import dao.AuditDao;
import dao.CardDao;
import dao.CreditDao;
import dao.HostelDao;
import dao.RoomDao;
import factory.EJBFactory;
import model.Card;
import model.Hostel;
import model.Income;
import model.Reserved;
import model.enumerate.SubmitState;
import model.enumerate.SubmitType;
import service.HistoryService;

@Stateless
public class HistoryServiceImpl implements HistoryService {
	private static RoomDao roomDao = EJBFactory.getDaoEJB(RoomDao.class);
	private static HostelDao hostelDao = EJBFactory.getDaoEJB(HostelDao.class);
	private static CardDao cardDao = EJBFactory.getDaoEJB(CardDao.class);
	private static AuditDao auditDao = EJBFactory.getDaoEJB(AuditDao.class);
	private static CreditDao creditDao = EJBFactory.getDaoEJB(CreditDao.class);
	@Override
	public List<Reserved> getAssociatorReserveList(String alogin) {
		return roomDao.getAssociatorReserveList(alogin);
	}
	
	@Override
	public List<Reserved> getHostelReserveList(String hlogin) {
		return roomDao.getHostelReserveList(hlogin);
	}
	@Override
	public List<Income> getAssociatorPayList(String alogin) {
		return roomDao.getAssociatorPayList(alogin);
	}
	@Override
	public List<Income> getHostelPayList(String hlogin) {
		return roomDao.getHostelPayList(hlogin);
	}

	@Override
	public List<Hostel> getHostelStat() {
		List<Hostel> hostels = hostelDao.getHostels();
		for (Hostel hostel : hostels) {
			hostel.setTotalIncome(hostelDao.getHostelIncome(hostel.getLogin()));
			hostel.setNotPayIncome(hostelDao.getHostelNotPayIncome(hostel.getLogin()));
		}
		return hostels;
	}

	@Override
	public List<Card> getAssociatorStat() {
		List<Card> associators = cardDao.getAllAssociator();
		for (Card associator : associators) {
			associator.setTotalConsum(hostelDao.getAssociatorConsum(associator.getLogin()));
		}
		return associators;
	}

	@Override
	public int getUserNum() {
		return getAssociatorStat().size();
	}

	@Override
	public int getHostelNum() {
		return getHostelStat().size();
	}

	@Override
	public int getAuditHostelOpenNum() {
		return auditDao.getAudits(SubmitType.OPEN, SubmitState.NOT_AUDIT).size();
	}

	@Override
	public int getAuditHostelModifyNum() {
		return auditDao.getAudits(SubmitType.EDIT, SubmitState.NOT_AUDIT).size();
	}

	@Override
	public int getAuditHostelIncomeNum() {
		int num=0;
		List<Hostel> hostels = hostelDao.getHostels();
		for (Hostel hostel : hostels) {
			if(hostelDao.getHostelNotPayIncome(hostel.getLogin()).signum()>0)
				++num;
		}
		return num;
	}

	@Override
	public BigDecimal getHostelWorldIncome() {
		return creditDao.getTotalIncome();
	}

	@Override
	public BigDecimal getHosgtelWorldOut() {
		return creditDao.getTotalOutput();
	}

	@Override
	public BigDecimal getHostelIncome(String hlogin) {
		return hostelDao.getHostelIncome(hlogin);
	}

	@Override
	public int getPeopleTime(String login) {
		return hostelDao.getPeopleTime(login);
	}

	@Override
	public BigDecimal getAssoMoney(String login) {
		return hostelDao.getAssociatorConsum(login);
	}

	@Override
	public int discount(String login) {
		return cardDao.getDiscount(login).multiply(new BigDecimal(100)).intValue();
	}

	@Override
	public int renttime(String login) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Hostel> getHostelAuditIncome(){
		List<Hostel> resultHostels = new ArrayList<>();
		for(Hostel hostel:getHostelStat()){
			if(hostel.getNotPayIncome().signum()>0)
				resultHostels.add(hostel);
		}
		return resultHostels;
	}
}
