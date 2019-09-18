package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="card")
public class Card implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private int state;
	private BigDecimal money;
	private Date activateDate;
	private Date suspendDate;
	private Date stopDate;
	private Date deadDate;
	private String pin;
	private String name;
	private int level;
	private int accpoint;
	private BigDecimal totalConsum;
	@Id
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Date getActivateDate() {
		return activateDate;
	}
	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}
	public Date getSuspendDate() {
		return suspendDate;
	}
	public void setSuspendDate(Date suspendDate) {
		this.suspendDate = suspendDate;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	public Date getDeadDate() {
		return deadDate;
	}
	public void setDeadDate(Date deadDate) {
		this.deadDate = deadDate;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Transient
	public BigDecimal getTotalConsum() {
		return totalConsum;
	}
	public void setTotalConsum(BigDecimal totalConsum) {
		this.totalConsum = totalConsum;
	}
	public int getAccpoint() {
		return accpoint;
	}
	public void setAccpoint(int accpoint) {
		this.accpoint = accpoint;
	}

	
	
}
