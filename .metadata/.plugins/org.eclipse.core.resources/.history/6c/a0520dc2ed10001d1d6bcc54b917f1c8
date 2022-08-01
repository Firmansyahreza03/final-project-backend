package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_polling_option")
public class PollingAnswer extends BaseEntity {
	private static final long serialVersionUID = -134301425904467360L;

	@OneToOne
	@JoinColumn(name = "option_id")
	private PollingOption option;

	public PollingOption getOption() {
		return option;
	}

	public void setOption(PollingOption option) {
		this.option = option;
	}

}