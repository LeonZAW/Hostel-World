package service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import model.Audit;
import model.Income;
import model.enumerate.SubmitState;
import model.enumerate.SubmitType;


@Remote
public interface AuditService {
	public void openHostel(Audit audit);
	public void modifyHostel(Audit audit);
	public List<Audit> getAudits();
	public List<Audit> getAudits(SubmitType type,SubmitState state);
	public void passAudit(int auditId);
	public void repudiateAudit(int auditId);
	public void auditIncome(Income income);
	public void auditIncomes(List<Income> incomes);
	public boolean canPay(BigDecimal money);
	public boolean canPay(List<BigDecimal> moneys);
	public void auditIncome(String hlogin);
}
