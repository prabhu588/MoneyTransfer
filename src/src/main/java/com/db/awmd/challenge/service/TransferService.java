package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.AccountTransfer;
import com.db.awmd.challenge.repository.TransferRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

  @Getter
  private final TransferRepository transferRepository;

  @Autowired
  public TransferService(TransferRepository accountsRepository) {
    this.transferRepository = accountsRepository;
  }


  public Account getAccount(String accountId) {
    return this.transferRepository.getAccount(accountId);
  }
  public void transferAmount(AccountTransfer account) {
	    this.transferRepository.transferAmount(account);
	  }

}
