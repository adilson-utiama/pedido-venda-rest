package com.asuprojects.pvconceitual.services.mail;

import org.springframework.mail.SimpleMailMessage;

import com.asuprojects.pvconceitual.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
