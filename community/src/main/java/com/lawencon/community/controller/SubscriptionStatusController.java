package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.subscriptionstatus.PojoFindByIdSubsStatusRes;
import com.lawencon.community.pojo.subscriptionstatus.PojoUpdateSubsStatusReq;
import com.lawencon.community.service.SubscriptionStatusService;

@RestController
@RequestMapping("subscriptions-status")
public class SubscriptionStatusController {

	@Autowired
	private SubscriptionStatusService service;

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdSubsStatusRes> findById(@PathVariable("id") String id) throws Exception {
		PojoFindByIdSubsStatusRes res = service.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid PojoUpdateSubsStatusReq req) throws Exception {
		PojoUpdateRes res = service.update(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("users/id")
	public ResponseEntity<PojoFindByIdSubsStatusRes> findByUserId(@PathVariable("id") String id) throws Exception {
		PojoFindByIdSubsStatusRes res = service.findByUserId(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
