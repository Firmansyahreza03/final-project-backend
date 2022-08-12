package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.RefreshTokenDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.User;
import com.lawencon.security.PrincipalServiceImpl;
import com.lawencon.security.RefreshTokenEntity;

@Service
public class LogoutService extends BaseCoreService<RefreshTokenEntity>{
	@Autowired
	private RefreshTokenDao refreshTokenDao;
	@Autowired
	private PrincipalServiceImpl principalServiceImpl;
	@Autowired
	private UserDao userDao;
	
	public void deleteRefreshToken(String id) throws Exception{
		try {
			begin();
			refreshTokenDao.deleteById(id);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public void updateUserLogged() throws Exception{
		try {
			User user = userDao.getById(principalServiceImpl.getAuthPrincipal());
			user.setToken(null);
			userDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
