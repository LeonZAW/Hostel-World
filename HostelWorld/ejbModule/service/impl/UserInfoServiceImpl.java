package service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import dao.CardDao;
import dao.HostelDao;
import factory.EJBFactory;
import model.Card;
import model.Hostel;
import model.enumerate.AssociatorState;
import service.UserInfoService;

@Stateless
public class UserInfoServiceImpl implements UserInfoService {

	private CardDao cardDao = EJBFactory.getDaoEJB(CardDao.class);
	private HostelDao hostelDao = EJBFactory.getDaoEJB(HostelDao.class);
	@Override
	public Card getAssociator(String login) {
		return cardDao.getCard(login);
	}
	
	@Override
	public BigDecimal getCardMoney(String login) {
		Card card = cardDao.getCard(login);
		return card.getMoney();
	}

	@Override
	public Hostel getHostel(String login) {
		return hostelDao.getHostel(login);
	}
	@Override
	public void checkAssociatorLevelUp(String alogin) {
		Card associator = getAssociator(alogin);
		int old_level = associator.getLevel();
		int new_level = cardDao.getNewLevel(alogin);
		if(old_level!=new_level){
			associator.setLevel(new_level);
			cardDao.modifyLevel(associator);
		}
	}

	@Override
	public void payByCard(String login, BigDecimal money, int accpoint) {
//		cardDao.pay(login, money);
		cardDao.payPoint(login, accpoint);
	}

	@Override
	public void testState() {
		List<Card> associators = cardDao.getAllAssociator();
		for (Card associator : associators) {
			AssociatorState state = AssociatorState.values()[associator.getState()];
			if(state==AssociatorState.ACTIVATED)
				testStateActivated(associator);
			else if(state==AssociatorState.SUSPENDED)
				testStateSuspended(associator);
		}
	}
	
	private void testStateSuspended(Card associator) {
		Date deadDate = associator.getDeadDate();
		Date now = new Date();
		if(deadDate.after(now))
			return;
		cardDao.killCard(associator.getLogin());
		
	}

	private void testStateActivated(Card associator){
		Date stopDate = associator.getStopDate();
		Date now = new Date();
		if(stopDate.after(now))
			return;
		cardDao.suspendCard(associator.getLogin());
	}

	@Override
	public void modifyCard(Card card) {
		cardDao.modify(card);
	}

}
