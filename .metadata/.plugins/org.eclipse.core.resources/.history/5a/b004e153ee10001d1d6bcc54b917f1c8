package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "comm_thread_hdr", uniqueConstraints = {
		@UniqueConstraint(name = "thread_code_bk", columnNames = { "thread_code" }) })
public class ThreadHdr extends BaseEntity {

	private static final long serialVersionUID = 6710710220692651928L;

	@Column(name = "thread_name", columnDefinition = "TEXT")
	private String threadName;

	@Column(name = "thread_code", columnDefinition = "TEXT")
	private String threadCode;

	@Column(name = "thread_content", columnDefinition = "TEXT")
	private String threadContent;

	@Column(name = "is_premium")
	private Boolean isPremium;

	@OneToOne
	@JoinColumn(name = "polling_id")
	private PollingHdr polling;

	@OneToOne
	@JoinColumn(name = "category_id")
	private ThreadCategory category;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getThreadContent() {
		return threadContent;
	}

	public void setThreadContent(String threadContent) {
		this.threadContent = threadContent;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getThreadCode() {
		return threadCode;
	}

	public void setThreadCode(String threadCode) {
		this.threadCode = threadCode;
	}

	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}

	public PollingHdr getPolling() {
		return polling;
	}

	public void setPolling(PollingHdr polling) {
		this.polling = polling;
	}

	public ThreadCategory getCategory() {
		return category;
	}

	public void setCategory(ThreadCategory category) {
		this.category = category;
	}

}
