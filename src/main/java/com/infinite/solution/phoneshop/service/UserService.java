package com.infinite.solution.phoneshop.service;

import java.util.Optional;

import com.infinite.solution.phoneshop.config.security.UserAuth;

public interface UserService{
	Optional<UserAuth> findUserByUsername(String username);
}
