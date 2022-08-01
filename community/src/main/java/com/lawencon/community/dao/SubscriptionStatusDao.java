package com.lawencon.community.dao;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.PaymentTransaction;
import com.lawencon.community.model.SubscriptionStatus;

@Repository
public class SubscriptionStatusDao extends AbstractJpaDao<SubscriptionStatus> {

	public SubscriptionStatus findByUserId(String id) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append("SELECT s.* FROM comm_subscription_status s ")
				.append(" INNER JOIN comm_user u ON u.subscription_status_id = s.id ")
				.append(" WHERE u.id = :id ");
		
		SubscriptionStatus res = null;
		try {
			Object result = createNativeQuery(sql.toString())
					.setParameter("id", id)
					.getSingleResult();
			if(result != null) {
				Object[] objArr = (Object[]) result;
				res = new SubscriptionStatus();
				res.setId(objArr[0].toString());
				res.setIsSubscriber(Boolean.valueOf(objArr[1].toString()));
				
				PaymentTransaction transaction = new PaymentTransaction();
				if(objArr[2] != null) {	
					transaction.setId(objArr[2].toString());
					res.setPayment(transaction);
				}
				if(objArr[3] != null) {	
					res.setExpiredAt(((Timestamp) objArr[3]).toLocalDateTime());
				}
				res.setCreatedBy(objArr[4].toString());
				res.setCreatedAt(((Timestamp) objArr[5]).toLocalDateTime());
				if(objArr[6] != null) {					
					res.setUpdatedBy(objArr[6].toString());
					res.setUpdatedAt(((Timestamp) objArr[7]).toLocalDateTime());
				}
				res.setIsActive(Boolean.valueOf(objArr[8].toString()));
				res.setVersion(Integer.valueOf(objArr[9].toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}