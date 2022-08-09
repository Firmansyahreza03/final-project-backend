package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.PollingAnswer;

@Repository
public class PollingAnswerDao extends AbstractJpaDao<PollingAnswer> {

	public Integer countPollingAnswer(String id) {
		Integer response = 0;
		
		StringBuilder sql = new StringBuilder()
				.append(" SELECT COUNT(a.option_id) ")
				.append(" FROM comm_polling_answer a ")
				.append(" WHERE a.option_id = :id ")
				.append(" GROUP BY a.option_id ");
		
		try {
			Object result = createNativeQuery(sql.toString())
					.setParameter("id", id)
					.getSingleResult();
			
			if(result != null) {
				response = Integer.valueOf(result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}