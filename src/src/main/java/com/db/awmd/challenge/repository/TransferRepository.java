package com.db.awmd.challenge.repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.AccountTransfer;

public interface TransferRepository {

  Account getAccount(String accountId);
  void transferAmount(AccountTransfer account) ;
  
}
