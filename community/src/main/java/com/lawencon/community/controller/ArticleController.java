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
import com.lawencon.community.pojo.article.PojoDataArticle;
import com.lawencon.community.pojo.article.PojoFindByIdArticleRes;
import com.lawencon.community.pojo.article.PojoInsertArticleReq;
import com.lawencon.community.pojo.article.PojoUpdateArticleReq;
import com.lawencon.community.service.ArticleService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("articles")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdArticleRes> findById(@PathVariable("id") String id) throws Exception {
		PojoFindByIdArticleRes res = articleService.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<SearchQuery<PojoDataArticle>> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<PojoDataArticle> res = articleService.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertArticleReq req) throws Exception {
		PojoInsertRes res = articleService.insert(req);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid PojoUpdateArticleReq req) throws Exception {
		PojoUpdateRes res = articleService.update(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") String id) throws Exception{
		PojoDeleteRes res = articleService.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("industry/{id}")
	public ResponseEntity<SearchQuery<PojoDataArticle>> findByIndustryId(@PathVariable("id") String id,
			Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PojoDataArticle> res = articleService.getAllByIdIndustry(id, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
