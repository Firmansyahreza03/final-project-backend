package com.lawencon.community.pojo.threadhdr;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdateThreadHdrReq {

	@NotBlank(message = "id can not be empty")
	private String id;
	@NotBlank(message = "thread name can not be empty")
	private String threadName;
	@NotBlank(message = "thread content can not be empty")
	private String threadContent;
	private String pollingHdrsId;
	@NotBlank(message = "category can not be empty")
	private String categoryId;
	@NotNull(message = "must be true or false")
	private Boolean isActive;
	@NotNull(message = "version can not be empty")
	private Integer version;
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

	public String getThreadContent() {
		return threadContent;
	}

	public void setThreadContent(String threadContent) {
		this.threadContent = threadContent;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getPollingHdrsId() {
		return pollingHdrsId;
	}

	public void setPollingHdrsId(String pollingHdrsId) {
		this.pollingHdrsId = pollingHdrsId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
