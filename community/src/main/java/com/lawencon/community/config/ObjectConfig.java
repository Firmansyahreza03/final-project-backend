package com.lawencon.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;

@Configuration
public class ObjectConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods(
						HttpMethod.DELETE.name(), HttpMethod.PUT.name(), HttpMethod.POST.name(), HttpMethod.GET.name());

			}
		};
	}
	
	@Bean
	public FreeMarkerConfigurer freemarkerClassLoaderConfig() {
		freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_27);
		TemplateLoader templateLoader = new ClassTemplateLoader(this.getClass(), "/mail-template");
		configuration.setTemplateLoader(templateLoader);
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.setConfiguration(configuration);
		return freeMarkerConfigurer;
	}
}
