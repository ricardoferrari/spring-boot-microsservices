package com.autopeca.client.service.adapter;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.autopeca.client.model.Balance;

@Component
public class BalanceAdapter {

    public Balance getBalance(String clientId) {
        RestTemplate restTemplate = new RestTemplate();

        String serviceUri = "http://localhost:8081/v1/client/{clientId}/score";
    
        try {
            ResponseEntity<Balance> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, Balance.class, clientId);
            return restExchange.getBody();
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e.toString());
        }
        
        return new Balance();
    }
}
