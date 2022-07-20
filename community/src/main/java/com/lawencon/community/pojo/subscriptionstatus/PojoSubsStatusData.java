package com.lawencon.community.pojo.subscriptionstatus;

import java.time.LocalDateTime;

public class PojoSubsStatusData {

	private Boolean isSubscriber;
	private String paymentId;
	private Boolean isAcc;
	private LocalDateTime expiredAt;

	public Boolean getIsSubscriber() {
		return isSubscriber;
	}

	public void setIsSubscriber(Boolean isSubscriber) {
		this.isSubscriber = isSubscriber;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Boolean getIsAcc() {
		return isAcc;
	}

	public void setIsAcc(Boolean isAcc) {
		this.isAcc = isAcc;
	}

	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}

}
