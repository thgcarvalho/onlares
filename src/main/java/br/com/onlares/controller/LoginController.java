package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Usuario;
import br.com.onlares.model.UsuarioLogado;

@Controller
public class LoginController {
	
	private final UsuarioDao dao;
	private final Validator validator;
	private final Result result;
	private final UsuarioLogado usuarioLogado;
	
	@Inject
	public LoginController(UsuarioDao dao, Validator validator, Result result, UsuarioLogado usuarioLogado) {
		this.dao = dao;
		this.validator = validator;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}
	
	public LoginController() {
		this(null, null, null, null);
	}

	@Get
	@Public
	public void form() {

	}

	@Post
	@Public
	public void auth(Usuario usuario) {
		System.out.println("Usuario Existe? " + dao.existe(usuario));
		if (!dao.existe(usuario)) {
			validator.add(new I18nMessage("login", "login.invalido"));
			validator.onErrorUsePageOf(this).form();
		} 
		usuarioLogado.setUsuario(usuario);
		result.redirectTo(PagesController.class).index();
	}

}
