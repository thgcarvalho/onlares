package br.com.onlares.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.MoradorDao;
import br.com.onlares.dao.UnidadeDao;
import br.com.onlares.model.Usuario;
import br.com.onlares.model.Unidade;

@Controller
public class AdminMoradorController {

	private final MoradorDao moradorDao;
	private final UnidadeDao unidadeDao;
	@SuppressWarnings("unused")
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminMoradorController(MoradorDao moradorDao, UnidadeDao unidadeDao, Validator validator, Result result) {
		this.moradorDao = moradorDao;
		this.unidadeDao = unidadeDao;
		this.validator = validator;
		this.result = result;
	}
	
	public AdminMoradorController() {
		this(null, null, null, null);
	}
	
	@Get
	public void lista() {
		result.include("moradorList", moradorDao.lista());
	}
	
	@Get
	public void novo() {
		result.include("unidadeList", unidadeDao.lista());
	}

	@Admin
	@Post
	public void adiciona(Usuario usuario) {
		System.out.println("Usuario.nome=" + usuario.getNome());
		System.out.println("Usuario.email=" + usuario.getEmail());
		moradorDao.adiciona(usuario);
		result.redirectTo(HomeController.class).index();
	}
}
