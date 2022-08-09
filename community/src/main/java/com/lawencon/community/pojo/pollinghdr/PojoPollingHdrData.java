package com.lawencon.community.pojo.pollinghdr;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawencon.community.pojo.pollingoption.PojoPollingOptionData;

public class PojoPollingHdrData {

	private String id;
	private String pollingName;
	private Boolean isActive;
	private Integer version;
	private List<PojoPollingOptionData> option;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime expiredAt;

	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}

	public List<PojoPollingOptionData> getOption() {
		return option;
	}

	public void setOption(List<PojoPollingOptionData> option) {
		this.option = option;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

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

}
