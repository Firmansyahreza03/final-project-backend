package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.RoleType;
import com.lawencon.community.dao.BalanceDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.dao.SubscriptionStatusDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Balance;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.SubscriptionStatus;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.profile.PojoFindByIdProfileRes;
import com.lawencon.community.pojo.profile.PojoInsertProfileReq;
import com.lawencon.community.pojo.profile.PojoProfileData;
import com.lawencon.model.SearchQuery;

@Service
public class ProfileService extends BaseCoreService<Profile> {

	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private BalanceDao balanceDao;
	@Autowired
	private SubscriptionStatusDao statusDao;
	@Autowired
	private IndustryDao industryDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private PojoProfileData modelToRes(Profile data) {
		PojoProfileData result = new PojoProfileData();

		result.setCompanyName(data.getCompanyName());
		result.setFullName(data.getFullName());
		result.setId(data.getId());
		result.setIndustryId(data.getIndustry().getId());
		result.setIndustryName(data.getIndustry().getIndustryName());
		result.setIsActive(data.getIsActive());
		result.setIsSubscriber(data.getUser().getSubscriptionStatus().getIsSubscriber());
		result.setPositionName(data.getPositionName());
		result.setUserEmail(data.getUser().getUserEmail());
		result.setUserId(data.getUser().getId());
		result.setVersion(data.getVersion());

		if (data.getUser().getFile() != null) {
			result.setFileId(data.getUser().getFile().getId());
		}

		return result;
	}

	public PojoFindByIdProfileRes findById(String id) throws Exception {
		Profile data = profileDao.getById(id);

		PojoProfileData result = modelToRes(data);
		PojoFindByIdProfileRes res = new PojoFindByIdProfileRes();
		res.setData(result);

		return res;
	}

	public SearchQuery<PojoProfileData> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Profile> profileList = profileDao.findAll(query, startPage, maxPage);
		List<PojoProfileData> results = new ArrayList<>();

		profileList.getData().forEach(d -> {
			try {
				PojoProfileData data = modelToRes(d);
				results.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		SearchQuery<PojoProfileData> result = new SearchQuery<>();
		result.setData(results);
		result.setCount(profileList.getCount());
		return result;
	}

	public PojoInsertRes regist(PojoInsertProfileReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();

			Profile reqData = new Profile();
			reqData.setCompanyName(data.getCompanyName());
			reqData.setFullName(data.getFullName());

			Industry industry = industryDao.getById(data.getIndustryId());
			reqData.setIndustry(industry);

			reqData.setIsActive(data.getIsActive());
			reqData.setPositionName(data.getPositionName());

			User creator = userDao.findByRoleCode(RoleType.SYSTEM.name());

			User userData = new User();
			Balance balanceData = new Balance();
			balanceData.setBalance(0l);
			balanceData.setIsActive(true);
			balanceData.setCreatedBy(creator.getId());
			begin();
			Balance balance = balanceDao.save(balanceData);
			if (data.getFileName() != null) {
				File fileData = new File();
				fileData.setFileName(data.getFileName());
				fileData.setFileExtension(data.getFileExt());
				fileData.setCreatedBy(creator.getId());
				fileData.setIsActive(true);
				File file = fileDao.save(fileData);
				userData.setFile(file);
			}
			Role role = roleDao.findByRoleCode(RoleType.NONADMIN.name());
			
			SubscriptionStatus statusData = new SubscriptionStatus();
			statusData.setIsSubscriber(false);
			statusData.setCreatedBy(creator.getId());
			statusData.setIsActive(true);
			SubscriptionStatus status = statusDao.save(statusData);

			userData.setBalance(balance);
			userData.setRole(role);
			userData.setSubscriptionStatus(status);

			userData.setIsActive(true);
			userData.setUserEmail(data.getUserEmail());

			String passCode = passwordEncoder.encode(data.getUserPassword());

			userData.setUserPassword(passCode);

			userData.setVerificationCode(data.getVerificationCode());
			userData.setCreatedBy(creator.getId());
			User user = userDao.save(userData);

			reqData.setUser(user);
			Profile profile = saveNonLogin(reqData, () -> creator.getId());
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(profile.getId());
			insertRes.setData(resData);
			insertRes.setMessage("Successfully Registration");
			return insertRes;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
