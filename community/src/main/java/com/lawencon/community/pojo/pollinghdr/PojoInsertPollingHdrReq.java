package com.lawencon.community.pojo.pollinghdr;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoInsertPollingHdrReq {

	@NotBlank(message = "name can not be empty")
	private String pollingName;
	@NotNull(message = "must be true or false")
	private Boolean isActive;

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}