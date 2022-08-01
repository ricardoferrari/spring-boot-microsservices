package com.autopeca.client.controller;

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
@RestController
@RequestMapping(value="v1/store/{storeId}/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@RolesAllowed({"ADMIN", "EMPLOYEE"})
	@GetMapping(value="/{clientId}")
	public ResponseEntity<Client> getLicense( @PathVariable("storeId") String storeId,
			@PathVariable("clientId") String clientId) throws IOException{
		
			Client client = clientService.getClient(clientId, storeId);
			client.add( 
				linkTo(methodOn(ClientController.class).getLicense(storeId, clientId)).withSelfRel()
			);
	
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
