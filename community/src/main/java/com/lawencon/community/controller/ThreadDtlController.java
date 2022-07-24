package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.threaddtl.PojoFindByIdThreadDtlRes;
import com.lawencon.community.pojo.threaddtl.PojoInsertThreadDtlReq;
import com.lawencon.community.pojo.threaddtl.PojoThreadDtlData;
import com.lawencon.community.pojo.threaddtl.PojoUpdateThreadDtlReq;
import com.lawencon.community.service.ThreadDtlService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("thread-comments")
public class ThreadDtlController {

	@Autowired
	private ThreadDtlService service;

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdThreadDtlRes> findById(@PathVariable("id") String id) throws Exception {
		PojoFindByIdThreadDtlRes res = service.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<SearchQuery<PojoThreadDtlData>> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<PojoThreadDtlData> res = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertThreadDtlReq req) throws Exception {
		PojoInsertRes res = service.insert(req);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid PojoUpdateThreadDtlReq req) throws Exception {
		PojoUpdateRes res = service.update(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
