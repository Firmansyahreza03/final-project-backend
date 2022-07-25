package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

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
import com.lawencon.community.pojo.user.PojoFindByIdUserRes;
import com.lawencon.community.pojo.user.PojoUserData;
import com.lawencon.model.SearchQuery;
import com.lawencon.security.RefreshTokenEntity;
import com.lawencon.security.RefreshTokenService;
import com.lawencon.util.JwtUtil;

@Service
public class UserService extends BaseCoreService<User> implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private RefreshTokenService tokenService;

	private PojoUserData modelToRes(User data) {
		PojoUserData res = new PojoUserData();

		res.setBalance(data.getBalance().getBalance());
		res.setBalanceId(data.getBalance().getId());
		res.setExpiredAt(data.getSubscriptionStatus().getExpiredAt());
		if (data.getFile() != null)
			res.setFileId(data.getFile().getId());
		res.setId(data.getId());
		res.setIsActive(data.getIsActive());
		res.setIsSubscriber(data.getSubscriptionStatus().getIsSubscriber());
		res.setRoleCode(data.getRole().getRoleCode());
		res.setRoleId(data.getRole().getId());
		res.setSubscriptionStatusId(data.getSubscriptionStatus().getId());
		res.setUserEmail(data.getUserEmail());
		res.setVerificationCode(data.getVerificationCode());
		res.setVersion(data.getVersion());

		return res;
	}

	public PojoFindByIdUserRes findById(String id) throws Exception {
		User data = userDao.getById(id);

		PojoUserData res = modelToRes(data);
		PojoFindByIdUserRes result = new PojoFindByIdUserRes();
		result.setData(res);

		return result;
	}

	public SearchQuery<PojoUserData> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<User> getAllUser = userDao.findAll(query, startPage, maxPage);
		List<PojoUserData> results = new ArrayList<>();

		getAllUser.getData().forEach(d -> {
			PojoUserData data = modelToRes(d);

			results.add(data);
		});

		SearchQuery<PojoUserData> result = new SearchQuery<>();
		result.setData(results);
		result.setCount(getAllUser.getCount());
		return result;
	}

	public User findUserToLogin(String mail) throws Exception {
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
