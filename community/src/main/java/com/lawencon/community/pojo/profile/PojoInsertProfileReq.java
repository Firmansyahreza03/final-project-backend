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

	@NotBlank(message = "verification code")
	private String verificationCode;

	@NotBlank(message = "file name can not be empty")
	private String fileName;

	@NotBlank(message = "file extinsion can not be empty")
	private String fileExt;

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

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
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
