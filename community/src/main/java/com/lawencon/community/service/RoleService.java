package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.model.Role;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.role.PojoDataRole;
import com.lawencon.community.pojo.role.PojoFindByIdRoleRes;
import com.lawencon.community.pojo.role.PojoInsertRoleReq;
import com.lawencon.community.pojo.role.PojoUpdateRoleReq;
import com.lawencon.model.SearchQuery;

public class RoleService extends BaseCoreService{
	@Autowired
	private RoleDao roleDao;

	public Role inputRoleData(Role result, String nama, String kode) {
		result.setRoleName(nama);
		result.setRoleCode(kode);

		return result;
	}

	private PojoDataRole modelToRes(Role data) {
		PojoDataRole result = new PojoDataRole();
		
		result.setName(data.getRoleName());
		result.setCode(data.getRoleCode());
		
		return result;
	}
	
	public PojoFindByIdRoleRes findById(String id) throws Exception {
		Role data = roleDao.getById(id);
				
		PojoDataRole result = modelToRes(data);
		PojoFindByIdRoleRes resultData = new PojoFindByIdRoleRes();
		resultData.setData(result);
		
		return resultData;
	}
	
	public SearchQuery<PojoDataRole> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Role> getAllRole= roleDao.findAll(query, startPage, maxPage);
		List<PojoDataRole> resultList = new ArrayList<>();
		
		getAllRole.getData().forEach(d -> {
			PojoDataRole data = modelToRes(d);
			
			resultList.add(data);
			
		});
		SearchQuery<PojoDataRole> result = new SearchQuery<PojoDataRole>();
		result.setData(resultList);
		result.setCount(getAllRole.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertRoleReq data) throws Exception {
		PojoInsertRes insertRes = new PojoInsertRes();
		
		Role reqData = inputRoleData(new Role(), data.getRoleName(), data.getRoleCode());
		reqData.setCreatedBy("1");
		reqData.setIsActive(true);
		try {
			begin();
			Role result = roleDao.save(reqData);
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());
			
			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Role");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insertRes;
	}

	public PojoUpdateRes update(PojoUpdateRoleReq data) throws Exception {
		PojoUpdateRes updateRes = new PojoUpdateRes();
		
		try {

			Role reqData = roleDao.getById(data.getId());
			reqData = inputRoleData(reqData, data.getRoleName(), reqData.getRoleCode());
			reqData.setVersion(data.getVersion());
			reqData.setUpdatedBy("1");
			reqData.setIsActive(data.getIsActive());

			begin();
			Role result = roleDao.save(reqData);
			commit();
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());
			
			updateRes.setData(resData);
			updateRes.setMessage("Successfully Adding Role");
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return updateRes;
	}
	

	public PojoDeleteRes deleteById(String id) throws Exception {
		try {
			begin();
			boolean result = roleDao.deleteById(id);
			commit();
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if(result)
				deleteRes.setMessage("Delete Berhasil");
			else 
				deleteRes.setMessage("Delete Gagal");
			return deleteRes;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	
}
