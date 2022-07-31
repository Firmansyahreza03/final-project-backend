package com.lawencon.community.pojo.bookmark;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoInsertBookmarkReq {
	@NotNull(message = "must be true or false")
	private Boolean isActive;
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@NotBlank(message = "id thread header cannot be empty")
	private String idThreadHdr;

	public String getIdThreadHdr() {
		return idThreadHdr;
	}
	public void setIdThreadHdr(String idThreadHdr) {
		this.idThreadHdr = idThreadHdr;
	}
}
