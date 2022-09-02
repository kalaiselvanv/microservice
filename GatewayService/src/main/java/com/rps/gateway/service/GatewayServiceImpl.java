package com.rps.gateway.service;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rps.gateway.dto.LoginDto;
import com.rps.gateway.dto.UserInfoDto;
import com.rps.gateway.entity.UserInfo;
import com.rps.gateway.exception.ApplicationException;
import com.rps.gateway.exception.ErrorCodes;
import com.rps.gateway.repository.GatewayRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class GatewayServiceImpl implements GatewayService {

	private static final Logger logger = LoggerFactory.getLogger(GatewayServiceImpl.class);

	@Autowired
	GatewayRepository gatewayRepository;

	@Autowired
	ModelMapper modelMapper;

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Override
	public UserInfoDto validateUser(LoginDto loginDto) {
		UserInfoDto userInfoDto = null;
		try {
			UserInfo user = gatewayRepository.getUser(loginDto.getUserName());
			if (user != null && user.getUserName() != null) {
				if (user.getUserName().equalsIgnoreCase(loginDto.getUserName())
						&& user.getPassword().equals(loginDto.getPassword())) {
					userInfoDto = modelMapper.map(user, UserInfoDto.class);
				} else {
					throw new ApplicationException(ErrorCodes.UNAUTHORIZAED_USER);
				}
			}

		} catch (ApplicationException e) {
			throw new ApplicationException(ErrorCodes.UNAUTHORIZAED_USER);
		} catch (Exception e) {
			throw new ApplicationException(ErrorCodes.EXCEPTION);
		}
		return userInfoDto;
	}

	public UserInfoDto getTokenInfo(HttpServletRequest request) {
		UserInfoDto userInfoDto = null;
		try {
			String headerAuth = request.getHeader("Authorization");
			String token = null;
			if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")) {
				token = headerAuth.replace("Bearer", "").trim();
			}
			if (token != null) {
				Claims claim = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
				Object user = claim.get("user");
				Gson gson = new GsonBuilder().serializeNulls().create();
				String json = gson.toJson(user);
				userInfoDto = gson.fromJson(json, UserInfoDto.class);
			} else {
				throw new ApplicationException(ErrorCodes.UNAUTHORIZAED_TOKEN);
			}
		} catch (ApplicationException e) {
			throw new ApplicationException(ErrorCodes.UNAUTHORIZAED_TOKEN);
		} catch (Exception e) {
			throw new ApplicationException(ErrorCodes.EXCEPTION.getErroeCode());
		}
		return userInfoDto;
	}

}
