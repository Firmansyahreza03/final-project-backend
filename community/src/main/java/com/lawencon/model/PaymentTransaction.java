package com.lawencon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class PaymentTransaction extends BaseEntity {

	private static final long serialVersionUID = -6432954813396046200L;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	@Column(name = "is_acc")
	private Boolean isAcc;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Boolean getIsAcc() {
		return isAcc;
	}

	public void setIsAcc(Boolean isAcc) {
		this.isAcc = isAcc;
	}

}
