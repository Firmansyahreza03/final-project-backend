package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.pollinganswer.PojoInsertPollingAnswerReq;
import com.lawencon.community.pojo.pollinghdr.PojoInsertPollingHdrReq;
import com.lawencon.community.pojo.pollinghdr.PojoPollingHdrData;
import com.lawencon.community.service.PollingAnswerService;
import com.lawencon.community.service.PollingHdrService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("pollings")
public class PollingController {

	@Autowired
	private PollingHdrService service;
	@Autowired
	private PollingAnswerService answerService;
	
	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertPollingHdrReq req) throws Exception{
		PojoInsertRes res = service.insert(req);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PostMapping("/answer")
	public ResponseEntity<PojoInsertRes> insertAnswer(@RequestBody @Valid PojoInsertPollingAnswerReq req) throws Exception{
		PojoInsertRes res = answerService.insert(req);
		return new ResponseEntity<PojoInsertRes>(res, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<SearchQuery<PojoPollingHdrData>> getAllPolling(String query, Integer startPage, Integer maxPage) throws Exception{
		SearchQuery<PojoPollingHdrData> res = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<SearchQuery<PojoPollingHdrData>>(res, HttpStatus.OK);
	}
}
