package service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import dao.CardDao;
import dao.CreditDao;
import dao.HostelDao;
import dao.RoomDao;
import factory.EJBFactory;
import model.Card;
import model.Checkin;
import model.Hostel;
import model.Income;
import model.Reserved;
import model.Room;
import model.Schedule;
import model.enumerate.CheckFlag;
import model.enumerate.ReserveFlag;
import model.enumerate.RoomState;
import model.message.RoomManageMessage;
import service.HostelService;

@Stateless
public class HostelServiceImpl implements HostelService {
	private static final String ROOM_EXIST = "该房间号已经注册";
	private static final String ROOM_ADD_SUCCESS = "添加成功";
	private RoomDao roomDao = EJBFactory.getDaoEJB(RoomDao.class);
	private HostelDao hostelDao = EJBFactory.getDaoEJB(HostelDao.class);
	private CardDao cardDao = EJBFactory.getDaoEJB(CardDao.class);
	private CreditDao creditDao = EJBFactory.getDaoEJB(CreditDao.class);
	private static final BigDecimal RESERVE_RATE = new BigDecimal("0.1");
	@Override
	public RoomManageMessage addRoom(Room room) {
		if (getRoom(room.getLogin(), room.getRoomNo()) != null)
			return new RoomManageMessage(false, ROOM_EXIST);
		roomDao.addRoom(room);
		return new RoomManageMessage(true, ROOM_ADD_SUCCESS);
	}

	@Override
	public void addSchedule(Schedule schedule) {
		roomDao.addSchedule(schedule);
	}

	@Override
	public boolean canCheckin(int rid,Date begin, Date end) {
		return roomDao.isNotReserved(rid, begin, end)&&
				roomDao.isInSchedule(rid, begin, end);
	}
	
	@Override
	public boolean canReserve(int rid, Date begin, Date end) {
		return roomDao.isNotReserved(rid, begin, end)&&
				roomDao.isInSchedule(rid, begin, end)&&
				roomDao.isNotUsed(rid, begin, end);
	}

	@Override
	public void checkin(Checkin checkin) {
		int checkinid = hostelDao.checkin(checkin);
		checkin.setId(checkinid);
		roomDao.checkin(checkin);
	}

	@Override
	public void checkinByReserve(int reserveid) {
		Reserved reserved = roomDao.getReserved(reserveid);
		Checkin checkin = new Checkin();
		checkin.setAssociator_login(reserved.getAssociator_login());
		checkin.setCheckintime(reserved.getStarttime());
		checkin.setCheckouttime(reserved.getEndtime());
		checkin.setRid(reserved.getRid());
		checkin.setRflag(ReserveFlag.RESERVED.ordinal());
		checkin(checkin);
		roomDao.completeReserve(reserveid);
	}

	@Override
	public void checkout(Income income) {
		income.setTime(new Date());
		int rid = income.getRid();
		income.setHotel_login(roomDao.getHostelLogin(rid));
		roomDao.checkout(rid);
		hostelDao.checkout(income);
		if(income.getAssociator_login()!=null){
			cardDao.addPoint(income.getAssociator_login(), income.getMoney().intValue()/10);
			creditDao.payToManager(income.getAssociator_login(), income.getMoney());
		}
	}

	@Override
	public List<Room> getCheckoutRooms(String login) {
		return roomDao.getCheckoutRooms(login);
	}

	@Override
	public Date getEndDate(int rid) {
		return roomDao.getScheduleEndDate(rid);
	}

	@Override
	public List<Hostel> getHostels() {
		return hostelDao.getHostels();
	}
	
