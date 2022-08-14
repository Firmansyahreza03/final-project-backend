package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.threadhdr.PojoFindByIdThreadHdrRes;
import com.lawencon.community.pojo.threadhdr.PojoInsertThreadHdrReq;
import com.lawencon.community.pojo.threadhdr.PojoThreadHdrData;
import com.lawencon.community.pojo.threadhdr.PojoUpdateThreadHdrReq;
import com.lawencon.community.service.ThreadHdrService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("threads")
public class ThreadHdrController {

	@Autowired
	private ThreadHdrService service;

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdThreadHdrRes> findById(@PathVariable("id") String id, String email) throws Exception {
		PojoFindByIdThreadHdrRes res = service.findById(id, email);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<SearchQuery<PojoThreadHdrData>> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<PojoThreadHdrData> res = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertThreadHdrReq req) throws Exception {
		PojoInsertRes res = service.insert(req);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid PojoUpdateThreadHdrReq req) throws Exception {
		PojoUpdateRes res = service.update(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") String id) throws Exception {
		PojoDeleteRes res = service.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("users/{email}")
	public ResponseEntity<SearchQuery<PojoThreadHdrData>> findByCreatorId(@PathVariable("email") String email,
			Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PojoThreadHdrData> res = service.findByCreatorEmail(email, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("user-like/{email}")
	public ResponseEntity<SearchQuery<PojoThreadHdrData>> findThreadThatAreLikedByUserLoggedEmail(@PathVariable("email") String email,
			Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PojoThreadHdrData> res = service.findThreadThatAreLikedByUserLoggedEmail(email, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("user-bookmark/{email}")
	public ResponseEntity<SearchQuery<PojoThreadHdrData>> findThreadThatAreBookmarkedByUserLoggedEmail(@PathVariable("email") String email,
			Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PojoThreadHdrData> res = service.findThreadThatAreBookmarkedByUserLoggedEmail(email, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("no-login")
	public ResponseEntity<SearchQuery<PojoThreadHdrData>> findThreadWithoutLogin(String query, Integer startPage, Integer maxPage) throws Exception{
		SearchQuery<PojoThreadHdrData> res = service.getAllWithoutLogin(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
