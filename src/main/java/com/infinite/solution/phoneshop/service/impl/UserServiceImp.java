package com.infinite.solution.phoneshop.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.config.security.UserAuth;
import com.infinite.solution.phoneshop.entity.Role;
import com.infinite.solution.phoneshop.entity.User;
import com.infinite.solution.phoneshop.repository.UserRepository;
import com.infinite.solution.phoneshop.service.UserService;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
	private final UserRepository userRepository;

	@Override
	public Optional<UserAuth> findUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		UserAuth auth = UserAuth.builder().username(user.getUsername())
							.password(user.getPassword())
							.authorities(getAuthorities(user.getRoles()))
							.accountNonExpired(user.getAccountNonExpired())
							.accountNonLocked(user.getAccountNonLocked())
							.credentialsNonExpired(user.getCredentialsNonExpired())
							.enabled(user.getEnabled()).build();

		return Optional.ofNullable(auth);
	}

	//Helper
	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
		Set<SimpleGrantedAuthority> roleAuthorities = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase())).collect(Collectors.toSet());
		Set<SimpleGrantedAuthority> authorities = roles.stream().flatMap(role -> {
			return toStream(role);
		}).collect(Collectors.toSet());
		authorities.addAll(roleAuthorities);
		return authorities;
	}
	
	private Stream<SimpleGrantedAuthority> toStream(Role role){
		return role.getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getName()));
	}
}
