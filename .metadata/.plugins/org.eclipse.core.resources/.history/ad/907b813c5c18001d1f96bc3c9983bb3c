package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.community.dao.CommunityDao;
import com.lawencon.community.dao.MemberCommunityDao;
import com.lawencon.community.dao.PaymentTransactionDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.model.Community;
import com.lawencon.community.model.MemberCommunity;
import com.lawencon.community.model.PaymentTransaction;
import com.lawencon.community.model.Profile;
import com.lawencon.community.pojo.memberCommunity.PojoFindMemberCommunityWithTimeLimitReq;
import com.lawencon.community.pojo.report.PojoReportPaymentByCommunity;
import com.lawencon.community.pojo.report.PojoReportUserByCommunity;
import com.lawencon.model.SearchQuery;

public class ReportService {
	@Autowired
	private MemberCommunityDao memberCommunityDao;
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private CommunityDao communityDao;
	@Autowired
	private PaymentTransactionDao paymentDao;

	public SearchQuery<PojoReportUserByCommunity> userReport(PojoFindMemberCommunityWithTimeLimitReq dataReq) throws Exception {
		List<MemberCommunity> res = memberCommunityDao.findByPeriode(dataReq.getStartAt(), dataReq.getEndAt());
		List<PojoReportUserByCommunity> resultList = new ArrayList<>();

		res.forEach(d -> {
			PojoReportUserByCommunity data = new PojoReportUserByCommunity();
			Profile fkProfile = profileDao.getByUserId(d.getUser().getId());
			Community fkCommunity = communityDao.getById(d.getCommunity().getId());

			data.setNameUser(fkProfile.getFullName());
			data.setNameCommunity(fkCommunity.getCommunityTitle());

			data.setStartDate(fkCommunity.getCommunityStartAt());
			data.setEndDate(fkCommunity.getCommunityEndAt());

			resultList.add(data);
		});

		SearchQuery<PojoReportUserByCommunity> result = new SearchQuery<PojoReportUserByCommunity>();
		result.setData(resultList);
		result.setCount(resultList.size());
		return result;
	}
	public SearchQuery<PojoReportPaymentByCommunity> paymentReport(PojoFindMemberCommunityWithTimeLimitReq dataReq) throws Exception {
		List<MemberCommunity> res = memberCommunityDao.findByPeriode(dataReq.getStartAt(), dataReq.getEndAt());
		List<PojoReportPaymentByCommunity> resultList = new ArrayList<>();

		res.forEach(d -> {
			PojoReportPaymentByCommunity data = new PojoReportPaymentByCommunity();
			PaymentTransaction fkPayment = paymentDao.getById(d.getPayment().getId());
			Community fkCommunity = communityDao.getById(d.getCommunity().getId());

			data.setIsAcc(fkPayment.getIsAcc());

			data.setCode(fkPayment.getCode());
			data.setDesc(fkPayment.getDesc());
			data.setPrice(fkPayment.getPrice());
			data.setType(fkPayment.getType());

			data.setNameCommunity(fkCommunity.getCommunityTitle());
			data.setStartDate(fkCommunity.getCommunityStartAt());
			data.setEndDate(fkCommunity.getCommunityEndAt());

			resultList.add(data);
		});

		SearchQuery<PojoReportPaymentByCommunity> result = new SearchQuery<PojoReportPaymentByCommunity>();
		result.setData(resultList);
		result.setCount(resultList.size());
		return result;
	}
}
