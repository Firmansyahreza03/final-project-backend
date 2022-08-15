package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_article")
public class Article extends BaseEntity {

	private static final long serialVersionUID = -4406344676030726269L;

	@Column(name = "article_title", columnDefinition = "TEXT")
	private String articleTitle;

	@Column(name = "article_content", columnDefinition = "TEXT")
	private String articleContent;

	@OneToOne
	@JoinColumn(name = "industry_id")
	private Industry industry;

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

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
}
