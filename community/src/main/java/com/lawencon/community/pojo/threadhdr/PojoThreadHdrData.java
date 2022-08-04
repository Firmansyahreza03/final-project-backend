package com.lawencon.community.pojo.threadhdr;

import java.time.LocalDateTime;

public class PojoThreadHdrData {

	private String id;
	private String threadName;
	private String threadCode;
	private String threadContent;
	private Boolean isPremium;
	private String pollingHdrsId;
	private String pollingName;
	private String categoryid;
	private String categoryName;
	private Boolean isActive;
	private Integer version;
	private String fileId;
	private String fileName;
	private String fileExt;
	private LocalDateTime createdAt;
	private String creatorName;
	private String photoProfileCreator;
	private Long counterLike;
	private Long countComment;
	private Boolean isLike;
	private Boolean isBookmark;

	public Boolean getIsLike() {
		return isLike;
	}

	public void setIsLike(Boolean isLike) {
		this.isLike = isLike;
	}

	public Boolean getIsBookmark() {
		return isBookmark;
	}

	public void setIsBookmark(Boolean isBookmark) {
		this.isBookmark = isBookmark;
	}

	public Long getCounterLike() {
		return counterLike;
	}

	public void setCounterLike(Long counterLike) {
		this.counterLike = counterLike;
	}

	public Long getCountComment() {
		return countComment;
	}

	public void setCountComment(Long countComment) {
		this.countComment = countComment;
	}

	public String getPhotoProfileCreator() {
		return photoProfileCreator;
	}

	public void setPhotoProfileCreator(String photoProfileCreator) {
		this.photoProfileCreator = photoProfileCreator;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getThreadContent() {
		return threadContent;
	}

	public void setThreadContent(String threadContent) {
		this.threadContent = threadContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public String getPollingHdrsId() {
		return pollingHdrsId;
	}

	public void setPollingHdrsId(String pollingHdrsId) {
		this.pollingHdrsId = pollingHdrsId;
	}

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
