package com.asuprojects.pvconceitual.services.mail;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.asuprojects.pvconceitual.domain.Cliente;
import com.asuprojects.pvconceitual.domain.Pedido;

public abstract class AbstractMailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context thymeleafContext = new Context();
		thymeleafContext.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", thymeleafContext);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			e.printStackTrace();
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido Confirmado! Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true); //<-- segundo argumento true, apply content type "text/html" for an HTML mail
		return mimeMessage;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitacação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova Senha: " + newPass);
		return sm;
	}
}
