package dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.AuditDao;
import model.Audit;
import model.Hostel;
import model.enumerate.HostelState;
import model.enumerate.SubmitState;
import model.enumerate.SubmitType;

@Stateless
public class AuditDaoImpl implements AuditDao {
	@PersistenceContext
	protected EntityManager em;

	@Override
	public void submit(Audit audit) {
		audit.setState(SubmitState.NOT_AUDIT.ordinal());
		em.persist(audit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> getAudits() {
		Query query = em.createQuery("select a from Audit a");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> getAudits(SubmitType type, SubmitState state) {
		Query query = em.createQuery("select a from Audit a where type=?1 and state=?2");
		query.setParameter(1, type.ordinal());
		query.setParameter(2, state.ordinal());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> getAudits(SubmitState state) {
		Query query = em.createQuery("select a from Audit a where state=?1");
		query.setParameter(1, state.ordinal());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> getAudits(SubmitType type) {
		Query query = em.createQuery("select a from Audit a where type=?1");
		query.setParameter(1, type.ordinal());
		return query.getResultList();
	}

	@Override
	public void pass(int auditId) {
		Audit audit = em.find(Audit.class, auditId);
		audit.setState(SubmitState.PASS.ordinal());
		SubmitType type = SubmitType.values()[audit.getType()];
		String login = audit.getLogin();
		Hostel hostel = em.find(Hostel.class, login);
		if (type==SubmitType.OPEN) {
			hostel.setState(HostelState.OPEN.ordinal());
		} else { 
			//type==SubmitType.EDIT
		}
		hostel.setName(audit.getName());
		hostel.setStar(audit.getStar());
		hostel.setAddress(audit.getAddress());
		hostel.setSynopsis(audit.getSynopsis());
		hostel.setCreditId(audit.getCreditId());
		hostel.setCreditPassword(audit.getCreditPassword());
	}

	@Override
	public void repudiate(int auditId) {
		Audit audit = em.find(Audit.class, auditId);
		audit.setState(SubmitState.REPUDIATE.ordinal());
		SubmitType type = SubmitType.values()[audit.getType()];
		if (type==SubmitType.OPEN) {
			Hostel hostel = em.find(Hostel.class, audit.getLogin());
			hostel.setState(HostelState.NOT_OPEN.ordinal());
		}else {
			 //type==SubmitType.EDIT
		}
	}

}
