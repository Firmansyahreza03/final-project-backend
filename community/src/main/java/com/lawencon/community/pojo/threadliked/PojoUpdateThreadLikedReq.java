package com.lawencon.community.pojo.threadliked;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdateThreadLikedReq {

	@NotBlank(message = "id can not be empty")
	private String id;
	@NotBlank(message = "thread can not be empty")
	private String hdrId;
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
