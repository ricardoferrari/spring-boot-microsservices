package com.autopeca.client.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.autopeca.client.model.Client;
// import com.autopeca.client.service.adapter.BalanceAdapter;
import com.autopeca.client.service.adapter.BalanceRestTemplate;
import com.autopeca.client.model.Balance;
import com.autopeca.client.utils.GetItem;

@Service
public class ClientService {

	// @Autowired
	// BalanceAdapter balanceAdapter;
	@Autowired
	BalanceRestTemplate balanceAdapter;

	public Client getClient(String id, String storeId){
		String firstName = "Andreza";
		
		// Depends on dynamo DB
		// firstName = new GetItem().item();

		Client client = new Client();
		client.setId(id);
		client.setStoreId(storeId);
		client.setName(firstName);
		client.setSurname("Vinicius");
		client.setDescription("Cliente da filial de São Paulo");
		Balance balance = retrieveBalance(id);
		if (null != balance) {
		 	client.setScore(balance.getScore());
		} else {
			client.setScore(10);
		}

		return client;
	}

	private Balance retrieveBalance(String clientId) {
		Balance balance = null;

		balance = balanceAdapter.getBalance(clientId);

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
