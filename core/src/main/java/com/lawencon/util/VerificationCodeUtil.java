package com.lawencon.util;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author lawencon05
 */

@Component
public class VerificationCodeUtil {

	@Autowired
	@Qualifier("verificationCodes")
	private Map<String, VerificationCodes> verificationCodes;

	private final LocalDateTime expiredDate = LocalDateTime.now().plusDays(1);

	public void addVerificationCode(String email, String verificationCode) {
		verificationCodes.put(email, new VerificationCodes(verificationCode, expiredDate));
	}

	public void validateVerificationCode(String email, String verificationCode) {
		if (verificationCodes.containsKey(email)) {
			if (verificationCodes.get(email).getCode().equals(verificationCode)) {
				if (verificationCodes.get(email).getExpiredDate().compareTo(LocalDateTime.now()) < 0) {
					throw new InvalidVerificationCodeException("Expired Verification Code");
				}
				verificationCodes.remove(email);
			} else {
				throw new InvalidVerificationCodeException("Invalid Verification Code");
			}
		} else {
			throw new InvalidVerificationCodeException("Invalid Verification Code");
		}
	}

	public class VerificationCodes {
		private String code;
		private LocalDateTime expiredDate;

		public VerificationCodes(String code, LocalDateTime expiredDate) {
			this.code = code;
			this.expiredDate = expiredDate;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public LocalDateTime getExpiredDate() {
			return expiredDate;
		}

		public void setExpiredDate(LocalDateTime expiredDate) {
			this.expiredDate = expiredDate;
		}
	}

	public class InvalidVerificationCodeException extends RuntimeException {

		private static final long serialVersionUID = 7700588143021812902L;

		public InvalidVerificationCodeException(String message) {
			super(message);
		}

	}
}
