package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Article;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.User;

@Repository
public class ArticleDao extends AbstractJpaDao<Article> {

	private Article inputData(Object obj) throws Exception{
		Article results = new Article();
		Object[] objArr = (Object[]) obj;
		results.setId(objArr[0].toString());
		

		results.setArticleTitle(objArr[1].toString());
		results.setArticleContent(objArr[2].toString());
		
		User fkUser= new User();
		fkUser.setId(objArr[3].toString());
		results.setUser(fkUser);
		
		Industry fkIndustry = new Industry();
		fkIndustry.setId(objArr[4].toString());
		results.setIndustry(fkIndustry);
		
		results.setCreatedBy(objArr[5].toString());
		results.setCreatedAt(((Timestamp) objArr[6]).toLocalDateTime());
		
		if(objArr[7] != null)
			results.setUpdatedBy(objArr[7].toString());
		if(objArr[8] != null)
			results.setUpdatedAt(((Timestamp) objArr[8]).toLocalDateTime());
		
		results.setIsActive(Boolean.valueOf(objArr[9].toString()));
		results.setVersion(Integer.valueOf(objArr[10].toString()));
		
		return results;
	}

	public List<Article> getByIdIndustry(String id, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder()
		.append("SELECT a.* FROM comm_article a ")
		.append(" WHERE a.industry_id = :id ");
		
		List<Article> res = new ArrayList<>();
		Query q = createNativeQuery(sql.toString())
				.setParameter("id", id);
		
		if(startPage != null && maxPage != null) {
			q.setFirstResult(startPage).setMaxResults(maxPage);
		}
		
		List<?> rs = q.getResultList();

		rs.forEach(obj ->{
			try {
				Article data = inputData(obj);
				res.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return res;
	}
	
}
