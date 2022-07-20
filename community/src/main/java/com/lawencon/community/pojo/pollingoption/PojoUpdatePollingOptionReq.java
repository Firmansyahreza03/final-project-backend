package com.lawencon.community.pojo.pollingoption;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdatePollingOptionReq {
	
	@NotBlank(message = "id can not be empty")
	private String id;

	@NotBlank(message = "polling name can not be empty")
	private String pollingHdrId;

	@NotBlank(message = "option can not be empty")
	private String optionName;
	
	@NotNull(message = "must be true or false")
	private Boolean isActive;

	@NotNull(message = "version can not be empty")
	private Integer version;

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
