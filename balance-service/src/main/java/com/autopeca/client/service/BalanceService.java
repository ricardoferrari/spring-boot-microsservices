package com.autopeca.client.service;

import org.springframework.stereotype.Service;

import com.autopeca.client.model.Balance;

@Service
public class BalanceService {

	public Balance getBalance(String clientId){

		Balance balance = new Balance();
		balance.setClientId(clientId);
		balance.setScore(5);
		balance.setBalance(0);
		balance.setAnnotation("Cliente fidelizado");

		return balance;
	}

}
