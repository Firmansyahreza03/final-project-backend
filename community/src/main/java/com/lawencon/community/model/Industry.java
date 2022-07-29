package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.lawencon.base.BaseEntity;

@Entity
@Indexed
@Table(name = "comm_industry", uniqueConstraints = {
		@UniqueConstraint(
				name = "industry_code_bk", columnNames = { "industry_code" }
				) 
		}
)
public class Industry extends BaseEntity {

	private static final long serialVersionUID = 480630458937507031L;

	@FullTextField
	@Column(name = "industry_name", columnDefinition = "TEXT")
	private String industryName;

	@FullTextField
	@Column(name = "industry_code", columnDefinition = "TEXT")
	private String industryCode;

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

}
