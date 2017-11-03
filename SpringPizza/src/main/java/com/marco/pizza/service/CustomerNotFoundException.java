package com.marco.pizza.service;

public class CustomerNotFoundException extends Exception {
		
	private static final long serialVersionUID = -4979522420196664075L;

	public CustomerNotFoundException() {	
	}

	public CustomerNotFoundException(String message) {
		super(message);
	}

	
}
