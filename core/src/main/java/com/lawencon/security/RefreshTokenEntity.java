package com.lawencon.security;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "refresh_token")
public class RefreshTokenEntity extends BaseEntity {

	private static final long serialVersionUID = 4663269825742405548L;

	@Column(columnDefinition = "TEXT", nullable = false, unique = true, name = "access_token")
	private String token;

	@Column(nullable = false)
	private LocalDateTime expiredDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDateTime expiredDate) {
		this.expiredDate = expiredDate;
	}
}
