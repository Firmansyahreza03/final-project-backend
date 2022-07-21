package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class RoleService extends BaseCoreService<Role>{
	@Autowired
	private RoleDao roleDao;

	private Role inputRoleData(Role result, String name, String code, Boolean isActive) {
		result.setRoleName(name);
		result.setRoleCode(code);
		result.setIsActive(isActive);

		return result;
	}

	private PojoDataRole modelToRes(Role data) {
		PojoDataRole result = new PojoDataRole();
		
		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());
		
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

	@Transactional(rollbackOn = Exception.class)
	public PojoInsertRes insert(PojoInsertRoleReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();
			
			Role reqData = inputRoleData(new Role(), data.getName(), data.getCode(), true);
			
			begin();
			Role result = super.save(reqData);
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());
			
			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Role");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public PojoUpdateRes update(PojoUpdateRoleReq data) throws Exception {
		try {
			PojoUpdateRes updateRes = new PojoUpdateRes();
			Role reqData = roleDao.getById(data.getId());
			
			reqData = inputRoleData(reqData, data.getName(), reqData.getRoleCode(), data.getIsActive());

			begin();
			Role result = super.save(reqData);
			commit();
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());
			
			updateRes.setData(resData);
			updateRes.setMessage("Successfully Editing Role");
			return updateRes;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public PojoDeleteRes deleteById(String id) throws Exception {
		try {
			begin();
			boolean result = roleDao.deleteById(id);
			commit();
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if(result)
				deleteRes.setMessage("Successfully Delete Role");
			else 
				deleteRes.setMessage("Failed Delete Role");
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
