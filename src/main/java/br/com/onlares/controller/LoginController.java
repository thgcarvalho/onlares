package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Usuario;

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
	
	@Deprecated
	public LoginController() {
		this(null, null, null, null);
	}

	@Get("/login")
	@Public
	public void login() { }

	@Post("/auth")
	@Public
	public void auth(Usuario usuario) {
		try {
			if (!dao.loginValido(usuario)) {
				validator.add(new I18nMessage("login", "login.invalido"));
				validator.onErrorUsePageOf(this).login();
			}
		} catch (Exception e) {
			e.printStackTrace();
			validator.add(new SimpleMessage("login", e.getMessage(), Severity.ERROR));
			validator.onErrorUsePageOf(this).login();
		} 
		Usuario usuarioDB = dao.buscaPorEmail(usuario.getEmail());
		usuarioLogado.setUsuario(usuarioDB);
		result.redirectTo(HomeController.class).index();
	}
	
	@Get("/registro")
	@Public
	public void registro() { }
	
	@Get("/esqueci")
	@Public
	public void esqueci() { }
	
	@Get("/logout")
	public void sair() {
		usuarioLogado.logout();
		result.redirectTo(this).login();
	}

	public UsuarioLogado getUsuarioLogado() {
		return usuarioLogado;
	}

}
