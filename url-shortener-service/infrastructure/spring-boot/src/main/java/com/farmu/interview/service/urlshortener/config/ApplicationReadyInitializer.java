package com.farmu.interview.service.urlshortener.config;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.ConfigPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(0)
@Slf4j
public class ApplicationReadyInitializer implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private Environment environment;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("");
        log.info("********************************************************************");
		log.info(" URL SHORTENER SERVICE STARTED");
        log.info("********************************************************************");
		log.info("Default Charset: {}", Charset.defaultCharset());
		log.info("File Encoding:   {}", System.getProperty("file.encoding"));
		log.info("Server time:     {}", ZonedDateTime.now());
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
		log.info("----------------------- JVM memory ----------------------");
		log.info("Max Heap size: {}", FileUtils.byteCountToDisplaySize(memoryBean.getHeapMemoryUsage().getMax()));
		log.info("Initial Heap size: {}", FileUtils.byteCountToDisplaySize(memoryBean.getHeapMemoryUsage().getInit()));
		log.info("Heap usage: {}", FileUtils.byteCountToDisplaySize(memoryBean.getHeapMemoryUsage().getUsed()));
		log.info("------------------------ Postgres -----------------------");
        log.info("Host:     {}", environment.getProperty("spring.datasource.host"));
        log.info("Port:     {}", environment.getProperty("spring.datasource.port"));
        log.info("Schema:   {}", environment.getProperty("spring.datasource.schemaName"));
        log.info("Username: {}", environment.getProperty("spring.datasource.username"));
        String password = environment.getProperty("spring.datasource.password", "");
        password = password.substring(0, 1) + StringUtils.repeat("*", password.length()-2) + password.substring(password.length()-1);
        log.info("Password: {}", password);
		log.info("------------------------ Redis --------------------------");
        log.info("Redisson config: {}", environment.getProperty("spring.jpa.properties.hibernate.cache.redisson.config"));
		new ConfigPrinter().print(log, environment.getProperty("spring.jpa.properties.hibernate.cache.redisson.config"));
		log.info("---------------------- App config -----------------------");
        log.info("Name:    {}", environment.getProperty("farmu.interview.service.urlshortener.name"));
        log.info("Version: {}", environment.getProperty("farmu.interview.service.urlshortener.version"));
        log.info("Domain:  {}", environment.getProperty("farmu.interview.service.urlshortener.domain"));
        log.info("********************************************************************");
        log.info("");
	}

}
