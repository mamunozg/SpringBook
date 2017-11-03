package com.marco.pizza.service;

import com.marco.pizza.domain.Customer;

public interface CustomerService {
	Customer lookupCustomer(String phoneNumber) throws CustomerNotFoundException;
}
