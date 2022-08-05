package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_payment_transaction")
public class PaymentTransaction extends BaseEntity {

	private static final long serialVersionUID = -6432954813396046200L;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	@Column(name = "is_acc")
	private Boolean isAcc;

	@Column(name = "transaction_code ")
	private String code;
	@Column(name = "transaction_desc")
	private String desc;
	@Column(name = "transaction_price")
	private Long price;
    

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
}
