package com.rps.gateway.repository;

import com.rps.gateway.entity.UserInfo;

public interface GatewayRepository {

	public UserInfo getUser(String userName);
}
