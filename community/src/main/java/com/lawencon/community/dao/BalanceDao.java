package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Balance;
import com.lawencon.community.model.User;

@Repository
public class BalanceDao extends AbstractJpaDao<Balance> {

//	public Balance findbyUserId(Long id) throws Exception {
//		StringBuilder sql = new StringBuilder()
//		.append("SELECT * FROM comm_balance b ")
//		.append(" INNER JOIN comm_user u ON b.id = u.balance_id ")
//		.append(" INNER JOIN comm_")
//		.append(" WHERE b.id = :id ");
//		
//		Balance res = null;
//		try {			
//			Object result = createNativeQuery(sql.toString()).getSingleResult();
//			if(result != null) {
//				Object[] objArr = (Object[]) result;
//				Balance balance = new Balance();
//				User user = new User();
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
