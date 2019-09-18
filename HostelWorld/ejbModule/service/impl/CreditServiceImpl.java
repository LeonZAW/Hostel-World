package service.impl;

import javax.ejb.Stateless;

import dao.CreditDao;
import factory.EJBFactory;
import model.message.CreditMessage;
import service.CreditService;

@Stateless
public class CreditServiceImpl implements CreditService {

	private static final String CREDIT_NOT_EXIST = "银行账号不存在";
	private static final String PASSWORD_NOT_CORRECT_MESSAGE = "密码错误";
	private static final String CREDIT_CORRECT = "银行信息正确";

	private CreditDao creditDao = EJBFactory.getDaoEJB(CreditDao.class);

	@Override
	public CreditMessage check(String credit, String password) {
		boolean creditExist = creditDao.hasCredit(credit);
		if(!creditExist)
			return new CreditMessage(false, CREDIT_NOT_EXIST);
		boolean check = creditDao.check(credit, password);
		if(!check)
			return new CreditMessage(false, PASSWORD_NOT_CORRECT_MESSAGE);
		return new CreditMessage(true, CREDIT_CORRECT);
	}


}
