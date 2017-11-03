package com.marco.pizza.service;

import org.apache.log4j.Logger;

import com.marco.pizza.domain.Order;

import static org.apache.log4j.Logger.*;

public class OrderServiceImpl {
	public static Logger LOGGER = getLogger(OrderServiceImpl.class);
	
	public OrderServiceImpl() {	
	}
	
	public void saveOrder(Order order) {
		LOGGER.debug("SAVING ORDER:  " );
	    LOGGER.debug("   Customer:  " + order.getCustomer().getName());
	    LOGGER.debug("   # of Pizzas:  " + order.getPizzas().size());
		LOGGER.debug("   Payment:  " + order.getPayment());
	}

}
