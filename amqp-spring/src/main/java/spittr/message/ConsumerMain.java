package spittr.message;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerMain {

	public static void main(String[] args) {
	    ApplicationContext context = new ClassPathXmlApplicationContext("amqp-consumer.xml");
	 }
}
