package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_file")
public class File extends BaseEntity {

	private static final long serialVersionUID = -1864343559952755777L;

	@Column(name = "file_name", columnDefinition = "TEXT")
	private String fileName;

	@Column(name = "file_extension", columnDefinition = "TEXT")
	private String fileExtension;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

}
