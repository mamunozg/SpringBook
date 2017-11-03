package com.marco.pizza.flow;

import static org.apache.log4j.Logger.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marco.pizza.domain.CashOrCheckPayment;
import com.marco.pizza.domain.CreditCardPayment;
import com.marco.pizza.domain.Customer;
import com.marco.pizza.domain.Order;
import com.marco.pizza.domain.Payment;
import com.marco.pizza.domain.PaymentDetails;
import com.marco.pizza.domain.PaymentType;
import com.marco.pizza.service.CustomerNotFoundException;
import com.marco.pizza.service.CustomerService;

@Component
public class PizzaFlowActions {

	private static final Logger LOGGER = getLogger(PizzaFlowActions.class);

	@Autowired
	CustomerService cusService;

	public Customer lookupCustomer(String phoneNumber) throws CustomerNotFoundException {

		Customer cus = cusService.lookupCustomer(phoneNumber);
		if (cus == null) {
			throw new CustomerNotFoundException();
		}
		return cus;

	}

	public void addCustomer(Customer cus) {
		LOGGER.warn("TODO: Flesh out the addCustomer() method.");
	}

	public Payment verifyPayment(PaymentDetails details) {
		Payment payment = null;
		if (details.getPaymentType() == PaymentType.CREDIT_CARD) {
			payment = new CreditCardPayment();
		} else {
			payment = new CashOrCheckPayment();
		}
		return payment;
	}
	
	public void saveOrder(Order order) {
		LOGGER.warn("TODO: Flesh out the saveOrder() method.");	
	}

	public boolean checkDeliveryArea(String zipCode) {
		LOGGER.warn("TODO: Flesh out the checkDeliveryArea() method.");
		return "28021".equals(zipCode);
	}
	
	
}
