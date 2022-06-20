package com.autopeca.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
