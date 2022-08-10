package com.lawencon.community.pojo.report;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PojoLimitTimeReq {
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "start time cannot be empty")
	private LocalDate startAt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "end time cannot be empty")
	private LocalDate endAt;

	public LocalDate getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDate startAt) {
		this.startAt = startAt;
	}

	public LocalDate getEndAt() {
		return endAt;
	}

	public void setEndAt(LocalDate endAt) {
		this.endAt = endAt;
	}

}
