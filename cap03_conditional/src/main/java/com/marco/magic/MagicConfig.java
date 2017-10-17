package com.marco.magic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MagicConfig {

	@Bean
	@Conditional(value=MagicExistsCondition.class)
	public MagicBean magicBean() {
		return new MagicBean();
	}
	
}
