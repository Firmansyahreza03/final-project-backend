package com.lawencon.community.pojo.file;

public class PojoDataFile {
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
	private String name;
	private String extension;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
}
