package com.autopeca.client.service;

import org.springframework.stereotype.Service;

import com.autopeca.client.model.Client;

@Service
public class ClientService {

	public Client getClient(String id, String storeId){
		Client client = new Client();
		client.setId(id);
		client.setStoreId(storeId);
		client.setName("Haroldo");
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
