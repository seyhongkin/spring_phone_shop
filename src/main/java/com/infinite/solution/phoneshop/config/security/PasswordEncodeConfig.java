package com.infinite.solution.phoneshop.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncodeConfig{
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		//PasswordEncoder encoder = new BCryptPasswordEncoder();
		return new BCryptPasswordEncoder();
	}
}
