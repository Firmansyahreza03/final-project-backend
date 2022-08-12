package com.lawencon.community.controller;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.model.User;
import com.lawencon.community.pojo.LoginDataRes;
import com.lawencon.community.pojo.LoginRes;
import com.lawencon.community.service.ProfileUserService;
import com.lawencon.security.RefreshTokenService;
import com.lawencon.util.JwtUtil;
import com.lawencon.util.JwtUtil.ClaimKey;

@RestController
@RequestMapping("refresh-token")
public class RefreshTokenController {

	@Autowired
	private RefreshTokenService tokenService;
	@Autowired
	private ProfileUserService userService;
	@Autowired
	private JwtUtil jwtUtil;
		
	@PostMapping
	public ResponseEntity<LoginRes> validateRefreshToken(@RequestBody String token) throws Exception{
		tokenService.validateRefreshToken(token);
		LoginRes response = new LoginRes();
		LoginDataRes data = new LoginDataRes();
		
		User user = userService.findUserRefreshToken(token);
		
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(ClaimKey.ID.name(), user.getId());
		claims.put(ClaimKey.ROLE.name(), user.getRole().getRoleCode());
		claims.put("exp", Timestamp.valueOf(LocalDateTime.now().plusSeconds(5)));
		
		String newToken = jwtUtil.generateToken(claims, Duration.ofSeconds(5));
		
		data.setEmail(user.getUserEmail());
		data.setRoleCode(user.getRole().getRoleCode());
		data.setToken(newToken);
		data.setRefreshToken(token);
		response.setData(data);

		return new ResponseEntity<LoginRes>(response, HttpStatus.OK);
	}
}
