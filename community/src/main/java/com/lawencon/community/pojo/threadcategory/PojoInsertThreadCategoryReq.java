package com.lawencon.community.pojo.threadcategory;

import javax.validation.constraints.NotBlank;

public class PojoInsertThreadCategoryReq {

	@NotBlank(message = "category name can not be empty")
	private String categoryName;
	
	@NotBlank(message = "category code can not be empty")
	private String categoryCode;
	
	@NotBlank(message = "must be true or false")
	private Boolean isActive;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
