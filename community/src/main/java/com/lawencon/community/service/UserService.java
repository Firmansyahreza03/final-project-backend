package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.Role;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.user.PojoFindByIdUserRes;
import com.lawencon.community.pojo.user.PojoRegisUserReq;
import com.lawencon.community.pojo.user.PojoUserData;
import com.lawencon.model.SearchQuery;

public class UserService extends BaseCoreService<User> {

	@Autowired
	private UserDao userDao;

	private PojoUserData modelToRes(User data) {
		PojoUserData res = new PojoUserData();

		res.setBalance(data.getBalance().getBalance());
		res.setBalanceId(data.getBalance().getId());
		res.setExpiredAt(data.getSubscriptionStatus().getExpiredAt());
		if (data.getFile() != null)
			res.setFileId(data.getFile().getId());
		res.setId(data.getId());
		res.setIsActive(data.getIsActive());
		res.setIsSubscriber(data.getSubscriptionStatus().getIsSubscriber());
		res.setRoleCode(data.getRole().getRoleCode());
		res.setRoleId(data.getRole().getId());
		res.setSubscriptionStatusId(data.getSubscriptionStatus().getId());
		res.setUserEmail(data.getUserEmail());
		res.setVerificationCode(data.getVerificationCode());
		res.setVersion(data.getVersion());

		return res;
	}

	public PojoInsertRes regisUser(PojoRegisUserReq data) throws Exception {
		PojoInsertRes insertRes = new PojoInsertRes();
		data.setIsActive(true);
		try {
			User user = new User();
			user.setUserEmail(data.getUserEmail());
			user.setIsActive(data.getIsActive());

			// KIRIM EMAIL KODE VERIFIKASI

			User res = saveNonLogin(user, () -> userDao.findByRoleCode(Role.SYSTEM.name()).getId());

			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(res.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Successfully Register User");
			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoFindByIdUserRes findById(String id) throws Exception {
		User data = userDao.getById(id);

		PojoUserData res = modelToRes(data);
		PojoFindByIdUserRes result = new PojoFindByIdUserRes();
		result.setData(res);

		return result;
	}

	public SearchQuery<PojoUserData> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<User> getAllUser = userDao.findAll(query, startPage, maxPage);
		List<PojoUserData> results = new ArrayList<>();

		getAllUser.getData().forEach(d -> {
			PojoUserData data = modelToRes(d);

			results.add(data);
		});

		SearchQuery<PojoUserData> result = new SearchQuery<>();
		result.setData(results);
		result.setCount(getAllUser.getCount());
		return result;
	}

}