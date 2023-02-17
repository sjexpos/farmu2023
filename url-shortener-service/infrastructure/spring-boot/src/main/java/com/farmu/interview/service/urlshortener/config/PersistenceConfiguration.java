package com.farmu.interview.service.urlshortener.config;

import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.farmu.interview.service.urlshortener.domain.Auditable;

@Configuration
@EntityScan( "com.farmu.interview.service.urlshortener.domain" )
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@ConfigurationPropertiesScan
public class PersistenceConfiguration {

    static class AuditorAwareImpl implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {
            return Optional.ofNullable("UNKNOWN");
        }
    }

	@Bean
	AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
            EntityManagerFactoryBuilder builder, ConfigurableListableBeanFactory beanFactory) {

        return builder.dataSource(dataSource)
                .packages(Auditable.class)
                .properties(Map.of(org.hibernate.cfg.AvailableSettings.BEAN_CONTAINER, new SpringBeanContainer(beanFactory)))
                .build();
    }

}
