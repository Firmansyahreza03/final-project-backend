package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;

@Repository
public class UserDao extends AbstractJpaDao<User> {

	public User findByRoleCode(String code){
		StringBuilder sql = new StringBuilder()
				.append(" SELECT u.* ")
				.append(" FROM comm_user u ")
				.append(" INNER JOIN comm_role r ON r.id = u.role_id ")
				.append(" WHERE r.role_code = :code ");
		
		User user = null;
		try {
			Object res = createNativeQuery(sql.toString())
					.setParameter("code", code)
					.getSingleResult();
			
			if (res != null) {
				Object[] objArr = (Object[]) res;
				user = new User();
				user.setId(objArr[0].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User findByEmail(String email) {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT u.id, r.role_code, u.user_email, u.user_password ")
				.append(" FROM comm_user u ")
				.append(" INNER JOIN comm_role r ON u.role_id = r.id ")
				.append(" WHERE u.is_active = TRUE AND u.user_email = :email");
		
		User user = null;
		try {
			Object res = createNativeQuery(sql.toString())
					.setParameter("email", email)
					.getSingleResult();
			
			if (res != null) {
				Object[] objArr = (Object[]) res;
				user = new User();
				user.setId(objArr[0].toString());
				Role role = new Role();
				role.setRoleCode(objArr[1].toString());
				user.setRole(role);
				user.setUserEmail(objArr[2].toString());
				user.setUserPassword(objArr[3].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User findByRefreshToken(String token) {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT u.id, r.role_code, u.user_email, u.user_password ")
				.append(" FROM comm_user u ")
				.append(" INNER JOIN comm_role r ON u.role_id = r.id ")
				.append(" INNER JOIN refresh_token rt ON u.refresh_token_id = rt.id ")
				.append(" WHERE u.is_active = TRUE AND rt.access_token = :token");
		
		User user = null;
		try {
			Object res = createNativeQuery(sql.toString())
					.setParameter("token", token)
					.getSingleResult();
			
			if (res != null) {
				Object[] objArr = (Object[]) res;
				user = new User();
				user.setId(objArr[0].toString());
				Role role = new Role();
				role.setRoleCode(objArr[1].toString());
				user.setRole(role);
				user.setUserEmail(objArr[2].toString());
				user.setUserPassword(objArr[3].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}