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
import com.lawencon.community.pojo.bookmark.PojoBookmark;
import com.lawencon.community.pojo.bookmark.PojoDataBookmark;
import com.lawencon.community.pojo.bookmark.PojoFindByIdBookmarkRes;
import com.lawencon.community.pojo.bookmark.PojoInsertBookmarkReq;
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
	
	@DeleteMapping("{id}")
	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") String id) throws Exception{
		PojoDeleteRes res = service.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("thread/{threadId}/user/{email}")
	public ResponseEntity<PojoBookmark> findByThreadHdrAndEmail(@PathVariable("threadId") String idThread, @PathVariable("email") String email) throws Exception{
		PojoBookmark res = service.bookmarkThread(email, idThread);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
