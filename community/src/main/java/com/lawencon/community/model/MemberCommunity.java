package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_member_community")
public class MemberCommunity extends BaseEntity {

	private static final long serialVersionUID = 3371675803556409760L;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne
	@JoinColumn(name = "community_id")
	private Community community;

	@OneToOne
	@JoinColumn(name = "payment_id")
	private PaymentTransaction payment;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public PaymentTransaction getPayment() {
		return payment;
	}

	public void setPayment(PaymentTransaction payment) {
		this.payment = payment;
	}

}
