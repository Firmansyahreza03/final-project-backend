package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.ThreadHdrDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Bookmark;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.ThreadHdr;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.bookmark.PojoDataBookmark;
import com.lawencon.community.pojo.bookmark.PojoFindByIdBookmarkRes;
import com.lawencon.community.pojo.bookmark.PojoInsertBookmarkReq;
import com.lawencon.community.pojo.bookmark.PojoUpdateBookmarkReq;
import com.lawencon.model.SearchQuery;

@Service
public class BookmarkService extends BaseCoreService<Bookmark> {
	@Autowired
	private BookmarkDao bookmarkDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private ThreadHdrDao threadHdrDao;

	private Bookmark inputBookmarkData(Bookmark result, Boolean isActive, 
			String idUser, String idThreadHdr) throws Exception {
		result.setIsActive(isActive);
		User fkUser = userDao.getById(idUser);
		ThreadHdr fkThreadHdr = threadHdrDao.getById(idThreadHdr);

		result.setUser(fkUser);
		result.setThreadHdr(fkThreadHdr);

		return result;
	}

	private PojoDataBookmark modelToRes(Bookmark data) throws Exception {
		PojoDataBookmark result = new PojoDataBookmark();
		User fkUser = userDao.getById(data.getUser().getId());
		Profile fkProfile = profileDao.getByIdUser(data.getUser().getId());
		ThreadHdr fkThreadHdr = threadHdrDao.getById(data.getThreadHdr().getId());

		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());

		result.setIdUser(fkUser.getId());
		result.setIdThreadHdr(fkThreadHdr.getId());
		result.setNameUser(fkProfile.getFullName());
		result.setNameThreadHdr(fkThreadHdr.getThreadName());

		return result;
	}

	public PojoFindByIdBookmarkRes findById(String id) throws Exception {
		Bookmark data = bookmarkDao.getById(id);

		PojoDataBookmark result = modelToRes(data);
		PojoFindByIdBookmarkRes resultData = new PojoFindByIdBookmarkRes();
		resultData.setData(result);

		return resultData;
	}

	public SearchQuery<PojoDataBookmark> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Bookmark> getAllBookmark = bookmarkDao.findAll(query, startPage, maxPage);
		List<PojoDataBookmark> resultList = new ArrayList<>();

		getAllBookmark.getData().forEach(d -> {
			PojoDataBookmark data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		SearchQuery<PojoDataBookmark> result = new SearchQuery<PojoDataBookmark>();
		result.setData(resultList);
		result.setCount(getAllBookmark.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertBookmarkReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();

			Bookmark reqData = inputBookmarkData(new Bookmark(), true, data.getIdUser(), data.getIdThreadHdr());

			begin();
			Bookmark result = super.save(reqData);
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Bookmark");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoUpdateRes update(PojoUpdateBookmarkReq data) throws Exception {
		try {
			PojoUpdateRes updateRes = new PojoUpdateRes();
			Bookmark reqData = bookmarkDao.getById(data.getId());

			reqData = inputBookmarkData(reqData, data.getIsActive(), data.getIdUser(), data.getIdThreadHdr());

			begin();
			Bookmark result = bookmarkDao.save(reqData);
			commit();
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());

			updateRes.setData(resData);
			updateRes.setMessage("Successfully Editing Bookmark");
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
			boolean result = bookmarkDao.deleteById(id);
			commit();
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if (result)
				deleteRes.setMessage("Successfully Delete Bookmark");
			else
				deleteRes.setMessage("Failed Delete Bookmark");
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public SearchQuery<PojoDataBookmark> getAllByIdIndustry(String idx, Integer startPage, Integer maxPage) throws Exception {
		List<Bookmark> tmp = bookmarkDao.getByIdUser(idx, startPage, maxPage);
		
		SearchQuery<Bookmark> articleList = findAll(()->tmp);
		
		List<PojoDataBookmark> resultList = new ArrayList<>();
		
		articleList.getData().forEach(d -> {
			PojoDataBookmark data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		SearchQuery<PojoDataBookmark> result = new SearchQuery<PojoDataBookmark>();
		result.setData(resultList);
		result.setCount(articleList.getCount());
		return result;
	}
}
