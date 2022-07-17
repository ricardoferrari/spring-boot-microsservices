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

import com.autopeca.client.model.Balance;
import com.autopeca.client.service.BalanceService;

@RestController
@RequestMapping(value="v1/client")
public class BalanceController {

	@Autowired
	private ScoreService scoreService;

	@RolesAllowed({"ADMIN", "EMPLOYEE"})
	@GetMapping(value="/{clientId}/score")
	public ResponseEntity<Client> getScore( @PathVariable("clientId") String clientId) {
		
		Number score = scoreService.getScore(clientId);
	
		return ResponseEntity.ok(score);
	}


}
