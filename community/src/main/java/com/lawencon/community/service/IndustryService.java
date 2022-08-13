package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.model.Industry;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.industry.PojoDataIndustry;
import com.lawencon.community.pojo.industry.PojoFindByIdIndustryRes;
import com.lawencon.community.pojo.industry.PojoInsertIndustryReq;
import com.lawencon.community.pojo.industry.PojoUpdateIndustryReq;
import com.lawencon.model.SearchQuery;

@Service
public class IndustryService extends BaseCoreService<Industry> {

	@Autowired
	private IndustryDao industryDao;

	private Industry inputIndustry(Industry result, String name, Boolean isActive) {
		result.setIndustryName(name);
		result.setIsActive(isActive);

		return result;
	}

	private PojoDataIndustry modelToRes(Industry data) {
		PojoDataIndustry res = new PojoDataIndustry();
		res.setCode(data.getIndustryCode());
		res.setId(data.getId());
		res.setIsActive(data.getIsActive());
		res.setName(data.getIndustryName());
		res.setVersion(data.getVersion());

		return res;
	}

	public PojoFindByIdIndustryRes findById(String id) throws Exception {
		Industry data = industryDao.getById(id);

		PojoDataIndustry result = modelToRes(data);
		PojoFindByIdIndustryRes resultData = new PojoFindByIdIndustryRes();
		resultData.setData(result);

		return resultData;
	}

	public SearchQuery<PojoDataIndustry> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		
		SearchQuery<Industry> industryList = industryDao.searchQueryTable(query, startPage, maxPage, "industryName", "industryCode");
		List<PojoDataIndustry> resultList = new ArrayList<>();

		industryList.getData().forEach(d -> {
			PojoDataIndustry data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		SearchQuery<PojoDataIndustry> result = new SearchQuery<PojoDataIndustry>();
		result.setData(resultList);
		result.setCount(industryList.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertIndustryReq data) throws Exception {
		try {
			begin();
			PojoInsertRes insertRes = new PojoInsertRes();

			Industry reqData = inputIndustry(new Industry(), data.getName(), data.getIsActive());
			reqData.setIndustryCode(data.getCode());

			Industry result = save(reqData);
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Industry");

			commit();
			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoUpdateRes update(PojoUpdateIndustryReq data) throws Exception {
		try {
			begin();
			PojoUpdateRes updateRes = new PojoUpdateRes();
			Industry reqData = industryDao.getById(data.getId());

			reqData = inputIndustry(reqData, data.getName(), data.getIsActive());
			reqData.setVersion(data.getVersion());

			Industry result = save(reqData);

			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());

			updateRes.setData(resData);
			updateRes.setMessage("Successfully Editing Industry");

			commit();
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
			boolean result = industryDao.deleteById(id);
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if (result)
				deleteRes.setMessage("Successfully Delete Industry");
			else
				deleteRes.setMessage("Failed Delete Industry");
			commit();
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
