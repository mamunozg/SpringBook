package com.marco.pizza.domain;

import java.io.Serializable;

public abstract class Payment implements Serializable {

	private static final long serialVersionUID = 4845442267133482334L;

	private float amount;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
