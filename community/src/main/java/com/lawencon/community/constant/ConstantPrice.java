package com.lawencon.community.constant;

public enum ConstantPrice {

	BASIC(50000);
	
	private Integer price;
	
	private ConstantPrice(Integer price) {
		this.price = price;
	}

	public Integer getPrice() {
		return price;
	}
}
