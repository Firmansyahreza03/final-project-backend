package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.LogoutReq;
import com.lawencon.community.service.LogoutService;

@RestController
@RequestMapping("logout")
public class LogoutController {
	@Autowired
	private LogoutService logoutService;
	
	@DeleteMapping("{id}")
	public void deleteRefreshToken(@PathVariable("id") String id) throws Exception {
		logoutService.deleteRefreshToken(id);
	}
	
	@PatchMapping
	public void updateUserLogged(@RequestBody @Valid LogoutReq logoutReq)throws Exception{
		logoutService.updateUserLogged(logoutReq);
	}
}