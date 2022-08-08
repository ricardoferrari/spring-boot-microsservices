package com.autopeca.client.model;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
public class Client extends RepresentationModel<Client>{
	private String id;
	private String name;
	private String surname;
	private String description;
	private String storeId;
	private Number score;
}