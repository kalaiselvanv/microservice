package com.rps.gateway.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rps.gateway.entity.UserInfo;
import com.rps.gateway.repository.GatewayRepository;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private GatewayRepository gatewayRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = null;
		UserInfo userinfo = gatewayRepository.getUser(username);
		if (userinfo == null || userinfo.getUserName() == null) {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
		user = new User(username, bCryptEncoder.encode(userinfo.getPassword()), new ArrayList<>());

		return user;
	}

}