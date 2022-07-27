package com.lawencon.community.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.base.ConnHandler;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.User;
import com.lawencon.security.RefreshTokenEntity;
import com.lawencon.security.RefreshTokenService;
import com.lawencon.util.JwtUtil;

@Service
public class LoginService extends BaseCoreService<User> implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private RefreshTokenService tokenService;

	public User findUserByMail(String mail) throws Exception {
		User resultUser = userDao.findByEmail(mail);
		return resultUser;
	}

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		User userDb = null;
		try {
			userDb = userDao.findByEmail(mail);
			if (userDb == null) {
				throw new RuntimeException("USER DOES NOT EXIST");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(userDb.getId(), null);
		SecurityContextHolder.getContext().setAuthentication(auth);
		return new org.springframework.security.core.userdetails.User(mail, userDb.getUserPassword(),
				new ArrayList<>());

	}

	public String updateToken(String id) throws Exception {
		User user = userDao.getById(id);

		RefreshTokenEntity refreshToken = jwtUtil.generateRefreshToken();
		if(user.getToken() != null) {						
			RefreshTokenEntity token = ConnHandler.getManager().find(RefreshTokenEntity.class, user.getToken().getId());
			token.setToken(refreshToken.getToken());
			token.setExpiredDate(refreshToken.getExpiredDate());
			begin();
			tokenService.saveToken(token);
		} else {
			begin();
			RefreshTokenEntity tokenNew = tokenService.saveToken(refreshToken);
			user.setToken(tokenNew);
		}
		
		User res = save(user);
		String token = res.getToken().getToken();
		commit();
		return token;
	}

}
