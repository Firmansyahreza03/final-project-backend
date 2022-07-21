package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Bookmark;
import com.lawencon.community.model.ThreadHdr;
import com.lawencon.community.model.User;

@Repository
public class BookmarkDao extends AbstractJpaDao<Bookmark>{

	private Bookmark inputData(Object obj) throws Exception{
		Bookmark results = new Bookmark();
		Object[] objArr = (Object[]) obj;
		results.setId(objArr[0].toString());

		ThreadHdr fkThreadHdr = new ThreadHdr();
		fkThreadHdr.setId(objArr[1].toString());
		results.setThreadHdr(fkThreadHdr);
		
		User fkUser= new User();
		fkUser.setId(objArr[2].toString());
		results.setUser(fkUser);
		
		results.setCreatedBy(objArr[3].toString());
		results.setCreatedAt(((Timestamp) objArr[4]).toLocalDateTime());
		
		if(objArr[5] != null)
			results.setUpdatedBy(objArr[5].toString());
		if(objArr[6] != null)
			results.setUpdatedAt(((Timestamp) objArr[6]).toLocalDateTime());
		
		results.setIsActive(Boolean.valueOf(objArr[7].toString()));
		results.setVersion(Integer.valueOf(objArr[8].toString()));
		
		return results;
	}

	public List<Bookmark> getByIdUser(String id, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder()
		.append("SELECT b.* FROM comm_bookmark b ")
		.append(" INNER JOIN comm_user u ON b.id = u.balance_id ")
		.append(" WHERE u.id = :id ");
		
		List<Bookmark> res = new ArrayList<>();
		
		List<?> rs = createNativeQuery(sql.toString())
				.setParameter("id", id)
				.setFirstResult(startPage)
				.setMaxResults(maxPage)
				.getResultList();

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
}
