//package br.com.onlares.email;
//
///**  
//* Copyright (c) 2015 GranDev - All rights reserved.
//* @author Thiago Carvalho - tcarvalho@grandev.com.br
//* 
//*/
//public class ConfirmarAlteracaoDeSenha {
//	
//	private String email; 
//	private String codigo;
//	
//	public ConfirmarAlteracaoDeSenha(String email, String codigo) {
//		this.email = email; 
//		this.codigo = codigo;
//	}
//	
//	public boolean enviar() {
//		String to = this.email;
//		String subject = subject();
//		StringBuffer message = message();
//		try {
//			Login login = new Login(EmailService.EMAIL_USER, EmailService.EMAIL_PASS);
//			SendEmail sendEmail = new SendEmail(EmailService.SMTP_SERVER, EmailService.SMTP_PORT);
//			sendEmail.setLog(false);
//			sendEmail.setDebug(false);
//			sendEmail.send(login, to, subject, message, SendEmail.CONTENT_TEXT_HTML, null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//	
//	public String subject() {
//		return "Solicitação de recuperação de senha";
//	}
//	
//	public StringBuffer message() {
//		StringBuffer message = new StringBuffer();
//		message = message.append("Clique no link para realizar a alteração: ");
//		message = message.append("<a href='http://localhost:8080/onlares/public/alterasenha.jsf?codigo=" + this.codigo + "'>Link de Altera��o</a>");
//		return message;
//	}
//
//}
