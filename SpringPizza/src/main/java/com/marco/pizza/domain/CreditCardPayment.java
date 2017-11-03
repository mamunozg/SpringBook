package com.marco.pizza.domain;

public class CreditCardPayment extends Payment {

	private static final long serialVersionUID = -2258464962121408940L;
	
	private String authorization;
	
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	
	@Override
	public String toString() {
		return "CREDIT:  $" + getAmount() + " ; AUTH: " + authorization;
	}
}
