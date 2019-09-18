package model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="reserve")
public class Reserved implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer rid;
	private String associator_login;
	private int reserve_state;
	private Date starttime;
	private Date endtime;
	private Room room;
	private BigDecimal reserveMoney;

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
	public int getReserve_state() {
		return reserve_state;
	}
	public void setReserve_state(int reserve_state) {
		this.reserve_state = reserve_state;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="rid", insertable = false, updatable = false)
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public BigDecimal getReserveMoney() {
		return reserveMoney;
	}
	public void setReserveMoney(BigDecimal reserveMoney) {
		this.reserveMoney = reserveMoney;
	}
	
	
}
