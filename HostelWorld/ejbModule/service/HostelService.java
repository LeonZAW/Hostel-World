package service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.Checkin;
import model.Hostel;
import model.Income;
import model.Reserved;
import model.Room;
import model.Schedule;
import model.message.RoomManageMessage;

@Remote
public interface HostelService {
	public RoomManageMessage addRoom(Room room);
	public void addSchedule(Schedule schedule);
	public boolean canCheckin(int rid,Date begin,Date end);
	public boolean canReserve(int rid,Date begin,Date end);
	public void checkin(Checkin checkin);
	public void checkout(Income income);
	public void checkinByReserve(int reserveid);
	public Date getEndDate(int rid); 
	public List<Room> getCheckoutRooms(String login);
	public List<Hostel> getHostels();
	public Income getIncome(int rid);
	public BigDecimal getMoney(int rid);
	public List<Room> getOffScheduleRooms(String login);
	public List<Room> getOnSaleRooms(String login);
	public List<Room> getOnScheduleRooms(String login);
	public BigDecimal getPrice(int rid, Date begin, Date end);
	public Room getRoom(int rid);
	public Room getRoom(String login,String roomNo);
	public List<Room> getRooms(String login);
	public Reserved getServed(int rid);
	public void setReserved(Reserved reserved,BigDecimal money);
	public boolean canReserveForMoney(Reserved reserved,BigDecimal money);
	public void cancelReserve(int reserveid);
}
