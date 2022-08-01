package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.lawencon.base.BaseEntity;

@Entity
@Indexed
@Table(name = "comm_polling_option")
public class PollingOption extends BaseEntity {
	private static final long serialVersionUID = -134301425904467360L;

	@OneToOne
	@JoinColumn(name = "polling_hdr")
	private PollingHdr pollingHdr;

	@FullTextField
	@Column(name = "option_name", columnDefinition = "TEXT")
	private String optionName;

	public PollingHdr getPollingHdr() {
		return pollingHdr;
	}

	public void setPollingHdr(PollingHdr pollingHdr) {
		this.pollingHdr = pollingHdr;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
}