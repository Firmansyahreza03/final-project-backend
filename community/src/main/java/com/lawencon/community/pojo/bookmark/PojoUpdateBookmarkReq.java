package com.lawencon.community.pojo.bookmark;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdateBookmarkReq {
	@NotNull(message = "Id cannot be empty")
	private String id;
	@NotNull(message = "Version cannot be empty")
	private Integer version;
	@NotNull(message = "must be true or false")
	private Boolean isActive;

	@NotBlank(message = "id thread header cannot be empty")
	private String idThreadHdr;
	@NotBlank(message = "id user cannot be empty")
	private String idUser;

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
}
