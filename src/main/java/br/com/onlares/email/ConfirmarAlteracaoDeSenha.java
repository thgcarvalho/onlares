//package br.com.onlares.email;
//
//import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpServletRequest;
//
//import br.com.onlares.service.EmailService;
//
//
///**
// * @author Thiago Carvalho
// * 
// */
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
//		return "Solicita��o de recupera��o de senha";
//	}
//	
//	public StringBuffer message() {
//		StringBuffer message = new StringBuffer();
//		FacesContext ctx = FacesContext.getCurrentInstance();
//		HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
//		System.out.println(ctx.getExternalContext().getRequestContextPath());
//		System.out.println(req.getContextPath());
//		
//		
//		FacesContext context = FacesContext.getCurrentInstance();
//		String appPath = context.getExternalContext().getRealPath("");
//		System.out.println(appPath);
//		
//		message = message.append("Clique no link para realizar a alteração: ");
//		message = message.append("<a href='http://localhost:8080/onlares/public/alterasenha.jsf?codigo=" + this.codigo + "'>Link de Altera��o</a>");
//		return message;
//	}
//
//}
