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
@Table(name = "comm_community_category", uniqueConstraints = {
		@UniqueConstraint(
				name = "community_category_code_bk", columnNames = { "category_code" }
				) 
		}
)
public class CommunityCategory extends BaseEntity {

	private static final long serialVersionUID = 7772057554439641789L;

	@FullTextField
	@Column(name = "category_name", columnDefinition = "TEXT")
	private String categoryName;

	@FullTextField
	@Column(name = "category_code", columnDefinition = "TEXT")
	private String categoryCode;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}
