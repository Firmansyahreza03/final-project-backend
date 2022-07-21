package com.lawencon.community.pojo.threadliked;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoInsertThreadLikedReq {

	@NotBlank(message = "thread can not be empty")
	private String hdrId;
	@NotNull(message = "must be true or false")
	private Boolean isActive;

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
