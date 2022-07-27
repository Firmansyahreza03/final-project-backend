package com.lawencon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.code.PojoCodeData;
import com.lawencon.community.pojo.profile.PojoFindByIdProfileRes;
import com.lawencon.community.pojo.profile.PojoInsertProfileReq;
import com.lawencon.community.pojo.profile.PojoProfileData;
import com.lawencon.community.service.CodeService;
import com.lawencon.community.service.ProfileUserService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private CodeService codeService;

	@Autowired
	private ProfileUserService service;

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdProfileRes> findById(@PathVariable("id") String id) throws Exception {
		PojoFindByIdProfileRes res = service.findById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/email={mail}")
	public ResponseEntity<PojoFindByIdProfileRes> findByMail(@PathVariable("mail") String mail) throws Exception {
		PojoFindByIdProfileRes res = service.findByMail(mail);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<SearchQuery<PojoProfileData>> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<PojoProfileData> res = service.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(res, HttpStatus.OK);

	}
	
	@GetMapping("/generate-valid-code/{mail}")
	public ResponseEntity<PojoCodeData> generateValidationCode(@PathVariable("mail") String mail) throws Exception {
		PojoCodeData findRes = codeService.generateRandomCode(mail);
		return new ResponseEntity<PojoCodeData>(findRes, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody PojoInsertProfileReq data) throws Exception {

		PojoInsertRes insertRes = service.regist(data);

		return new ResponseEntity<PojoInsertRes>(insertRes, HttpStatus.CREATED);
	}
}
