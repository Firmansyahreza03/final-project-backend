package com.lawencon.community.pojo.pollinghdr;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PojoInsertPollingHdrReq {

	@NotBlank(message = "name can not be empty")
	private String pollingName;
	@NotNull(message = "must be true or false")
	private Boolean isActive;
	@NotNull(message = "option can not be empty")
	private List<String> options;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX'Z'")
	@NotNull(message = "start time cannot be empty")
	private String expiredAt;

	public String getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(String expiredAt) {
		this.expiredAt = expiredAt;
	}

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
