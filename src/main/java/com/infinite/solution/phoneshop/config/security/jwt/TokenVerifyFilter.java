package com.infinite.solution.phoneshop.config.security.jwt;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenVerifyFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String PREFIX ="Bearer ";
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader == null || !authorizationHeader.contains(PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authorizationHeader.replace(PREFIX, "");
		String signKey = "#(@#(@*#(@(KKSKJHFKSHLKA@)((D(FD@9ajfk1929";
		Jws<Claims> claimsJws = Jwts.parserBuilder()
			.setSigningKey(Keys.hmacShaKeyFor(signKey.getBytes()))
			.build()
			.parseClaimsJws(token);
		
		Claims body = claimsJws.getBody();
		String username = body.getSubject();
		List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
		
		Set<SimpleGrantedAuthority> authSet = authorities.stream()
			.map(a -> new SimpleGrantedAuthority(a.get("authority")))
			.collect(Collectors.toSet());
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authSet);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

}