	@Override
	public Income getIncome(int rid) {
		Room room = getRoom(rid);
		//获得入住时的登记人的入住记录
		Checkin checkin = hostelDao.getCheckin(room.getCheckinid());
		//根据入住记录获得入住人
		String alogin = checkin.getAssociator_login();
		BigDecimal discount = new BigDecimal(1);
		Date begin = checkin.getCheckintime();
		Date end = checkin.getCheckouttime();
		long l = end.getTime() - begin.getTime();
		BigDecimal day = new BigDecimal(1+l / (1000 * 60 * 60 * 24));
		//创建新收入记录
		Income income = new Income();
		Schedule schedule = roomDao.getSchedule(checkin.getRid());
		BigDecimal former_money = schedule.getPrice().multiply(day);
		income.setReserveMoney(new BigDecimal(0));
		if(alogin!=null){
			Card associator = cardDao.getCard(alogin);
			int level = associator.getLevel();
			discount = hostelDao.getDiscount(level);
			if(checkin.getRflag()==ReserveFlag.RESERVED.ordinal()){
				discount.subtract(RESERVE_RATE);
				income.setReserveMoney(former_money.multiply(RESERVE_RATE));
			}
		}
		income.setRid(checkin.getRid());
		income.setAssociator_login(alogin);
		income.setFormer_money(former_money);
		income.setMoney(former_money.multiply(discount));
		return income;
	}
	
	@Override
	public BigDecimal getMoney(int rid) {
		Schedule schedule = roomDao.getSchedule(rid);
		return schedule.getPrice();
	}

	@Override
	public List<Room> getOffScheduleRooms(String login) {
		return roomDao.getOffScheduleRooms(login);
	}

	@Override
	public List<Room> getOnSaleRooms(String login) {
		List<Room> rooms = roomDao.getOnScheduleRooms(login);
		List<Room> result = new ArrayList<>();
		rooms.forEach(r->{
			if(r.getCflag()==CheckFlag.NOT_CHECK_IN.ordinal())
				result.add(r);
		});
		return result;
	}
	
	@Override
	public List<Room> getOnScheduleRooms(String login) {
		return roomDao.getOnScheduleRooms(login);
	}

	@Override
	public BigDecimal getPrice(int rid, Date begin, Date end) {
		Date limitDate = getEndDate(rid);
		if (limitDate.before(end))
			return null;
		Schedule schedule = roomDao.getSchedule(rid);
		long l = end.getTime() - begin.getTime();
		BigDecimal day = new BigDecimal(1+l / (1000 * 60 * 60 * 24));
		BigDecimal price = schedule.getPrice().multiply(day);
		return price;
	}

	@Override
	public Room getRoom(int rid) {
		Room room = roomDao.getRoom(rid);
		return roomDao.setRoomState(room);
	}

	@Override
	public Room getRoom(String login, String roomNo) {
		return roomDao.getRoom(login, roomNo);
	}

	@Override
	public List<Room> getRooms(String login) {
		return roomDao.getRooms(login);
	}

	@Override
	public Reserved getServed(int rid) {
		Room room = roomDao.getRoom(rid);
		room = roomDao.setRoomState(room);
		if(room.getRoomState()!=RoomState.RESERVED){
			System.out.println("Error in reserved@HostelServiceImpl#getServedLogin");
			return null;
		}
		Reserved reserved = roomDao.getReservedByRoom(rid);
		return reserved;
	}

	@Override
	public void setReserved(Reserved reserved,BigDecimal money) {
		BigDecimal totalMoney = getTotalMoney(reserved.getStarttime(),reserved.getEndtime(),money);
//		cardDao.pay(reserved.getAssociator_login(), totalMoney);
		creditDao.payToManager(reserved.getAssociator_login(), totalMoney);
		reserved.setReserveMoney(totalMoney);
		hostelDao.setReserved(reserved);
	}

	@Override
	public boolean canReserveForMoney(Reserved reserved,BigDecimal money) {
		BigDecimal totalMoney = getTotalMoney(reserved.getStarttime(),reserved.getEndtime(),money);
		BigDecimal balance = cardDao.getMoney(reserved.getAssociator_login());
		System.out.println(balance);
		return balance.compareTo(totalMoney)>=0;
	}
	
	private BigDecimal getTotalMoney(Date begin,Date end,BigDecimal oneDayMoney){
		long l = end.getTime() - begin.getTime();
		BigDecimal day = new BigDecimal(1+l / (1000 * 60 * 60 * 24));
		BigDecimal price = oneDayMoney.multiply(day);
		return price;
	}

	@Override
	public void cancelReserve(int reserveid) {
		creditDao.payToAssociator(reserveid);
		roomDao.cancelReserve(reserveid);
	}
	


	
}
