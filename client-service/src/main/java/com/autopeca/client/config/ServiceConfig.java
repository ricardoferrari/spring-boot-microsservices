package com.autopeca.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "example")
@Getter @Setter
public class ServiceConfig{

  @Value("property")
  private String property;
  @Value("host")
  private String host;
  @Value("name")
  private String name;
  @Value("${keycloak.realm}")
  private String realm;
    
}