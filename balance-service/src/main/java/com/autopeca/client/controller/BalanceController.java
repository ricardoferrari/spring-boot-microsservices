package com.autopeca.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.PermitAll;

import com.autopeca.client.model.Balance;
import com.autopeca.client.service.BalanceService;

@RestController
@RequestMapping(value="v1/client")
public class BalanceController {

	@Autowired
	private BalanceService balanceService;

	// @RolesAllowed({"ADMIN", "EMPLOYEE"})
	@PermitAll
	@GetMapping(value="/{clientId}/score")
	public ResponseEntity<Balance> getBalance( @PathVariable("clientId") String clientId) {
		
		Balance balance = balanceService.getBalance(clientId);
	
		return ResponseEntity.ok(balance);
	}


}
