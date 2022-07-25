package com.lawencon.util;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.lawencon.security.RefreshTokenEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private SecretKey secretKey;
	private final LocalDateTime expiredDateRefreshToken = LocalDateTime.now().plusWeeks(1);

	public JwtUtil() {
		secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}

	public String generateToken(Map<String, Object> claims, Duration duration) {
		LocalDateTime expiredDate = LocalDateTime.now().plusSeconds(duration.getSeconds());
		
		JwtBuilder jwtBuilder = Jwts.builder()
				.signWith(secretKey)
				.setExpiration(Timestamp.valueOf(expiredDate))
				.setClaims(claims);

		return jwtBuilder.compact();
	}

	public RefreshTokenEntity generateRefreshToken() {
		String uuid = UUID.randomUUID().toString();
		RefreshTokenEntity refreshToken = new RefreshTokenEntity();
		refreshToken.setToken(uuid);
		refreshToken.setExpiredDate(expiredDateRefreshToken);
		return refreshToken;
	}

	public Claims getClaims(String token) {
		Claims result = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();

		return result;
	}

	public enum ClaimKey {
		ID, ROLE
	}
}
