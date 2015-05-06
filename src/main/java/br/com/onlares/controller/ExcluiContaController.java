package br.com.onlares.controller;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.ExcluiContaDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.ExcluiConta;
import br.com.onlares.model.Usuario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class ExcluiContaController {
	
	private final int TAMANHO_DO_CODIGO = 8;
	
	private final Mailer mailer;
	private final UsuarioDao usuarioDao;
	private final ExcluiContaDao excluiContaDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public ExcluiContaController(Mailer mailer, UsuarioDao usuarioDao, ExcluiContaDao excluiContaDao, 
			Validator validator, Result result) {
		this.mailer = mailer;
		this.usuarioDao = usuarioDao;
		this.excluiContaDao = excluiContaDao;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
	public ExcluiContaController() {
		this(null, null, null, null, null);
	}
	
	@Public
	@Get("/excluiConta")
	public void index() { 
	}
	
	@Public
	@Get("/excluiConta/codigo/{codigo}")
	public void codigo(String codigo) { 
		if (!checkValidPatern(codigo)) {
			validator.add(new SimpleMessage("excluiconta.codigo", "Código inválido!"));
			validator.onErrorUsePageOf(this).index();
		}
		
		ExcluiConta excluiConta = excluiContaDao.buscaPorCodigo(codigo);
		String emailDoUsuario = null;
		Usuario usuario = null;
		if (excluiConta == null) {
			validator.add(new SimpleMessage("excluiconta.codigo", "Código não encontrado!"));
			validator.onErrorUsePageOf(this).index();
		} 
		
		emailDoUsuario = excluiConta.getEmail();
		System.out.println("Código encotrado! - emailDoUsuario = " + emailDoUsuario);
		usuario = usuarioDao.buscaPorEmail(emailDoUsuario);
		if (usuario == null) {
			validator.add(new SimpleMessage("excluiconta.codigo", "Usuário não encontrado!"));
			validator.onErrorUsePageOf(this).index();
		}
		
		try {
			enviaSolicitacaoDeExclusaoPorEmail(emailDoUsuario, usuario);
		} catch(EmailException eExp) {
			eExp.printStackTrace();	
			validator.add(new SimpleMessage("excluiconta.email", "Erro ao enviar email de solicitação!"));
			result.redirectTo(this).index();
		}
		
		result.include("notice", "Sua conta será excluida no prazo de 24 horas.");
		result.redirectTo(this).index();
	}
	
	public void enviaSolicitacaoDeExclusaoPorEmail(String emailDoUsuario, Usuario usuario) throws EmailException {
		// TODO adicionar html content e context path
	    Email email = new SimpleEmail();
	    email.setSubject("Excluir Usuário!!!");
	    email.addTo(emailDoUsuario);
	    email.setMsg("Id:  " + usuario.getId() +
	    			 "Nome: " + usuario.getNome() +
	    			 "Email:" + usuario.getEmail());
	    mailer.send(email);
	}
	
	private boolean checkValidPatern(String codigo) {
		boolean valid = true;
		if (codigo == null || codigo.length() != TAMANHO_DO_CODIGO) {
			valid = false;
		}
		return valid;
	}
	
	@SuppressWarnings("unused")
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}
