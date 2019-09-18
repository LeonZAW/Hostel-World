package dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import model.Checkin;
import model.Hostel;
import model.Income;
import model.Reserved;
import model.enumerate.HostelState;

@Remote
public interface HostelDao {
	public Hostel getHostel(String login);
	public void setHostelState(String login,HostelState state);
	public void registerNewHostel(String login);
	public List<Hostel> getHostels();
	public int checkin(Checkin checkin);
	public Checkin getCheckin(int checkinid);
	public void checkout(Income income);
	public BigDecimal getDiscount(int level);
	public void setReserved(Reserved reserved);
	public BigDecimal getHostelIncome(String hlogin);
	public BigDecimal getAssociatorConsum(String alogin);
	public BigDecimal getHostelNotPayIncome(String login);
	public List<Hostel> getAllHostels();
	public List<Income> getAuditingIncomes(String hlogin);
	public int getPeopleTime(String hlogin);
	public int getRentTime(String alogin);
}
