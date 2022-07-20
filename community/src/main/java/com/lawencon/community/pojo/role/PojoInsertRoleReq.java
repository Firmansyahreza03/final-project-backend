package com.lawencon.community.pojo.role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoInsertRoleReq {
	@NotNull(message = "must be true or false")
	private Boolean isActive;
	@NotBlank(message = "name cannot be empty")
	private String roleName;
	@NotBlank(message = "code cannot be empty")
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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
