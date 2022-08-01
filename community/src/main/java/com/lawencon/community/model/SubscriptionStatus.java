package com.lawencon.community.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_subscription_status")
public class SubscriptionStatus extends BaseEntity {

	private static final long serialVersionUID = -3366122230557102472L;

	@Column(name = "is_subscriber")
	private Boolean isSubscriber;

	@OneToOne
	@JoinColumn(name = "payment_id")
	private PaymentTransaction payment;

	@Column(name = "expired_at")
	private LocalDateTime expiredAt;

	public Boolean getIsSubscriber() {
		return isSubscriber;
	}

	public void setIsSubscriber(Boolean isSubscriber) {
		this.isSubscriber = isSubscriber;
	}

	public PaymentTransaction getPayment() {
		return payment;
	}

	public void setPayment(PaymentTransaction payment) {
		this.payment = payment;
	}

	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}

}
