package com.infinite.solution.phoneshop.config.security;

import static com.infinite.solution.phoneshop.config.security.PermissionEnum.BRAND_READ;
import static com.infinite.solution.phoneshop.config.security.PermissionEnum.BRAND_WRITE;
import static com.infinite.solution.phoneshop.config.security.PermissionEnum.MODEL_READ;
import static com.infinite.solution.phoneshop.config.security.PermissionEnum.MODEL_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
	ADMIN(Set.of(BRAND_WRITE,BRAND_READ, MODEL_WRITE, MODEL_READ)),
	SALE(Set.of(BRAND_READ,MODEL_READ));
	
	private Set<PermissionEnum> permissions;
	
	public Set<SimpleGrantedAuthority> getAuthorities(){
		Set<SimpleGrantedAuthority> authurities = this.permissions.stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
			.collect(Collectors.toSet());
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+this.name());
		authurities.add(authority);
		return authurities;
	}
}
