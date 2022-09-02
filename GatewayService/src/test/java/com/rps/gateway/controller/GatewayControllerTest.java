package com.rps.gateway.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import com.rps.gateway.config.JwtTokenUtils;
import com.rps.gateway.dto.LoginDto;
import com.rps.gateway.dto.TokenDto;
import com.rps.gateway.dto.UserInfoDto;
import com.rps.gateway.repository.GatewayRepository;
import com.rps.gateway.service.GatewayService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { GatewayController.class })
@MockBeans({ @MockBean(GatewayService.class), @MockBean(AuthenticationManager.class), @MockBean(JwtTokenUtils.class) })
public class GatewayControllerTest {

	@Autowired
	GatewayController gatewayController;

	@Autowired
	GatewayService gatewayService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenUtils jwtTokenUtils;

	@Mock
	HttpServletRequest request;

	@Mock
	Authentication authentication;

	@Mock
	UserInfoDto userInfoDto;

	@Mock
	GatewayRepository gatewayRepository;

	@Mock
	ModelMapper modelMapper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testTokenNotNull() {
		String jwt = "";
		LoginDto loginDto = new LoginDto();
		loginDto.setUserName("kalaiv");
		loginDto.setPassword("test");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
		Mockito.when(gatewayService.validateUser(loginDto)).thenReturn(userInfoDto);
		Mockito.when(jwtTokenUtils.generateJwtToken(userInfoDto)).thenReturn(jwt);
		ResponseEntity<TokenDto> token = gatewayController.token(loginDto);
		assertNotNull(token);

	}

	@Test
	public void testTokenUserName() {
		String jwt = "";
		LoginDto loginDto = new LoginDto();
		loginDto.setUserName("kalaiv");
		loginDto.setPassword("test");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
		Mockito.when(gatewayService.validateUser(loginDto)).thenReturn(userInfoDto);
		Mockito.when(jwtTokenUtils.generateJwtToken(userInfoDto)).thenReturn(jwt);
		ResponseEntity<TokenDto> token = gatewayController.token(loginDto);
		assertEquals(loginDto.getUserName(), token.getBody().getUserName());

	}

	@Test
	public void testTokenInvalid() {
		String jwt = "";
		LoginDto loginDto = new LoginDto();
		loginDto.setUserName("kalaiv");
		loginDto.setPassword("test1");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
		Mockito.when(gatewayService.validateUser(loginDto)).thenReturn(userInfoDto);
		Mockito.when(jwtTokenUtils.generateJwtToken(userInfoDto)).thenReturn(jwt);
		ResponseEntity<TokenDto> token = gatewayController.token(loginDto);

		assertEquals("", token.getBody().getToken());

	}

	@Test
	public void testGetToneInfo() {
		String jwt = "";
		LoginDto loginDto = new LoginDto();
		loginDto.setUserName("kalaiv");
		loginDto.setPassword("test");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
		Mockito.when(gatewayService.validateUser(loginDto)).thenReturn(userInfoDto);
		Mockito.when(jwtTokenUtils.generateJwtToken(userInfoDto)).thenReturn(jwt);
		ResponseEntity<TokenDto> token = gatewayController.token(loginDto);
		Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token.getBody().getToken());
		ResponseEntity<UserInfoDto> user = gatewayController.getTokenInfo(request);
		assertNotNull(user);
	}

}
