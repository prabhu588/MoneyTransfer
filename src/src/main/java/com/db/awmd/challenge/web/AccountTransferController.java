package com.db.awmd.challenge.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.db.awmd.challenge.domain.AccountTransfer;
import com.db.awmd.challenge.exception.TrasactonFailedException;
import com.db.awmd.challenge.service.TransferService;
import com.db.awmd.challenge.service.EmailNotificationService;


import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/v2/accounts")
@Slf4j
public class AccountTransferController {
	
	private final TransferService transferService;
	  private final EmailNotificationService emailNotificationService;
	  @Autowired
	  public AccountTransferController(TransferService accountsService,EmailNotificationService emailNotificationService) {
	    this.transferService = accountsService;
	    this.emailNotificationService=emailNotificationService;
	  }

	  //transferring money from one account to another also sending mails to notify users  
	  @PostMapping(value="/transfer",consumes = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> transferMoney(@RequestBody @Valid AccountTransfer account) {
	    log.info("Creating account {}", account);
	    
	    try {
	    	
	    this.transferService.transferAmount(account);
	    this.emailNotificationService.notifyAboutTransfer( transferService.getAccount(account.getSenderAccountId()), "Amount"+account.getAmount()+ "debitted successfully");
	    this.emailNotificationService.notifyAboutTransfer( transferService.getAccount(account.getReciverAccountId()), "Amount" +account.getAmount()+"Credited succsfully successfully");
	    } catch (TrasactonFailedException e) {
	      return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
	      
	    }

	    return new ResponseEntity<>(HttpStatus.CREATED);
	  }

}
