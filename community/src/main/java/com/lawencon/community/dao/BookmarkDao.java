package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Bookmark;
import com.lawencon.community.model.ThreadHdr;

@Repository
public class BookmarkDao extends AbstractJpaDao<Bookmark>{

	private Bookmark inputData(Object obj) throws Exception{
		Bookmark results = new Bookmark();
		Object[] objArr = (Object[]) obj;
		results.setId(objArr[0].toString());

		ThreadHdr fkThreadHdr = new ThreadHdr();
		fkThreadHdr.setId(objArr[1].toString());
		results.setThreadHdr(fkThreadHdr);
		
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

	public List<Bookmark> getByIdUser(String id, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder()
		.append("SELECT b.* FROM comm_bookmark b ")
		.append(" WHERE b.created_by = :id ");
		
		List<Bookmark> res = new ArrayList<>();
		
		Query q = createNativeQuery(sql.toString())
				.setParameter("id", id);
		
		if(startPage != null && maxPage != null) {
			q.setFirstResult(startPage)
			.setMaxResults(maxPage);
		}
		
		List<?> rs = q.getResultList();

		rs.forEach(obj ->{
			try {
				Bookmark data = inputData(obj);
				res.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return res;
	}
	
	public String findByUserLogged(String idUser, String idThreadHdr) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT b.id FROM comm_bookmark b ")
				.append(" WHERE b.created_by = :idUser AND b.thread_hdr_id = :idThreadHdr ");
		
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
	
	public Boolean findIsBookmarkByThreadHdrIdAndUserLogged(String idUser, String idThreadHdr) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT b.is_active FROM comm_bookmark b ")
				.append(" WHERE b.created_by = :idUser AND b.thread_hdr_id = :idThreadHdr ");
		
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
