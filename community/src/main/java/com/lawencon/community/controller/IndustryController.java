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
import com.lawencon.community.pojo.industry.PojoDataIndustry;
import com.lawencon.community.pojo.industry.PojoFindByIdIndustryRes;
import com.lawencon.community.pojo.industry.PojoInsertIndustryReq;
import com.lawencon.community.pojo.industry.PojoUpdateIndustryReq;
import com.lawencon.community.service.IndustryService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("industries")
public class IndustryController {

	@Autowired
	private IndustryService service;

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdIndustryRes> findById(@PathVariable("id") String id) throws Exception {
		PojoFindByIdIndustryRes res = service.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	} 

	@GetMapping
	public ResponseEntity<SearchQuery<PojoDataIndustry>> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<PojoDataIndustry> result = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertIndustryReq req) throws Exception {
		PojoInsertRes insertRes = service.insert(req);
		return new ResponseEntity<>(insertRes, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid PojoUpdateIndustryReq req) throws Exception {
		PojoUpdateRes updateRes = service.update(req);
		return new ResponseEntity<>(updateRes, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") String id) throws Exception {
		PojoDeleteRes delRes = service.deleteById(id);
		return new ResponseEntity<>(delRes, HttpStatus.OK);
	}
}
