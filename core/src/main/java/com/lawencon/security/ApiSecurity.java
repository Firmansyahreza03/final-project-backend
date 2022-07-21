package com.lawencon.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthorizationFilter authorizationFilter;

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("webIgnoring")
	private List<RequestMatcher> antMatchers;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();

		http.addFilterAfter(authorizationFilter, BasicAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		antMatchers.forEach(matcher -> web.ignoring().requestMatchers(matcher));
	}

	@Bean("authManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
