package br.com.onlares.controller;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.comparador.ComparadorAutorizacao;
import br.com.onlares.dao.AutorizacaoDao;
import br.com.onlares.dao.TipoDeAutorizacaoDao;
import br.com.onlares.model.Autorizacao;
import br.com.onlares.model.TipoDeAutorizacao;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AdminAutorizacaoController {

	private final AutorizacaoDao autorizacaoDao;
	private final TipoDeAutorizacaoDao tipoDeAutorizacaoDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminAutorizacaoController(UsuarioLogado usuarioLogado, AutorizacaoDao autorizacaoDao, TipoDeAutorizacaoDao tipoDeAutorizacaoDao, Validator validator, Result result) {
		this.autorizacaoDao = autorizacaoDao;
		this.tipoDeAutorizacaoDao = tipoDeAutorizacaoDao;
		this.validator = validator;
		this.result = result;
	}
	
	public AdminAutorizacaoController() {
		this(null, null, null, null, null);
	}
	
	@Admin
	@Get("/adminAutorizacao/lista")
	public void lista() {
		result.include("tipoDeAutorizacaoList", tipoDeAutorizacaoDao.lista());
	}
	
	@Admin
	@Get("/adminAutorizacao/listaDoCondominio")
	public void listaDoCondominio() {
		List<Autorizacao> autorizacoes = autorizacaoDao.listaDoCondominio();
		ComparadorAutorizacao comparadorAutorizacao = new ComparadorAutorizacao();
		Collections.sort(autorizacoes, comparadorAutorizacao);
		result.include("autorizacaoList", autorizacoes);
	}
	
	@Admin
	@Get("/adminAutorizacao/novo")
	public void novo() {
	}
	
	@Admin
	@Post("/adminAutorizacao/")
	public void adiciona(TipoDeAutorizacao tipoDeAutorizacao) {
		if (checkNull(tipoDeAutorizacao.getDescricao()).equals("")) {
			validator.add(new I18nMessage("tipoDeAutorizacao.adiciona", "campo.obrigatorio", "Descrição"));
		}
		
		validator.onErrorUsePageOf(this).novo();
		tipoDeAutorizacaoDao.adiciona(tipoDeAutorizacao);
		result.include("notice", "Tipo de autorização adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Get("/adminAutorizacao/edita/{tipoDeAutorizacaoId}")
	public void edita(Long tipoDeAutorizacaoId) {
		TipoDeAutorizacao tipoDeAutorizacao = tipoDeAutorizacaoDao.buscaTipoDeAutorizacao(tipoDeAutorizacaoId);
		if (tipoDeAutorizacao == null) {
			result.notFound();
		} else {
			result.include("tipoDeAutorizacao", tipoDeAutorizacao);
		}
	}
	
	@Admin
	@Put("/adminAutorizacao")
	public void altera(TipoDeAutorizacao tipoDeAutorizacao) {
		if (checkNull(tipoDeAutorizacao.getDescricao()).equals("")) {
			validator.add(new I18nMessage("tipoDeAutorizacao.edita", "campo.obrigatorio", "Descrição"));
		}
		
		validator.onErrorUsePageOf(this).edita(tipoDeAutorizacao.getId());
		
		tipoDeAutorizacaoDao.altera(tipoDeAutorizacao);
		result.include("notice", "Tipo de autorização atualizado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Delete("/adminAutorizacao/{tipoDeAutorizacaoId}")
	public void remove(Long tipoDeAutorizacaoId){
		System.out.println("TipoDeAutorizacao = " + tipoDeAutorizacaoId + " FOI REMOVIDO!");
		TipoDeAutorizacao tipoDeAutorizacao = tipoDeAutorizacaoDao.buscaTipoDeAutorizacao(tipoDeAutorizacaoId);
		tipoDeAutorizacaoDao.removeTipoDeAutorizacao(tipoDeAutorizacao);
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
