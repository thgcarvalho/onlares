package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Usuario;
import br.com.onlares.util.MD5Hashing;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class PrimeiroAcessoController {

	private final UsuarioDao dao;
	private final Validator validator;
	private final Result result;

	@Inject
	public PrimeiroAcessoController(UsuarioDao dao, Validator validator, Result result) {
		this.dao = dao;
		this.validator = validator;
		this.result = result;
	}
	
	public PrimeiroAcessoController() {
		this(null, null, null);
	}
	
	@Get("/registro")
	@Public
	public void registro() { }
	
	@Post("/registrar")
	@Public
	public void registrar(Usuario usuario) {
		System.out.println("registrar");
		if (checkNull(usuario.getEmail()).equals("")) {
			validator.add(new I18nMessage("egistro.email", "campo.obrigatorio", "Email"));
		}
		if (checkNull(usuario.getNome()).equals("")) {
			validator.add(new I18nMessage("registro.nome", "campo.obrigatorio", "Nome"));
		}
		if (checkNull(usuario.getSenha()).equals("")) {
			validator.add(new I18nMessage("registro.senha", "campo.obrigatorio", "Senha"));
		}
		validator.onErrorUsePageOf(this).registro();
		
		Usuario usuarioDB = null;
		try {
			usuarioDB = dao.buscaPorEmail(usuario.getEmail());
			
			if (usuarioDB == null) {
				validator.add(new SimpleMessage("Email não cadastrado", "Entre em contato com administrador do sistema"));
				validator.onErrorUsePageOf(this).registro();
			}
			if (!checkNull(usuarioDB.getSenha()).equals("")) {
				validator.add(new SimpleMessage("registro", "Usuário já registrado"));
				validator.onErrorUsePageOf(this).registro();
			}
			dao.registra(usuarioDB, usuario.getNome(), MD5Hashing.convertStringToMd5(usuario.getSenha()));
			result.include("notice", "Usuário registrado com sucesso!");
			result.redirectTo(LoginController.class).login();
		} catch (Exception e) {
			e.printStackTrace();
			validator.add(new SimpleMessage("registro", e.getMessage()));
			validator.onErrorUsePageOf(this).registro();
		} 
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}

}
