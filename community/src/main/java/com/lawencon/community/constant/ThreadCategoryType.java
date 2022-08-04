package com.lawencon.community.constant;

public enum ThreadCategoryType {

	BASIC("CA-01"), POLLING("CA-02"), PREMIUM("CA-03");
	
	private String code;
	
	private ThreadCategoryType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
