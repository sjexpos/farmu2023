package com.farmu.interview.service.urlshortener.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.farmu.interview.service.urlshortener.web.http.LoggingRequestFilter;


@Configuration
public class WebConfiguration {
    
	@Bean
	public FilterRegistrationBean<LoggingRequestFilter> loggingRequestFilterRegistrationBean() {
		FilterRegistrationBean<LoggingRequestFilter> registrationBean = new FilterRegistrationBean<LoggingRequestFilter>();
		LoggingRequestFilter filter = new LoggingRequestFilter();
		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns("*");
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE+1);
		return registrationBean;
	}
	
}
