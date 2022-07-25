package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.ThreadDtlDao;
import com.lawencon.community.dao.ThreadHdrDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.ThreadDtl;
import com.lawencon.community.model.ThreadHdr;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.threaddtl.PojoFindByIdThreadDtlRes;
import com.lawencon.community.pojo.threaddtl.PojoInsertThreadDtlReq;
import com.lawencon.community.pojo.threaddtl.PojoThreadDtlData;
import com.lawencon.community.pojo.threaddtl.PojoUpdateThreadDtlReq;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadDtlService extends BaseCoreService<ThreadDtl> {

	@Autowired
	private ThreadDtlDao dtlDao;
	@Autowired
	private ThreadHdrDao hdrDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProfileDao profileDao;

	private ThreadDtl inputThreadData(ThreadDtl result, String hdrId, Boolean isActive, String comment, String userId) {
		ThreadHdr hdr = hdrDao.getById(hdrId);
		result.setHdr(hdr);
		result.setIsActive(isActive);
		result.setThreadComment(comment);
		User user = userDao.getById(userId);
		result.setUser(user);

		return result;
	}

	private PojoThreadDtlData modelToRes(ThreadDtl data) {
		PojoThreadDtlData res = new PojoThreadDtlData();
		res.setHdrId(data.getHdr().getId());
		res.setId(data.getId());
		res.setIsActive(data.getIsActive());
		res.setThreadComment(data.getThreadComment());
		res.setUserId(data.getUser().getId());
		res.setVersion(data.getVersion());

		return res;
	}

	public PojoFindByIdThreadDtlRes findById(String id) throws Exception {
		ThreadDtl data = dtlDao.getById(id);

		PojoThreadDtlData result = modelToRes(data);

		Profile profile = profileDao.getByIdUser(data.getUser().getId());
		result.setUserFullName(profile.getFullName());

		PojoFindByIdThreadDtlRes res = new PojoFindByIdThreadDtlRes();
		res.setData(result);
		return res;
	}

	public SearchQuery<PojoThreadDtlData> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadDtl> threadList = dtlDao.findAll(query, startPage, maxPage);
		List<PojoThreadDtlData> results = new ArrayList<>();

		threadList.getData().forEach(d -> {
			PojoThreadDtlData data = modelToRes(d);
			try {
				Profile profile = profileDao.getByIdUser(d.getUser().getId());
				data.setUserFullName(profile.getFullName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			results.add(data);
		});
		SearchQuery<PojoThreadDtlData> result = new SearchQuery<>();
		result.setData(results);
		result.setCount(threadList.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertThreadDtlReq req) throws Exception {
		try {
			begin();
			PojoInsertRes insertRes = new PojoInsertRes();

			ThreadDtl reqData = inputThreadData(new ThreadDtl(), req.getHdrId(), req.getIsActive(),
					req.getThreadComment(), req.getUserId());
			ThreadDtl result = save(reqData);

			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Succesfully Adding Comment");

			commit();
			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoUpdateRes update(PojoUpdateThreadDtlReq req) throws Exception {
		try {
			begin();
			PojoUpdateRes updateRes = new PojoUpdateRes();

			ThreadDtl reqData = inputThreadData(dtlDao.getById(req.getId()), req.getHdrId(), req.getIsActive(),
					req.getThreadComment(), req.getUserId());

			reqData.setVersion(req.getVersion());
			ThreadDtl result = save(reqData);

			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());

			updateRes.setData(resData);
			updateRes.setMessage("Succesfully Update Comment");

			commit();
			return updateRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public SearchQuery<PojoThreadDtlData> findByHdrId(String id, Integer startPage, Integer maxPage) throws Exception{
		List<ThreadDtl> detailList = dtlDao.findByHdrId(id, startPage, maxPage);
		
		SearchQuery<ThreadDtl> details = findAll(() -> detailList);
		
		List<PojoThreadDtlData> resultList = new ArrayList<>();
		
		details.getData().forEach(d -> {
			PojoThreadDtlData data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		SearchQuery<PojoThreadDtlData> result = new SearchQuery<>();
		result.setData(resultList);
		result.setCount(details.getCount());
		return result;
	}
}
