package com.lawencon.community.pojo.balance;

import javax.validation.constraints.NotNull;

public class PojoUpdateBalanceReq {
	@NotNull(message = "Id cannot be empty")
	private String id;
	@NotNull(message = "Version cannot be empty")
	private Long version;
	@NotNull(message = "must be true or false")
	private Boolean isActive;

	@NotNull(message = "balance cannot be empty")
	private Long balance;

	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
}
