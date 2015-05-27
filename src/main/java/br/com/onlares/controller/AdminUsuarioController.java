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

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
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
		List<Unidade> unidadesAUX = new ArrayList<Unidade>();
		Unidade unidadeAUX;
		if (unidades != null) {
			for (Long unidadeId: unidades) {
				unidadeAUX = new Unidade();
				unidadeAUX.setId(unidadeId);
				unidadesAUX.add(unidadeAUX);
			}
		}
		// adiciona para poder obter 
		usuario.setUnidades(unidadesAUX);
	}

	@Admin
	@Post("/adminUsuario")
	public void adiciona(Usuario usuario, List<Long> unidades) {
		adicionaListaDeUnidades(usuario, unidades);
		
		if (checkNull(usuario.getNomeCompleto()).equals("")) {
			validator.add(new I18nMessage("usuario.adiciona", "campo.obrigatorio", "Nome"));
		}
		if (checkNull(usuario.getEmail()).equals("")) {
			validator.add(new I18nMessage("usuario.adiciona", "campo.obrigatorio", "Email"));
		}
		if (usuarioDao.existe(usuario)) {
			validator.add(new SimpleMessage("usuario.adiciona", "Email já cadastrado"));
		}
		
		result.include("unidadeList", unidadeDao.lista());
		validator.onErrorUsePageOf(this).novo();
		
		if (usuario.getNomeCompleto() != null) {
			usuario.setNomeCompleto(usuario.getNomeCompleto().toUpperCase());
		}
		if (usuario.getUf() != null) {
			usuario.setUf(usuario.getUf().toUpperCase());
		}
		
		usuarioDao.adiciona(usuario, unidades);
		result.include("notice", "Usuário adicionado com sucesso!");
		result.redirectTo(this).lista();
	}

	@Admin
	@Get("/adminUsuario/edita/{id}")
	public void edita(Long id) {
		Usuario usuario = usuarioDao.buscaPorId(id);
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
		
		if (checkNull(usuario.getNomeCompleto()).equals("")) {
			erro=true;
			validator.add(new I18nMessage("usuario.edita", "campo.obrigatorio", "Nome"));
		}
		if (checkNull(usuario.getEmail()).equals("")) {
			erro=true;
			validator.add(new I18nMessage("usuario.edita", "campo.obrigatorio", "Email"));
			validator.onErrorUsePageOf(this).edita(usuario.getId());
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
		validator.onErrorUsePageOf(this).edita(usuario.getId());
		
		if (usuario.getNomeCompleto() != null) {
			usuario.setNomeCompleto(usuario.getNomeCompleto().toUpperCase());
		}
		if (usuario.getUf() != null) {
			usuario.setUf(usuario.getUf().toUpperCase());
		}
		
		Usuario usuarioDB = usuarioDao.busca(usuario);
		usuarioDB.setNomeCompleto(usuario.getNomeCompleto());
		usuarioDB.setEmail(usuario.getEmail());
		usuarioDB.setEmailSecundario(usuario.getEmailSecundario());
		usuarioDB.setCpf(usuario.getCpf());
		usuarioDB.setCnpj(usuario.getCnpj());
		usuarioDB.setFoneResidencial(usuario.getFoneResidencial());
		usuarioDB.setFoneCelular(usuario.getFoneCelular());
		usuarioDB.setFoneComercial(usuario.getFoneComercial());
		usuarioDB.setLogradouro(usuario.getLogradouro());
		usuarioDB.setComplemento(usuario.getComplemento());
		usuarioDB.setBairro(usuario.getBairro());
		usuarioDB.setCidade(usuario.getCidade());
		usuarioDB.setUf(usuario.getUf());
		usuarioDB.setCep(usuario.getCep());
		
		usuarioDao.altera(usuarioDB, unidades);
		result.include("notice", "Usuário atualizado com sucesso!");
		result.redirectTo(this).lista();
	}

	@Admin
	@Get("/adminUsuario/visualiza/{id}")
	public void visualiza(Long id) {
		Usuario usuario = usuarioDao.buscaPorId(id);
		if (usuario == null) {
			result.notFound();
		} else {
			result.include("usuario", usuario);
		}
	}
	
	@Admin
	@Delete("/adminUsuario/{id}")
	public void remove(Long id){
		System.out.println("USUARIO = " + id + " FOI REMOVIDO!");
		Usuario usuario = usuarioDao.buscaPorId(id);
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
