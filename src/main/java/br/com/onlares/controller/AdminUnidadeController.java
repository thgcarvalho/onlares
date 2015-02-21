package br.com.onlares.controller;

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
import br.com.onlares.model.Unidade;

@Controller
public class AdminUnidadeController {

	private final UnidadeDao unidadeDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminUnidadeController(UnidadeDao unidadeDao, Validator validator, Result result) {
		this.unidadeDao = unidadeDao;
		this.validator = validator;
		this.result = result;
	}
	
	public AdminUnidadeController() {
		this(null, null, null);
	}
	
	@Admin
	@Get("/adminUnidade/lista")
	public void lista() {
		result.include("unidadeList", unidadeDao.lista());
	}
	
	@Admin
	@Get("/adminUnidade/novo")
	public void novo() {
		
	}

	@Admin
	@Post("/adminUnidade")
	public void adiciona(Unidade unidade) {
		if (checkNull(unidade.getLocalizacao()).equals("")) {
			validator.add(new I18nMessage("usuario.adiciona", "campo.obrigatorio", "Localização"));
		}
		if (unidadeDao.existe(unidade)) {
			validator.add(new SimpleMessage("unidade.adiciona", "Unidade já cadastrada"));
		}
		
		validator.onErrorUsePageOf(this).novo();
		
		unidadeDao.adiciona(unidade);
		result.include("notice", "Unidade adicionada com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Get("/adminUnidade/edita/{id}")
	public void edita(long id) {
//		Usuario usuario = usuarioDao.buscaPorEmail(email);
//		if (usuario == null) {
//			result.notFound();
//		} else {
//			result.include("usuario", usuario);
//			result.include("unidadeList", unidadeDao.lista());
//		}
	}
	
	@Admin
	@Put("/adminUnidade/{id}")
	public void altera(Unidade unidade) {
		List<Unidade> unidades = unidadeDao.lista();
		unidades.add(unidade);
		int mesmoEmail = 0;
		for (Unidade unidadeCadastrada : unidades) {
			if (unidadeCadastrada.getLocalizacao().equals(unidade.getLocalizacao())) {
				mesmoEmail++;
			}
		}
		if (mesmoEmail > 1) {
			validator.add(new SimpleMessage("unidade.edita", "Unidade já existente"));
		}
		
		validator.onErrorUsePageOf(this).edita(unidade.getId());
		
		unidadeDao.altera(unidade);
		result.include("notice", "Unidade atualizada com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Delete("/adminUnidade/{id}")
	public void remove(long id){
//		System.out.println("REMOVE usuarioID=" + email);
//		Usuario usuario = usuarioDao.buscaPorEmail(email);
//		usuarioDao.remove(usuario);
//		result.nothing();
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}