package dao;

import java.util.List;

import javax.ejb.Remote;

import model.Audit;
import model.enumerate.SubmitState;
import model.enumerate.SubmitType;

@Remote
public interface AuditDao{
	public void submit(Audit audit);
	public List<Audit> getAudits();
	public List<Audit> getAudits(SubmitType type,SubmitState state);
	public List<Audit> getAudits(SubmitType type);
	public List<Audit> getAudits(SubmitState state);
	public void pass(int auditId);
	public void repudiate(int auditId);
}
