package com.infinite.solution.phoneshop.config.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService {
	private final PasswordEncoder passwordEncoder;

	@Override
	public Optional<UserAuth> findUserByUsername(String username) {
		List<UserAuth> users = List.of(
				UserAuth.builder().username("dara").password(passwordEncoder.encode("sok123"))
						.authorities(RoleEnum.ADMIN.getAuthorities()).accountNonExpired(true).accountNonLocked(true)
						.credentialsNonExpired(true).enabled(true).build(),
				UserAuth.builder().username("vichit").password(passwordEncoder.encode("vichit123"))
						.authorities(RoleEnum.ADMIN.getAuthorities()).accountNonExpired(true).accountNonLocked(true)
						.credentialsNonExpired(true).enabled(true).build());

		return users.stream()
			.filter(user -> user.getUsername().equals(username))
			.findFirst();
	}

}
