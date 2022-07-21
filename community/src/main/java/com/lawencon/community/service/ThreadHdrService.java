package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.dao.PollingHdrDao;
import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.dao.ThreadHdrDao;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.PollingHdr;
import com.lawencon.community.model.ThreadCategory;
import com.lawencon.community.model.ThreadHdr;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.threadhdr.PojoFindByIdThreadHdrRes;
import com.lawencon.community.pojo.threadhdr.PojoInsertThreadHdrReq;
import com.lawencon.community.pojo.threadhdr.PojoThreadHdrData;
import com.lawencon.community.pojo.threadhdr.PojoUpdateThreadHdrReq;
import com.lawencon.model.SearchQuery;

public class ThreadHdrService extends BaseCoreService<ThreadHdr> {

	@Autowired
	private ThreadHdrDao hdrDao;
	@Autowired
	private ThreadCategoryDao categoryDao;
	@Autowired
	private IndustryDao industryDao;
	@Autowired
	private PollingHdrDao pollingHdrDao;

	private ThreadHdr inputThreadData(ThreadHdr result, String name, Long price, String categoryId, String industryId,
			Boolean isActive, Boolean isPremium, String pollingId) {
		result.setThreadName(name);
		result.setThreadPrice(price);
		ThreadCategory category = categoryDao.getById(categoryId);
		result.setCategory(category);
		Industry industry = industryDao.getById(industryId);
		result.setIndustry(industry);
		result.setIsActive(isActive);
		result.setIsPremium(isPremium);
		PollingHdr polling = pollingHdrDao.getById(pollingId);
		if (polling != null) {
			result.setPolling(polling);
		}
		return result;
	}

	public PojoThreadHdrData modelToRes(ThreadHdr data) {
		PojoThreadHdrData result = new PojoThreadHdrData();

		result.setThreadName(data.getThreadName());
		result.setThreadCode(data.getThreadCode());
		result.setIsPremium(data.getIsPremium());
		result.setThreadPrice(data.getThreadPrice());
		result.setPollingHdrsId(data.getPolling().getId());
		result.setPollingName(data.getPolling().getPollingName());
		result.setCategoryid(data.getCategory().getId());
		result.setCategoryName(data.getCategory().getCategoryName());
		result.setIndustryId(data.getIndustry().getId());
		result.setIndustryName(data.getIndustry().getIndustryName());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());

		return result;
	}

	public PojoFindByIdThreadHdrRes findById(String id) throws Exception {
		ThreadHdr data = hdrDao.getById(id);

		PojoThreadHdrData result = modelToRes(data);
		PojoFindByIdThreadHdrRes res = new PojoFindByIdThreadHdrRes();
		res.setData(result);

		return res;
	}

	public SearchQuery<PojoThreadHdrData> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadHdr> getAllThread = hdrDao.findAll(query, startPage, maxPage);
		List<PojoThreadHdrData> results = new ArrayList<>();

		getAllThread.getData().forEach(d -> {
			PojoThreadHdrData data = modelToRes(d);

			results.add(data);
		});
		SearchQuery<PojoThreadHdrData> result = new SearchQuery<>();
		result.setData(results);
		result.setCount(getAllThread.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertThreadHdrReq data) throws Exception {
		try {
			begin();
			PojoInsertRes insertRes = new PojoInsertRes();

			ThreadHdr reqData = inputThreadData(new ThreadHdr(), data.getThreadName(), data.getThreadPrice(),
					data.getCategoryid(), data.getIndustryId(), data.getIsActive(), data.getIsPremium(),
					data.getPollingHdrId());

			reqData.setThreadCode(data.getThreadCode());

			ThreadHdr result = save(reqData);

			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Thread");

			commit();
			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoUpdateRes update(PojoUpdateThreadHdrReq data) throws Exception {
		try {
			begin();
			PojoUpdateRes updateRes = new PojoUpdateRes();
			ThreadHdr reqData = inputThreadData(hdrDao.getById(data.getId()), data.getThreadName(),
					data.getThreadPrice(), data.getCategoryid(), data.getIndustryId(), data.getIsActive(),
					data.getIsPremium(), data.getPollingHdrsId());

			reqData.setVersion(data.getVersion());
			ThreadHdr result = save(reqData);
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());

			updateRes.setData(resData);
			updateRes.setMessage("Successfully Editing Thread");
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
			boolean result = hdrDao.deleteById(id);
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if (result)
				deleteRes.setMessage("Succesfully Delete Thread");
			else
				deleteRes.setMessage("Failed Delete Thread");
			commit();
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
