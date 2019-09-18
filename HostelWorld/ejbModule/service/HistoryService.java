package service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import model.Card;
import model.Hostel;
import model.Income;
import model.Reserved;


@Remote
public interface HistoryService {
	public List<Reserved> getAssociatorReserveList(String alogin);
	public List<Reserved> getHostelReserveList(String hlogin);
	public List<Income> getAssociatorPayList(String alogin);
	public List<Income> getHostelPayList(String hlogin);
	public List<Hostel> getHostelStat();
	public List<Card> getAssociatorStat();
	public int getUserNum();
	public int getHostelNum();
	public int getAuditHostelOpenNum();
	public int getAuditHostelModifyNum();
	public int getAuditHostelIncomeNum();
	public BigDecimal getHostelWorldIncome();
	public BigDecimal getHosgtelWorldOut();
	public BigDecimal getHostelIncome(String hlogin);
	public int getPeopleTime(String login);
	public BigDecimal getAssoMoney(String login);
	public int discount(String login);
	public int renttime(String login);
	public List<Hostel> getHostelAuditIncome();
}
