package com.lawencon.community.pojo.pollinganswer;

public class PojoPollingAnswerData {

	private String id;
	private String pollingOptionId;
	private String pollingOptionName;
	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPollingOptionId() {
		return pollingOptionId;
	}

	public void setPollingOptionId(String pollingOptionId) {
		this.pollingOptionId = pollingOptionId;
	}

	public String getPollingOptionName() {
		return pollingOptionName;
	}

	public void setPollingOptionName(String pollingOptionName) {
		this.pollingOptionName = pollingOptionName;
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
