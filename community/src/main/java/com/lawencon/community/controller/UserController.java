package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.code.PojoCodeData;
import com.lawencon.community.pojo.profile.PojoInsertProfileReq;
import com.lawencon.community.pojo.user.PojoFindByIdUserRes;
import com.lawencon.community.service.CodeService;
import com.lawencon.community.service.ProfileService;
import com.lawencon.community.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CodeService codeService;

	@Autowired
	private ProfileService profileService;

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdUserRes> findById(@PathVariable("id") String id) throws Exception {
		PojoFindByIdUserRes findRes = userService.findById(id);
		return new ResponseEntity<PojoFindByIdUserRes>(findRes, HttpStatus.OK);
	}
//
//	@GetMapping
//	public ResponseEntity<SearchQuery<PojoUserData>> findAll() throws Exception{
//		SearchQuery<PojoUserData>  viewRes = userService.getAll();
//		return new ResponseEntity<SearchQuery<PojoUserData>>(viewRes, HttpStatus.OK);
//	}
	

	@GetMapping("/generate-valid-code/{mail}")
	public ResponseEntity<PojoCodeData> generateValidationCode(@PathVariable("mail") String mail) throws Exception {
		PojoCodeData findRes = codeService.generateRandomCode(mail);
		return new ResponseEntity<PojoCodeData>(findRes, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody PojoInsertProfileReq data) throws Exception{
		PojoInsertRes insertRes = profileService.regist(data);
		return new ResponseEntity<PojoInsertRes>(insertRes, HttpStatus.CREATED);
	}
//	
//	@PutMapping
//	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid UpdateUserReq data) throws Exception{
//		PojoUpdateRes updateRes = userService.updateById(data);
//		return new ResponseEntity<PojoUpdateRes>(updateRes, HttpStatus.OK);
//	}
//
//	@DeleteMapping("{id}")
//	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") Long id) throws Exception {
//		PojoDeleteRes delRes = userService.deleteById(id);
//		return new ResponseEntity<PojoDeleteRes>(delRes, HttpStatus.OK);
//	}
}
