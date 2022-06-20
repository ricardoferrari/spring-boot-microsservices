package com.autopeca.client.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
public class Client {
	private String id;
	private String description;
	private String storeId;
}