package com.autopeca.client.service.adapter;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.autopeca.client.model.Balance;

@Component
public class BalanceRestTemplate {
    
    @Autowired
    private KeycloakRestTemplate restTemplate;

    public Balance getBalance(String clientId) {
        String serviceUri = "http://localhost:8081/v1/client/{clientId}/score";

        try {
            ResponseEntity<Balance> restExchange = 
                    restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, Balance.class, clientId);

            return restExchange.getBody();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        return new Balance();
    }

    
}
