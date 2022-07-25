package com.lawencon.community.pojo.pollingoption;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoInsertPollingOptionReq {

	@NotBlank(message = "polling can not be empty")
	private String pollingHdrId;

	@NotBlank(message = "option can not be empty")
	private String optionName;

	@NotNull(message = "must be true or false")
	private Boolean isActive;

	public String getPollingHdrId() {
		return pollingHdrId;
	}

	public void setPollingHdrId(String pollingHdrId) {
		this.pollingHdrId = pollingHdrId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
