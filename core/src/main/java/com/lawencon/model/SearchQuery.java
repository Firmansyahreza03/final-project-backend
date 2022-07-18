package com.lawencon.model;

import java.util.List;

public class SearchQuery<T> {

	public SearchQuery() {
	}

	public SearchQuery(List<T> data, Integer count) {
		this.data = data;
		this.count = count;
	}

	private List<T> data;

	private Integer count;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
