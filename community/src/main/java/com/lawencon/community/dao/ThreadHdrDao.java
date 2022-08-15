package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.File;
import com.lawencon.community.model.PollingHdr;
import com.lawencon.community.model.ThreadCategory;
import com.lawencon.community.model.ThreadHdr;

@Repository
public class ThreadHdrDao extends AbstractJpaDao<ThreadHdr> {

	public List<ThreadHdr> findByCreatorId(String id, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT th.thread_name, th.is_premium, th.category_id, tc.category_name, th.created_by, th.id, th.thread_content, th.file_id, th.created_at, th.polling_id, ph.polling_name, ph.expired_at ")
				.append(" FROM comm_thread_hdr th ")
				.append(" INNER JOIN comm_thread_category tc ON tc.id = th.category_id ")
				.append(" LEFT JOIN comm_polling_hdr ph ON ph.id = th.polling_id ")
				.append(" WHERE th.created_by = :id ")
				.append(" ORDER BY th.created_at DESC ");

		List<ThreadHdr> hdrs = new ArrayList<>();
		try {

			Query q = createNativeQuery(sql.toString()).setParameter("id", id);

			if (startPage != null && maxPage != null) {
				q.setFirstResult(startPage).setMaxResults(maxPage);
			}

			List<?> result = q.getResultList();

			result.forEach(obj -> {
				Object[] objArr = (Object[]) obj;
				ThreadHdr hdr = new ThreadHdr();
				hdr.setThreadName(objArr[0].toString());
				hdr.setIsPremium(Boolean.valueOf(objArr[1].toString()));
				ThreadCategory category = new ThreadCategory();
				category.setId(objArr[2].toString());
				category.setCategoryName(objArr[3].toString());
				hdr.setCategory(category);
				hdr.setCreatedBy(objArr[4].toString());
				hdr.setId(objArr[5].toString());
				hdr.setThreadContent(objArr[6].toString());
				if (objArr[7] != null) {
					File file = new File();
					file.setId(objArr[7].toString());
					hdr.setFile(file);
				}
				
				
				hdr.setCreatedAt(((Timestamp) objArr[8]).toLocalDateTime());
				if(objArr[9] != null) {
					PollingHdr pollingHdr = new PollingHdr();
					pollingHdr.setId(objArr[9].toString());
					pollingHdr.setPollingName(objArr[10].toString());
					pollingHdr.setExpiredAt(Timestamp.valueOf(objArr[11].toString()).toLocalDateTime());
					hdr.setPolling(pollingHdr);
				}

				hdrs.add(hdr);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hdrs;
	}
	
	public List<ThreadHdr> findThreadByThreadLikeAndUserId(String idUser, Integer startPage, Integer maxPage) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT th.id, th.thread_name, th.thread_code, th.thread_content, th.is_premium, th.polling_id, th.category_id, th.created_by, th.created_at, th.updated_at, th.updated_by, th.is_active, th.version, th.file_id ")
				.append(" FROM comm_thread_hdr th ")
				.append(" INNER JOIN comm_thread_liked tl ON tl.hdr_id = th.id ")
				.append(" WHERE tl.created_by = :idUser");
		
		List<ThreadHdr> results = new ArrayList<>();
		try {
			Query q = createNativeQuery(sql.toString())
					.setParameter("idUser", idUser);
			
			if(startPage != null && maxPage != null) {
				q.setFirstResult(startPage)
				.setMaxResults(maxPage);
			}
			
			List<?> res = q.getResultList();
			
			if(res != null) {
				res.forEach(obj -> {
					Object[] objArr = (Object[]) obj;
					ThreadHdr result = new ThreadHdr();
					result.setId(objArr[0].toString());
					result.setThreadName(objArr[1].toString());
					result.setThreadCode(objArr[2].toString());
					result.setThreadContent(objArr[3].toString());
					result.setIsPremium(Boolean.valueOf(objArr[4].toString()));
					
					if(objArr[5] != null) {	
						PollingHdr pollingHdr = new PollingHdr();
						pollingHdr.setId(objArr[5].toString());
						result.setPolling(pollingHdr);
					}
					
					ThreadCategory category = new ThreadCategory();
					category.setId(objArr[6].toString());
					result.setCategory(category);
					result.setCreatedBy(objArr[7].toString());
					result.setCreatedAt(((Timestamp) objArr[8]).toLocalDateTime());
					result.setIsActive(Boolean.valueOf(objArr[11].toString()));
					result.setVersion(Integer.valueOf(objArr[12].toString()));
					if(objArr[13] != null) {	
						File file = new File();
						file.setId(objArr[13].toString());
						result.setFile(file);
					}
					
					results.add(result);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public List<ThreadHdr> findThreadByBookmarkAndUserId(String idUser, Integer startPage, Integer maxPage) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT th.id, th.thread_name, th.thread_code, th.thread_content, th.is_premium, th.polling_id, th.category_id, th.created_by, th.created_at, th.updated_at, th.updated_by, th.is_active, th.version, th.file_id ")
				.append(" FROM comm_thread_hdr th ")
				.append(" INNER JOIN comm_bookmark b ON b.thread_hdr_id = th.id ")
				.append(" WHERE b.created_by = :idUser");
		
		List<ThreadHdr> results = new ArrayList<>();
		try {
			Query q = createNativeQuery(sql.toString())
					.setParameter("idUser", idUser);
			
			if(startPage != null && maxPage != null) {
				q.setFirstResult(startPage)
				.setMaxResults(maxPage);
			}
			
			List<?> res = q.getResultList();
			
			if(res != null) {
				res.forEach(obj -> {
					Object[] objArr = (Object[]) obj;
					ThreadHdr result = new ThreadHdr();
					result.setId(objArr[0].toString());
					result.setThreadName(objArr[1].toString());
					result.setThreadCode(objArr[2].toString());
					result.setThreadContent(objArr[3].toString());
					result.setIsPremium(Boolean.valueOf(objArr[4].toString()));
					
					if(objArr[5] != null) {	
						PollingHdr pollingHdr = new PollingHdr();
						pollingHdr.setId(objArr[5].toString());
						result.setPolling(pollingHdr);
					}
					
					ThreadCategory category = new ThreadCategory();
					category.setId(objArr[6].toString());
					result.setCategory(category);
					result.setCreatedBy(objArr[7].toString());
					result.setCreatedAt(((Timestamp) objArr[8]).toLocalDateTime());
					result.setIsActive(Boolean.valueOf(objArr[11].toString()));
					result.setVersion(Integer.valueOf(objArr[12].toString()));
					if(objArr[13] != null) {	
						File file = new File();
						file.setId(objArr[13].toString());
						result.setFile(file);
					}
					
					results.add(result);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}