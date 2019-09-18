package service.impl;

import javax.ejb.Stateless;

import dao.CardDao;
import dao.HostelDao;
import dao.LoginDao;
import factory.EJBFactory;
import model.enumerate.UserType;
import model.message.RegisterMessage;
import service.RegisterService;

@Stateless
public class RegisterServiceImpl implements RegisterService{

	private static final int LOGIN_LEN=7;
	private static final String REGISTER_SUCCESS = "注册成功";
	private static final String REGISTER_NULL_TYPE = "无用户类型";
	private LoginDao loginDao = EJBFactory.getDaoEJB(LoginDao.class);
	private CardDao cardDao = EJBFactory.getDaoEJB(CardDao.class);
	private HostelDao hostelDao = EJBFactory.getDaoEJB(HostelDao.class);
	@Override
	public RegisterMessage register(String password, UserType type) {
		if(type==null)
			return new RegisterMessage(false,REGISTER_NULL_TYPE,null);
		String login = loginDao.getNewLogin(LOGIN_LEN-1);
		//根据type进行特定注册
		if(type==UserType.ASSOCIATOR){
			login="A"+login;			
			cardDao.registerNewAssociator(login);
		}
		if(type==UserType.HOSTEL){
			login="H"+login;		
			hostelDao.registerNewHostel(login);
		}
		loginDao.register(login,password,type);
		return new RegisterMessage(true,REGISTER_SUCCESS,login);
	}
	
}
