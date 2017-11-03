package com.marco.pizza.service;

import com.marco.pizza.domain.Order;

public interface PricingEngine {
	public float calculateOrderTotal(Order order);
}
