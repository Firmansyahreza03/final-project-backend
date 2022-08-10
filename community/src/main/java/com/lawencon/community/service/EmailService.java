package com.lawencon.community.service;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.lawencon.community.model.EmailDtl;

import freemarker.template.Template;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String sender;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Autowired
	private JavaMailSender mailSender;

	public String sendSimpleMail(EmailDtl details) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

			javaMailSender.send(mailMessage);
			return "Email berhasil dikirim...";
		}
		catch (Exception e) {
			return "Email gagal berhasil dikirim";
		}
	}
	
	private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(htmlBody, true);
		mailSender.send(message);
	}
	
	public void sendMessageUsingFreemarkerTemplate(String to, String subject, Map<String, Object> templateModel) throws Exception {
		Template freemarkerTemplate = freeMarkerConfigurer.getConfiguration().getTemplate("email.ftl");
		String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);
		
		sendHtmlMessage(to, subject, htmlBody);
	}
}
