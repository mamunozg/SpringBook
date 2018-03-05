package com.marco.spittr.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
@ComponentScan("com.marco.spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {	
		configurer.enable();
	}
	
	
	/**
	 * Configure content-negociation
	 * 
	 */
	
	public static class ContentNegociationConfig extends WebMvcConfigurerAdapter {
		
		@Bean
		public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
			ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
			cnvr.setContentNegotiationManager(cnm);
			return cnvr;
		}
		
		@Override
		public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
			configurer.defaultContentType(MediaType.TEXT_HTML);
		}
		
		@Bean
		public ViewResolver beanNameViewResolver() {
			return new BeanNameViewResolver();
		}
		
		
		public View spittles() {
			return new MappingJackson2JsonView(); 
		}
		
	}
	
}
