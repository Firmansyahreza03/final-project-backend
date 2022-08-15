package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.RefreshTokenDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.LogoutReq;
import com.lawencon.community.pojo.LogoutRes;
import com.lawencon.security.RefreshTokenEntity;

@Service
public class LogoutService extends BaseCoreService<RefreshTokenEntity>{
	@Autowired
	private RefreshTokenDao refreshTokenDao;
	@Autowired
	private UserDao userDao;
	
	public void deleteRefreshToken(String accessToken) throws Exception{
		try {
			begin();
			String id = refreshTokenDao.findRefreshTokenIdFromAccessToken(accessToken);
			refreshTokenDao.deleteById(id);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public LogoutRes updateUserLogged(LogoutReq logoutReq) throws Exception{
		try {
			LogoutRes res = new LogoutRes();
			User user = userDao.findByEmail(logoutReq.getEmail());
			User userData = userDao.getById(user.getId());
			userData.setToken(null);
			userDao.save(userData);
			
			res.setMessage("Logout succesfully");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
