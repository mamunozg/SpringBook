package spittr.message;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spittr.domain.Spittle;

public class ProducerMain {
	public static void main(String[] args) throws Exception {
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("amqp-producer.xml");
		AmqpTemplate template = (AmqpTemplate)context.getBean("rabbitTemplate");
		
		for(int i=0;i<20;i++) {
			
			System.out.println("Sending message #" + i);
			
			Spittle spittle = new Spittle((long) i, null, "Hello world (" + i + ")", new Date());
			template.convertAndSend(spittle);
			Thread.sleep(2500);
			
		}
		
		context.close();
		System.out.println("Done!");
		
				
	}
}
