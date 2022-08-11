package com.lawencon.community.pojo.community;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PojoUpdateCommunityReq {
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

	@NotBlank(message = "title cannot be empty")
	private String title;
	@NotBlank(message = "provider cannot be empty")
	private String provider;
	@NotBlank(message = "location cannot be empty")
	private String location;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX'Z'")
	@NotBlank(message = "start time cannot be empty")
	private String startAt;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX'Z'")
	@NotBlank(message = "end time cannot be empty")
	private String endAt;
	@NotBlank(message = "description cannot be empty")
	private String desc;
	@NotBlank(message = "price cannot be empty")
	private BigDecimal price;
	@NotBlank(message = "id category cannot be empty")
	private String idCategory;
	@NotBlank(message = "id industry cannot be empty")
	private String idIndustry;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartAt() {
		return startAt;
	}
	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}
	public String getEndAt() {
		return endAt;
	}
	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(String idCategory) {
		this.idCategory = idCategory;
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
