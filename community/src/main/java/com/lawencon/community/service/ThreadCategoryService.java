package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.model.ThreadCategory;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.threadcategory.PojoFindByIdThreadCategoryRes;
import com.lawencon.community.pojo.threadcategory.PojoInsertThreadCategoryReq;
import com.lawencon.community.pojo.threadcategory.PojoThreadCategoryData;
import com.lawencon.community.pojo.threadcategory.PojoUpdateThreadCategoryReq;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadCategoryService extends BaseCoreService<ThreadCategory> {

	@Autowired
	private ThreadCategoryDao categoryDao;

	private ThreadCategory inputCategoryData(ThreadCategory result, String name, Boolean isActive) {
		result.setCategoryName(name);
		result.setIsActive(isActive);

		return result;
	}

	private PojoThreadCategoryData modelToRes(ThreadCategory data) {
		PojoThreadCategoryData result = new PojoThreadCategoryData();

		result.setId(data.getId());
		result.setCategoryName(data.getCategoryName());
		result.setCategoryCode(data.getCategoryCode());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());
		return result;
	}

	public PojoFindByIdThreadCategoryRes findById(String id) throws Exception {
		ThreadCategory data = categoryDao.getById(id);

		PojoThreadCategoryData result = modelToRes(data);
		PojoFindByIdThreadCategoryRes res = new PojoFindByIdThreadCategoryRes();
		res.setData(result);

		return res;
	}

	public SearchQuery<PojoThreadCategoryData> getAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<ThreadCategory> categoryList = categoryDao.findAll(query, startPage, maxPage);
		List<PojoThreadCategoryData> results = new ArrayList<>();

		categoryList.getData().forEach(d -> {
			PojoThreadCategoryData data;
			try {				
				data = modelToRes(d);
				results.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		SearchQuery<PojoThreadCategoryData> result = new SearchQuery<>();
		result.setData(results);
		result.setCount(categoryList.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertThreadCategoryReq data) throws Exception {
		try {
			begin();
			PojoInsertRes insertRes = new PojoInsertRes();

			ThreadCategory reqData = inputCategoryData(new ThreadCategory(), data.getCategoryName(),
					data.getIsActive());
			reqData.setCategoryCode(data.getCategoryCode());

			ThreadCategory result = save(reqData);

			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Category");

			commit();
			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoUpdateRes update(PojoUpdateThreadCategoryReq data) throws Exception {
		try {
			begin();
			PojoUpdateRes updateRes = new PojoUpdateRes();
			ThreadCategory reqData = inputCategoryData(categoryDao.getById(data.getId()), data.getCategoryName(),
					data.getIsActive());
			reqData.setVersion(data.getVersion());

			ThreadCategory result = save(reqData);
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());

			updateRes.setData(resData);
			updateRes.setMessage("Successfully Updating Category");
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
			boolean result = categoryDao.deleteById(id);
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if (result)
				deleteRes.setMessage("Successfully Delete Category");
			else
				deleteRes.setMessage("Failed Delete Category");
			commit();
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	
}
