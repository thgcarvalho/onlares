package br.com.onlares.controller;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.AlteraSenhaDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.AlteraSenha;
import br.com.onlares.model.Usuario;
import br.com.onlares.service.GeradorDeCodigo;
import br.com.onlares.util.MD5Hashing;
import br.com.onlares.util.Status;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AlteraSenhaController {
	
	private final int TAMANHO_DO_CODIGO = 8;
	
	private final Mailer mailer;
	private final Environment environment;
	private final UsuarioDao usuarioDao;
	private final AlteraSenhaDao alteraSenhaDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AlteraSenhaController(Mailer mailer, Environment environment, UsuarioDao usuarioDao, AlteraSenhaDao alteraSenhaDao, 
			Validator validator, Result result) {
		this.mailer = mailer;
		this.environment = environment;
		this.usuarioDao = usuarioDao;
		this.alteraSenhaDao = alteraSenhaDao;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
	public AlteraSenhaController() {
		this(null, null, null, null, null, null);
	}
	
	@Get("/esqueci")
	@Public
	public void esqueci() { }
	
	@Public
	@Post("/alteraSenha/enviaCodigo")
    public void enviaCodigo(String emailDoUsuario) {
		if (checkNull(emailDoUsuario).equals("")) {
			validator.add(new I18nMessage("usuario.adiciona", "campo.obrigatorio", "Email"));
			validator.onErrorUsePageOf(this).esqueci();
		}
		Usuario usuario = usuarioDao.buscaPorEmail(emailDoUsuario);
		System.out.println("Usuario " + usuario);
		if (usuario == null) {
			validator.add(new SimpleMessage("alterasenha.solicita", "Email não cadastrado!"));
			validator.onErrorUsePageOf(this).esqueci();
		}
		
		GeradorDeCodigo geradorDeCodigo = new GeradorDeCodigo();
		String codigo = geradorDeCodigo.gerar(TAMANHO_DO_CODIGO);
		// Salva novo registro 
		AlteraSenha alteraSenha = new AlteraSenha();
		alteraSenha.setCodigo(codigo);
		alteraSenha.setEmail(emailDoUsuario);
		alteraSenha.setStatus(Status.PENDENTE.getCodigo());
		alteraSenhaDao.adiciona(alteraSenha);
		// Envia email
		try {
			// TODO adicionar html content e context path
	        Email email = new SimpleEmail();
	        email.setSubject("Instruções para Nova Senha");
	        email.addTo(emailDoUsuario);
	        email.setMsg("Clique no <a>link</a> para realizar a alteração: "
	        		+ environment.get("context") + "alteraSenha/codigo/" + codigo );
	        // email.setMsg(emailTemplate());
	        mailer.send(email);
		} catch(EmailException eExp) {
			eExp.printStackTrace();	
			validator.add(new SimpleMessage("alterasenha.solicita", "Erro ao enviar email!"));
			validator.onErrorUsePageOf(this).esqueci();
		}
		
        result.include("notice", "Um email com as instruções foi enviado para " + emailDoUsuario);
		result.redirectTo(this).esqueci();
    }
	
	@SuppressWarnings("unused")
	private String emailTemplate(){
		return "<!doctype html>"
		+ "<html xmlns='http://www.w3.org/1999/xhtml'>"
		+ "<head>"
		+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
		+ "<meta name='viewport' content='initial-scale=1.0' />"
		+ "<meta name='format-detection' content='telephone=no' />"
		+ "<title></title>"
		+ "<style type='text/css'>"
		+ "	body {"
		+ "		width: 100%;"
		+ "		margin: 0;"
		+ "		padding: 0;"
		+ "		-webkit-font-smoothing: antialiased;"
		+ "	}"
		+ "	@media only screen and (max-width: 600px) {"
		+ "		table[class='table-row'] {"
		+ "			float: none !important;"
		+ "			width: 98% !important;"
		+ "			padding-left: 20px !important;"
		+ "			padding-right: 20px !important;"
		+ "		}"
		+ "		table[class='table-row-fixed'] {"
		+ "			float: none !important;"
		+ "			width: 98% !important;"
		+ "		}"
		+ "		table[class='table-col'], table[class='table-col-border'] {"
		+ "			float: none !important;"
		+ "			width: 100% !important;"
		+ "			padding-left: 0 !important;"
		+ "			padding-right: 0 !important;"
		+ "			table-layout: fixed;"
		+ "		}"
		+ "		td[class='table-col-td'] {"
		+ "			width: 100% !important;"
		+ "		}"
		+ "		table[class='table-col-border'] + table[class='table-col-border'] {"
		+ "			padding-top: 12px;"
		+ "			margin-top: 12px;"
		+ "			border-top: 1px solid #E8E8E8;"
		+ "		}"
		+ "		table[class='table-col'] + table[class='table-col'] {"
		+ "			margin-top: 15px;"
		+ "		}"
		+ "		td[class='table-row-td'] {"
		+ "			padding-left: 0 !important;"
		+ "			padding-right: 0 !important;"
		+ "		}"
		+ "		table[class='navbar-row'] , td[class='navbar-row-td'] {"
		+ "			width: 100% !important;"
		+ "		}"
		+ "		img {"
		+ "			max-width: 100% !important;"
		+ "			display: inline !important;"
		+ "		}"
		+ "		img[class='pull-right'] {"
		+ "			float: right;"
		+ "			margin-left: 11px;"
		+ "         max-width: 125px !important;"
		+ "			padding-bottom: 0 !important;"
		+ "		}"
		+ "		img[class='pull-left'] {"
		+ "			float: left;"
		+ "			margin-right: 11px;"
		+ "			max-width: 125px !important;"
		+ "			padding-bottom: 0 !important;"
		+ "		}"
		+ "		table[class='table-space'], table[class='header-row'] {"
		+ "			float: none !important;"
		+ "			width: 98% !important;"
		+ "		}"
		+ "		td[class='header-row-td'] {"
		+ "			width: 100% !important;"
		+ "		}"
		+ "	}"
		+ "	@media only screen and (max-width: 480px) {"
		+ "		table[class='table-row'] {"
		+ "			padding-left: 16px !important;"
		+ "			padding-right: 16px !important;"
		+ "		}"
		+ "	}"
		+ "	@media only screen and (max-width: 320px) {"
		+ "		table[class='table-row'] {"
		+ "			padding-left: 12px !important;"
		+ "			padding-right: 12px !important;"
		+ "		}"
		+ "	}"
		+ "	@media only screen and (max-width: 458px) {"
		+ "		td[class='table-td-wrap'] {"
		+ "			width: 100% !important;"
		+ "		}"
		+ "	}"
		+ " </style>"
		+ " </head>"
		+ " <body style='font-family: Arial, sans-serif; font-size:13px; color: #444444; min-height: 200px;' bgcolor='#E4E6E9' leftmargin='0' topmargin='0' marginheight='0' marginwidth='0'>"
		+ " <table width='100%' height='100%' bgcolor='#E4E6E9' cellspacing='0' cellpadding='0' border='0'>"
		+ " <tr><td width='100%' align='center' valign='top' bgcolor='#E4E6E9' style='background-color:#E4E6E9; min-height: 200px;'>"
		+ "<table><tr><td class='table-td-wrap' align='center' width='458'><table class='table-space' height='18' style='height: 18px; font-size: 0px; line-height: 0; width: 450px; background-color: #e4e6e9;' width='450' bgcolor='#E4E6E9' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-space-td' valign='middle' height='18' style='height: 18px; width: 450px; background-color: #e4e6e9;' width='450' bgcolor='#E4E6E9' align='left'>&nbsp;</td></tr></tbody></table>"
		+ "<table class='table-space' height='8' style='height: 8px; font-size: 0px; line-height: 0; width: 450px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-space-td' valign='middle' height='8' style='height: 8px; width: 450px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' align='left'>&nbsp;</td></tr></tbody></table>"
		+ ""
		+ "<table class='table-row' width='450' bgcolor='#FFFFFF' style='table-layout: fixed; background-color: #ffffff;' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-row-td' style='font-family: Arial, sans-serif; line-height: 19px; color: #444444; font-size: 13px; font-weight: normal; padding-left: 36px; padding-right: 36px;' valign='top' align='left'>"
		+ "  <table class='table-col' align='left' width='378' cellspacing='0' cellpadding='0' border='0' style='table-layout: fixed;'><tbody><tr><td class='table-col-td' width='378' style='font-family: Arial, sans-serif; line-height: 19px; color: #444444; font-size: 13px; font-weight: normal; width: 378px;' valign='top' align='left'>"
		+ "    <table class='header-row' width='378' cellspacing='0' cellpadding='0' border='0' style='table-layout: fixed;'><tbody><tr><td class='header-row-td' width='378' style='font-family: Arial, sans-serif; font-weight: normal; line-height: 19px; color: #478fca; margin: 0px; font-size: 18px; padding-bottom: 10px; padding-top: 15px;' valign='top' align='left'>Thank you for signing up!</td></tr></tbody></table>"
		+ "    <div style='font-family: Arial, sans-serif; line-height: 20px; color: #444444; font-size: 13px;'>"
		+ "      <b style='color: #777777;'>We are excited to have you join us in Ace community</b>"
		+ "      <br>"
		+ "      Please confirm your registration to continue"
		+ "    </div>"
		+ "  </td></tr></tbody></table>"
		+ "</td></tr></tbody></table>"
		+ ""
		+ "<table class='table-space' height='12' style='height: 12px; font-size: 0px; line-height: 0; width: 450px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-space-td' valign='middle' height='12' style='height: 12px; width: 450px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' align='left'>&nbsp;</td></tr></tbody></table>"
		+ "<table class='table-space' height='12' style='height: 12px; font-size: 0px; line-height: 0; width: 450px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-space-td' valign='middle' height='12' style='height: 12px; width: 450px; padding-left: 16px; padding-right: 16px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' align='center'>&nbsp;<table bgcolor='#E8E8E8' height='0' width='100%' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td bgcolor='#E8E8E8' height='1' width='100%' style='height: 1px; font-size:0;' valign='top' align='left'>&nbsp;</td></tr></tbody></table></td></tr></tbody></table>"
		+ "<table class='table-space' height='16' style='height: 16px; font-size: 0px; line-height: 0; width: 450px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-space-td' valign='middle' height='16' style='height: 16px; width: 450px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' align='left'>&nbsp;</td></tr></tbody></table>"
		+ ""
		+ "<table class='table-row' width='450' bgcolor='#FFFFFF' style='table-layout: fixed; background-color: #ffffff;' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-row-td' style='font-family: Arial, sans-serif; line-height: 19px; color: #444444; font-size: 13px; font-weight: normal; padding-left: 36px; padding-right: 36px;' valign='top' align='left'>"
		+ " <table class='table-col' align='left' width='378' cellspacing='0' cellpadding='0' border='0' style='table-layout: fixed;'><tbody><tr><td class='table-col-td' width='378' style='font-family: Arial, sans-serif; line-height: 19px; color: #444444; font-size: 13px; font-weight: normal; width: 378px;' valign='top' align='left'>"
		+ "   <div style='font-family: Arial, sans-serif; line-height: 19px; color: #444444; font-size: 13px; text-align: center;'>"
		+ "      <a href='#' style='color: #ffffff; text-decoration: none; margin: 0px; text-align: center; vertical-align: baseline; border: 4px solid #6fb3e0; padding: 4px 9px; font-size: 15px; line-height: 21px; background-color: #6fb3e0;'>&nbsp; Confirm &nbsp;</a>"
		+ "   </div>"
		+ "   <table class='table-space' height='16' style='height: 16px; font-size: 0px; line-height: 0; width: 378px; background-color: #ffffff;' width='378' bgcolor='#FFFFFF' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-space-td' valign='middle' height='16' style='height: 16px; width: 378px; background-color: #ffffff;' width='378' bgcolor='#FFFFFF' align='left'>&nbsp;</td></tr></tbody></table>"
		+ "  </td></tr></tbody></table>"
		+ "</td></tr></tbody></table>"
		+ ""
		+ "<table class='table-space' height='6' style='height: 6px; font-size: 0px; line-height: 0; width: 450px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-space-td' valign='middle' height='6' style='height: 6px; width: 450px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' align='left'>&nbsp;</td></tr></tbody></table>"
		+ ""
		+ "<table class='table-row-fixed' width='450' bgcolor='#FFFFFF' style='table-layout: fixed; background-color: #ffffff;' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-row-fixed-td' style='font-family: Arial, sans-serif; line-height: 19px; color: #444444; font-size: 13px; font-weight: normal; padding-left: 1px; padding-right: 1px;' valign='top' align='left'>"
		+ "  <table class='table-col' align='left' width='448' cellspacing='0' cellpadding='0' border='0' style='table-layout: fixed;'><tbody><tr><td class='table-col-td' width='448' style='font-family: Arial, sans-serif; line-height: 19px; color: #444444; font-size: 13px; font-weight: normal;' valign='top' align='left'>"
		+ "    <table width='100%' cellspacing='0' cellpadding='0' border='0' style='table-layout: fixed;'><tbody><tr><td width='100%' align='center' bgcolor='#f5f5f5' style='font-family: Arial, sans-serif; line-height: 24px; color: #bbbbbb; font-size: 13px; font-weight: normal; text-align: center; padding: 9px; border-width: 1px 0px 0px; border-style: solid; border-color: #e3e3e3; background-color: #f5f5f5;' valign='top'>"
		+ "      <a href='#' style='color: #428bca; text-decoration: none; background-color: transparent;'>Ace &copy; 2014</a>"
		+ "      <br>"
		+ "      <a href='#' style='color: #478fca; text-decoration: none; background-color: transparent;'>twitter</a>"
		+ "      ."
		+ "      <a href='#' style='color: #5b7a91; text-decoration: none; background-color: transparent;'>facebook</a>"
		+ "      ."
		+ "      <a href='#' style='color: #dd5a43; text-decoration: none; background-color: transparent;'>google+</a>"
		+ "    </td></tr></tbody></table>"
		+ "  </td></tr></tbody></table>"
		+ "</td></tr></tbody></table>"
		+ "<table class='table-space' height='1' style='height: 1px; font-size: 0px; line-height: 0; width: 450px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-space-td' valign='middle' height='1' style='height: 1px; width: 450px; background-color: #ffffff;' width='450' bgcolor='#FFFFFF' align='left'>&nbsp;</td></tr></tbody></table>"
		+ "<table class='table-space' height='36' style='height: 36px; font-size: 0px; line-height: 0; width: 450px; background-color: #e4e6e9;' width='450' bgcolor='#E4E6E9' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td class='table-space-td' valign='middle' height='36' style='height: 36px; width: 450px; background-color: #e4e6e9;' width='450' bgcolor='#E4E6E9' align='left'>&nbsp;</td></tr></tbody></table></td></tr></table>"
		+ "</td></tr>"
		+ " </table>"
		+ " </body>"
		+ " </html>";
	}
	
	@Public
	@Get("/alteraSenha")
	public void index() { 
	}
	
	@Public
	@Get("/alteraSenha/codigo/{codigo}")
	public void codigo(String codigo) { 
		if (!checkValidPatern(codigo)) {
			validator.add(new SimpleMessage("alterasenha.codigo", "Código inválido!"));
			validator.onErrorUsePageOf(this).index();
		}
		result.forwardTo(this).form(codigo);
	}
	
	@Public
	@Get("/alteraSenha/form")
	public void form(String codigo) { 
		result.include("codigo", codigo);
	}

	@Public
	@Post("/alteraSenha/altera")
	public void altera(AlteraSenha alteraSenha) {
		if (alteraSenha.getCodigo() == null) {
			validator.add(new SimpleMessage("alterasenha.altera", "Código inválido!"));
			validator.onErrorUsePageOf(this).index();
		}

		String codigo = alteraSenha.getCodigo();
		String novaSenha = alteraSenha.getNovaSenha();
		String confirmacaoDeNovaSenha = alteraSenha.getConfirmacaoDeNovaSenha();
		
		if (!novaSenha.equals(confirmacaoDeNovaSenha)) {
			result.include("codigo", codigo);
			validator.add(new SimpleMessage("alterasenha.altera", "Senhas não conferem!"));
			validator.onErrorUsePageOf(this).form(codigo);
		}
		
		AlteraSenha alteraSenhaDB;
		Usuario usuarioDB;
		String email;
		try {
			alteraSenhaDB = alteraSenhaDao.buscaPorCodigo(codigo);
			if (alteraSenhaDB == null) {
				result.include("codigo", codigo);
				validator.add(new SimpleMessage("alterasenha.altera", "Código não localizado!"));
				validator.onErrorUsePageOf(this).form(codigo);
			} else if (alteraSenhaDB.getStatus().equals("1")){
				result.include("codigo", codigo);
				validator.add(new SimpleMessage("alterasenha.altera", "Solicitação já realizada!"));
				validator.onErrorUsePageOf(this).form(codigo);
			} else {
				email = alteraSenhaDB.getEmail();
				usuarioDB = usuarioDao.buscaPorEmail(email);
				// Atualiza usuario
				usuarioDB.setSenha(MD5Hashing.convertStringToMd5(novaSenha));
				usuarioDao.altera(usuarioDB);
				// Atualiza alteraSenha
				alteraSenhaDB.setStatus(Status.ALTERADO.getCodigo());
				alteraSenhaDao.edita(alteraSenhaDB);
			}
		} catch (Exception e) {
			result.include("codigo", codigo);
			validator.add(new SimpleMessage("alterasenha.altera", "Solicitação inválida!"));
			validator.onErrorUsePageOf(this).form(codigo);
		}
		
		result.include("notice", "Senha alterada com sucesso!");
		result.redirectTo(LoginController.class).login();
	}
	
	private boolean checkValidPatern(String codigo) {
		boolean valid = true;
		if (codigo == null || codigo.length() != TAMANHO_DO_CODIGO) {
			valid = false;
		}
		return valid;
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}
