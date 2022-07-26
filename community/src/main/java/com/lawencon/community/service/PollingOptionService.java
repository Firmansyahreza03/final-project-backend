package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.PollingHdrDao;
import com.lawencon.community.dao.PollingOptionDao;
import com.lawencon.community.model.PollingHdr;
import com.lawencon.community.model.PollingOption;
import com.lawencon.community.pojo.pollingoption.PojoPollingOptionData;
import com.lawencon.model.SearchQuery;

@Service
public class PollingOptionService extends BaseCoreService<PollingOption> {

	@Autowired
	private PollingOptionDao optionDao;
	@Autowired
	private PollingHdrDao pollingHdrDao;

	private PojoPollingOptionData modelToRes(PollingOption data) {
		PojoPollingOptionData result = new PojoPollingOptionData();

		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setOptionName(data.getOptionName());

		PollingHdr hdr = pollingHdrDao.getById(data.getPollingHdr().getId());
		result.setPollingId(hdr.getId());
		result.setVersion(data.getVersion());

		return result;
	}

	public SearchQuery<PojoPollingOptionData> findByThreadId(String id, Integer startPage, Integer maxPage)
			throws Exception {
		List<PollingOption> optionList = optionDao.findByThreadId(id, startPage, maxPage);

		SearchQuery<PollingOption> options = findAll(() -> optionList);

		List<PojoPollingOptionData> resultList = new ArrayList<>();

		options.getData().forEach(d -> {
			PojoPollingOptionData data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});

		SearchQuery<PojoPollingOptionData> result = new SearchQuery<>();
		result.setData(resultList);
		result.setCount(options.getCount());
		return result;
	}
	
	public PollingOption insert(PollingOption req) throws Exception{
		try {			
			PollingOption res = save(req);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
