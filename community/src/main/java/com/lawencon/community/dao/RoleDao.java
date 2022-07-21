package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Role;

@Repository
public class RoleDao extends AbstractJpaDao<Role> {

	public Role findByRoleCode(String code) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT r.id, r.role_code ")
				.append(" FROM comm_role r ")
				.append(" WHERE r.role_code = :code ");

		Role role = null;
		try {
			Object res = createNativeQuery(sql.toString())
					.setParameter("code", code)
					.getSingleResult();

			if (res != null) {
				Object[] objArr = (Object[]) res;
				role = new Role();
				role.setId(objArr[0].toString());
				role.setRoleCode(objArr[1].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
}