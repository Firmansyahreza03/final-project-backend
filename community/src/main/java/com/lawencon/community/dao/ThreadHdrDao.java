package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.File;
import com.lawencon.community.model.ThreadCategory;
import com.lawencon.community.model.ThreadHdr;

@Repository
public class ThreadHdrDao extends AbstractJpaDao<ThreadHdr> {

	public List<ThreadHdr> findByCreatorId(String id, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder().append(
				" SELECT th.thread_name, th.is_premium, th.category_id, tc.category_name, th.created_by, th.id, th.thread_content, th.file_id ")
				.append(" FROM comm_thread_hdr th ")
				.append(" INNER JOIN comm_thread_category tc ON tc.id = th.category_id ")
				.append(" WHERE th.created_by = :id ").append(" ORDER BY th.created_at DESC ");

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
<<<<<<< HEAD

				if (objArr[7] != null) {
=======
				if(objArr[7]!=null) {					
>>>>>>> 86623a6520b71bf171ed0eddde4a26b53c0e40e4
					File file = new File();
					file.setId(objArr[7].toString());
					hdr.setFile(file);
				}

				hdrs.add(hdr);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hdrs;
	}
}