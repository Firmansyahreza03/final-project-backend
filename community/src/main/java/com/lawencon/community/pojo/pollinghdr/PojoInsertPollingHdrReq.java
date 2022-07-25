package com.lawencon.community.pojo.pollinghdr;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoInsertPollingHdrReq {

	@NotBlank(message = "name can not be empty")
	private String pollingName;
	@NotNull(message = "must be true or false")
	private Boolean isActive;
	@NotNull(message = "option can not be empty")
	private List<String> options;

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

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
