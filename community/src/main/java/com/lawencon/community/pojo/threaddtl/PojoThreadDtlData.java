package com.lawencon.community.pojo.threaddtl;

public class PojoThreadDtlData {

	private String id;
	private String UserId;
	private String UserUsername;
	private String hdrId;
	private String threadComment;
	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getUserUsername() {
		return UserUsername;
	}

	public void setUserUsername(String userUsername) {
		UserUsername = userUsername;
	}

	public String getHdrId() {
		return hdrId;
	}

	public void setHdrId(String hdrId) {
		this.hdrId = hdrId;
	}

	public String getThreadComment() {
		return threadComment;
	}

	public void setThreadComment(String threadComment) {
		this.threadComment = threadComment;
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
