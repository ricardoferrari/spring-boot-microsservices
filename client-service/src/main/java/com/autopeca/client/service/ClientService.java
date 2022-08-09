package com.autopeca.client.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.autopeca.client.model.Client;
import com.autopeca.client.service.BalanceService;
import com.autopeca.client.model.Balance;
import com.autopeca.client.config.ServiceConfig;

import java.util.concurrent.TimeoutException;
import java.io.IOException;
import org.aspectj.lang.annotation.Aspect;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;

@Service
@Aspect
public class ClientService {

	@Autowired
	ServiceConfig config;
	
	@Autowired
	BalanceService balanceService;

	private static final java.util.logging.Logger logger = Logger.getLogger( ClientService.class.getName() );

	public Client getClient(String id, String storeId) throws TimeoutException, IOException, RuntimeException {
		String firstName = "Andreza";
		
		Client client = new Client();
		client.setId(id);
		client.setStoreId(storeId);
		client.setName(firstName);
		client.setSurname("Vinicius");
		client.setDescription("Cliente da filial de São Paulo");
		Balance balance = null;
		balance = balanceService.retrieveBalance(id);

		if (balance != null) {
			client.setScore(balance.getScore());
		} else {
			client.setScore(13);
		}
		return client;
	}
	
	public Client getClientScoreOffline(String id, String storeId) {
		String firstName = "Andreza";
		
		Client client = new Client();
		client.setId(id);
		client.setStoreId(storeId);
		client.setName(firstName);
		client.setSurname("Vinicius");
		client.setDescription("Sem análise de crédito");
		client.setScore(7);
		return client;
	}

	public Client getClientScoreFailed(String id, String storeId) {
		String firstName = "Andreza";
		
		Client client = new Client();
		client.setId(id);
		client.setStoreId(storeId);
		client.setName(firstName);
		client.setSurname("Vinicius");
		client.setDescription("Falha na consulta de crédito");
		client.setScore(7);
		return client;
	}

	public Client addClient(String storeId, String name, String surname){
		logger.log(Level.WARNING,config.getProperty()+config.getName()+config.getHost());
		Client client = new Client();
		client.setId("1234444");
		client.setStoreId(storeId);
		client.setName(name);
		client.setSurname(surname);
		client.setDescription("Adicionado posteriormente");

		return client;
	}

}
