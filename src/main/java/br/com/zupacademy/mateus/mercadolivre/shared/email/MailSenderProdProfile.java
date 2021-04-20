package br.com.zupacademy.mateus.mercadolivre.shared.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Profile({ "prod", "test" })
@Component
public class MailSenderProdProfile implements MailSender {

	@Value("${spring.mail.username}")
	private static String fromUsername;
	
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void execute(String recipient, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromUsername);
		message.setTo(recipient);
		message.setSubject(subject);
		message.setText(body);
		emailSender.send(message);
	}
}
