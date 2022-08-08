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
import com.lawencon.community.model.EmailDtl;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.SubscriptionStatus;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.code.PojoCodeData;
import com.lawencon.community.pojo.profile.PojoFindByIdProfileRes;
import com.lawencon.community.pojo.profile.PojoInsertProfileReq;
import com.lawencon.community.pojo.profile.PojoProfileData;
import com.lawencon.community.pojo.profile.PojoUpdateProfileReq;
import com.lawencon.community.pojo.user.PojoVerificationUserReq;
import com.lawencon.community.pojo.user.PojoVerificationUserRes;
import com.lawencon.model.SearchQuery;
import com.lawencon.security.PrincipalServiceImpl;
import com.lawencon.util.VerificationCodeUtil;

@Service
public class ProfileUserService extends BaseCoreService<Profile> {

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
	@Autowired
	private EmailService emailService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private VerificationCodeUtil verificationCodeUtil;
	@Autowired
	private PrincipalServiceImpl principalServiceImpl;

	private PojoProfileData modelToProfileRes(Profile data) throws Exception {
		PojoProfileData result = new PojoProfileData();
		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());

		result.setFullName(data.getFullName());
		result.setCompanyName(data.getCompanyName());
		result.setPositionName(data.getPositionName());

		SubscriptionStatus fkSubs = statusDao.findByUserId(data.getUser().getId());
		result.setIsSubscriber(fkSubs.getIsSubscriber());

		Industry fkIndustry = industryDao.getById(data.getIndustry().getId());
		result.setIndustryId(fkIndustry.getId());
		result.setIndustryName(fkIndustry.getIndustryName());

		User fkUser = userDao.getById(data.getUser().getId());
		result.setUserId(fkUser.getId());
		result.setUserEmail(fkUser.getUserEmail());
		result.setRoleCode(fkUser.getRole().getRoleCode());
		result.setRoleName(fkUser.getRole().getRoleName());
		result.setBalance(fkUser.getBalance().getBalance());

		if (fkUser.getFile() != null) {
			File fkFile = fileDao.getById(fkUser.getFile().getId());
			result.setFileId(fkFile.getId());
		}

		return result;
	}

	public PojoFindByIdProfileRes findById(String id) throws Exception {
		Profile data = profileDao.getById(id);

		PojoProfileData result = modelToProfileRes(data);
		PojoFindByIdProfileRes res = new PojoFindByIdProfileRes();
		res.setData(result);

		return res;
	}
	
	public PojoFindByIdProfileRes findByUserLogged() throws Exception{
		Profile data = profileDao.getByUserId(principalServiceImpl.getAuthPrincipal());

		PojoProfileData result = modelToProfileRes(data);
		PojoFindByIdProfileRes res = new PojoFindByIdProfileRes();
		res.setData(result);

		return res;
	}

	public PojoFindByIdProfileRes findByMail(String mail) throws Exception {
		Profile data = profileDao.getByUserMail(mail);

		PojoProfileData result = modelToProfileRes(data);
		PojoFindByIdProfileRes res = new PojoFindByIdProfileRes();
		res.setData(result);

		return res;
	}

	public SearchQuery<PojoProfileData> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Profile> profileList = null;
		if (query != null) {
			profileList = profileDao.searchQuery(query, startPage, maxPage,
					 "fullName", "companyName", "positionName", "user.userEmail", "industry.industryName" );
		} else {
			profileList = profileDao.findAll(query, startPage, maxPage);
		}
		List<PojoProfileData> results = new ArrayList<>();

		profileList.getData().forEach(d -> {
			try {
				PojoProfileData data = modelToProfileRes(d);
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
	
	public void sendCodeVerification (String email) {
		EmailDtl emailDtl = new EmailDtl();
		PojoCodeData code = codeService.generateRandomCode();
		verificationCodeUtil.addVerificationCode(email, code.getCode());
		
		emailDtl.setMsgBody("CODE ANDA ADALAH : "+code.getCode());
		emailDtl.setRecipient(email);
		emailDtl.setSubject("CODE VERIFICATION");
		new Thread(() -> emailService.sendSimpleMail(emailDtl)).start();
		
	}
	
	public PojoVerificationUserRes verificationCode(PojoVerificationUserReq data) {
		verificationCodeUtil.validateVerificationCode(data.getEmail(), data.getCodeVerification());
		PojoVerificationUserRes res = new PojoVerificationUserRes();
		res.setResult(true);
		return res;
	}
	
	public PojoUpdateRes updateProfile(PojoUpdateProfileReq data) throws Exception {
		begin();
		PojoUpdateRes res = new PojoUpdateRes();
		User user = userDao.getById(principalServiceImpl.getAuthPrincipal());
		File file = null;
		
		try {			
			file = fileDao.getById(user.getFile().getId());
			file.setUpdatedBy(principalServiceImpl.getAuthPrincipal());
		} catch (Exception e) {
			file = new File();
			file.setCreatedBy(principalServiceImpl.getAuthPrincipal());
		}
		if(data.getFileName() != null && data.getFileExt() != null && (data.getFileExt().equals("jpg") || data.getFileExt().equals("jpeg") || data.getFileExt().equals("png") || data.getFileExt().equals("jfif"))) {
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExt());
			file.setIsActive(true);
		}
		File resFile = fileDao.save(file);
		
		user.setFile(resFile);
		user.setUpdatedBy(principalServiceImpl.getAuthPrincipal());
		User userRes = userDao.save(user);
		
		Profile profile = profileDao.getByUserId(principalServiceImpl.getAuthPrincipal());
		profile.setCompanyName(data.getCompanyName());
		profile.setFullName(data.getFullName());
		Industry industry = industryDao.getById(data.getIndustryId());
		profile.setIndustry(industry);
		profile.setPositionName(data.getPositionName());
		profile.setVersion(data.getVersion());
		profile.setUser(userRes);
		Profile result = save(profile);
		PojoUpdateResData resData = new PojoUpdateResData();
		resData.setVersion(result.getVersion());
		res.setData(resData);
		res.setMessage("Update profile successfully");
		commit();
		return res;
	}
	
}
