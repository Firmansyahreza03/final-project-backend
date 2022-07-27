package com.lawencon.community.pojo.threadhdr;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoInsertThreadHdrReq {

	@NotBlank(message = "thread name can not be empty")
	private String threadName;
	@NotBlank(message = "thread content can not be empty")
	private String threadContent;
	@NotNull(message = "must be true or false")
	private Boolean isPremium;
	private String pollingHdrId;
	@NotBlank(message = "category can not be empty")
	private String categoryId;
	@NotNull(message = "must be true or false")
	private Boolean isActive;
	private String fileName;
	private String fileExt;
	@NotBlank(message = "email can not be empty")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}

	public String getPollingHdrId() {
		return pollingHdrId;
	}

	public void setPollingHdrId(String pollingHdrId) {
		this.pollingHdrId = pollingHdrId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getThreadContent() {
		return threadContent;
	}

	public void setThreadContent(String threadContent) {
		this.threadContent = threadContent;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
