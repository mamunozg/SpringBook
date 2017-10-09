package com.springinaction.knights;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {

	public static void main(String[] args) {

		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knight.xml");			
			Knight knight = context.getBean(Knight.class);
			knight.embarkOnQuest();
			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
