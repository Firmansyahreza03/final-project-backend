package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.CommunityDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.MemberCommunityDao;
import com.lawencon.community.dao.PaymentTransactionDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Community;
import com.lawencon.community.model.File;
import com.lawencon.community.model.MemberCommunity;
import com.lawencon.community.model.PaymentTransaction;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.memberCommunity.PojoDataMemberCommunity;
import com.lawencon.community.pojo.memberCommunity.PojoFindByIdMemberCommunityRes;
import com.lawencon.community.pojo.memberCommunity.PojoInsertMemberCommunityReq;
import com.lawencon.community.pojo.memberCommunity.PojoUpdateMemberCommunityReq;
import com.lawencon.model.SearchQuery;

@Service
public class MemberCommuniyService extends BaseCoreService<MemberCommunity> {
	@Autowired
	private MemberCommunityDao memberCommunityDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private CommunityDao communityDao;
	@Autowired
	private PaymentTransactionDao paymentDao;
	@Autowired
	private FileDao fileDao;

	private MemberCommunity inputMemberCommunityData(MemberCommunity result, Boolean isActive, String idUser,
			String idCommunity, String idPayment) throws Exception {
		result.setIsActive(isActive);
		User fkUser = userDao.getById(idUser);
		Community fkCommunity = communityDao.getById(idCommunity);
		PaymentTransaction fkPayment = paymentDao.getById(idPayment);

		result.setUser(fkUser);
		result.setPayment(fkPayment);
		result.setCommunity(fkCommunity);

		return result;
	}

	private PojoDataMemberCommunity modelToRes(MemberCommunity data) throws Exception {
		PojoDataMemberCommunity result = new PojoDataMemberCommunity();
		User fkUser = userDao.getById(data.getUser().getId());
		Profile fkProfile = profileDao.getByIdUser(data.getUser().getId());
		Community fkCommunity = communityDao.getById(data.getCommunity().getId());
		PaymentTransaction fkPayment = paymentDao.getById(data.getPayment().getId());

		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());

		result.setIdUser(fkUser.getId());
		result.setIdCommunity(fkCommunity.getId());
		result.setNameUser(fkProfile.getFullName());
		result.setNameCommunity(fkCommunity.getCommunityTitle());

		result.setIdPayment(fkPayment.getId());
		result.setIsAccPayment(fkPayment.getIsAcc());
	
		if(fkPayment.getFile()!=null) {
			File fkFile = fileDao.getById(fkPayment.getFile().getId());
			result.setIdPayment(fkFile.getId());
			result.setFileNamePayment(fkFile.getFileName());
			result.setFileExtPayment(fkFile.getFileExtension());
		}

		return result;
	}

	public PojoFindByIdMemberCommunityRes findById(String id) throws Exception {
		MemberCommunity data = memberCommunityDao.getById(id);

		PojoDataMemberCommunity result = modelToRes(data);
		PojoFindByIdMemberCommunityRes resultData = new PojoFindByIdMemberCommunityRes();
		resultData.setData(result);

		return resultData;
	}

	public SearchQuery<PojoDataMemberCommunity> getAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<MemberCommunity> getAllMemberCommunity = memberCommunityDao.findAll(query, startPage, maxPage);
		List<PojoDataMemberCommunity> resultList = new ArrayList<>();

		getAllMemberCommunity.getData().forEach(d -> {
			PojoDataMemberCommunity data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		SearchQuery<PojoDataMemberCommunity> result = new SearchQuery<PojoDataMemberCommunity>();
		result.setData(resultList);
		result.setCount(getAllMemberCommunity.getCount());
		return result;
	}

	@Transactional(rollbackOn = Exception.class)
	public PojoInsertRes insert(PojoInsertMemberCommunityReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();

			MemberCommunity reqData = inputMemberCommunityData(new MemberCommunity(), true, data.getIdUser(),
					data.getIdCommunity(), data.getIdPayment());

			begin();
			MemberCommunity result = super.save(reqData);
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding MemberCommunity");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public PojoUpdateRes update(PojoUpdateMemberCommunityReq data) throws Exception {
		try {
			PojoUpdateRes updateRes = new PojoUpdateRes();
			MemberCommunity reqData = memberCommunityDao.getById(data.getId());

			reqData = inputMemberCommunityData(reqData, reqData.getIsActive(), data.getIdUser(),
					data.getIdCommunity(), data.getIdPayment());
			begin();
			MemberCommunity result = memberCommunityDao.save(reqData);
			commit();
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());

			updateRes.setData(resData);
			updateRes.setMessage("Successfully Editing MemberCommunity");
			return updateRes;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public PojoDeleteRes deleteById(String id) throws Exception {
		try {
			begin();
			boolean result = memberCommunityDao.deleteById(id);
			commit();
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if (result)
				deleteRes.setMessage("Successfully Delete MemberCommunity");
			else
				deleteRes.setMessage("Failed Delete MemberCommunity");
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}