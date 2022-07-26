package com.lawencon.community.controller;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.exception.InvalidLoginException;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.LoginDataRes;
import com.lawencon.community.pojo.LoginReq;
import com.lawencon.community.pojo.LoginRes;
import com.lawencon.community.service.LoginService;
import com.lawencon.util.JwtUtil;
import com.lawencon.util.JwtUtil.ClaimKey;

@RestController
@RequestMapping
public class LoginController {
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtComponent;

	@Autowired
	private LoginService loginService;

	@PostMapping("login")
	public ResponseEntity<LoginRes> login(@RequestBody @Valid LoginReq loginReq) throws Exception {
		LoginRes response = new LoginRes();
		LoginDataRes data = new LoginDataRes();
		try {
			authManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()))
					.isAuthenticated();

		} catch (Exception e) {
			throw new InvalidLoginException("email or password is wrong");
		}

		User user = loginService.findUserByMail(loginReq.getEmail());

		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(ClaimKey.ID.name(), user.getId());
		claims.put(ClaimKey.ROLE.name(), user.getRole().getRoleCode());

		String token = jwtComponent.generateToken(claims, Duration.ofHours(6));
		
		data.setEmail(user.getUserEmail());
		data.setRoleCode(user.getRole().getRoleCode());
		data.setToken(token);
		data.setRefreshToken(loginService.updateToken(user.getId()));
		response.setData(data);

		return new ResponseEntity<LoginRes>(response, HttpStatus.OK);
	}

}
