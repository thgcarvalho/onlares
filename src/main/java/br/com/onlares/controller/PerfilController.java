package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Usuario;

@Controller
public class PerfilController {

	private final UsuarioDao usuarioDao;
	@SuppressWarnings("unused")
	private final Validator validator;
	private final Result result;

	@Inject
	public PerfilController(UsuarioDao usuarioDao, Validator validator, Result result) {
		this.usuarioDao = usuarioDao;
		this.validator = validator;
		this.result = result;
	}
	
	public PerfilController() {
		this(null, null, null);
	}
	
	@Get("/perfil/visualiza/{email}")
	public void visualiza(String email) {
		Usuario usuario = usuarioDao.buscaPorEmail(email);
		if (usuario == null) {
			result.notFound();
		} else {
			result.include("usuario", usuario);
		}
	}
	
}
