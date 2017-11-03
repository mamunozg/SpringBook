package com.marco.pizza.domain;

public class CashOrCheckPayment extends Payment {

	private static final long serialVersionUID = 4550379746304179717L;
	
	public CashOrCheckPayment() {
	}

	@Override
	public String toString() {
		return "CASH or CHECK:  $" + getAmount();
	}
}
