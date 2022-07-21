package com.lawencon.community.pojo.balance;

import javax.validation.constraints.NotNull;

public class PojoInsertBalanceReq {
	@NotNull(message = "must be true or false")
	private Boolean isActive;

	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@NotNull(message = "balance cannot be empty")
	private Long balance;

	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
}
