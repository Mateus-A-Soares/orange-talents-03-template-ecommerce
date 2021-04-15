package br.com.zupacademy.mateus.mercadolivre.shared.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default", "dev"})
@Component
public class MailSenderDevProfile implements MailSender {

	@Override
	public void execute(String recipient, String subject, String body) {
		System.out.println("to: " + recipient + ", subject: " + subject + ", message: " + body);
	}
}
