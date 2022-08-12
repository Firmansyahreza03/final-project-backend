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
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.memberCommunity.PojoDataMemberCommunity;
import com.lawencon.community.pojo.memberCommunity.PojoFindByIdMemberCommunityRes;
import com.lawencon.community.pojo.memberCommunity.PojoInsertMemberCommunityReq;
import com.lawencon.community.pojo.memberCommunity.PojoUpdateMemberCommunityReq;
import com.lawencon.community.service.MemberCommuniyService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("community-members")
public class MemberCommunityController {

	@Autowired
	private MemberCommuniyService service;
	
	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdMemberCommunityRes> findById(@PathVariable("id") String id) throws Exception{
		PojoFindByIdMemberCommunityRes res = service.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<SearchQuery<PojoDataMemberCommunity>> findAll(String query, Integer startPage, Integer maxPage) throws Exception{
		SearchQuery<PojoDataMemberCommunity> res = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping
	public void insert(@RequestBody @Valid PojoInsertMemberCommunityReq req) throws Exception {
		service.insert(req);
	}

	@PutMapping
	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid PojoUpdateMemberCommunityReq req) throws Exception {
		PojoUpdateRes res = service.update(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") String id) throws Exception{
		PojoDeleteRes res = service.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("communities/{id}")
	public ResponseEntity<Boolean> checkIsJoinedMember(@PathVariable("id") String id) throws Exception{
		Boolean res = service.checkIsJoinedCommunity(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
