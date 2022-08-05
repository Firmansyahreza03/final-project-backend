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
import com.lawencon.community.pojo.paymentTransaction.PojoDataPaymentTransaction;
import com.lawencon.community.pojo.paymentTransaction.PojoFindByIdPaymentTransactionRes;
import com.lawencon.community.pojo.paymentTransaction.PojoInsertPaymentTransactionReq;
import com.lawencon.community.pojo.paymentTransaction.PojoUpdatePaymentTransactionReq;
import com.lawencon.community.service.PaymentTransactionService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("payment-transactions")
public class PaymentTransactionController {

	@Autowired
	private PaymentTransactionService service;
	
	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdPaymentTransactionRes> findById(@PathVariable("id") String id) throws Exception{
		PojoFindByIdPaymentTransactionRes res = service.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<SearchQuery<PojoDataPaymentTransaction>> findAll(String query, Integer startPage, Integer maxPage) throws Exception{
		SearchQuery<PojoDataPaymentTransaction> res = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertPaymentTransactionReq req) throws Exception {
		PojoInsertRes res = service.insert(req);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid PojoUpdatePaymentTransactionReq req) throws Exception {
		PojoUpdateRes res = service.update(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") String id) throws Exception{
		PojoDeleteRes res = service.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
