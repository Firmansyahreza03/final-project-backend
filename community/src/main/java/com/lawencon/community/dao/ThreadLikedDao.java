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
	
	public String findByUserLogged(String idUser, String idThreadHdr) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT tl.id FROM comm_thread_liked tl ")
				.append(" WHERE tl.created_by = :idUser AND tl.hdr_id = :idThreadHdr ");
		
		String result = null;
		try {
			Object res = createNativeQuery(sql.toString())
					.setParameter("idUser", idUser)
					.setParameter("idThreadHdr", idThreadHdr)
					.getSingleResult();
			
			if(res != null) {
				result = res.toString();
			}
		} catch (Exception e) {
			result = null;
		}
		return result;
	}
	
	public Long countThreadLikedId(String hdrId) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT COUNT(tl.id) FROM comm_thread_liked tl ")
				.append(" WHERE tl.hdr_id = :hdrId ")
				.append(" GROUP BY (tl.hdr_id) ");
		
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
	
	public Boolean findIslikeByThreadHdrIdAndUserLogged(String idUser, String idThreadHdr) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT tl.is_active FROM comm_thread_liked tl ")
				.append(" WHERE tl.created_by = :idUser AND tl.hdr_id = :idThreadHdr ");
		
		Boolean result = null;
		try {
			Object res = createNativeQuery(sql.toString())
					.setParameter("idUser", idUser)
					.setParameter("idThreadHdr", idThreadHdr)
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