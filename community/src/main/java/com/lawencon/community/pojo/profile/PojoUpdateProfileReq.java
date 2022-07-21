package com.lawencon.community.pojo.profile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdateProfileReq {

	@NotBlank(message = "id can not be empty")
	private String id;

	@NotBlank(message = "fullname can not be empty")
	private String fullName;

	@NotBlank(message = "company name can not be empty")
	private String companyName;

	@NotBlank(message = "position can not be empty")
	private String positionName;

	@NotBlank(message = "industry can not be empty")
	private String industryId;

	@NotBlank(message = "user can not be empty")
	private String userId;

	@NotNull(message = "must be true or false")
	private Boolean isActive;

	@NotNull(message = "version can not be empty")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getIndustryId() {
		return industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
