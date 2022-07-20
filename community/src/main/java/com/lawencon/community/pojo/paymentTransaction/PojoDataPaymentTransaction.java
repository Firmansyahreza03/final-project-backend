package com.lawencon.community.pojo.paymentTransaction;

public class PojoDataPaymentTransaction {
	private Long id;
	private Boolean isActive;
	private Integer version;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	private Boolean isAcc;

	private String fileId;
	private String fileName;
	private String fileExt;

	public Boolean getIsAcc() {
		return isAcc;
	}
	public void setIsAcc(Boolean isAcc) {
		this.isAcc = isAcc;
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
