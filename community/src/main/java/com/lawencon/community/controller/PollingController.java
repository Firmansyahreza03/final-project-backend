package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.pollinghdr.PojoInsertPollingHdrReq;
import com.lawencon.community.service.PollingHdrService;

@RestController
@RequestMapping("pollings")
public class PollingController {

	@Autowired
	private PollingHdrService service;
	
	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertPollingHdrReq req) throws Exception{
		PojoInsertRes res = service.insert(req);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
}
