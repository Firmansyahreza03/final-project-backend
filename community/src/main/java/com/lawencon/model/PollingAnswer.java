package com.lawencon.model;

import javax.persistence.Column;
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
	private PollingOption pollingOption;
	
	@Column(name = "option_name", columnDefinition = "TEXT")
	private String optionName;

	public String getoptionName() {
		return optionName;
	}

	public void setoptionName(String optionName) {
		this.optionName = optionName;
	}

	public PollingOption getPollingOption() {
		return pollingOption;
	}

	public void setPollingOption(PollingOption pollingOption) {
		this.pollingOption = pollingOption;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	
}