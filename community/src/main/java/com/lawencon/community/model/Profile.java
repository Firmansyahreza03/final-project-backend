package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_profile")
public class Profile extends BaseEntity {

	private static final long serialVersionUID = -8968030431783184704L;

	@Column(name = "full_name", columnDefinition = "TEXT")
	private String fullName;

	@Column(name = "company_name", columnDefinition = "TEXT")
	private String companyName;

	@Column(name = "position_name", columnDefinition = "TEXT")
	private String positionName;

	@OneToOne
	@JoinColumn(name = "industry_id")
	private Industry industry;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
