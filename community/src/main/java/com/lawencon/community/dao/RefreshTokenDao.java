package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.security.RefreshTokenEntity;

@Repository
public class RefreshTokenDao extends AbstractJpaDao<RefreshTokenEntity>{
	public String findRefreshTokenIdFromAccessToken(String accessToken) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT rt.id FROM refresh_token rt ")
				.append(" WHERE rt.access_token = :accessToken ");
		
		String res = null;
		try {
			Object result = createNativeQuery(sql.toString())
					.setParameter("accessToken", accessToken)
					.getSingleResult();
			
			if(result != null) {
				res = result.toString();
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return res;
	}
}
