package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.RoleType;
import com.lawencon.community.constant.ThreadCategoryType;
import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.PollingAnswerDao;
import com.lawencon.community.dao.PollingHdrDao;
import com.lawencon.community.dao.PollingOptionDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.dao.ThreadDtlDao;
import com.lawencon.community.dao.ThreadHdrDao;
import com.lawencon.community.dao.ThreadLikedDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.File;
import com.lawencon.community.model.PollingHdr;
import com.lawencon.community.model.PollingOption;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.ThreadCategory;
import com.lawencon.community.model.ThreadHdr;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.pollinghdr.PojoInsertPollingHdrReq;
import com.lawencon.community.pojo.pollinghdr.PojoPollingHdrData;
import com.lawencon.community.pojo.pollingoption.PojoPollingOptionData;
import com.lawencon.community.pojo.threadhdr.PojoFindByIdThreadHdrRes;
import com.lawencon.community.pojo.threadhdr.PojoInsertThreadHdrReq;
import com.lawencon.community.pojo.threadhdr.PojoThreadHdrData;
import com.lawencon.community.pojo.threadhdr.PojoUpdateThreadHdrReq;
import com.lawencon.model.SearchQuery;
import com.lawencon.security.PrincipalServiceImpl;

@Service
public class ThreadHdrService extends BaseCoreService<ThreadHdr> {

	@Autowired
	private ThreadHdrDao hdrDao;
	@Autowired
	private ThreadCategoryDao categoryDao;
	@Autowired
	private PollingHdrDao pollingHdrDao;
	@Autowired
	private CodeService codeService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private ThreadLikedDao likedDao;
	@Autowired
	private BookmarkDao bookmarkDao;
	@Autowired
	private PrincipalServiceImpl principalServiceImpl;
	@Autowired
	private PollingHdrService pollingService;
	@Autowired
	private PollingOptionDao pollingOptionDao;
	@Autowired
	private ThreadDtlDao dtlDao;
	@Autowired
	private PollingAnswerDao answerDao;

