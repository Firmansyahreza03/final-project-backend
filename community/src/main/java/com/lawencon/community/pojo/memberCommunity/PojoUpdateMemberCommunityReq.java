package com.lawencon.community.pojo.memberCommunity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdateMemberCommunityReq {
	@NotNull(message = "Id cannot be empty")
	private String id;
	@NotNull(message = "Version cannot be empty")
	private Integer version;
	@NotNull(message = "must be true or false")
	private Boolean isActive;

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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@NotBlank(message = "id user cannot be empty")
	private String idUser;
	@NotBlank(message = "id comunity cannot be empty")
	private String idCommunity;
	@NotBlank(message = "id payment cannot be empty")
	private String idPayment;

	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIdCommunity() {
		return idCommunity;
	}
	public void setIdCommunity(String idCommunity) {
		this.idCommunity = idCommunity;
	}
	public String getIdPayment() {
		return idPayment;
	}
	public void setIdPayment(String idPayment) {
		this.idPayment = idPayment;
	}
	
	private String idFile;
	private String nameFile;
	private String extFile;
	
	public String getIdFile() {
		return idFile;
	}
	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}
	public String getNameFile() {
		return nameFile;
	}
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}
	public String getExtFile() {
		return extFile;
	}
	public void setExtFile(String extFile) {
		this.extFile = extFile;
	}
}
