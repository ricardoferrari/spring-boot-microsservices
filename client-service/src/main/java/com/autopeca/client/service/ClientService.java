package com.autopeca.client.service;

import org.springframework.stereotype.Service;

import com.autopeca.client.model.Client;
import com.autopeca.client.utils.GetItem;

@Service
public class ClientService {

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

		return client;
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
