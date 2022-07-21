package com.lawencon.community.pojo.threadliked;

public class PojoThreadLikedData {

	private String id;
	private String hdrId;
	private String hdrName;
	private Boolean isActive;
	private Integer version;

	public String getHdrName() {
		return hdrName;
	}

	public void setHdrName(String hdrName) {
		this.hdrName = hdrName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
