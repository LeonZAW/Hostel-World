package service;

import javax.ejb.Remote;

import model.enumerate.UserType;
import model.message.RegisterMessage;

@Remote
public interface RegisterService {
	public RegisterMessage register(String password,UserType type);
}
