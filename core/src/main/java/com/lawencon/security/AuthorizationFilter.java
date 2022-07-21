package com.lawencon.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.util.JwtUtil;
import com.lawencon.util.JwtUtil.ClaimKey;

import io.jsonwebtoken.Claims;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	@Qualifier("webIgnoring")
	private List<RequestMatcher> antMatchers;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		long count = antMatchers.stream().filter(matcher -> matcher.matches(request)).collect(Collectors.counting());
		if (count == 0 && !request.getRequestURI().equals("/login")) {
			String authorization = request.getHeader("Authorization");

			if (authorization == null || authorization.length() < 8 || !authorization.contains("Bearer")) {
				resError(response);
				return;
			}

			String token = authorization.replaceFirst("Bearer ", "");

			try {
				//getting claims data
				Claims claims = jwtUtil.getClaims(token);
				
				String id = claims.get(ClaimKey.ID.name()).toString();
				String role = claims.get(ClaimKey.ROLE.name()).toString();

				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				List<GrantedAuthority> authorities = Arrays.asList(authority);

				//register id and authority
				Authentication auth = new UsernamePasswordAuthenticationToken(id, null, authorities);
				SecurityContextHolder.getContext().setAuthentication(auth);

			} catch (Exception e) {
				resError(response);
				return;
			}

		}

		filterChain.doFilter(request, response);
	}

	private void resError(HttpServletResponse response) {
		try {
			response.setStatus(401);
			Map<String, Object> map = new HashMap<>();
			map.put("message", "Invalid token");
			response.getWriter().append(objectMapper.writeValueAsString(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
