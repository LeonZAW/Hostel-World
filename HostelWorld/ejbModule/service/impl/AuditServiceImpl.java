package service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

import dao.AuditDao;
import dao.CreditDao;
import dao.HostelDao;
import factory.EJBFactory;
import model.Audit;
import model.Income;
import model.enumerate.HostelState;
import model.enumerate.SubmitState;
import model.enumerate.SubmitType;
import service.AuditService;

@Stateless
public class AuditServiceImpl implements AuditService {

	private AuditDao auditDao = EJBFactory.getDaoEJB(AuditDao.class);
	private HostelDao hostelDao = EJBFactory.getDaoEJB(HostelDao.class);
	private CreditDao creditDao = EJBFactory.getDaoEJB(CreditDao.class);

	@Override
	public List<Audit> getAudits() {
		return auditDao.getAudits();
	}

	@Override
	public List<Audit> getAudits(SubmitType type, SubmitState state) {
		boolean typeNull = type == null;
		boolean stateNull = state == null;
		if (typeNull)
			if (stateNull)
				return auditDao.getAudits();
			else
				return auditDao.getAudits(state);
		else if (stateNull)
			return auditDao.getAudits(type);
		else
			return auditDao.getAudits(type, state);
	}

	@Override
	public void openHostel(Audit audit) {
		audit.setType(SubmitType.OPEN.ordinal());
		hostelDao.setHostelState(audit.getLogin(), HostelState.AUDITING);
		auditDao.submit(audit);
	}

	@Override
	public void passAudit(int auditId) {
		auditDao.pass(auditId);
	}

	@Override
	public void repudiateAudit(int auditId) {
		auditDao.repudiate(auditId);
	}


	@Override
	public void auditIncome(Income income) {
		creditDao.payForHostel(income);
	}

	@Override
	public void auditIncomes(List<Income> incomes) {
		for (Income income : incomes) {
			creditDao.payForHostel(income);
		}
	}
	
	

	@Override
	public boolean canPay(BigDecimal money) {
		return creditDao.checkManagerCredit(money);
	}

	@Override
	public boolean canPay(List<BigDecimal> moneys) {
		for (BigDecimal money : moneys) {
			if(!creditDao.checkManagerCredit(money))
				return false;
		}
		return true;
		
	}

	@Override
	public void modifyHostel(Audit audit) {
		audit.setType(SubmitType.EDIT.ordinal());
		auditDao.submit(audit);
	}

	@Override
	public void auditIncome(String hlogin) {
		List<Income> incomes = hostelDao.getAuditingIncomes(hlogin);
		auditIncomes(incomes);
	}
	
	

}
