package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.LogoutReq;
import com.lawencon.community.pojo.LogoutRes;
import com.lawencon.community.service.LogoutService;

@RestController
@RequestMapping("logout")
public class LogoutController {
	@Autowired
	private LogoutService logoutService;
	
	@DeleteMapping("{accessToken}")
	public void deleteRefreshToken(@PathVariable("accessToken") String accessToken) throws Exception {
		logoutService.deleteRefreshToken(accessToken);
	}
	
	@PatchMapping
	public ResponseEntity<LogoutRes> updateUserLogged(@RequestBody @Valid LogoutReq logoutReq)throws Exception{
		LogoutRes res = logoutService.updateUserLogged(logoutReq);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
