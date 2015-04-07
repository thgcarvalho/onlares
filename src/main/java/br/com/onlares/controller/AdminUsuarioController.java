package br.com.onlares.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.UnidadeDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Unidade;
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
	
	public void adicionaListaDeUnidades(Usuario usuario, List<Long> unidades) {
		System.out.println("unidades=" + unidades);
		List<Unidade> unidadesAUX = new ArrayList<Unidade>();
		Unidade unidadeAUX;
		if (unidades != null) {
			for (Long unidadeId: unidades) {
				unidadeAUX = new Unidade();
				unidadeAUX.setId(unidadeId);
				unidadesAUX.add(unidadeAUX);
				System.out.println("unidade=" + unidadeId);
			}
		}
		// adiciona para poder obter 
		usuario.setUnidades(unidadesAUX);
	}

	@Admin
	@Post("/adminUsuario")
	public void adiciona(Usuario usuario, List<Long> unidades) {
		adicionaListaDeUnidades(usuario, unidades);
		
		if (checkNull(usuario.getNome()).equals("")) {
			validator.add(new I18nMessage("usuario.adiciona", "campo.obrigatorio", "Nome"));
		}
		if (checkNull(usuario.getEmail()).equals("")) {
			validator.add(new I18nMessage("usuario.adiciona", "campo.obrigatorio", "Email"));
		}
		if (usuarioDao.existe(usuario)) {
			validator.add(new SimpleMessage("usuario.adiciona", "Email já cadastrado"));
		}
		if (unidades == null || unidades.isEmpty()) {
			validator.add(new SimpleMessage("usuario.adiciona", "Selecione alguma unidade"));
		}
		
		result.include("unidadeList", unidadeDao.lista());
		validator.onErrorUsePageOf(this).novo();
		
		usuarioDao.adiciona(usuario, unidades);
		result.include("notice", "Usuário adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Get("/adminUsuario/edita/{email}")
	public void edita(String email) {
		Usuario usuario = usuarioDao.buscaPorEmail(email);
		System.out.println("/adminUsuario/edita/{email} " + usuario);
		if (usuario == null) {
			result.notFound();
		} else {
			result.include("usuario", usuario);
			result.include("unidadeList", unidadeDao.lista());
		}
	}
	
	@Admin
	@Put("/adminUsuario/{email}")
	public void altera(Usuario usuario, List<Long> unidades) {
		boolean erro = false;
		adicionaListaDeUnidades(usuario, unidades);
		
		if (checkNull(usuario.getNome()).equals("")) {
			erro=true;
			validator.add(new I18nMessage("usuario.edita", "campo.obrigatorio", "Nome"));
		}
		if (unidades == null || unidades.isEmpty()) {
			validator.add(new SimpleMessage("usuario.edita", "Selecione alguma unidade"));
		}
		if (checkNull(usuario.getEmail()).equals("")) {
			erro=true;
			validator.add(new I18nMessage("usuario.edita", "campo.obrigatorio", "Email"));
			validator.onErrorUsePageOf(this).edita(usuario.getEmail());
		}
		
		List<Usuario> usuarios = usuarioDao.listaTodos();
		for (Usuario usuarioCadastrado : usuarios) {
			if (usuarioCadastrado.getEmail().equals(usuario.getEmail())) {
				if (!usuarioCadastrado.getId().equals(usuario.getId())) {
					validator.add(new SimpleMessage("usuario.edita", "Email usado por outro usuário"));
					erro=true;
					break;
				}
			}
		}
		
		if (erro) {
			result.include("unidadeList", unidadeDao.lista());
		}
		validator.onErrorUsePageOf(this).edita(usuario.getEmail());
		
		Usuario usuarioDB = usuarioDao.busca(usuario);
		usuarioDB.setNome(usuario.getNome());
		usuarioDB.setFoneResidencial(usuario.getFoneResidencial());
		usuarioDB.setFoneCelular(usuario.getFoneCelular());
		usuarioDB.setEmail(usuario.getEmail());
		
		usuarioDao.altera(usuarioDB, unidades);
		result.include("notice", "Usuário atualizado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Delete("/adminUsuario/{email}")
	public void remove(String email){
		System.out.println("USUARIO = " + email + " FOI REMOVIDO!");
		Usuario usuario = usuarioDao.buscaPorEmail(email);
		usuarioDao.remove(usuario);
		result.nothing();
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}
