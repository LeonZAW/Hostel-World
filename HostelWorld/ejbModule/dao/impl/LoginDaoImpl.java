package dao.impl;

import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.LoginDao;
import model.User;
import model.enumerate.UserType;

/**
 * Session Bean implementation class LoginDaoImpl
 */
@Stateless
public class LoginDaoImpl implements LoginDao{
	@PersistenceContext
	protected EntityManager em;

	@Override
	public boolean hasLogin(String login) {
		User associator = em.find(User.class, login);
		return associator!=null;
	}

	@Override
	public UserType login(String login, String password) {
		User associator = em.find(User.class, login);
		if(associator.getPassword().equals(password))
			return UserType.values()[associator.getUserType()];
		else
			return null;
	}

	@Override
	public String getNewLogin(int length) {
		StringBuffer buffer = new StringBuffer("0123456789");   
		Random random = new Random();   
		int range = buffer.length();
		String login;
		StringBuffer sb;
		do {
	        sb = new StringBuffer();   
	        for (int i = 0; i < length; i ++) {   
	            sb.append(buffer.charAt(random.nextInt(range)));   
	        }   
	        login = sb.toString();
		} while (hasLogin("A"+login)||hasLogin("H"+login));
        return login;
        
	}

	@Override
	public void register(String login, String password, UserType userType) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setUserType(userType.ordinal());
		em.persist(user);
	}

}
