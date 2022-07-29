package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.model.EmailDtl;
import com.lawencon.community.pojo.code.PojoCodeData;

@Service
public class CodeService {

	@Autowired
	private EmailService emailService;
	
	public PojoCodeData generateRandomCode(String email) {

		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890";

		StringBuilder sb = new StringBuilder(5);

		for (int i = 0; i < 5; i++) {
			int index = (int) (alphaNumericString.length() * Math.random());
			sb.append(alphaNumericString.charAt(index));
		}

		EmailDtl emailDtl = new EmailDtl();
		emailDtl.setMsgBody("CODE ANDA ADALAH : "+sb.toString());
		emailDtl.setRecipient(email);
		emailDtl.setSubject("CODE VERIFICATION");
		new Thread(() -> emailService.sendSimpleMail(emailDtl)).start();
		
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
