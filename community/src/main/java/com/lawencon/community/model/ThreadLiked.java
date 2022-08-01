package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_thread_liked")
public class ThreadLiked extends BaseEntity {

	private static final long serialVersionUID = -1157920507506356453L;

	@OneToOne
	@JoinColumn(name = "hdr_id")
	private ThreadHdr hdr;

	public ThreadHdr getHdr() {
		return hdr;
	}

	public void setHdr(ThreadHdr hdr) {
		this.hdr = hdr;
	}

}
