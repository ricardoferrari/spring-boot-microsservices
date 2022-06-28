package com.autopeca.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.http.HttpStatus;

import com.autopeca.client.model.Client;
import com.autopeca.client.service.ClientService;

@RestController
@RequestMapping(value="v1/store/{storeId}/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping(value="/{clientId}")
	public ResponseEntity<Client> getLicense( @PathVariable("storeId") String storeId,
			@PathVariable("clientId") String clientId) {
		
			Client client = clientService.getClient(clientId, storeId);
	
		return ResponseEntity.ok(client);
	}
	
	@PostMapping
	// @ResponseStatus(HttpStatus.CREATED);
	public ResponseEntity<Client> addClient( @PathVariable("storeId") String storeId,
			@RequestBody() String name) {
		
			Client client = clientService.addClient(storeId, name, "surname");
	
		return ResponseEntity.ok(client);
	}

}
