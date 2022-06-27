package com.autopeca.client.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
public class Client {
	private String id;
	private String name;
	private String surname;
	private String description;
	private String storeId;
}