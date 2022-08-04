package com.lawencon.community.pojo.paymentTransaction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoInsertPaymentTransactionReq {
	@NotNull(message = "isActive must be true or false")
	private Boolean isActive;
	
	@NotBlank(message = "Description can't be empty")
	private String desc;
	@NotNull(message = "Price can't be empty")
	private Long price;

	private String fileId;
	private String fileName;
	private String fileExt;

	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
}
