package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.lawencon.base.BaseEntity;

@Entity
@Indexed
@Table(name = "comm_polling_hdr")
public class PollingHdr extends BaseEntity {
	private static final long serialVersionUID = 1171432366136273732L;

	@FullTextField
	@Column(name = "polling_name", columnDefinition = "TEXT")
	private String pollingName;

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}
}