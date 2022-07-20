package com.lawencon.community.pojo.paymentTransaction;

import javax.validation.constraints.NotNull;

public class PojoInsertPaymentTransactionReq {
	@NotNull(message = "isActive must be true or false")
	private Boolean isActive;
	
	@NotNull(message = "Acc must be true or false")
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
