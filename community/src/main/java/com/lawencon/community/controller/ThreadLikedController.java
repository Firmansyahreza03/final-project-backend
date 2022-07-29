package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.threadliked.PojoFindByIdThreadLikedRes;
import com.lawencon.community.pojo.threadliked.PojoInsertThreadLikedReq;
import com.lawencon.community.pojo.threadliked.PojoThreadLiked;
import com.lawencon.community.pojo.threadliked.PojoThreadLikedData;
import com.lawencon.community.service.ThreadLikedService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("threads-liked")
public class ThreadLikedController {

	@Autowired
	private ThreadLikedService service;

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdThreadLikedRes> findById(@PathVariable("id") String id) throws Exception {
		PojoFindByIdThreadLikedRes res = service.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<SearchQuery<PojoThreadLikedData>> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<PojoThreadLikedData> res = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertThreadLikedReq req) throws Exception {
		PojoInsertRes res = service.insert(req);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") String id) throws Exception {
		PojoDeleteRes res = service.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("thread/{threadId}/user/{email}")
	public ResponseEntity<PojoThreadLiked> countLike(@PathVariable("threadId") String idThread, @PathVariable("email") String email) throws Exception{
		PojoThreadLiked res = service.likeThread(email, idThread);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
