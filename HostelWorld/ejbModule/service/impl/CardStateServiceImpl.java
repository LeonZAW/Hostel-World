package service.impl;

import java.math.BigDecimal;

import javax.ejb.Stateless;

import dao.CardDao;
import dao.CreditDao;
import factory.EJBFactory;
import model.Card;
import model.enumerate.AssociatorState;
import model.message.ActivateMessage;
import model.message.RechargeMessage;
import model.message.RelinquishMessage;
import model.message.SuspendMessage;
import service.CardStateService;

@Stateless
public class CardStateServiceImpl implements CardStateService{
	private static final BigDecimal ACTIVATE_MONEY = new BigDecimal(1000);
	private static final BigDecimal RELINQUISH_RATE = new BigDecimal("0.8");
	private static final String RECHARGE_CREDIT_NOT_EXIST = "银行账号不存在";
	private static final String RECHARGE_PASSWORD_NOT_CORRECT_MESSAGE = "密码错误";
	private static final String RECHARGE_SUCCESS_FROM_NOT_ACTIVATED = "充值成功";
//	private static final String RECHARGE_SUCCESS_FROM_DUE = "续费成功";
	private static final String RECHARGE_MONEY_NOT_ENOUGH = "因银行卡金额不足，不能完成充值";
	private static final String ACTIVATE_SUSPENDED_MONEY_NOT_ENOUGH = "会员卡余额和充值金额不足"+ACTIVATE_MONEY.intValue()+"，不能完成会员续费操作";
	private static final String ACTIVATE_SUSPEND_SUCCESS = "会员续费成功";
	private static final String ACTIVATE_NOT_ACTIVATED_MONEY_NOT_ENOUGH = "会员卡充值金额不足"+ACTIVATE_MONEY.intValue()+"，不能完成激活及充值操作";
	private static final String ACTIVATE_ACTIVATED = "账户已被激活，请勿重复激活";
	private static final String ACTIVATE_NOT_ACTIVATED_SUCCESS = "账户激活成功";
	private static final String SUSPEND_SUCCESS = "挂起成功";
	private static final String SUSPEND_STATE_ERROR = "用户状态异常，挂起失败";
	private static final String RELINQUISH_SUCCESS = "完成停用";
//	private static final String RECOVER_DATE_ERROR = "日期异常，挂起恢复失败";
//	private static final String RECOVER_SUCCESS = "挂起恢复成功";
	private static final String LOGIC_ERROR = "逻辑错误 from HostelWorld.dao.impl.ActivateServiceImpl";
	private CreditDao creditDao = EJBFactory.getDaoEJB(CreditDao.class);
	private CardDao cardDao = EJBFactory.getDaoEJB(CardDao.class);
	@Override
	public RechargeMessage recharge(String login, String credit, String password, BigDecimal money) {
		boolean creditExist = creditDao.hasCredit(credit);
		if(!creditExist)
			return new RechargeMessage(false, RECHARGE_CREDIT_NOT_EXIST, null);
		boolean check = creditDao.check(credit, password);
		if(!check)
			return new RechargeMessage(false, RECHARGE_PASSWORD_NOT_CORRECT_MESSAGE, null);
		BigDecimal balance = creditDao.getBalance(credit);
		if(balance.compareTo(money)<0)
			return new RechargeMessage(false, RECHARGE_MONEY_NOT_ENOUGH, balance);
		else{
			creditDao.recharge(credit, login, money);
			return new RechargeMessage(true, RECHARGE_SUCCESS_FROM_NOT_ACTIVATED, cardDao.getMoney(login));
		}
			
	}

	@Override
	public ActivateMessage activate(String login, String credit, String password, BigDecimal money) {
		//检查账号信息
		boolean creditExist = creditDao.hasCredit(credit);
		if(!creditExist)
			return new ActivateMessage(false, RECHARGE_CREDIT_NOT_EXIST);
		boolean check = creditDao.check(credit, password);
		if(!check)
			return new ActivateMessage(false, RECHARGE_PASSWORD_NOT_CORRECT_MESSAGE);
		Card card = cardDao.getCard(login);
		BigDecimal balance = creditDao.getBalance(credit);
		AssociatorState state = AssociatorState.values()[card.getState()];
		//排除非法状态
		if(state==AssociatorState.ACTIVATED)
			return new ActivateMessage(false,ACTIVATE_ACTIVATED);
		//检查余额
		if(balance.compareTo(money)<0)
			return new ActivateMessage(false,RECHARGE_MONEY_NOT_ENOUGH);
		//挂起状态进行激活
		if(state==AssociatorState.SUSPENDED){
			BigDecimal cardMoney = cardDao.getMoney(login);
			BigDecimal postMoney = cardMoney.add(money);
			if (postMoney.compareTo(ACTIVATE_MONEY)<0) {
				return new ActivateMessage(false,ACTIVATE_SUSPENDED_MONEY_NOT_ENOUGH);
			}else{
				cardDao.recover(login);
				creditDao.recharge(credit, login, money);
				return new ActivateMessage(true, ACTIVATE_SUSPEND_SUCCESS);
			}
		}
		if(state==AssociatorState.NOT_ACTIVATED){
			if (money.compareTo(ACTIVATE_MONEY)<0) {
				return new ActivateMessage(false,ACTIVATE_NOT_ACTIVATED_MONEY_NOT_ENOUGH);
			}else{
				cardDao.activate(login);
				creditDao.recharge(credit, login, money);
				return new ActivateMessage(true, ACTIVATE_NOT_ACTIVATED_SUCCESS);
			}
		}
		return new ActivateMessage(false,LOGIC_ERROR);
	}

	

	@Override
	public SuspendMessage suspendCheck(String login) {
		Card card = cardDao.getCard(login);
		AssociatorState state = AssociatorState.values()[card.getState()];
		if(state!=AssociatorState.ACTIVATED)
			return new SuspendMessage(false,SUSPEND_STATE_ERROR);
		cardDao.suspend(login);
		return new SuspendMessage(true,SUSPEND_SUCCESS);
	}

	@Override
	public RelinquishMessage relinquish(String login, String credit) {
		//检查账号信息
		boolean creditExist = creditDao.hasCredit(credit);
		if(!creditExist)
			return new RelinquishMessage(false, RECHARGE_CREDIT_NOT_EXIST);
		creditDao.relinquish(credit, login, RELINQUISH_RATE);
		return new RelinquishMessage(true, RELINQUISH_SUCCESS);
	}
	
	@Override
	public void decreaseMoneyForReserve(String login, BigDecimal money) {
		cardDao.pay(login, money);
	}

	@Override
	public int getAccPoint(String login) {
		return cardDao.getAccpoints(login);
	}

}
