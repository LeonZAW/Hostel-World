package service;

import javax.ejb.Remote;

import model.message.CreditMessage;


@Remote
public interface CreditService {
	public CreditMessage check(String credit, String password);
}
