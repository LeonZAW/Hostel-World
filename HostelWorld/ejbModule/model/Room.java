package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import model.enumerate.RoomState;
@Entity
@Table(name="room")
public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String login;
	private String roomNo;
	private int limitNo;
	private RoomState roomState;
	private String description;
	private int cflag;
	private Integer checkinid;
	private Hostel hostel;
	
	public Integer getCheckinid() {
		return checkinid;
	}
	public void setCheckinid(Integer checkinid) {
		this.checkinid = checkinid;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public int getLimitNo() {
		return limitNo;
	}
	public void setLimitNo(int limitNo) {
		this.limitNo = limitNo;
	}
	@Transient
	public RoomState getRoomState() {
		return roomState;
	}
	public void setRoomState(RoomState roomState) {
		this.roomState = roomState;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getCflag() {
		return cflag;
	}
	public void setCflag(int cflag) {
		this.cflag = cflag;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Room){
			Room room = (Room) obj;
			return  this.id==room.getId()&&
					this.login.equals(room.getLogin())&&
					this.roomNo.equals(room.getRoomNo())&&
					this.limitNo==room.getLimitNo()&&
					this.description.equals(room.getDescription());
		}
		return super.equals(obj);
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="login", insertable = false, updatable = false)
	public Hostel getHostel() {
		return hostel;
	}
	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}
	
	
	
}
