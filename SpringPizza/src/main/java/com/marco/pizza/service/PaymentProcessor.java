package com.marco.pizza.service;

import com.marco.pizza.PaymentException;

public class PaymentProcessor {
	public PaymentProcessor() {

	}
	
	public void approveCreditCard(String creditCardNumber, String expMonth, String expYear, float amount) throws PaymentException {
		
		if(amount > 20.00) {
			throw new PaymentException();
		}
		
	}
}
