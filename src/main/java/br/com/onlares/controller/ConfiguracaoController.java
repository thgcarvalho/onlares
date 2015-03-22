package br.com.onlares.controller;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.dao.UsuarioDao;

@Controller
public class ConfiguracaoController implements Serializable{

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final UsuarioDao usuarioDao;
	@SuppressWarnings("unused")
	private final UsuarioLogado usuarioLogado;
	@SuppressWarnings("unused")
	private final Validator validator;
	@SuppressWarnings("unused")
	private final Result result;

	@Inject
	public ConfiguracaoController(UsuarioDao usuarioDao, UsuarioLogado usuarioLogado, Validator validator, Result result) {
		this.usuarioDao = usuarioDao;
		this.usuarioLogado = usuarioLogado;
		this.validator = validator;
		this.result = result;
	}
	
	public ConfiguracaoController() {
		this(null, null, null, null);
	}
	
	@Get
	public void email() {
	}
	
	@Get
	public void notificacoes() {
	}
	
	@Get
	public void senha() {
	}
	
	@Get
	public void conta() {
	}
	
	

}
