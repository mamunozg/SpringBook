package com.marco.rabbit;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate template;
	private final Receiver receiver;
	private final ConfigurableApplicationContext context;
	
	public Runner(RabbitTemplate template, Receiver receiver, ConfigurableApplicationContext context) {
		super();
		this.template = template;
		this.receiver = receiver;
		this.context = context;
	}
	
	
	@Override
	public void run(String... arg0) throws Exception {
		
		System.out.println("Sending message");		
		template.convertAndSend(Application.queueName, "Hello from rabbitMQ!!!!!");
		System.out.println("000000000000");		
		receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
		System.out.println("111111111111111");
		context.close();
		System.out.println("222222222222222222222222");
	}
		
}
