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
		try {
			if (!dao.loginValido(usuario)) {
				validator.add(new I18nMessage("registro", "login.invalido"));
				validator.onErrorUsePageOf(this).registro();
			}
		} catch (Exception e) {
			e.printStackTrace();
			validator.add(new SimpleMessage("registro", e.getMessage()));
			validator.onErrorUsePageOf(this).registro();
		} 
		//Usuario usuarioDB = dao.buscaPorEmail(usuario.getEmail());
		//usuarioLogado.setUsuario(usuarioDB);
		result.redirectTo(this).registro();
	}

}
