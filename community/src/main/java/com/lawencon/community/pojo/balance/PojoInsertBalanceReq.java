package com.lawencon.community.pojo.balance;

import java.math.BigDecimal;

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
	private BigDecimal balance;

	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
