package com.asuprojects.pvconceitual.services.mail;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.asuprojects.pvconceitual.domain.Cliente;

public class MockEmailService extends AbstractMailService{
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de Email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado.");
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando envio de Email HTML...");
		LOG.info(msg.toString());
		LOG.info("Email enviado.");
		
	}

	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		LOG.info("Simulando envio de Email HTML...");
		LOG.info(cliente.toString() + " Nova Senha: " + newPass);
		LOG.info("Email enviado.");
		
	}

}
