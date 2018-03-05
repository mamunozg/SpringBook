package com.marco.rabbit;

import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

@Component
public class Receiver {

	
	private CountDownLatch latch = new CountDownLatch(1);
	
	
	public void receiveMessage(String message) {
		System.out.printf("\n\nRecibiendo mensaje %S\n\n", message);
		latch.countDown();
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}
	
	
}
