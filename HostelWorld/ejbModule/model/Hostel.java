package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="hostel")
public class Hostel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private int state;
	
	private String name;
	private int star;
	private String address;
	private String synopsis;
	private String creditId;
	private String creditPassword;
	private BigDecimal totalIncome;
	private BigDecimal notPayIncome;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getCreditId() {
		return creditId;
	}
	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
	public String getCreditPassword() {
		return creditPassword;
	}
	public void setCreditPassword(String creditPassword) {
		this.creditPassword = creditPassword;
	}
	@Transient
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
	@Transient
	public BigDecimal getNotPayIncome() {
		return notPayIncome;
	}
	public void setNotPayIncome(BigDecimal notPayIncome) {
		this.notPayIncome = notPayIncome;
	}
	
	
	
}
