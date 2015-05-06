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
import br.com.onlares.exception.RestricaoDeIntegridadeException;
import br.com.onlares.model.Unidade;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
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
		if (checkNull(unidade.getDescricao()).equals("")) {
			validator.add(new I18nMessage("usuario.adiciona", "campo.obrigatorio", "Descrição"));
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
		Unidade unidade = unidadeDao.buscaPorId(id);
		if (unidade == null) {
			result.notFound();
		} else {
			result.include("unidade", unidade);
		}
	}
	
	@Admin
	@Put("/adminUnidade/{id}")
	public void altera(Unidade unidade) {
		if (checkNull(unidade.getDescricao()).equals("")) {
			validator.add(new I18nMessage("usuario.edita", "campo.obrigatorio", "Descrição"));
			validator.onErrorUsePageOf(this).edita(unidade.getId());
		}
		List<Unidade> unidades = unidadeDao.lista();
		unidades.add(unidade);
		int mesmoEmail = 0;
		for (Unidade unidadeCadastrada : unidades) {
			if (unidadeCadastrada.getDescricao().equals(unidade.getDescricao())) {
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
	public void remove(long id) {
		Unidade unidade = unidadeDao.buscaPorId(id);
		try {
			unidadeDao.verificaIntegridade(unidade.getId());
			unidadeDao.remove(unidade);
			result.nothing();
		} catch (RestricaoDeIntegridadeException exp) {
			// TODO Mostrar mensagem
			System.out.println("RestricaoDeIntegridadeException:" + exp.getMessage());
		}
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}
