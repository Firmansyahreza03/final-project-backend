package com.lawencon.community.pojo.threadcategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdateThreadCategoryReq {

	@NotBlank(message = "id can not be empty")
	private String id;

	@NotBlank(message = "category name can not be empty")
	private String categoryName;

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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
