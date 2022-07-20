package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_bookmark")
public class Bookmark extends BaseEntity {

	private static final long serialVersionUID = -7082603208744824780L;

	@OneToOne
	@JoinColumn(name = "thread_hdr_id")
	private ThreadHdr threadHdr;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public ThreadHdr getThreadHdr() {
		return threadHdr;
	}

	public void setThreadHdr(ThreadHdr threadHdr) {
		this.threadHdr = threadHdr;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
