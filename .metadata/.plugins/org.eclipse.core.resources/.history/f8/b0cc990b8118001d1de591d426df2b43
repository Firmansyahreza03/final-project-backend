package com.lawencon.community.pojo.report;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PojoLimitTimeReq {
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "start time cannot be empty")
	private LocalDateTime startAt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "end time cannot be empty")
	private LocalDateTime endAt;
	
	public LocalDateTime getStartAt() {
		return startAt;
	}
	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}
	public LocalDateTime getEndAt() {
		return endAt;
	}
	public void setEndAt(LocalDateTime endAt) {
		this.endAt = endAt;
	}
	
	
}
