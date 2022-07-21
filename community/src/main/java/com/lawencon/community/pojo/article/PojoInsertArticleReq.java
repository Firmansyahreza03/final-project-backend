package com.lawencon.community.pojo.article;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoInsertArticleReq {
	@NotNull(message = "must be true or false")
	private Boolean isActive;

	@NotBlank(message = "title cannot be empty")
	private String title;
	@NotBlank(message = "content cannot be empty")
	private String content;
	@NotBlank(message = "id user cannot be empty")
	private String idUser;
	@NotBlank(message = "id industry cannot be empty")
	private String idIndustry;
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIdIndustry() {
		return idIndustry;
	}
	public void setIdIndustry(String idIndustry) {
		this.idIndustry = idIndustry;
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