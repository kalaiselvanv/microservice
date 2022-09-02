package com.rps.gateway.service;

import javax.servlet.http.HttpServletRequest;

import com.rps.gateway.dto.LoginDto;
import com.rps.gateway.dto.UserInfoDto;

public interface GatewayService {

	public UserInfoDto validateUser(LoginDto loginDto);

	public UserInfoDto getTokenInfo(HttpServletRequest request);

}
