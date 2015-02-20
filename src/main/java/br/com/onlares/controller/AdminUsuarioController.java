package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.UnidadeDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Usuario;

@Controller
public class AdminUsuarioController {

	private final UsuarioDao usuarioDao;
	private final UnidadeDao unidadeDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminUsuarioController(UsuarioDao usuarioDao, UnidadeDao unidadeDao, Validator validator, Result result) {
		this.usuarioDao = usuarioDao;
		this.unidadeDao = unidadeDao;
		this.validator = validator;
		this.result = result;
	}
	
	public AdminUsuarioController() {
		this(null, null, null, null);
	}
	
	@Admin
	@Get("/adminUsuario/lista")
	public void lista() {
		result.include("usuarioList", usuarioDao.lista());
	}
	
	@Admin
	@Get("/adminUsuario/novo")
	public void novo() {
		result.include("unidadeList", unidadeDao.lista());
	}

	@Admin
	@Post("/adminUsuario")
	public void adiciona(Usuario usuario) {
		if (usuarioDao.existe(usuario)) {
			validator.add(new SimpleMessage("usuario.adiciona", "Email já cadastrado", Severity.ERROR));
			validator.onErrorUsePageOf(this).novo();
		}
		if (usuario.getUnidade() == null) {
			validator.add(new SimpleMessage("usuario.adiciona", "Selecione a unidade", Severity.ERROR));
			validator.onErrorUsePageOf(this).novo();
		}
		usuarioDao.adiciona(usuario);
		result.include("notice", "Usuário adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Get("/adminUsuario/edita/{email}")
	public void edita(String email) {
		Usuario usuario = usuarioDao.buscaPorEmail(email);
		if (usuario == null) {
			result.notFound();
		} else {
			result.include("usuario", usuario);
			result.include("unidadeList", unidadeDao.lista());
		}
	}
	
	@Admin
	@Put("/adminUsuario/{email}")
	public void altera(Usuario usuario) {
//		if (usuarioDao.existe(usuario)) {
//			validator.add(new SimpleMessage("usuario.adiciona", "Email já cadastrado", Severity.ERROR));
//			validator.onErrorUsePageOf(this).edita(usuario.getEmail());
//		}
		if (usuario.getUnidade() == null) {
			validator.add(new SimpleMessage("usuario.adiciona", "Selecione a unidade", Severity.ERROR));
			validator.onErrorUsePageOf(this).edita(usuario.getEmail());
		}
		usuarioDao.altera(usuario);
		result.include("notice", "Usuário atualizado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Delete("/adminUsuario/{email}")
	public void remove(String email){
		System.out.println("REMOVE usuarioID=" + email);
		Usuario usuario = usuarioDao.buscaPorEmail(email);
		usuarioDao.remove(usuario);
		result.nothing();
	}
	
}
