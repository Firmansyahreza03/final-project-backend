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
import com.lawencon.community.pojo.community.PojoDataCommunity;
import com.lawencon.community.pojo.community.PojoFindByIdCommunityRes;
import com.lawencon.community.pojo.community.PojoInsertCommunityReq;
import com.lawencon.community.pojo.community.PojoUpdateCommunityReq;
import com.lawencon.community.service.CommunityService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("communities")
public class CommunityController {

	@Autowired
	private CommunityService service;

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdCommunityRes> findById(@PathVariable("id") String id) throws Exception {
		PojoFindByIdCommunityRes res = service.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<SearchQuery<PojoDataCommunity>> findAll
	(String code, String query, Integer startPage, Integer maxPage, String email)
	throws Exception {
		SearchQuery<PojoDataCommunity> res;
		if(email != null && code != null)
			res = service.getByIdIndustryAndCategoryCode(email, code, startPage, maxPage);
		else if(code != null)
			res = service.getByCategoryCode(code, startPage, maxPage);
		else
			res = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertCommunityReq req) throws Exception {
		PojoInsertRes res = service.insert(req);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid PojoUpdateCommunityReq req) throws Exception {
		PojoUpdateRes res = service.update(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") String id) throws Exception {
		PojoDeleteRes res = service.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("categories/{code}/industries/{email}")
	public ResponseEntity<SearchQuery<PojoDataCommunity>> findByIndustryIdAndCategoryCode(@PathVariable("email") String email, @PathVariable("code") String code,
			Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PojoDataCommunity> res = service.getByIdIndustryAndCategoryCode(email, code, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("byLogUser")
	public ResponseEntity<SearchQuery<PojoDataCommunity>> findLogUser
		(Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PojoDataCommunity> res = service.getByLogUser(startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
