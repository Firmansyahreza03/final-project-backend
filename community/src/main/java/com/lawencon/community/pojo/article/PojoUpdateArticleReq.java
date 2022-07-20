package com.lawencon.community.pojo.article;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PojoUpdateArticleReq {
	@NotNull(message = "Id cannot be empty")
	private String id;
	@NotNull(message = "Version cannot be empty")
	private Long version;
	@NotNull(message = "isActive cannot be empty")
	private Boolean isActive;

	@NotBlank(message = "title cannot be empty")
	private String articleTitle;
	@NotBlank(message = "content cannot be empty")
	private String articleContent;
	@NotBlank(message = "id user cannot be empty")
	private String user_id;
	@NotBlank(message = "id industry cannot be empty")
	private String industry_id;
	
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getIndustry_id() {
		return industry_id;
	}
	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}
}
