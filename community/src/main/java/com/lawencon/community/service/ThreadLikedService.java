package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.ThreadHdrDao;
import com.lawencon.community.dao.ThreadLikedDao;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.ThreadHdr;
import com.lawencon.community.model.ThreadLiked;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.threadliked.PojoFindByIdThreadLikedRes;
import com.lawencon.community.pojo.threadliked.PojoInsertThreadLikedReq;
import com.lawencon.community.pojo.threadliked.PojoThreadLiked;
import com.lawencon.community.pojo.threadliked.PojoThreadLikedData;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadLikedService extends BaseCoreService<ThreadLiked> {

	@Autowired
	private ThreadLikedDao threadLikedDao;
	@Autowired
	private ThreadHdrDao hdrDao;
	@Autowired
	private ProfileDao profileDao;

	private PojoThreadLikedData modelToRes(ThreadLiked data) {
		PojoThreadLikedData result = new PojoThreadLikedData();

		result.setHdrId(data.getHdr().getId());
		result.setHdrName(data.getHdr().getThreadName());
		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());

		return result;
	}

	public PojoFindByIdThreadLikedRes findById(String id) throws Exception {
		ThreadLiked data = threadLikedDao.getById(id);

		PojoThreadLikedData result = modelToRes(data);
		PojoFindByIdThreadLikedRes res = new PojoFindByIdThreadLikedRes();
		res.setData(result);

		return res;
	}

	public SearchQuery<PojoThreadLikedData> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadLiked> getAllThread = threadLikedDao.findAll(query, startPage, maxPage);
		List<PojoThreadLikedData> results = new ArrayList<>();

		getAllThread.getData().forEach(d -> {
			try {
				PojoThreadLikedData data = modelToRes(d);
				results.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		SearchQuery<PojoThreadLikedData> result = new SearchQuery<>();
		result.setData(results);
		result.setCount(getAllThread.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertThreadLikedReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();

			ThreadLiked reqData = new ThreadLiked();
			ThreadHdr hdr = hdrDao.getById(data.getHdrId());
			reqData.setHdr(hdr);
			reqData.setIsActive(data.getIsActive());

			ThreadLiked result = save(reqData);

			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Successfully Liking Thread");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public PojoDeleteRes deleteById(String id) throws Exception {
		try {
			boolean result = threadLikedDao.deleteById(id);

			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if (result) {
				deleteRes.setMessage("Succesfully Delete Like Thread");
			} else {
				deleteRes.setMessage("Failed Delete Like");
			}
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public SearchQuery<PojoThreadLikedData> findByCreatorId(String id, Integer startPage, Integer maxPage)
			throws Exception {
		List<ThreadLiked> threadList = threadLikedDao.findByCreatedBy(id, startPage, maxPage);

		SearchQuery<ThreadLiked> threads = findAll(() -> threadList);

		List<PojoThreadLikedData> resultList = new ArrayList<>();

		threads.getData().forEach(d -> {
			PojoThreadLikedData data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		SearchQuery<PojoThreadLikedData> result = new SearchQuery<>();
		result.setData(resultList);
		result.setCount(threads.getData().size());
		return result;
	}

	public PojoThreadLiked likeThread(String email, String hdrId) throws Exception {
		PojoThreadLiked result = new PojoThreadLiked();

		Profile profile = profileDao.getByUserMail(email);
		String threadLikedId = threadLikedDao.findByUserLogged(profile.getUser().getId(), hdrId);
		try {
			begin();
			if (threadLikedId != null) {
				deleteById(threadLikedId);
				result.setIsLiked(false);
			} else {
				PojoInsertThreadLikedReq data = new PojoInsertThreadLikedReq();
				data.setHdrId(hdrId);
				data.setIsActive(true);
				insert(data);
				result.setIsLiked(true);
			}
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
		return result;
	}
}
