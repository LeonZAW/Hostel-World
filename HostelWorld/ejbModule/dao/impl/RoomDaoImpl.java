package dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.RoomDao;
import model.Checkin;
import model.Income;
import model.Reserved;
import model.Room;
import model.Schedule;
import model.enumerate.CheckFlag;
import model.enumerate.ReserveState;
import model.enumerate.RoomState;

@Stateless
public class RoomDaoImpl implements RoomDao {
	@PersistenceContext
	protected EntityManager em;

	@Override
	public void addRoom(Room room) {
		em.persist(room);
	}

	@Override
	public void addSchedule(Schedule schedule) {
		em.persist(schedule);
	}


	@Override
	public void checkin(Checkin checkin) {
		Room room = em.find(Room.class, checkin.getRid());
		room.setCflag(CheckFlag.CHECK_IN.ordinal());
		room.setCheckinid(checkin.getId());
	}

	@Override
	public void checkout(int rid) {
		Room room = em.find(Room.class, rid);
		room.setCflag(CheckFlag.NOT_CHECK_IN.ordinal());
		room.setCheckinid(null);
	}

	@Override
	public void completeReserve(int reserveid) {
		Reserved reserved = getReserved(reserveid);
		reserved.setReserve_state(ReserveState.COMPLETE.ordinal());
	}

	@Override
	public boolean isInSchedule(int rid, Date begin, Date end){
		Schedule schedule = getSchedule(rid);
		return (!begin.before(schedule.getBegintime()))&&(!end.after(schedule.getEndtime()));
	}
	@Override
	public boolean isNotReserved(int rid, Date begin, Date end) {
		Query query = em.createQuery("select r from Reserved r where rid=?1 and reserve_state=?2 and "
				+ "?3>=starttime and ?4<=endtime");
		query.setParameter(1, rid);
		query.setParameter(2, ReserveState.RESERVED.ordinal());
		query.setParameter(3, end);
		query.setParameter(4, begin);
		return query.getResultList().isEmpty();
	}

	@Override
	public boolean isNotUsed(int rid, Date begin,Date end){
		Date date = new Date();
		Query query = em.createQuery("select c from Checkin c where rid=?1 and ?2 between checkintime and checkouttime");
		query.setParameter(1, rid);
		query.setParameter(2, date);
		if(query.getResultList().isEmpty())
			return true;
		else{
			Checkin checkin = (Checkin) query.getSingleResult();
			return checkin.getCheckouttime().before(begin);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getCheckoutRooms(String login) {
		Query query = em.createQuery("select distinct r from Checkin c,Room r where c.rid=r.id and login=?1 and ?2>=checkouttime and cflag=?3");
		query.setParameter(1, login);
		query.setParameter(2, new Date());
		query.setParameter(3, CheckFlag.CHECK_IN.ordinal());
		return query.getResultList();
	}
	@Override
	public String getHostelLogin(int rid) {
		Room room = em.find(Room.class, rid);
		return room.getLogin();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getOffScheduleRooms(String login) {
		Date date = new Date();
		Query query = em.createQuery("select r from Room r where r.login=?1 and r.id not in(select r1.id from Schedule s,Room r1 "
				+ "where s.rid=r1.id and login=?1 and ?2 between begintime and endtime)");
		query.setParameter(1, login);
		query.setParameter(2, date);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getOnScheduleRooms(String login) {
		Date date = new Date();
		Query query = em.createQuery("select r from Schedule s,Room r where s.rid=r.id and login=?1 and ?2 between begintime and endtime");
		query.setParameter(1, login);
		query.setParameter(2, date);
		List<Room> rooms = query.getResultList();
		rooms.forEach(r->{
			r=setRoomState(r);
		});
		return rooms;
	}
	
	@Override
	public Reserved getReserved(int reservedid) {
		return em.find(Reserved.class,reservedid);
	}

	@Override
	public Reserved getReservedByRoom(int rid) {
		Query query = em.createQuery("select r from Reserved r where rid=?1 and reserve_state=?2 and "
				+ "?3 between starttime and endtime");
		query.setParameter(1, rid);
		query.setParameter(2, ReserveState.RESERVED.ordinal());
		query.setParameter(3, new Date());
		return (Reserved) query.getSingleResult();
	}

	@Override
	public Room getRoom(int rid) {
		return em.find(Room.class, rid);
	}

	@Override
	public Room getRoom(String login, String roomNo) {
		Query query = em.createQuery("select r from Room r where login=?1 and roomNo=?2");
		query.setParameter(1, login);
		query.setParameter(2, roomNo);
		try {
			return (Room)query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getRooms(String login) {
		Query query = em.createQuery("select r from Room r where login=?1");
		query.setParameter(1, login);
		return query.getResultList();
	}
	
	@Override
	public Schedule getSchedule(int rid) {
		Date date = new Date();
		Query query = em.createQuery("select s from Schedule s where s.rid=?1 and ?2 between begintime and endtime");
		query.setParameter(1, rid);
		query.setParameter(2, date);
		return (Schedule) query.getSingleResult();
	}

	@Override
	public Date getScheduleEndDate(int rid) {
		return getSchedule(rid).getEndtime();
	}
	
	@Override
	public Room setRoomState(Room room){
		RoomState state;
		Date date = new Date();
		Query query = em.createQuery("select c from Checkin c where rid=?1 and ?2 between checkintime and checkouttime");
		query.setParameter(1, room.getId());
		query.setParameter(2, date);
		if(!query.getResultList().isEmpty())
			state = RoomState.USING;
		else{
			query = em.createQuery("select r from Reserved r where rid=?1 and reserve_state=?2 and "
					+ "?3 between starttime and endtime");
			query.setParameter(1, room.getId());
			query.setParameter(2, ReserveState.RESERVED.ordinal());
			query.setParameter(3, date);
			if(!query.getResultList().isEmpty())
				state = RoomState.RESERVED;
			else
				state = RoomState.ONSALE;
		}
		room.setRoomState(state);
		return room;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reserved> getAssociatorReserveList(String alogin) {
		Query query = em.createQuery("select r from Reserved r where associator_login=?1");
		query.setParameter(1, alogin);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reserved> getHostelReserveList(String hlogin) {
		Query query = em.createQuery("select r from Reserved r,Room room where r.rid=room.id and room.login=?1");
		query.setParameter(1, hlogin);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Income> getAssociatorPayList(String alogin) {
		Query query = em.createQuery("select i from Income i where associator_login=?1");
		query.setParameter(1, alogin);
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Income> getHostelPayList(String hlogin) {
		Query query = em.createQuery("select i from Income i,Room r where login=?1 and i.rid=r.id");
		query.setParameter(1, hlogin);
		return query.getResultList();
	}

	@Override
	public void cancelReserve(int reserveid) {
		Reserved reserved = em.find(Reserved.class, reserveid);
		ReserveState state = ReserveState.values()[reserved.getReserve_state()];
		if(state!=ReserveState.COMPLETE)
			System.err.println("error @RoomDaoImpl#canclReserve");
		reserved.setReserve_state(ReserveState.CANCEL.ordinal());
	}


	

}
