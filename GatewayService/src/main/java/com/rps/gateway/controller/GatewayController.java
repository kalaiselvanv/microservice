package com.rps.gateway.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rps.gateway.config.JwtTokenUtils;
import com.rps.gateway.dto.LoginDto;
import com.rps.gateway.dto.TokenDto;
import com.rps.gateway.dto.UserInfoDto;
import com.rps.gateway.service.GatewayService;

@RestController
@RequestMapping("/api/v1/")
public class GatewayController {

	private static final Logger logger = LoggerFactory.getLogger(GatewayController.class);
	@Autowired
	private GatewayService gatewayService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenUtils jwtUtils;

	@PostMapping("/token")
	public ResponseEntity<TokenDto> token(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserInfoDto userInfoDto = gatewayService.validateUser(loginDto);
		userInfoDto.setPassword(null);
		String jwt = jwtUtils.generateJwtToken(userInfoDto);
		TokenDto tokenDto = new TokenDto();
		tokenDto.setToken(jwt);
		tokenDto.setUserName(loginDto.getUserName());
		tokenDto.setEmployeeId(userInfoDto.getEmployeeId());
		tokenDto.setId(userInfoDto.getId());
		tokenDto.setFirstName(userInfoDto.getFirstName());
		tokenDto.setLastName(userInfoDto.getLastName());
		tokenDto.setRoleId(userInfoDto.getRoleId());
		if("Admin".equalsIgnoreCase(userInfoDto.getRoleId().getRoleName())) {
			tokenDto.setAdminRoleCheck(true);
		}
		return new ResponseEntity<>(tokenDto, HttpStatus.OK);

	}

	@GetMapping("/token/info")
	public ResponseEntity<UserInfoDto> getTokenInfo(HttpServletRequest request) {
		return new ResponseEntity<>(gatewayService.getTokenInfo(request), HttpStatus.OK);
	}
}
