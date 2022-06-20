package com.autopeca.client.service;

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

}
