package com.lawencon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.profile.PojoFindByIdProfileRes;
import com.lawencon.community.pojo.profile.PojoProfileData;
import com.lawencon.community.service.ProfileService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("profiles")
public class ProfileController {

	@Autowired
	private ProfileService service;

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdProfileRes> findById(@PathVariable("id") String id) throws Exception {
		PojoFindByIdProfileRes res = service.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<SearchQuery<PojoProfileData>> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<PojoProfileData> res = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
