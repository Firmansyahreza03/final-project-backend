package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.ThreadCategory;
import com.lawencon.community.model.ThreadHdr;

@Repository
public class ThreadHdrDao extends AbstractJpaDao<ThreadHdr> {

	public List<ThreadHdr> findByCreatorId(String id, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder().append(
				" SELECT th.thread_name, th.is_premium, th.industry_id, ti.industry_name, th.category_id, tc.category_name, th.created_by, th.id ")
				.append(" FROM comm_thread_hdr th ").append(" INNER JOIN comm_industry ti ON ti.id = th.industry_id ")
				.append(" INNER JOIN comm_thread_category tc ON tc.id = th.category_id ")
				.append(" WHERE th.created_by = :id ");

		List<ThreadHdr> hdrs = new ArrayList<>();
		try {
			List<?> result = createNativeQuery(sql.toString()).setParameter("id", id).setFirstResult(startPage)
					.setMaxResults(maxPage).getResultList();

			result.forEach(obj -> {
				Object[] objArr = (Object[]) obj;
				ThreadHdr hdr = new ThreadHdr();
				hdr.setThreadName(objArr[0].toString());
				hdr.setIsPremium(Boolean.valueOf(objArr[1].toString()));
				Industry industry = new Industry();
				industry.setId(objArr[2].toString());
				industry.setIndustryName(objArr[3].toString());
				hdr.setIndustry(industry);
				ThreadCategory category = new ThreadCategory();
				category.setId(objArr[4].toString());
				category.setCategoryName(objArr[5].toString());
				hdr.setCategory(category);
				hdr.setCreatedBy(objArr[6].toString());
				hdr.setId(objArr[7].toString());

				hdrs.add(hdr);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hdrs;
	}
}