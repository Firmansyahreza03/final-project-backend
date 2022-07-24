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
import com.lawencon.community.pojo.bookmark.PojoDataBookmark;
import com.lawencon.community.pojo.bookmark.PojoFindByIdBookmarkRes;
import com.lawencon.community.pojo.bookmark.PojoInsertBookmarkReq;
import com.lawencon.community.pojo.bookmark.PojoUpdateBookmarkReq;
import com.lawencon.community.service.BookmarkService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("bookmarks")
public class BookmarkController {

	@Autowired
	private BookmarkService service;
	
	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdBookmarkRes> findById(@PathVariable("id") String id) throws Exception{
		PojoFindByIdBookmarkRes res = service.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<SearchQuery<PojoDataBookmark>> findAll(String query, Integer startPage, Integer maxPage) throws Exception{
		SearchQuery<PojoDataBookmark> res = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertBookmarkReq req) throws Exception {
		PojoInsertRes res = service.insert(req);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid PojoUpdateBookmarkReq req) throws Exception {
		PojoUpdateRes res = service.update(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") String id) throws Exception{
		PojoDeleteRes res = service.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("industries/{id}")
	public ResponseEntity<SearchQuery<PojoDataBookmark>> findByIndustryId(@PathVariable("id") String id, Integer startPage, Integer maxPage) throws Exception{
		SearchQuery<PojoDataBookmark> res = service.getByIdIndustry(id, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
