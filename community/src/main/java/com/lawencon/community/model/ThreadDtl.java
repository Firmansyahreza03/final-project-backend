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
@Table(name = "comm_thread_dtl")
public class ThreadDtl extends BaseEntity {

	private static final long serialVersionUID = 7910721000750756493L;

	@FullTextField
	@Column(name = "thread_comment", columnDefinition = "TEXT")
	private String threadComment;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

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

	public ThreadHdr getHdr() {
		return hdr;
	}

	public void setHdr(ThreadHdr hdr) {
		this.hdr = hdr;
	}

}
