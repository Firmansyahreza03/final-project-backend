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
import com.lawencon.community.pojo.bookmark.PojoBookmark;
import com.lawencon.community.pojo.bookmark.PojoDataBookmark;
import com.lawencon.community.pojo.bookmark.PojoFindByIdBookmarkRes;
import com.lawencon.community.pojo.bookmark.PojoInsertBookmarkReq;
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

	private Bookmark inputBookmarkData(Bookmark result, Boolean isActive, String idThreadHdr)
			throws Exception {
		result.setIsActive(isActive);
		ThreadHdr fkThreadHdr = threadHdrDao.getById(idThreadHdr);

		result.setThreadHdr(fkThreadHdr);

		return result;
	}

	private PojoDataBookmark modelToRes(Bookmark data) throws Exception {
		PojoDataBookmark result = new PojoDataBookmark();
		User fkUser = userDao.getById(data.getCreatedBy());
		Profile fkProfile = profileDao.getByUserMail(fkUser.getUserEmail());
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
		SearchQuery<Bookmark> bookmarkList = bookmarkDao.findAll(query, startPage, maxPage);
		List<PojoDataBookmark> resultList = new ArrayList<>();

		bookmarkList.getData().forEach(d -> {
			PojoDataBookmark data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});

		SearchQuery<PojoDataBookmark> result = new SearchQuery<PojoDataBookmark>();
		result.setData(resultList);
		result.setCount(bookmarkList.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertBookmarkReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();

			Bookmark reqData = inputBookmarkData(new Bookmark(), true, data.getIdThreadHdr());

			Bookmark result = super.save(reqData);
			
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Bookmark");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public PojoDeleteRes deleteById(String id) throws Exception {
		try {
			boolean result = bookmarkDao.deleteById(id);

			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if (result)
				deleteRes.setMessage("Successfully Delete Bookmark");
			else
				deleteRes.setMessage("Failed Delete Bookmark");
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public SearchQuery<PojoDataBookmark> findByCreatorId(String id, Integer startPage, Integer maxPage) throws Exception{
		List<Bookmark> bookmarkList = bookmarkDao.getByIdUser(id, startPage, maxPage);
		
		SearchQuery<Bookmark> bookmarks = findAll(() -> bookmarkList);
		
		List<PojoDataBookmark> resultList = new ArrayList<>();
		
		bookmarks.getData().forEach(d -> {
			PojoDataBookmark data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		SearchQuery<PojoDataBookmark> result = new SearchQuery<>();
		result.setData(resultList);
		result.setCount(bookmarks.getData().size());
		return result;
	}
	
	public PojoBookmark bookmarkThread(String email, String hdrId) throws Exception{
		PojoBookmark result = new PojoBookmark();
		
		Profile profile = profileDao.getByUserMail(email);
		String threadBookmarkId = bookmarkDao.findByUserLogged(profile.getUser().getId(), hdrId);
		try {
			begin();
			if(threadBookmarkId != null) {
				PojoDeleteRes res = deleteById(threadBookmarkId);
				result.setMessage(res.getMessage());
				result.setIsBookmark(false);
			} else {
				PojoInsertBookmarkReq data = new PojoInsertBookmarkReq();
				data.setIdThreadHdr(hdrId);
				data.setIsActive(true);
				PojoInsertRes res = insert(data);
				result.setMessage(res.getMessage());
				result.setIsBookmark(true);
			}
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
		return result;
	}
}
