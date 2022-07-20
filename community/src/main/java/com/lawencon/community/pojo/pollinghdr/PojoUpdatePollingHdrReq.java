package com.lawencon.community.pojo.pollinghdr;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdatePollingHdrReq {

	@NotBlank(message = "Id can not be empty")
	private String id;

	@NotBlank(message = "Name can not be empty")
	private String pollingName;

	@NotNull(message = "must be true or false")
	private Boolean isActive;

	@NotNull(message = "Version can not be empty")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
