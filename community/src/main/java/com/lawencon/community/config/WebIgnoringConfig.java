package com.lawencon.community.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


@Configuration
public class WebIgnoringConfig {
	private List<RequestMatcher> matchers = new ArrayList<RequestMatcher>();
	
	@Bean("webIgnoring")
	public List<RequestMatcher> antMatchers(){
		matchers.add(new AntPathRequestMatcher("/files/**", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/files/**", HttpMethod.POST.name()));
		matchers.add(new AntPathRequestMatcher("/files/**", HttpMethod.PUT.name()));

		matchers.add(new AntPathRequestMatcher("/users/**", HttpMethod.POST.name()));
        matchers.add(new AntPathRequestMatcher("/industries", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/swagger-ui/**", HttpMethod.GET.name()));
        matchers.add(new AntPathRequestMatcher("/v3/api-docs/**", HttpMethod.GET.name()));
        

        matchers.add(new AntPathRequestMatcher("/articles/**", HttpMethod.GET.name()));
        matchers.add(new AntPathRequestMatcher("/communities/**", HttpMethod.GET.name()));
        matchers.add(new AntPathRequestMatcher("/pollings/**", HttpMethod.GET.name()));
        matchers.add(new AntPathRequestMatcher("/thread-categories/**", HttpMethod.GET.name()));
        matchers.add(new AntPathRequestMatcher("/thread-comments/**", HttpMethod.GET.name()));
        matchers.add(new AntPathRequestMatcher("/threads/*", HttpMethod.GET.name()));
        matchers.add(new AntPathRequestMatcher("/threads/no-login", HttpMethod.GET.name()));
        matchers.add(new AntPathRequestMatcher("/users/user-id/**", HttpMethod.GET.name()));
        
        matchers.add(new AntPathRequestMatcher("/refresh-token/**", HttpMethod.POST.name()));
		return matchers;
	}
}
