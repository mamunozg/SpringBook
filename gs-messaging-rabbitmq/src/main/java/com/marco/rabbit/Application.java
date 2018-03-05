package com.marco.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;




@SpringBootApplication
public class Application {

	final static String queueName = "spring-boot";
	final static String topicName = "spring-boot-exchange";
	
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicName);
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);		
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFac, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFac);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
		
	}
	
	@Bean	
	MessageListenerAdapter listenerAdapter(Receiver rec) {
		return new MessageListenerAdapter(rec, "receiveMessage");
	}
	
	
	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}
	
}
