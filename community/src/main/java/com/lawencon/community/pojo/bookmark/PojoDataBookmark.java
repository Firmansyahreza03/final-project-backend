package com.lawencon.community.pojo.bookmark;

public class PojoDataBookmark {
	private String  id;
	private Boolean isActive;
	private Integer version;
	
	public String  getId() {
		return id;
	}
	public void setId(String  id) {
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

	private String idThreadHdr;
	private String idUser;

	private String nameThreadHdr;
	private String nameUser;

	public String getIdThreadHdr() {
		return idThreadHdr;
	}
	public void setIdThreadHdr(String idThreadHdr) {
		this.idThreadHdr = idThreadHdr;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getNameThreadHdr() {
		return nameThreadHdr;
	}
	public void setNameThreadHdr(String nameThreadHdr) {
		this.nameThreadHdr = nameThreadHdr;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
}
