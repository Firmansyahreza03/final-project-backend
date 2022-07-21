package com.lawencon.community.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.CommunityCategoryDao;
import com.lawencon.community.dao.CommunityDao;
import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Community;
import com.lawencon.community.model.CommunityCategory;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.community.PojoDataCommunity;
import com.lawencon.community.pojo.community.PojoFindByIdCommunityRes;
import com.lawencon.community.pojo.community.PojoInsertCommunityReq;
import com.lawencon.community.pojo.community.PojoUpdateCommunityReq;
import com.lawencon.model.SearchQuery;
@Service
public class CommunityService extends BaseCoreService<Community>{
	@Autowired
	private CommunityDao communityDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private IndustryDao industryDao;
	@Autowired
	private CommunityCategoryDao communityCategoryDao;

	public Community inputCommunityData(Community result, Boolean isActive, String title, 
			String provider, String location, LocalDateTime startAt, LocalDateTime endAt,
			String desc, String code, Long price, String idCategory, 
			String nameCategory, String idIndustry, String nameIndustry, 
			String idFile, String nameFile, String extFile) throws Exception {

		CommunityCategory fkCategory = communityCategoryDao.getById(idCategory);
		Industry fkIndustry = industryDao.getById(idIndustry);
	
		result.setIsActive(isActive);
		
		result.setCommunityTitle(title);
		result.setCommunityProvider(provider);
		result.setCommunityLocation(location);
		result.setCommunityStartAt(startAt);
		result.setCommunityEndAt(endAt);
		result.setCommunityDesc(desc);
		result.setCommunityCode(code);
		result.setCommunityPrice(price);
		result.setCategory(fkCategory);
		result.setIndustry(fkIndustry);

		result.setfile;


		return result;
	}

	private PojoDataCommunity modelToRes(Community data) throws Exception {
		PojoDataCommunity result = new PojoDataCommunity();
		User fkUser = userDao.getById(data.getUser().getId());
		Profile fkProfile = profileDao.getByIdUser(data.getUser().getId());
		Industry fkIndustry = industryDao.getById(data.getIndustry().getId());

		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());

		result.setTitle;
		result.setProvider;
		result.setLocation;
		result.setStartAt;
		result.setEndAt;
		result.setDesc;
		result.setCode;
		result.setPrice;
		result.setIdCategory;
		result.setNameCategory;
		result.setIdIndustry;
		result.setNameIndustry;

		result.setIdFile;
		result.setNameFile;
		result.setExtFile;
		
		return result;
	}
	
	public PojoFindByIdCommunityRes findById(String id) throws Exception {
		Community data = communityDao.getById(id);
				
		PojoDataCommunity result = modelToRes(data);
		PojoFindByIdCommunityRes resultData = new PojoFindByIdCommunityRes();
		resultData.setData(result);
		
		return resultData;
	}
	
	public SearchQuery<PojoDataCommunity> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Community> getAllCommunity= communityDao.findAll(query, startPage, maxPage);
		List<PojoDataCommunity> resultList = new ArrayList<>();
		
		getAllCommunity.getData().forEach(d -> {
			PojoDataCommunity data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		SearchQuery<PojoDataCommunity> result = new SearchQuery<PojoDataCommunity>();
		result.setData(resultList);
		result.setCount(getAllCommunity.getCount());
		return result;
	}

	@Transactional(rollbackOn = Exception.class)
	public PojoInsertRes insert(PojoInsertCommunityReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();
			
			Community reqData = inputCommunityData(new Community(), true, data.getTitle(), data.getContent(), 
					data.getIdUser(), data.getIdIndustry());
			
			begin();
			Community result = super.save(reqData);
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());
			
			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Community");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public PojoUpdateRes update(PojoUpdateCommunityReq data) throws Exception {
		try {
			PojoUpdateRes updateRes = new PojoUpdateRes();
			Community reqData = communityDao.getById(data.getId());

			reqData = inputCommunityData(reqData, data.getIsActive(), data.getTitle(), data.getContent(), 
					data.getIdUser(), data.getIdIndustry());
			
			begin();
			Community result = communityDao.save(reqData);
			commit();
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());
			
			updateRes.setData(resData);
			updateRes.setMessage("Successfully Editing Community");
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
			boolean result = communityDao.deleteById(id);
			commit();
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if(result)
				deleteRes.setMessage("Successfully Delete Community");
			else 
				deleteRes.setMessage("Failed Delete Community");
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
