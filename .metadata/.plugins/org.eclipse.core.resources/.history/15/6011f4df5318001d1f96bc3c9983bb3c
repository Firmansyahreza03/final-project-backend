package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.community.dao.CommunityDao;
import com.lawencon.community.dao.MemberCommunityDao;
import com.lawencon.community.dao.PaymentTransactionDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Community;
import com.lawencon.community.model.MemberCommunity;
import com.lawencon.community.model.PaymentTransaction;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.memberCommunity.PojoDataMemberCommunity;
import com.lawencon.community.pojo.memberCommunity.PojoFindWithTimeLimitReq;
import com.lawencon.community.pojo.report.PojoReportUserByCommunity;
import com.lawencon.model.SearchQuery;

public class ReportService {
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
	
	public PojoReportUserByCommunity modelToUserReport(MemberCommunity data) throws Exception{
		PojoReportUserByCommunity result = new PojoReportUserByCommunity();
		User fkUser = userDao.getById(data.getUser().getId());
		Profile fkProfile = profileDao.getByUserMail(fkUser.getUserEmail());
		Community fkCommunity = communityDao.getById(data.getCommunity().getId());
		
		result.setNameUser(fkProfile.getFullName());
		result.setNameCommunity(fkCommunity.getCommunityTitle());
		
		result.setStartDate(fkCommunity.getCommunityStartAt());
		result.setEndDate(fkCommunity.getCommunityEndAt());

		return result;
	}
	
	public SearchQuery<PojoReportUserByCommunity> userReport(PojoFindWithTimeLimitReq dataReq) throws Exception {

		List<MemberCommunity> res = memberCommunityDao.findByPeriode(dataReq.getStartAt(), dataReq.getEndAt());
		List<PojoReportUserByCommunity> resultList = new ArrayList<>();

		res.forEach(d -> {
			PojoReportUserByCommunity data;
			try {
				data = modelToUserReport(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});

		SearchQuery<PojoReportUserByCommunity> result = new SearchQuery<PojoReportUserByCommunity>();
		result.setData(resultList);
		result.setCount(resultList.size());
		return result;
	}
}
