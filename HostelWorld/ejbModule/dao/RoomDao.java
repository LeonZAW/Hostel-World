package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.Checkin;
import model.Income;
import model.Reserved;
import model.Room;
import model.Schedule;

@Remote
public interface RoomDao {
	public void addRoom(Room room);
	public void addSchedule(Schedule schedule);
	public void checkin(Checkin checkin);
	public void checkout(int rid);
	public void completeReserve(int reserveid);
	public List<Room> getCheckoutRooms(String login);
	public String getHostelLogin(int rid);
	public List<Room> getOffScheduleRooms(String login);
	public List<Room> getOnScheduleRooms(String login);
	public Room getRoom(int rid);
	public Room getRoom(String login,String roomNo);
	public List<Room> getRooms(String login);
	public Reserved getReserved(int reservedid);
	public Reserved getReservedByRoom(int rid);
	public Schedule getSchedule(int rid);
	public Date getScheduleEndDate(int rid);
	public boolean isInSchedule(int rid,Date begin,Date end);
	public boolean isNotReserved(int rid,Date begin, Date end);
	public boolean isNotUsed(int rid,Date begin,Date end);
	public Room setRoomState(Room room);
	public List<Reserved> getAssociatorReserveList(String alogin);
	public List<Reserved> getHostelReserveList(String hlogin);
	public List<Income> getAssociatorPayList(String alogin);
	public List<Income> getHostelPayList(String hlogin);
	public void cancelReserve(int reserveid);
}
