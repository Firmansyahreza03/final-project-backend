package com.lawencon.community.pojo.memberCommunity;

public class PojoDataMemberCommunity {
	private String id;
	private Boolean isActive;
	private Integer version;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	private String idUser;
	private String nameUser;

	private String idCommunity;
	private String nameCommunity;

	private String idPayment;
	private String fileIdPayment;
	private String fileNamePayment;
	private String fileExtPayment;
	private Boolean isAccPayment;

	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getIdCommunity() {
		return idCommunity;
	}
	public void setIdCommunity(String idCommunity) {
		this.idCommunity = idCommunity;
	}
	public String getNameCommunity() {
		return nameCommunity;
	}
	public void setNameCommunity(String nameCommunity) {
		this.nameCommunity = nameCommunity;
	}
	public String getIdPayment() {
		return idPayment;
	}
	public void setIdPayment(String idPayment) {
		this.idPayment = idPayment;
	}
	public String getFileIdPayment() {
		return fileIdPayment;
	}
	public void setFileIdPayment(String fileIdPayment) {
		this.fileIdPayment = fileIdPayment;
	}
	public String getFileNamePayment() {
		return fileNamePayment;
	}
	public void setFileNamePayment(String fileNamePayment) {
		this.fileNamePayment = fileNamePayment;
	}
	public String getFileExtPayment() {
		return fileExtPayment;
	}
	public void setFileExtPayment(String fileExtPayment) {
		this.fileExtPayment = fileExtPayment;
	}
	public Boolean getIsAccPayment() {
		return isAccPayment;
	}
	public void setIsAccPayment(Boolean isAccPayment) {
		this.isAccPayment = isAccPayment;
	}
}
