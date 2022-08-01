package com.autopeca.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.autopeca.client.service.adapter.BalanceRestTemplate;
import com.autopeca.client.model.Balance;

import java.util.Random;
import java.security.SecureRandom;
import java.util.concurrent.TimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Component
public class BalanceService {

	@Autowired
	BalanceRestTemplate balanceAdapter;

	private static final java.util.logging.Logger logger = Logger.getLogger( BalanceService.class.getName() );

	public Balance retrieveBalance(String clientId) throws IOException, TimeoutException {
		Balance balance = null;
		balance = balanceAdapter.getBalance(clientId);
		randomlyRunLong();
		return balance;
	}

	private void randomlyRunLong() throws TimeoutException{
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			return;
		}
		int randomNum = random.nextInt((3 - 1) + 1) + 1;
		if (randomNum==3) sleep();
	}
	private void sleep() throws TimeoutException{
		try {
			logger.log(Level.WARNING,"*** Sleeeeeeeep ***");
			Thread.sleep(5000);
			throw new java.util.concurrent.TimeoutException();
		} catch (InterruptedException e) {
			logger.log(Level.WARNING,e.getMessage());
			Thread.currentThread().interrupt();
		}
	}


}