	private ThreadHdr inputThreadData(ThreadHdr result, String name, String content, String categoryId,
			Boolean isActive, String pollingId, String fileName, String fileExt, String idCreator) {
		result.setThreadName(name);
		ThreadCategory category = categoryDao.getById(categoryId);
		result.setCategory(category);
		result.setIsActive(isActive);

		if (ThreadCategoryType.PREMIUM.getCode().equals(category.getCategoryCode())) {
			result.setIsPremium(true);
		} else {
			result.setIsPremium(false);
		}

		result.setThreadContent(content);

		if (fileName != null && fileExt != null) {
			File file = new File();
			file.setFileName(fileName);
			file.setFileExtension(fileExt);
			file.setIsActive(true);
			file.setCreatedBy(idCreator);
			try {
				File res = fileDao.save(file);
				result.setFile(res);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		if (pollingId != null) {
			PollingHdr polling = pollingHdrDao.getById(pollingId);
			if (polling != null) {
				result.setPolling(polling);
			}
		}
		return result;
	}

	private PojoThreadHdrData modelToRes(ThreadHdr data) {
		PojoThreadHdrData result = new PojoThreadHdrData();

		result.setId(data.getId());
		result.setThreadName(data.getThreadName());
		result.setThreadCode(data.getThreadCode());
		result.setThreadContent(data.getThreadContent());
		result.setIsPremium(data.getIsPremium());
		if (data.getPolling() != null) {
			PojoPollingHdrData dataPoll = new PojoPollingHdrData();
			dataPoll.setExpiredAt(data.getPolling().getExpiredAt());
			dataPoll.setId(data.getPolling().getId());
			dataPoll.setIsActive(data.getPolling().getIsActive());

			List<PollingOption> pollOpt = new ArrayList<>();
			try {
				pollOpt = pollingOptionDao.findByThreadId(data.getId(), null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PojoPollingOptionData> dataPollOpt = new ArrayList<>();
			for (int i = 0; i < pollOpt.size(); i++) {
				PojoPollingOptionData dataOpt = new PojoPollingOptionData();
				dataOpt.setId(pollOpt.get(i).getId());
				dataOpt.setIsActive(pollOpt.get(i).getIsActive());
				dataOpt.setOptionName(pollOpt.get(i).getOptionName());
				dataOpt.setPollingId(data.getPolling().getId());
				dataOpt.setVersion(pollOpt.get(i).getVersion());
				dataPollOpt.add(dataOpt);
			}
			dataPoll.setPollingName(data.getPolling().getPollingName());
			dataPoll.setOption(dataPollOpt);
			result.setPollingHdr(dataPoll);
		}
		result.setCategoryid(data.getCategory().getId());
		result.setCategoryName(data.getCategory().getCategoryName());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());

		User user = userDao.getById(data.getCreatedBy());
		try {
			Profile profile = profileDao.getByUserMail(user.getUserEmail());
			result.setCreatorName(profile.getFullName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		result.setCreatedAt(data.getCreatedAt());
		if (user.getFile() != null) {
			result.setPhotoProfileCreator(user.getFile().getId());
		}
		if (data.getFile() != null) {
			result.setFileId(data.getFile().getId());
			result.setFileName(data.getFile().getFileName());
			result.setFileExt(data.getFile().getFileExtension());
		}

		return result;
	}

	public PojoFindByIdThreadHdrRes findById(String id) throws Exception {
		Boolean isAllowed = false;
		ThreadHdr data = hdrDao.getById(id);

		if (ThreadCategoryType.PREMIUM.getCode().equals(data.getCategory().getCategoryCode())) {
			try {
				User user = userDao.getById(principalServiceImpl.getAuthPrincipal());
				String roleCode = user.getRole().getRoleCode();
				if (user.getSubscriptionStatus().getIsSubscriber() == true) {
					isAllowed = true;
				} else if (data.getCreatedBy().equals(principalServiceImpl.getAuthPrincipal())) {
					isAllowed = true;
				} else if (RoleType.ADMIN.name().equals(roleCode) || RoleType.SYSTEM.name().equals(roleCode)) {
					isAllowed = true;
				}
			} catch (Exception e) {
				isAllowed = false;
			}
		} else {
			isAllowed = true;
		}

		PojoFindByIdThreadHdrRes res = new PojoFindByIdThreadHdrRes();
		if (isAllowed == true) {
			PojoThreadHdrData result = modelToRes(data);
			Long counterLike = likedDao.countThreadLikedId(id);
			result.setCounterLike(counterLike);
			Long counterComment = dtlDao.countThreadDetailId(id);
			result.setCounterComment(counterComment);

			res.setData(result);
		} else {
			res.setData(null);
		}
		return res;
	}

	public SearchQuery<PojoThreadHdrData> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadHdr> threadList = hdrDao.findAll(query, startPage, maxPage, "threadCode", "threadName",
				"category.categoryName");
		List<PojoThreadHdrData> results = new ArrayList<>();

		threadList.getData().forEach(d -> {
			PojoThreadHdrData data;
			try {
				data = modelToRes(d);
				Long counterLike = likedDao.countThreadLikedId(d.getId());
				data.setCounterLike(counterLike);
				Long counterComment = dtlDao.countThreadDetailId(d.getId());
				data.setCounterComment(counterComment);
				Boolean isLike = false;
				Boolean isBookmark = false;
				Boolean isVoted = false;

				try {
					isLike = likedDao.findIslikeByThreadHdrIdAndUserLogged(principalServiceImpl.getAuthPrincipal(),
							d.getId());
					isBookmark = bookmarkDao.findIsBookmarkByThreadHdrIdAndUserLogged(
							principalServiceImpl.getAuthPrincipal(), d.getId());
					if(d.getPolling() != null) {						
						isVoted = answerDao.findIsVotedByThreadHdrIdAndUserLogged(principalServiceImpl.getAuthPrincipal(), d.getId());
					}
				} catch (Exception e) {
					isLike = false;
					isBookmark = false;
					isVoted = false;
				}

				data.setIsLike(isLike);
				data.setIsBookmark(isBookmark);
				data.setIsVoted(isVoted);
				results.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		SearchQuery<PojoThreadHdrData> result = new SearchQuery<>();
		result.setData(results);
		result.setCount(threadList.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertThreadHdrReq data) throws Exception {
		try {
			begin();
			PojoInsertRes insertRes = new PojoInsertRes();

			PojoInsertPollingHdrReq dataPoll = new PojoInsertPollingHdrReq();
			if (data.getPollingName() != null && data.getOptions() != null) {
				dataPoll.setIsActive(true);
				dataPoll.setPollingName(data.getPollingName());
				dataPoll.setOptions(data.getOptions());
				dataPoll.setExpiredAt(data.getExpiredAt());
				PojoInsertRes insertPolling = pollingService.insert(dataPoll);
				String idPolling = insertPolling.getData().getId();
				data.setPollingHdrId(idPolling);
			}

			commit();
			ThreadHdr reqData = inputThreadData(new ThreadHdr(), data.getThreadName(), data.getThreadContent(),
					data.getCategoryId(), data.getIsActive(), data.getPollingHdrId(), data.getFileName(),
					data.getFileExt(), userDao.findByEmail(data.getEmail()).getId());

			reqData.setThreadCode(codeService.generateRandomCodeAll().getCode());

			begin();
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
					data.getThreadContent(), data.getCategoryId(), true, data.getPollingHdrsId(), data.getFileName(),
					data.getFileExt(), userDao.findByEmail(data.getEmail()).getId());

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
				deleteRes.setMessage("Successfully Delete Thread");
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

	public SearchQuery<PojoThreadHdrData> findByCreatorEmail(String email, Integer startPage, Integer maxPage)
			throws Exception {
		User user = userDao.findByEmail(email);
		List<ThreadHdr> threadList = hdrDao.findByCreatorId(user.getId(), startPage, maxPage);

		SearchQuery<ThreadHdr> threads = findAll(() -> threadList);

		List<PojoThreadHdrData> resultList = new ArrayList<>();

		threads.getData().forEach(d -> {
			PojoThreadHdrData data;
			try {
				data = modelToRes(d);
				Long counterLike = likedDao.countThreadLikedId(d.getId());
				Long counterComment = dtlDao.countThreadDetailId(d.getId());
				data.setCounterLike(counterLike);
				data.setCounterComment(counterComment);
				Boolean isLike = false;
				Boolean isBookmark = false;
				Boolean isVoted = false;
				
				try {
					isLike = likedDao.findIslikeByThreadHdrIdAndUserLogged(principalServiceImpl.getAuthPrincipal(), d.getId());
					isBookmark = bookmarkDao.findIsBookmarkByThreadHdrIdAndUserLogged(principalServiceImpl.getAuthPrincipal(), d.getId());
					if(d.getPolling() != null) {						
						isVoted = answerDao.findIsVotedByThreadHdrIdAndUserLogged(principalServiceImpl.getAuthPrincipal(), d.getId());
					}
				} catch (Exception e) {
					isLike = false;
					isBookmark = false;
					isVoted = false;
				}
				data.setIsLike(isLike);
				data.setIsBookmark(isBookmark);
				data.setIsVoted(isVoted);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});

		SearchQuery<PojoThreadHdrData> result = new SearchQuery<>();
		result.setData(resultList);
		result.setCount(threads.getData().size());
		return result;
	}

	public SearchQuery<PojoThreadHdrData> findThreadThatAreLikedByUserLoggedEmail(String email, Integer startPage,
			Integer maxPage) throws Exception {
		User user = userDao.findByEmail(email);
		List<ThreadHdr> threadList = hdrDao.findThreadByThreadLikeAndUserId(user.getId(), startPage, maxPage);

		SearchQuery<ThreadHdr> threads = findAll(() -> threadList);

		List<PojoThreadHdrData> resultList = new ArrayList<>();

		threads.getData().forEach(d -> {
			PojoThreadHdrData data;
			try {
				data = modelToRes(d);
				Long counterLike = likedDao.countThreadLikedId(d.getId());
				data.setCounterLike(counterLike);
				Long counterComment = dtlDao.countThreadDetailId(d.getId());
				data.setCounterComment(counterComment);
				Boolean isLike = likedDao.findIslikeByThreadHdrIdAndUserLogged(user.getId(), d.getId());
				data.setIsLike(isLike);
				Boolean isBookmark = bookmarkDao.findIsBookmarkByThreadHdrIdAndUserLogged(user.getId(), d.getId());
				data.setIsBookmark(isBookmark);
				if(d.getPolling() != null) {					
					Boolean isVoted = answerDao.findIsVotedByThreadHdrIdAndUserLogged(user.getId(), d.getId());
					data.setIsVoted(isVoted);
				}
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});

		SearchQuery<PojoThreadHdrData> result = new SearchQuery<>();
		result.setData(resultList);
		result.setCount(threads.getData().size());
		return result;
	}

	public SearchQuery<PojoThreadHdrData> findThreadThatAreBookmarkedByUserLoggedEmail(String email, Integer startPage,
			Integer maxPage) throws Exception {
		User user = userDao.findByEmail(email);
		List<ThreadHdr> threadList = hdrDao.findThreadByBookmarkAndUserId(principalServiceImpl.getAuthPrincipal(), startPage, maxPage);

		SearchQuery<ThreadHdr> threads = findAll(() -> threadList);

		List<PojoThreadHdrData> resultList = new ArrayList<>();

		threads.getData().forEach(d -> {
			PojoThreadHdrData data;
			try {
				data = modelToRes(d);
				Long counterLike = likedDao.countThreadLikedId(d.getId());
				data.setCounterLike(counterLike);
				Long counterComment = dtlDao.countThreadDetailId(d.getId());
				data.setCounterComment(counterComment);
				Boolean isLike = likedDao.findIslikeByThreadHdrIdAndUserLogged(user.getId(), d.getId());
				data.setIsLike(isLike);
				Boolean isBookmark = bookmarkDao.findIsBookmarkByThreadHdrIdAndUserLogged(user.getId(), d.getId());
				data.setIsBookmark(isBookmark);
				if(d.getPolling() != null) {
					Boolean isVoted = answerDao.findIsVotedByThreadHdrIdAndUserLogged(user.getId(), d.getId());
					data.setIsVoted(isVoted);
				}
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});

		SearchQuery<PojoThreadHdrData> result = new SearchQuery<>();
		result.setData(resultList);
		result.setCount(threads.getData().size());
		return result;
	}
	
	public SearchQuery<PojoThreadHdrData> getAllWithoutLogin(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadHdr> threadList = hdrDao.findAll(query, startPage, maxPage, "threadCode", "threadName",
				"category.categoryName");
		List<PojoThreadHdrData> results = new ArrayList<>();

		threadList.getData().forEach(d -> {
			PojoThreadHdrData data;
			try {
				data = modelToRes(d);
				Long counterLike = likedDao.countThreadLikedId(d.getId());
				data.setCounterLike(counterLike);
				Long counterComment = dtlDao.countThreadDetailId(d.getId());
				data.setCounterComment(counterComment);
				Boolean isLike = false;
				Boolean isBookmark = false;
				Boolean isVoted = false;
				data.setIsLike(isLike);
				data.setIsBookmark(isBookmark);
				data.setIsVoted(isVoted);
				results.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		SearchQuery<PojoThreadHdrData> result = new SearchQuery<>();
		result.setData(results);
		result.setCount(threadList.getCount());
		return result;
	}
}
