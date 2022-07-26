package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.PollingHdrDao;
import com.lawencon.community.model.PollingHdr;
import com.lawencon.community.model.PollingOption;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.pollinghdr.PojoFindByIdPollingHdrRes;
import com.lawencon.community.pojo.pollinghdr.PojoInsertPollingHdrReq;
import com.lawencon.community.pojo.pollinghdr.PojoPollingHdrData;
import com.lawencon.model.SearchQuery;

@Service
public class PollingHdrService extends BaseCoreService<PollingHdr> {

	@Autowired
	private PollingHdrDao hdrDao;
	@Autowired
	private PollingOptionService optionService;

	private PollingHdr inputPollingData(PollingHdr result, String pollingName, Boolean isActive) {
		result.setIsActive(isActive);
		result.setPollingName(pollingName);

		return result;
	}

	private PojoPollingHdrData modelToRes(PollingHdr data) {
		PojoPollingHdrData result = new PojoPollingHdrData();

		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setPollingName(data.getPollingName());
		result.setVersion(data.getVersion());

		return result;
	}

	public PojoFindByIdPollingHdrRes findById(String id) throws Exception {
		PollingHdr data = hdrDao.getById(id);

		PojoPollingHdrData result = modelToRes(data);
		PojoFindByIdPollingHdrRes res = new PojoFindByIdPollingHdrRes();
		res.setData(result);

		return res;
	}

	public SearchQuery<PojoPollingHdrData> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PollingHdr> pollingList = hdrDao.findAll(query, startPage, maxPage);
		List<PojoPollingHdrData> results = new ArrayList<>();

		pollingList.getData().forEach(d -> {
			PojoPollingHdrData data;
			try {				
				data = modelToRes(d);
				results.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		SearchQuery<PojoPollingHdrData> result = new SearchQuery<>();
		result.setData(results);
		;
		result.setCount(pollingList.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertPollingHdrReq data) throws Exception {
		try {
			begin();
			PojoInsertRes insertRes = new PojoInsertRes();

			PollingHdr reqData = inputPollingData(new PollingHdr(), data.getPollingName(), data.getIsActive());
			
			PollingHdr result = save(reqData);
			
			data.getOptions().forEach(d -> {
				try {
					PollingOption option = new PollingOption();
					option.setOptionName(d);
					option.setPollingHdr(result);
					option.setIsActive(true);
					optionService.insert(option);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			});

			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Polling");

			commit();
			return insertRes;
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
				deleteRes.setMessage("Successfully Delete Polling");
			else
				deleteRes.setMessage("Failed Delete Polling");
			commit();
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
