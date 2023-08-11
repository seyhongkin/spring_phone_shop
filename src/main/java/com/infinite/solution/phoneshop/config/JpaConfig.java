package com.infinite.solution.phoneshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.infinite.solution.phoneshop.config.security.AuditAwareImpl;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditAwareImpl();
	}
}
