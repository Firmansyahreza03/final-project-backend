package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;
import com.lawencon.security.RefreshTokenEntity;

@Entity
@Table(name = "comm_user", uniqueConstraints = { @UniqueConstraint(name = "email_bk", columnNames = { "user_email" }) })
public class User extends BaseEntity {

	private static final long serialVersionUID = -5365616900976826931L;

	@Column(name = "user_email", columnDefinition = "TEXT")
	private String userEmail;

	@Column(name = "user_password", columnDefinition = "TEXT")
	private String userPassword;

	@OneToOne
	@JoinColumn(name = "subscription_status_id")
	private SubscriptionStatus subscriptionStatus;

	@Column(name = "verfication_code", columnDefinition = "TEXT")
	private String verificationCode;

	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	@OneToOne
	@JoinColumn(name = "balance_id")
	private Balance balance;

	@OneToOne
	@JoinColumn(name = "refresh_token_id")
	private RefreshTokenEntity token;

	public RefreshTokenEntity getToken() {
		return token;
	}

	public void setToken(RefreshTokenEntity token) {
		this.token = token;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public SubscriptionStatus getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}
}
