package com.lawencon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(
				name = "forum_code_bk",
				columnNames = {"forum_code"})		
})
public class ForumHdr extends BaseEntity {

	private static final long serialVersionUID = 2890252614003219109L;

	@Column(name = "forum_title", columnDefinition = "TEXT")
	private String forumTitle;

	@Column(name = "forum_code", columnDefinition = "TEXT")
	private String forumCode;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}

	public String getForumCode() {
		return forumCode;
	}

	public void setForumCode(String forumCode) {
		this.forumCode = forumCode;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
