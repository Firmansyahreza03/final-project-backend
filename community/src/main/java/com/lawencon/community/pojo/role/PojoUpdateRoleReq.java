package com.lawencon.community.pojo.role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdateRoleReq {
	@NotNull(message = "Id cannot be empty")
	private String id;
	@NotNull(message = "Version cannot be empty")
	private Long version;
	@NotNull(message = "must be true or false")
	private Boolean isActive;
	
	@NotBlank(message = "name cannot be empty")
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
