package com.lawencon.community.pojo.community;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PojoDataCommunity {
	private String  id;
	private Boolean isActive;
	private Integer version;
	
	private String title;
	private String provider;
	private String location;
	@JsonFormat (pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime startAt;
	@JsonFormat (pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime endAt;
	private String desc;
	private String code;
	private BigDecimal price;
	private String idCategory;
	private String nameCategory;
	private String idIndustry;
	private String nameIndustry;

	private String idFile;
	private String nameFile;
	private String extFile;
	
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
	public LocalDateTime getStartAt() {
		return startAt;
	}
	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}
	public LocalDateTime getEndAt() {
		return endAt;
	}
	public void setEndAt(LocalDateTime endAt) {
		this.endAt = endAt;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	public String getNameIndustry() {
		return nameIndustry;
	}
	public void setNameIndustry(String nameIndustry) {
		this.nameIndustry = nameIndustry;
	}

	
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
