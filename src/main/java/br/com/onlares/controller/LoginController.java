package br.com.onlares.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.LocalizadorDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Localizador;
import br.com.onlares.model.Usuario;

@Controller
public class LoginController {
	
	private final UsuarioDao usuariodao;
	private final Validator validator;
	private final Result result;
	private final UsuarioLogado usuarioLogado;
	private final LocalizadorDao localizadorDao;
	
	@Inject
	public LoginController(UsuarioDao usuariodao, LocalizadorDao localizadorDao, Validator validator, Result result, UsuarioLogado usuarioLogado) {
		this.usuariodao = usuariodao;
		this.localizadorDao = localizadorDao;
		this.validator = validator;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}
	
	@Deprecated
	public LoginController() {
		this(null, null, null, null, null);
	}

	@Get("/login")
	@Public
	public void login() { }

	@Post("/auth")
	@Public
	public void auth(Usuario usuario) {
		try {
			if (!usuariodao.loginValido(usuario)) {
				validator.add(new I18nMessage("login", "login.invalido"));
				validator.onErrorUsePageOf(this).login();
			}
		} catch (Exception e) {
			e.printStackTrace();
			validator.add(new SimpleMessage("login", e.getMessage()));
			validator.onErrorUsePageOf(this).login();
		} 
		Usuario usuarioDB = usuariodao.buscaPorEmail(usuario.getEmail());
		List<Localizador> localizadores = localizadorDao.lista(usuarioDB.getId());
		usuarioLogado.setUsuario(usuarioDB);
		usuarioLogado.setLocalizadores(localizadores);
		result.redirectTo(HomeController.class).index();
	}
	
	@Get("/logout")
	public void sair() {
		usuarioLogado.logout();
		result.redirectTo(this).login();
	}

	public UsuarioLogado getUsuarioLogado() {
		return usuarioLogado;
	}
	
}
