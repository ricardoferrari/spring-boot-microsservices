package com.autopeca.client.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.annotation.security.RolesAllowed;

import com.autopeca.client.model.Client;
import com.autopeca.client.service.ClientService;
import com.autopeca.client.model.Balance;

import org.apache.http.conn.HttpHostConnectException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;

@RestController
@RequestMapping(value="v1/store/{storeId}/client")
public class ClientController {

	private static final String BACKEND = "clientService";
	private static final java.util.logging.Logger logger = Logger.getLogger( ClientService.class.getName() );

	@Autowired
	private ClientService clientService;

	// @RateLimiter(name = "balanceService", fallbackMethod = "fallbackRetrieveBalance")
	// @Retry(name = "balanceService", fallbackMethod = "fallbackRetrieveBalance")
	// @Bulkhead(name = "balanceService", type= Type.THREADPOOL, fallbackMethod = "fallbackRetrieveBalance")
	@CircuitBreaker(name = BACKEND, fallbackMethod = "fallbackRetrieveBalance")
	@RolesAllowed({"ADMIN", "EMPLOYEE"})
	@GetMapping(value="/{clientId}")
	public ResponseEntity<Client> getLicense( @PathVariable("storeId") String storeId,
			@PathVariable("clientId") String clientId) throws IOException, TimeoutException{
		
			Client client = clientService.getClient(clientId, storeId);
			client.add( 
				linkTo(methodOn(ClientController.class).getLicense(storeId, clientId)).withSelfRel()
			);
	
		return ResponseEntity.ok(client);
	}

	@SuppressWarnings("unused")
	public ResponseEntity<Client> fallbackRetrieveBalance(String clientId, String storeId, CallNotPermittedException exception) {
		Client client = clientService.getClientScoreOffline(clientId, storeId);
		try {
			client.add( 
					linkTo(methodOn(ClientController.class).getLicense(storeId, clientId)).withSelfRel()
				);
		} catch (Exception e) {
			logger.log(Level.WARNING,e.toString());
		}
		return ResponseEntity.ok(client);
	}
	
	@SuppressWarnings("unused")
	public ResponseEntity<Client> fallbackRetrieveBalance(String clientId, String storeId, IOException exception) {
		Client client = clientService.getClientScoreOffline(clientId, storeId);
		try {
			client.add( 
					linkTo(methodOn(ClientController.class).getLicense(storeId, clientId)).withSelfRel()
				);
		} catch (Exception e) {
			logger.log(Level.WARNING,e.toString());
		}
		return ResponseEntity.ok(client);
	}
	
	@SuppressWarnings("unused")
	public ResponseEntity<Client> fallbackRetrieveBalance(String clientId, String storeId, TimeoutException exception) {
		Client client = clientService.getClientScoreFailed(clientId, storeId);
		try {
			client.add( 
					linkTo(methodOn(ClientController.class).getLicense(storeId, clientId)).withSelfRel()
				);
		} catch (Exception e) {
			logger.log(Level.WARNING,e.toString());
		}
		return ResponseEntity.ok(client);
	}
	
	@RolesAllowed({"ADMIN"})
	@PostMapping
	public ResponseEntity<Client> addClient( @PathVariable("storeId") String storeId,
			@RequestBody() String name) {
		
			Client client = clientService.addClient(storeId, name, "surname");
	
		return ResponseEntity.ok(client);
	}

}
