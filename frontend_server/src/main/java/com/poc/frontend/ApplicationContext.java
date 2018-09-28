package com.poc.frontend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

import base.spring.scope.ViewScope;

@Configuration
@ComponentScan(basePackages = { "base", "com.poc.frontend" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "base.test.domain.*") })
public class ApplicationContext {
	@Bean
	public static CustomScopeConfigurer viewScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		Map<String, Object> viewScope = new HashMap<String, Object>();
		viewScope.put("view", new ViewScope());
		configurer.setScopes(viewScope);
		return configurer;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
}
