package service;

import javax.ejb.Remote;

import model.message.LoginMessage;

@Remote
public interface LoginService {
	public LoginMessage check(String login, String password);
}
