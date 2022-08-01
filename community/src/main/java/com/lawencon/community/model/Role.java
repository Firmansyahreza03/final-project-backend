package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.lawencon.base.BaseEntity;

@Entity
@Indexed
@Table(name = "comm_role", uniqueConstraints = {
		@UniqueConstraint(name = "role_code_bk", columnNames = { "role_code" }) 
		}
)
public class Role extends BaseEntity {

	private static final long serialVersionUID = 4148215435585064923L;

	@FullTextField
	@Column(name = "role_name", columnDefinition = "TEXT")
	private String roleName;

	@FullTextField
	@Column(name = "role_code", columnDefinition = "TEXT")
	private String roleCode;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
