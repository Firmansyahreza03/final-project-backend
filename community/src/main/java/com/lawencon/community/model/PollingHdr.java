package com.lawencon.community.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_polling_hdr")
public class PollingHdr extends BaseEntity {
	private static final long serialVersionUID = 1171432366136273732L;

	@Column(name = "polling_name", columnDefinition = "TEXT")
	private String pollingName;
	
	@Column(name = "expired_at")
	private LocalDateTime expiredAt;
	
	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}
}