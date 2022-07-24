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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.role.PojoDataRole;
import com.lawencon.community.pojo.role.PojoFindByIdRoleRes;
import com.lawencon.community.pojo.role.PojoInsertRoleReq;
import com.lawencon.community.pojo.role.PojoUpdateRoleReq;
import com.lawencon.community.service.RoleService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("roles")
public class RoleController {

	private RoleService roleService;

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("{id}")
	public ResponseEntity<PojoFindByIdRoleRes> findById(@PathVariable("id") String id) throws Exception {
		PojoFindByIdRoleRes findRes = roleService.findById(id);
		return new ResponseEntity<PojoFindByIdRoleRes>(findRes, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<SearchQuery<PojoDataRole>> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PojoDataRole> result = roleService.getAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PojoInsertRes> insert(@RequestBody @Valid PojoInsertRoleReq role) throws Exception{
		PojoInsertRes insertRes = roleService.insert(role);
		return new ResponseEntity<PojoInsertRes>(insertRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<PojoUpdateRes> update(@RequestBody @Valid PojoUpdateRoleReq role) throws Exception{
		PojoUpdateRes updateRes = roleService.update(role);
		return new ResponseEntity<PojoUpdateRes>(updateRes, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<PojoDeleteRes> delete(@PathVariable("id") String id) throws Exception {
		PojoDeleteRes delRes = roleService.deleteById(id);
		return new ResponseEntity<PojoDeleteRes>(delRes, HttpStatus.OK);
	}
}
