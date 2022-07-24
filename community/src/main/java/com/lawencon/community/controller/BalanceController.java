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
import com.lawencon.community.pojo.balance.PojoDataBalance;
import com.lawencon.community.pojo.balance.PojoFindByIdBalanceRes;
import com.lawencon.community.pojo.balance.PojoInsertBalanceReq;
import com.lawencon.community.pojo.balance.PojoUpdateBalanceReq;
import com.lawencon.community.service.BalanceService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("balances")
public class BalanceController {

	@Autowired
	private BalanceService service;

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdBalanceRes> findById(@PathVariable("id") String id) throws Exception {
		PojoFindByIdBalanceRes res = service.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<SearchQuery<PojoDataBalance>> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<PojoDataBalance> res = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertBalanceReq req) throws Exception {
		PojoInsertRes res = service.insert(req);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid PojoUpdateBalanceReq req) throws Exception {
		PojoUpdateRes res = service.update(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") String id) throws Exception {
		PojoDeleteRes res = service.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("users/{id}")
	public ResponseEntity<PojoFindByIdBalanceRes> findByUserId(@PathVariable("id") String id) throws Exception {
		PojoFindByIdBalanceRes res = service.findByIdUser(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
