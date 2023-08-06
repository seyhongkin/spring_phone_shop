package com.infinite.solution.phoneshop.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import com.infinite.solution.phoneshop.config.security.jwt.JwtAuthFilter;
import com.infinite.solution.phoneshop.config.security.jwt.TokenVerifyFilter;

@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.addFilter(new JwtAuthFilter(authenticationManager()))
			.addFilterAfter(new TokenVerifyFilter(), JwtAuthFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
			.antMatchers("/").permitAll()
			//.antMatchers("/brands").hasRole(RoleEnum.SALE.name())
			//.antMatchers(HttpMethod.POST, "/brands").hasAuthority(BRAND_WRITE.getDescription())
			//.antMatchers(HttpMethod.GET, "/brands").hasAuthority(BRAND_READ.getDescription())
			.anyRequest()
			.authenticated();
			//WE DONT NEET TO USE HTTP_BASIC WHEN USE JWT/TOKEN
			//.and()
			//.httpBasic();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		//UserDetails user1 = new User("admin", passwordEncoder.encode("admin"), Collections.emptyList());
		
		UserDetails user1 = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				//.roles("ADMIN")
				.authorities(RoleEnum.ADMIN.getAuthorities())
				.build();
		
		UserDetails user2 = User.builder()
			.username("dara")
			.password(passwordEncoder.encode("dara123"))
			//.roles("SALE")
			.authorities(RoleEnum.SALE.getAuthorities())
			.build();
		UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(user1,user2);
		return userDetailsManager;
	}	
}
