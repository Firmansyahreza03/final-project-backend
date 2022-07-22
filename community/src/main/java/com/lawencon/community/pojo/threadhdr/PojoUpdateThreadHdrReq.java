package com.lawencon.community.pojo.threadhdr;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdateThreadHdrReq {

	@NotBlank(message = "id can not be empty")
	private String id;
	@NotBlank(message = "thread name can not be empty")
	private String threadName;
	@NotBlank(message = "thread code can not be empty")
	private String threadCode;
	@NotNull(message = "must be true or false")
	private Boolean isPremium;
	private String pollingHdrsId;
	@NotBlank(message = "category can not be empty")
	private String categoryid;
	@NotBlank(message = "industry can not be empty")
	private String industryId;
	@NotNull(message = "must be true or false")
	private Boolean isActive;
	@NotNull(message = "version can not be empty")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getThreadCode() {
		return threadCode;
	}

	public void setThreadCode(String threadCode) {
		this.threadCode = threadCode;
	}

	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}

	public String getPollingHdrsId() {
		return pollingHdrsId;
	}

	public void setPollingHdrsId(String pollingHdrsId) {
		this.pollingHdrsId = pollingHdrsId;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getIndustryId() {
		return industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
