package com.lawencon.community.pojo.pollingoption;

public class PojoPollingOptionData {

	private String id;
	private String pollingId;
	private String optionName;
	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPollingId() {
		return pollingId;
	}

	public void setPollingId(String pollingId) {
		this.pollingId = pollingId;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
