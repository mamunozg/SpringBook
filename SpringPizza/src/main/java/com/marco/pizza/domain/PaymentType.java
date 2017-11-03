package com.marco.pizza.domain;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

public enum PaymentType implements Serializable {
	CASH, CHECK, CREDIT_CARD;

	public static List<PaymentType> asList() {
		PaymentType[] pt = PaymentType.values();
		return Arrays.asList(pt); 
	}

	@Override
	public String toString() {
		return WordUtils.capitalizeFully(name().replace("_", " "));
	}
}
