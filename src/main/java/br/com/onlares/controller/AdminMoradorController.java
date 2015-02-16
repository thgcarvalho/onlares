package br.com.onlares.controller;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.UnidadeDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Usuario;

@Controller
public class AdminMoradorController {

	private final UsuarioDao usuarioDao;
	private final UnidadeDao unidadeDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminMoradorController(UsuarioDao usuarioDao, UnidadeDao unidadeDao, Validator validator, Result result) {
		this.usuarioDao = usuarioDao;
		this.unidadeDao = unidadeDao;
		this.validator = validator;
		this.result = result;
	}
	
	public AdminMoradorController() {
		this(null, null, null, null);
	}
	
	@Admin
	@Get
	public void lista() {
		result.include("moradorList", usuarioDao.lista());
	}
	
	@Admin
	@Get
	public void novo() {
		result.include("unidadeList", unidadeDao.lista());
	}

	@Admin
	@Post
	public void adiciona(Usuario usuario) throws NoSuchAlgorithmException {
		System.out.println("Usuario.nome=" + usuario.getNome());
		System.out.println("Usuario.email=" + usuario.getEmail());
		System.out.println("Usuario.fone1=" + usuario.getFone1());
		System.out.println("Usuario.fone2=" + usuario.getFone2());

		if (usuarioDao.existe(usuario)) {
			validator.add(new SimpleMessage("usuario.adiciona", "Email já cadastrado", Severity.ERROR));
			validator.onErrorUsePageOf(this).novo();
		}
		if (usuario.getUnidade() == null) {
			validator.add(new SimpleMessage("usuario.adiciona", "Selecione a unidade", Severity.ERROR));
			validator.onErrorUsePageOf(this).novo();
		}
		System.out.println("Usuario.unidade.id=" + usuario.getUnidade().getId());
		System.out.println("Usuario.unidade.localizacao=" + usuario.getUnidade().getLocalizacao());
		
		usuarioDao.adiciona(usuario);
		result.redirectTo(this).lista();
	}
}
