package com.asuprojects.pvconceitual.services.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.asuprojects.pvconceitual.domain.Pedido;

public interface EmailService {

	//Envio de email em texto plano
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);

	//Envio de email em HTML
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	void sendHtmlEmail(MimeMessage msg);
}
