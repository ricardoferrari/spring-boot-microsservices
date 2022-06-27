package com.autopeca.client.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.autopeca.client.model.Client;

@Service
public class ClientService {
	
	public Client getClient(String id, String storeId){
		Client client = new Client();
		client.setId(id);
		client.setStoreId(storeId);
		client.setDescription("Cliente da filial de São Paulo");

		return client;
	}

	public Client addClient(String storeId, String name, String surname){
		Client client = new Client();
		client.setId("654321");
		client.setStoreId(storeId);
		client.setName(name);
		client.setSurname(surname);
		client.setDescription("Adicionado posteriormente");

		return client;
	}

}
