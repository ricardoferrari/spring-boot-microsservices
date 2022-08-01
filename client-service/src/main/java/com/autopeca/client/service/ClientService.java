package com.autopeca.client.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.autopeca.client.model.Client;
import com.autopeca.client.service.adapter.BalanceRestTemplate;
import com.autopeca.client.service.BalanceService;
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

@Service
public class ClientService {

	@Autowired
	BalanceRestTemplate balanceAdapter;

	@Autowired
	BalanceService balanceService;

	private static final String BACKEND = "clientService";

	private static final java.util.logging.Logger logger = Logger.getLogger( ClientService.class.getName() );

	// @RateLimiter(name = "balanceService", fallbackMethod = "fallbackRetrieveBalance")
	// @Retry(name = "balanceService", fallbackMethod = "fallbackRetrieveBalance")
	// @Bulkhead(name = "balanceService", type= Type.THREADPOOL, fallbackMethod = "fallbackRetrieveBalance")
	@CircuitBreaker(name = BACKEND, fallbackMethod = "fallbackRetrieveBalance")
	public Client getClient(String id, String storeId) throws IOException, TimeoutException {
		String firstName = "Andreza";
		
		Client client = new Client();
		client.setId(id);
		client.setStoreId(storeId);
		client.setName(firstName);
		client.setSurname("Vinicius");
		client.setDescription("Cliente da filial de São Paulo");
		Balance balance = null;
		try {
			balance = balanceService.retrieveBalance(id);
		} catch (IOException | TimeoutException e) {
			logger.log(Level.WARNING,"*** Circuit braker almost open ***");
			throw e;
		}

		if (balance != null) {
			client.setScore(balance.getScore());
		} else {
			client.setScore(13);
		}
		return client;
	}

	@SuppressWarnings("unused")
	private Balance fallbackRetrieveBalance(String clientId, Exception exception) {
		Balance balance = new Balance();
		balance.setClientId(clientId);
		balance.setBalance(0.00);
		balance.setScore(10);
		balance.setAnnotation("Não foi possível obter a análise de crédito");
		return balance;
	}

	public Client addClient(String storeId, String name, String surname){
		Client client = new Client();
		client.setId("1234444");
		client.setStoreId(storeId);
		client.setName(name);
		client.setSurname(surname);
		client.setDescription("Adicionado posteriormente");

		return client;
	}

}
