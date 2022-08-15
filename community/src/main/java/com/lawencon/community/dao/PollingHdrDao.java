package com.lawencon.community.dao;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.PollingHdr;

@Repository
public class PollingHdrDao extends AbstractJpaDao<PollingHdr> {

	public PollingHdr checkExpiredAtFromOptionId(String optionId) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT ph.* FROM comm_polling_hdr ph ")
				.append(" INNER JOIN comm_polling_option po ON po.polling_hdr = ph.id ")
				.append(" WHERE po.id = :optionId AND ph.expired_at > now()");
		
		PollingHdr res = null;
		try {
			Object result = createNativeQuery(sql.toString())
					.setParameter("optionId", optionId)
					.getSingleResult();
			
			if(result != null) {
				res = new PollingHdr();
				Object[] objArr = (Object[]) result;
				res.setId(objArr[0].toString());
				res.setPollingName(objArr[1].toString());
				res.setExpiredAt(Timestamp.valueOf(objArr[2].toString()).toLocalDateTime());
				res.setCreatedBy(objArr[3].toString());
				res.setCreatedAt(Timestamp.valueOf(objArr[4].toString()).toLocalDateTime());
				if(objArr[5] != null) {
					res.setUpdatedBy(objArr[5].toString());
					res.setUpdatedAt(Timestamp.valueOf(objArr[6].toString()).toLocalDateTime());
				}
				res.setIsActive(Boolean.valueOf(objArr[7].toString()));
				res.setVersion(Integer.valueOf(objArr[8].toString()));
			} 
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return res;
	}
}