package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.dao.VeiculoDao;
import br.com.onlares.model.Veiculo;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class VeiculoController {

	private final VeiculoDao dao;
	private final Validator validator;
	private final Result result;

	@Inject
	public VeiculoController(UsuarioLogado usuarioLogado, VeiculoDao dao, Validator validator, Result result) {
		this.dao = dao;
		this.validator = validator;
		this.result = result;
	}
	
	public VeiculoController() {
		this(null, null, null, null);
	}
	
	@Get("/veiculo/lista")
	public void lista() {
		result.include("veiculoUnidadeList", dao.listaDaUnidade());
	}
	
	@Get("/veiculo/novo")
	public void novo() {
	}
	
	@Post("/veiculo/")
	public void adiciona(Veiculo veiculo) {
		if (checkNull(veiculo.getTipo()).equals("")) {
			validator.add(new I18nMessage("veiculo.adiciona", "campo.obrigatorio", "Tipo"));
		}
		validator.onErrorUsePageOf(this).novo();
	
		veiculo.setTipo(veiculo.getTipo().toUpperCase());
		if (veiculo.getPlaca() != null) {
			veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
		}
		dao.adiciona(veiculo);
		result.include("notice", "Veículo adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Get("/veiculo/edita/{veiculoId}")
	public void edita(Long veiculoId) {
		Veiculo veiculo = dao.buscaNaUnidade(veiculoId);
		if (veiculo == null) {
			result.notFound();
		} else {
			result.include("veiculo", veiculo);
		}
	}
	
	@Put("/veiculo")
	public void altera(Veiculo veiculo) {
		if (checkNull(veiculo.getTipo()).equals("")) {
			validator.add(new I18nMessage("veiculo.edita", "campo.obrigatorio", "Tipo"));
		}
		
		validator.onErrorUsePageOf(this).edita(veiculo.getId());
		
		veiculo.setTipo(veiculo.getTipo().toUpperCase());
		if (veiculo.getPlaca() != null) {
			veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
		}
		dao.altera(veiculo);
		result.include("notice", "Veículo atualizado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Delete("/veiculo/{veiculoId}")
	public void remove(Long veiculoId){
		System.out.println("Veículo = " + veiculoId + " FOI REMOVIDO!");
		Veiculo usuario = dao.buscaNaUnidade(veiculoId);
		dao.remove(usuario);
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
