package com.marco.spittr.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import com.marco.spittr.config.RootConfig.WebPackage; 

@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages = { "com.marco.spittr" }, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, value = WebPackage.class) })

public class RootConfig {
	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com.marco.spittr\\.web"));
		}
	}
}
