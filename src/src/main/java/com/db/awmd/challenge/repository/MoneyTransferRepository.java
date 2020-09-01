package com.db.awmd.challenge.repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.AccountTransfer;


import lombok.Synchronized;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class MoneyTransferRepository implements TransferRepository {

	@Autowired
	private RestTemplate resttemplate;
	 private final Map<String, Account> accounts = new ConcurrentHashMap<>();
@Override
public void transferAmount(AccountTransfer account) {
	// TODO Auto-generated method stub
	Account senderaccount  =resttemplate.getForObject("http://localhost:18081/v1/accounts/accountId/"+account.getSenderAccountId(),Account.class);  
	Account receiveraccount=resttemplate.getForObject("http://localhost:18081/v1/accounts/accountId/"+account.getReciverAccountId(),Account.class);
	  BigDecimal balance=	account.getAmount();
	  //check for account exist
	  if(senderaccount!=null && receiveraccount!=null)
		  {
		  //check if sender has enogh balnce to send money 
		  	if(senderaccount.getBalance().compareTo(balance) != -1)
		  		synchronized(senderaccount) {		
		  			synchronized(receiveraccount) {
		 	 senderaccount.setBalance(senderaccount.getBalance().subtract(balance));
	         receiveraccount.setBalance(senderaccount.getBalance().add(balance));
		  		}}
		  }
}


@Override
public Account getAccount(String accountId) {
	// TODO Auto-generated method stub
	return accounts.get(accountId);
}

}
