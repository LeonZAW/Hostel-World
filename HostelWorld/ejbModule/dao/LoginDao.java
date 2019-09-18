package dao;

import javax.ejb.Remote;

import model.enumerate.UserType;

@Remote
public interface LoginDao {
	public boolean hasLogin(String login);
	public UserType login(String login, String password);
	public String getNewLogin(int length);
	public void register(String login,String password,UserType userType);
}
