package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadHdr;
import com.lawencon.community.model.ThreadLiked;

@Repository
public class ThreadLikedDao extends AbstractJpaDao<ThreadLiked>{

	public List<ThreadLiked> findByCreatedBy(String id, Integer startPage, Integer maxPage) {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT tl.id, tl.hdr_id, tl.is_active, tl.version ")
				.append(" FROM comm_thread_liked tl ")
				.append(" WHERE tl.created_by = :id ");
		
		List<ThreadLiked> results = new ArrayList<>();
		try {
			
			Query q = createNativeQuery(sql.toString())
					.setParameter("id", id);
			
			if(startPage != null && maxPage != null) {
				q.setFirstResult(startPage)
				.setMaxResults(maxPage);
			}
			
			List<?> res = q.getResultList();
			
			if(res != null) {
				res.forEach(obj -> {					
					Object[] objArr = (Object[]) obj;
					ThreadLiked result = new ThreadLiked();
					result.setId(objArr[0].toString());
					ThreadHdr hdr = new ThreadHdr();
					hdr.setId(objArr[1].toString());
					result.setHdr(hdr);
					result.setIsActive(Boolean.valueOf(objArr[3].toString()));
					result.setVersion(Integer.valueOf(objArr[4].toString()));
					
					results.add(result);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}