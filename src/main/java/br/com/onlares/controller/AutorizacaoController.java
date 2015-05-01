package br.com.onlares.controller;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.comparador.ComparadorAutorizacao;
import br.com.onlares.dao.AutorizacaoDao;
import br.com.onlares.dao.TipoDeAutorizacaoDao;
import br.com.onlares.model.Autorizacao;
import br.com.onlares.model.TipoDeAutorizacao;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AutorizacaoController {

	private final AutorizacaoDao autorizacaoDao;
	private final TipoDeAutorizacaoDao tipoDeAutorizacaoDao;
	private final Validator validator;
	private final Result result;
	private final Long unidadeId;

	@Inject
	public AutorizacaoController(UsuarioLogado usuarioLogado, AutorizacaoDao autorizacaoDao, TipoDeAutorizacaoDao tipoDeAutorizacaoDao, Validator validator, Result result) {
		this.autorizacaoDao = autorizacaoDao;
		this.tipoDeAutorizacaoDao = tipoDeAutorizacaoDao;
		this.validator = validator;
		this.result = result;
		if (usuarioLogado != null && usuarioLogado.getLocalizadorAtual().getUnidade() != null) {
			this.unidadeId = usuarioLogado.getLocalizadorAtual().getUnidade().getId();
		} else {
			this.unidadeId = 0L;
		}
	}
	
	public AutorizacaoController() {
		this(null, null, null, null, null);
	}
	
	@Get("/autorizacao/index")
	public void index() {
		result.include("tipoDeAutorizacaoList", tipoDeAutorizacaoDao.lista());
	}
	
	@Get("/autorizacao/listaDaUnidade/")
	public void listaDaUnidade() {
		List<Autorizacao> autorizacoes = autorizacaoDao.listaDaUnidade(this.unidadeId);
		ComparadorAutorizacao comparadorAutorizacao = new ComparadorAutorizacao();
		Collections.sort(autorizacoes, comparadorAutorizacao);
		result.include("autorizacaoList", autorizacoes);
	}
	
	@Get("/autorizacao/novo/{tipoDeAutorizacaoId}")
	public void novo(Long tipoDeAutorizacaoId) {
		TipoDeAutorizacao tipoDeAutorizacao = tipoDeAutorizacaoDao.buscaTipoDeAutorizacao(tipoDeAutorizacaoId);
		if (tipoDeAutorizacao == null) {
			result.notFound();
		} else {
			result.include("tipoDeAutorizacao", tipoDeAutorizacao);
		}
	}
	
	@Post("/autorizacao/")
	public void adiciona(Autorizacao autorizacao) {
		System.out.println(autorizacao);
		if (autorizacao.getData() == null) {
			validator.add(new I18nMessage("autorizacao.adiciona", "campo.obrigatorio", "Data"));
		}
		if (autorizacao.getHora() == null) {
			validator.add(new I18nMessage("autorizacao.adiciona", "campo.obrigatorio", "Hora"));
		}
		
		validator.onErrorRedirectTo(this).novo(autorizacao.getTipoDeAutorizacao().getId());
		
		autorizacaoDao.autorizacao(autorizacao);
		result.include("notice", "Autorizacao realizada com sucesso!");
		result.redirectTo(this).index();
	}
	
	@Delete("/autorizacao/{autorizacaoId}")
	public void remove(Long autorizacaoId){
		Autorizacao autorizacao = autorizacaoDao.buscaAutorizacao(autorizacaoId);
		autorizacaoDao.removeAutorizacao(autorizacao);
		result.nothing();
	}
	
}
