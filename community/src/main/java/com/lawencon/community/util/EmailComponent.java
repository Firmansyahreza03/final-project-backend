package com.lawencon.community.util;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

@Component
public class EmailComponent {
	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private FreeMarkerConfigurer freemarkerConfigurer;

	public void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(htmlBody, true);
		mailSender.send(message);
	}

	public void sendMessageUsingFreemarkerTemplate(String to, String subject, Map<String, Object> templateModel)
			throws Exception {

		Template freemarkerTemplate = freemarkerConfigurer.getConfiguration().getTemplate("email.ftl");
		String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);

		sendHtmlMessage(to, subject, htmlBody);
	}
}