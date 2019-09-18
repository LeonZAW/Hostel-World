package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="audit")
public class Audit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int type;
	private String login;
	private int state;
	
	private String name;
	private int star;
	private String address;
	private String synopsis;
	private String creditId;
	private String creditPassword;
	
	private Hostel hostel;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
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
	@Override
	public String toString() {
		return "Audit [id=" + id + ", type=" + type + ", login=" + login + ", state=" + state + ", name=" + name
				+ ", star=" + star + ", address=" + address + ", synopsis=" + synopsis + ", creditId=" + creditId
				+ ", creditPassword=" + creditPassword + "]";
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
