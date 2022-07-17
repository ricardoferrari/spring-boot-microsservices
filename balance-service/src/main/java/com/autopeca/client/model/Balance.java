package com.autopeca.client.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
public class Balance {
	private String clientId;
	private Number balance;
	private Number score;
	private String annotation;
}