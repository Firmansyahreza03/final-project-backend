package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.CommunityCategoryDao;
import com.lawencon.community.model.CommunityCategory;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.communityCategory.PojoDataCommunityCategory;
import com.lawencon.community.pojo.communityCategory.PojoFindByIdCommunityCategoryRes;
import com.lawencon.community.pojo.communityCategory.PojoInsertCommunityCategoryReq;
import com.lawencon.community.pojo.communityCategory.PojoUpdateCommunityCategoryReq;
import com.lawencon.model.SearchQuery;
@Service
public class CommunityCategoryService extends BaseCoreService<CommunityCategory>{
	@Autowired
	private CommunityCategoryDao communityCategoryDao;

	private CommunityCategory inputCommunityCategoryData(
			CommunityCategory result, Boolean isActive, String code, String name) throws Exception {

		result.setIsActive(isActive);

		result.setCategoryCode(code);
		result.setCategoryName(name);

		return result;
	}

	private PojoDataCommunityCategory modelToRes(CommunityCategory data) throws Exception {
		PojoDataCommunityCategory result = new PojoDataCommunityCategory();
		
		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());

		result.setName(data.getCategoryName());
		result.setCode(data.getCategoryCode());
		
		return result;
	}
	
	public PojoFindByIdCommunityCategoryRes findById(String id) throws Exception {
		CommunityCategory data = communityCategoryDao.getById(id);
				
		PojoDataCommunityCategory result = modelToRes(data);
		PojoFindByIdCommunityCategoryRes resultData = new PojoFindByIdCommunityCategoryRes();
		resultData.setData(result);
		
		return resultData;
	}
	
	public SearchQuery<PojoDataCommunityCategory> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<CommunityCategory> communityCategoryList= communityCategoryDao.findAll(query, startPage, maxPage, "categoryName", "categoryCode");
		List<PojoDataCommunityCategory> resultList = new ArrayList<>();
		
		communityCategoryList.getData().forEach(d -> {
			PojoDataCommunityCategory data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		
		SearchQuery<PojoDataCommunityCategory> result = new SearchQuery<PojoDataCommunityCategory>();
		result.setData(resultList);
		result.setCount(communityCategoryList.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertCommunityCategoryReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();
			
			CommunityCategory reqData = inputCommunityCategoryData(new CommunityCategory(), true, 
					data.getCode(), data.getName());
			
			begin();
			CommunityCategory result = super.save(reqData);
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());
			
			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Category");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoUpdateRes update(PojoUpdateCommunityCategoryReq data) throws Exception {
		try {
			PojoUpdateRes updateRes = new PojoUpdateRes();
			CommunityCategory reqData = communityCategoryDao.getById(data.getId());

			reqData = inputCommunityCategoryData(reqData, data.getIsActive(),
					reqData.getCategoryCode(), data.getName());
			begin();
			CommunityCategory result = communityCategoryDao.save(reqData);
			commit();
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());
			
			updateRes.setData(resData);
			updateRes.setMessage("Successfully Editing Category");
			return updateRes;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoDeleteRes deleteById(String id) throws Exception {
		try {
			begin();
			boolean result = communityCategoryDao.deleteById(id);
			commit();
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if(result)
				deleteRes.setMessage("Successfully Delete Category");
			else 
				deleteRes.setMessage("Failed Delete Category");
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
