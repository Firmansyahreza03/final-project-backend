package com.lawencon.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lawencon.util.VerificationCodeUtil.VerificationCodes;

/**
 * @author lawencon05
 */

@Configuration
public class GlobalConfiguration {

	@Bean(name = "verificationCodes")
	public Map<String, VerificationCodes> verificationCodes() {
		return new HashMap<>();
	}
	
}
