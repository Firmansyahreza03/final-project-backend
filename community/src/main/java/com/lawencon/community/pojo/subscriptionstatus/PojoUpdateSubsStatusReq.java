package com.lawencon.community.pojo.subscriptionstatus;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdateSubsStatusReq {

	@NotBlank(message = "id can not be empty")
	private String id;

	@NotNull(message = "is subscriber can not be empty")
	private Boolean isSubscriber;

	@NotBlank(message = "payment can not be empty")
	private String paymentId;

	@NotNull(message = "expired at can not be empty")
	private LocalDateTime expiredAt;

	@NotNull(message = "must be true or false")
	private Boolean isActive;

	@NotNull(message = "version can not be empty")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
