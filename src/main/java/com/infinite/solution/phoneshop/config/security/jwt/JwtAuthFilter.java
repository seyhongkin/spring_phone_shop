package com.infinite.solution.phoneshop.config.security.jwt;

import java.io.IOException;
import java.security.Key;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);
			Authentication loginAuth = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
			Authentication authenticate = authenticationManager.authenticate(loginAuth);
			return authenticate;
		} catch (Exception e) {
			//if incorrect username or password this will throw
			//@TODO handle this exception and show message to user
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String signKey = "#(@#(@*#(@(KKSKJHFKSHLKA@)((D(FD@9ajfk1929";
		
		//token or jws
		String token = Jwts.builder()
			.setSubject(authResult.getName())
			.claim("authorities", authResult.getAuthorities())
			.setIssuedAt(new Date())
			.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
			.setIssuer("infinite-phoneshop.com")
			.signWith(Keys.hmacShaKeyFor(signKey.getBytes()))
			.compact();
		
		response.setHeader("Authorization", "Bearer "+ token);
	}
}
