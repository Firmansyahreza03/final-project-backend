package com.lawencon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_forum_dtl")
public class ForumDtl extends BaseEntity {

	private static final long serialVersionUID = 3353683076476579201L;

	@Column(name = "chat", columnDefinition = "TEXT")
	private String chat;

	@OneToOne
	@JoinColumn(name = "header_id")
	private ForumHdr header;

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	public ForumHdr getHeader() {
		return header;
	}

	public void setHeader(ForumHdr header) {
		this.header = header;
	}

}
