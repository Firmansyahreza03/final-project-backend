package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ThreadHdrDao;
import com.lawencon.community.dao.ThreadLikedDao;
import com.lawencon.community.model.ThreadHdr;
import com.lawencon.community.model.ThreadLiked;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.threadliked.PojoFindByIdThreadLikedRes;
import com.lawencon.community.pojo.threadliked.PojoInsertThreadLikedReq;
import com.lawencon.community.pojo.threadliked.PojoThreadLikedData;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadLikedService extends BaseCoreService<ThreadLiked> {

	@Autowired
	private ThreadLikedDao threadLikedDao;
	@Autowired
	private ThreadHdrDao hdrDao; 
	
	private PojoThreadLikedData modelToRes(ThreadLiked data) {
		PojoThreadLikedData result = new PojoThreadLikedData();
		
		result.setHdrId(data.getHdr().getId());
		result.setHdrName(data.getHdr().getThreadName());
		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());
		
		return result;
	}
	
	public PojoFindByIdThreadLikedRes findById(String id) throws Exception{
		ThreadLiked data = threadLikedDao.getById(id);
		
		PojoThreadLikedData result = modelToRes(data);
		PojoFindByIdThreadLikedRes res = new PojoFindByIdThreadLikedRes();
		res.setData(result);
		
		return res;
	}
	
	public SearchQuery<PojoThreadLikedData> getAll(String query, Integer startPage, Integer maxPage) throws Exception{
		SearchQuery<ThreadLiked> getAllThread = threadLikedDao.findAll(query, startPage, maxPage);
		List<PojoThreadLikedData> results = new ArrayList<>();
		
		getAllThread.getData().forEach(d -> {
			PojoThreadLikedData data = modelToRes(d);
			
			results.add(data);
		});
		SearchQuery<PojoThreadLikedData> result = new SearchQuery<>();
		result.setData(results);
		result.setCount(getAllThread.getCount());
		return result;
	}
	
	public PojoInsertRes insert(PojoInsertThreadLikedReq data) throws Exception{
		try {
			begin();
			PojoInsertRes insertRes = new PojoInsertRes();
			
			ThreadLiked reqData = new ThreadLiked();
			ThreadHdr hdr = hdrDao.getById(data.getHdrId());
			reqData.setHdr(hdr);
			reqData.setIsActive(data.getIsActive());
			
			ThreadLiked result = save(reqData);

			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());
			
			insertRes.setData(resData);
			insertRes.setMessage("Successfully Bookmarking Thread");
			
			commit();
			
			return insertRes;
		}catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public PojoDeleteRes deleteById(String id) throws Exception{
		try {
			begin();
			boolean result = threadLikedDao.deleteById(id);
			
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if(result) {
				deleteRes.setMessage("Succesfully Delete Bookmark Thread");
			} else {
				deleteRes.setMessage("Failed Delete Bookmark");
			}
			commit();
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
