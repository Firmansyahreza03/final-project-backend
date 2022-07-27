package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Community;
import com.lawencon.community.model.CommunityCategory;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Industry;

@Repository
public class CommunityDao extends AbstractJpaDao<Community> {

	private Community inputData(Object obj) throws Exception{
		Community results = new Community();
		Object[] objArr = (Object[]) obj;
		results.setId(objArr[0].toString());
		

		results.setCommunityTitle(objArr[1].toString());
		results.setCommunityProvider(objArr[2].toString());
		results.setCommunityLocation(objArr[3].toString());
		results.setCommunityStartAt(((Timestamp) objArr[4]).toLocalDateTime());
		results.setCommunityEndAt(((Timestamp) objArr[5]).toLocalDateTime());
		results.setCommunityDesc(objArr[6].toString());
		results.setCommunityCode(objArr[7].toString());
		results.setCommunityPrice(Long.valueOf(objArr[8].toString()));
		if(objArr[9]!=null) {
			File fkFile = new File();
			fkFile.setId(objArr[9].toString());
			results.setFile(fkFile);
		}
		CommunityCategory fkCategory = new CommunityCategory();
		fkCategory.setId(objArr[10].toString());
		results.setCategory(fkCategory);
		
		Industry fkIndustry = new Industry();
		fkIndustry.setId(objArr[11].toString());
		results.setIndustry(fkIndustry);
		
		results.setCreatedBy(objArr[12].toString());
		results.setCreatedAt(((Timestamp) objArr[13]).toLocalDateTime());
		
		if(objArr[14] != null)
			results.setUpdatedBy(objArr[14].toString());
		if(objArr[15] != null)
			results.setUpdatedAt(((Timestamp) objArr[15]).toLocalDateTime());
		
		results.setIsActive(Boolean.valueOf(objArr[16].toString()));
		results.setVersion(Integer.valueOf(objArr[17].toString()));
		
		return results;
	}

	public List<Community> getByIdIndustry(String id, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder()
		.append("SELECT c.* FROM comm_community c ")
		.append(" WHERE c.industry_id = :id ")
		.append(" ORDER BY c.created_at DESC ");
		
		List<Community> res = new ArrayList<>();
		
		Query q = createNativeQuery(sql.toString())
				.setParameter("id", id);
		
		if(startPage != null && maxPage != null) {
			q.setFirstResult(startPage)
			.setMaxResults(maxPage);
		}
		
		List<?> rs = q.getResultList();

		rs.forEach(obj ->{
			try {
				Community data = inputData(obj);
				res.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return res;
	}
	
	public List<Community> getByIdCategory(String id, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder()
		.append("SELECT c.* FROM comm_community c ")
		.append(" WHERE c.category_id = :id ")
		.append(" ORDER BY c.created_at DESC ");
		
		List<Community> res = new ArrayList<>();
		
		Query q = createNativeQuery(sql.toString())
				.setParameter("id", id);
		
		if(startPage != null && maxPage != null) {
			q.setFirstResult(startPage)
			.setMaxResults(maxPage);
		}
		
		List<?> rs = q.getResultList();

		rs.forEach(obj ->{
			try {
				Community data = inputData(obj);
				res.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return res;
	}
}
