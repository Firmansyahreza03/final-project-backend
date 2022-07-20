package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_thread_dtl")
public class ThreadDtl extends BaseEntity {

	private static final long serialVersionUID = 7910721000750756493L;

	@Column(name = "thread_comment", columnDefinition = "TEXT")
	private String threadComment;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "is_liked")
	private Boolean isLiked;

	@OneToOne
	@JoinColumn(name = "hdr_id")
	private ThreadHdr hdr;

	public String getThreadComment() {
		return threadComment;
	}

	public void setThreadComment(String threadComment) {
		this.threadComment = threadComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}

	public ThreadHdr getHdr() {
		return hdr;
	}

	public void setHdr(ThreadHdr hdr) {
		this.hdr = hdr;
	}

}
