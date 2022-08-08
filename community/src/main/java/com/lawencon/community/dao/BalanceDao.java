package com.lawencon.community.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Balance;

@Repository
public class BalanceDao extends AbstractJpaDao<Balance> {

	private Balance inputData(Object obj) throws Exception{
		Balance results = new Balance();
		Object[] objArr = (Object[]) obj;
		results.setId(objArr[0].toString());
		

		results.setBalance(new BigDecimal(objArr[1].toString()));
		
		results.setCreatedBy(objArr[2].toString());
		results.setCreatedAt(((Timestamp) objArr[3]).toLocalDateTime());
		
		if(objArr[4] != null)
			results.setUpdatedBy(objArr[4].toString());
		if(objArr[5] != null)
			results.setUpdatedAt(((Timestamp) objArr[5]).toLocalDateTime());
		
		results.setIsActive(Boolean.valueOf(objArr[6].toString()));
		results.setVersion(Integer.valueOf(objArr[7].toString()));
		
		return results;
	}
	
	public Balance findbyUserId(String id) throws Exception {
		StringBuilder sql = new StringBuilder()
		.append("SELECT b.* FROM comm_balance b ")
		.append(" INNER JOIN comm_user u ON b.id = u.balance_id ")
		.append(" WHERE u.id = :id ");

		Balance res = null;
		try {			
			Object result = createNativeQuery(sql.toString())
					.setParameter("id", id)
					.getSingleResult();
			if(result != null) {
				res = inputData(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
