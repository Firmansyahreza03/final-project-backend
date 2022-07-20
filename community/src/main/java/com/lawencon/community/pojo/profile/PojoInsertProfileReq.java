package com.lawencon.community.pojo.profile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoInsertProfileReq {

	@NotBlank(message = "name can not be empty")
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

}
