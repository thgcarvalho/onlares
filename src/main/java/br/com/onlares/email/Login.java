//package br.com.onlares.email;
//
//import javax.mail.Authenticator;
//import javax.mail.PasswordAuthentication;
//
///**
// * @author Thiago Carvalho
// * 
// */
//public class Login extends Authenticator{
//	private String username = null;
//	private String password = null;
//
//	public Login(String usuario, String senha) {
//		this.username = usuario;
//		this.password = senha;
//	}
//
//	protected PasswordAuthentication getPasswordAuthentication() {
//		return new PasswordAuthentication(username, password);
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	
//}
