package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.PaymentTransactionDao;
import com.lawencon.community.dao.SubscriptionStatusDao;
import com.lawencon.community.model.PaymentTransaction;
import com.lawencon.community.model.SubscriptionStatus;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.subscriptionstatus.PojoFindByIdSubsStatusRes;
import com.lawencon.community.pojo.subscriptionstatus.PojoSubsStatusData;
import com.lawencon.community.pojo.subscriptionstatus.PojoUpdateSubsStatusReq;

public class SubscriptionStatusService extends BaseCoreService<SubscriptionStatus> {

	@Autowired
	private SubscriptionStatusDao statusDao;
	@Autowired
	private PaymentTransactionDao transactionDao;
	
	private PojoSubsStatusData modelToRes(SubscriptionStatus data) throws Exception{
		PojoSubsStatusData result = new PojoSubsStatusData();
		
		result.setExpiredAt(data.getExpiredAt());
		
		PaymentTransaction transaction = transactionDao.getById(data.getPayment().getId());
		result.setIsAcc(transaction.getIsAcc());
		result.setIsActive(data.getIsActive());
		result.setIsSubscriber(data.getIsSubscriber());
		result.setPaymentId(transaction.getId());
		result.setVersion(data.getVersion());
		
		return result;
	}
	
	public PojoFindByIdSubsStatusRes findById(String id) throws Exception{
		SubscriptionStatus data = statusDao.getById(id);
		
		PojoSubsStatusData result = modelToRes(data);
		PojoFindByIdSubsStatusRes res = new PojoFindByIdSubsStatusRes();
		res.setData(result);
		
		return res;
	}
	
	public PojoUpdateRes update(PojoUpdateSubsStatusReq data) throws Exception{
		try {
			begin();
			PojoUpdateRes updateRes = new PojoUpdateRes();
			SubscriptionStatus reqData = statusDao.getById(data.getId());
			reqData.setExpiredAt(data.getExpiredAt());
			reqData.setIsActive(data.getIsActive());
			reqData.setIsSubscriber(data.getIsSubscriber());
			
			PaymentTransaction transaction = transactionDao.getById(data.getPaymentId());
			reqData.setPayment(transaction);
			reqData.setVersion(data.getVersion());
			
			SubscriptionStatus result = save(reqData);
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());
			
			updateRes.setData(resData);
			updateRes.setMessage("Successfully Updating Status Subscription");
			
			commit();
			return updateRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
