package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="checkin")
public class Checkin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer rid;
	private String associator_login;
	private String name;
	private String pin;
	private Date checkintime;
	private Date checkouttime;
	private int rflag;
	private Room room;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getAssociator_login() {
		return associator_login;
	}
	public void setAssociator_login(String associator_login) {
		this.associator_login = associator_login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public Date getCheckintime() {
		return checkintime;
	}
	public void setCheckintime(Date checkintime) {
		this.checkintime = checkintime;
	}
	public Date getCheckouttime() {
		return checkouttime;
	}
	public void setCheckouttime(Date checkouttime) {
		this.checkouttime = checkouttime;
	}
	@Override
	public String toString() {
		return "Checkin [id=" + id + ", rid=" + rid + ", associator_login=" + associator_login + ", name=" + name
				+ ", pin=" + pin + ", checkintime=" + checkintime + ", checkouttime=" + checkouttime + "]";
	}
	public int getRflag() {
		return rflag;
	}
	public void setRflag(int rflag) {
		this.rflag = rflag;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="rid", insertable = false, updatable = false)
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
}
