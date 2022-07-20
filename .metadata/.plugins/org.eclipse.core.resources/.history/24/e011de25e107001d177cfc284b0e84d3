package com.lawencon.community.pojo.role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdateDataRoleReq {
	@NotNull(message = "Id can not be empty")
	private String id;
	@NotNull(message = "Version can not be empty")
	private Long version;
	@NotNull(message = "isActive can not be empty")
	private Boolean isActive;
	
	@NotBlank(message = "name can not be empty")
	private String roleName;
	@NotBlank(message = "code can not be empty")
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
