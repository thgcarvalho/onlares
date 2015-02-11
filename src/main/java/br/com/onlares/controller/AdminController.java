package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Usuario;

@Controller
public class AdminController {
	
	@SuppressWarnings("unused")
	private final UsuarioDao dao;
	@SuppressWarnings("unused")
	private final Validator validator;
	private final Result result;
	@SuppressWarnings("unused")
	private final UsuarioLogado usuarioLogado;
	
	@Inject
	public AdminController(UsuarioDao dao, Validator validator, Result result, UsuarioLogado usuarioLogado) {
		this.dao = dao;
		this.validator = validator;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}
	
	@Deprecated
	public AdminController() {
		this(null, null, null, null);
	}

	@Get("/admin/test")
	@Public
	public void test() { }

	@Post("/admin/test")
	@Public
	public void test(Usuario usuario) {
		result.redirectTo(HomeController.class).index();
	}
	
}
