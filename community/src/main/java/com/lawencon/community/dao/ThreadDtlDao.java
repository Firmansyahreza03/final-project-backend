package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadDtl;
import com.lawencon.community.model.ThreadHdr;
import com.lawencon.community.model.User;

@Repository
public class ThreadDtlDao extends AbstractJpaDao<ThreadDtl> {

	public List<ThreadDtl> findByHdrId(String id, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT d.* FROM comm_thread_dtl d ")
				.append(" INNER JOIN comm_thread_hdr h ON h.id = d.hdr_id ")
				.append(" WHERE d.hdr_id = :id ");

		List<ThreadDtl> dtls = new ArrayList<>();

		Query q = createNativeQuery(sql.toString()).setParameter("id", id);

		if (startPage != null && maxPage != null) {
			q.setFirstResult(startPage)
			.setMaxResults(maxPage);
		}

		List<?> result = q.getResultList();

		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			ThreadDtl dtl = new ThreadDtl();
			dtl.setId(objArr[0].toString());
			dtl.setThreadComment(objArr[1].toString());

			User user = new User();
			user.setId(objArr[2].toString());
			dtl.setUser(user);

			ThreadHdr hdr = new ThreadHdr();
			hdr.setId(objArr[3].toString());
			dtl.setHdr(hdr);

			dtl.setCreatedBy(objArr[4].toString());
			dtl.setCreatedAt(((Timestamp) objArr[5]).toLocalDateTime());
			if (objArr[6] != null) {
				dtl.setUpdatedBy(objArr[6].toString());
				dtl.setUpdatedAt(((Timestamp) objArr[7]).toLocalDateTime());
			}
			dtl.setIsActive(Boolean.valueOf(objArr[8].toString()));
			dtl.setVersion(Integer.valueOf(objArr[9].toString()));

			dtls.add(dtl);
		});
		return dtls;
	}
	
	public Long countThreadDetailId(String hdrId) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT COUNT(td.id) FROM comm_thread_dtl td ")
				.append(" WHERE td.hdr_id = :hdrId ")
				.append(" GROUP BY (td.hdr_id) ");
		
		Long result = null;
		try {
			Object res = createNativeQuery(sql.toString())
					.setParameter("hdrId", hdrId)
					.getSingleResult();
			
			if(res != null) {
				result = Long.valueOf(res.toString());
			} else {
				result = 0l;
			}
		} catch (Exception e) {
			result = 0l;
		}
		return result;
	}
}