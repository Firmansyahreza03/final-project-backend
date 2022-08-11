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
	
	public Boolean findIsVotedByThreadHdrIdAndUserLogged(String idUser, String idThread) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT pa.is_active FROM comm_polling_answer pa ")
				.append(" INNER JOIN comm_polling_option po ON pa.option_id = po.id ")
				.append(" INNER JOIN comm_polling_hdr ph ON ph.id = po.polling_hdr ")
				.append(" INNER JOIN comm_thread_hdr th ON th.polling_id = ph.id ")
				.append(" WHERE th.id = :idThread AND pa.created_by = :idUser ");
		
		Boolean result = null;
		try {
			Object res = createNativeQuery(sql.toString())
					.setParameter("idUser", idUser)
					.setParameter("idThread", idThread)
					.getSingleResult();
			
			if(res != null) {
				result = Boolean.valueOf(res.toString());
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
}