package com.lawencon.community.pojo.community;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PojoInsertCommunityReq {
	@NotNull(message = "must be true or false")
	private Boolean isActive;
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@NotBlank(message = "title cannot be empty")
	private String title;
	@NotBlank(message = "provider cannot be empty")
	private String provider;
	@NotBlank(message = "location cannot be empty")
	private String location;
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
	@NotBlank(message = "start time cannot be empty")
	private LocalDateTime startAt;
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
	@NotBlank(message = "end time cannot be empty")
	private LocalDateTime endAt;
	@NotBlank(message = "description cannot be empty")
	private String desc;
	@NotBlank(message = "code cannot be empty")
	private String code;
	@NotBlank(message = "price cannot be empty")
	private Long price;
	@NotBlank(message = "id category cannot be empty")
	private String idCategory;
	@NotBlank(message = "id industry cannot be empty")
	private String idIndustry;


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
