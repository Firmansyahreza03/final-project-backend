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

	@NotBlank(message = "email can not be empty")
	private String userEmail;

	@NotBlank(message = "password can not be empty")
	private String userPassword;

	@NotBlank(message = "subscription status can not be empty")
	private String subscriptionStatus;

	@NotBlank(message = "verification code")
	private String verificationCode;

	@NotBlank(message = "role can not be empty")
	private String roleId;

	@NotBlank(message = "balance can not be empty")
	private String balanceId;

	@NotBlank(message = "file name can not be empty")
	private String fileName;

	@NotBlank(message = "file extinsion can not be empty")
	private String fileExt;

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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

}
