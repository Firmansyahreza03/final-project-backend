package com.lawencon.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.community.pojo.code.PojoCodeData;

@Service
public class CodeService {
	
	public PojoCodeData generateRandomCode() {

		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890";

		StringBuilder sb = new StringBuilder(5);

		for (int i = 0; i < 5; i++) {
			int index = (int) (alphaNumericString.length() * Math.random());
			sb.append(alphaNumericString.charAt(index));
		}
		
		PojoCodeData data = new PojoCodeData();
		data.setCode(sb.toString());
		return data;
	}
	
	public PojoCodeData generateRandomCodeAll() {

		String numericString = "1234567890";

		StringBuilder sb = new StringBuilder(5);

		for (int i = 0; i < 5; i++) {
			int index = (int) (numericString.length() * Math.random());
			sb.append(numericString.charAt(index));
		}
		PojoCodeData data = new PojoCodeData();
		data.setCode(sb.toString());
		return data;
	}
}
