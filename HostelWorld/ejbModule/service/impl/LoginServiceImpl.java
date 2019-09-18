package service.impl;

import javax.ejb.Stateless;

import dao.LoginDao;
import factory.EJBFactory;
import model.enumerate.UserType;
import model.message.LoginMessage;
import service.LoginService;
@Stateless
public class LoginServiceImpl implements LoginService {
	private static final int LOGIN_LEN=7;
	private static final String WRONG_LOGIN_FORMAT_MESSAGE = "账号长度错误";
	private static final String LOGIN_NOT_EXIST_MESSAGE = "账号不存在";
	private static final String WRONG_PASSWORD_MESSAGE = "密码错误";
	private static final String CORRECT_LOGIN_PASSWORD = "账号密码正确";
	
	private LoginDao loginDao = EJBFactory.getDaoEJB(LoginDao.class);
	@Override
	public LoginMessage check(String login, String password) {
		if(login.length()!=LOGIN_LEN)
			return new LoginMessage(false,WRONG_LOGIN_FORMAT_MESSAGE,null);
		boolean loginExist = loginDao.hasLogin(login);
		if(!loginExist)
			return new LoginMessage(false,LOGIN_NOT_EXIST_MESSAGE,null);
		UserType type = loginDao.login(login, password);
		if(type!=null){
			return new LoginMessage(true, CORRECT_LOGIN_PASSWORD,type);
		}
		else
			return new LoginMessage(false, WRONG_PASSWORD_MESSAGE,null);
	}

}
