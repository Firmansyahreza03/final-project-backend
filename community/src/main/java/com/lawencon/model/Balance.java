package com.lawencon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_balance")
public class Balance extends BaseEntity {

	private static final long serialVersionUID = -5301284539103168282L;

	@Column(name = "balance")
	private Long balance;

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
}
