package com.lawencon.community.pojo.threaddtl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoInsertThreadDtlReq {

	@NotBlank(message = "comment can not be empty")
	private String threadComment;
	@NotBlank(message = "user can not be empty")
	private String userId;
	@NotBlank(message = "thread can not be empty")
	private String hdrId;
	@NotNull(message = "must be true or false")
	private Boolean isActive;

	public String getThreadComment() {
		return threadComment;
	}

	public void setThreadComment(String threadComment) {
		this.threadComment = threadComment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHdrId() {
		return hdrId;
	}

	public void setHdrId(String hdrId) {
		this.hdrId = hdrId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}