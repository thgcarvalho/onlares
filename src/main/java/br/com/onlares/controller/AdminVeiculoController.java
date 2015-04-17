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
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.UnidadeDao;
import br.com.onlares.dao.VeiculoDao;
import br.com.onlares.model.Veiculo;

@Controller
public class AdminVeiculoController {

	private final VeiculoDao veiculoDao;
	private final UnidadeDao unidadeDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminVeiculoController(UsuarioLogado usuarioLogado, VeiculoDao veiculoDao, UnidadeDao unidadeDao, Validator validator, Result result) {
		this.veiculoDao = veiculoDao;
		this.unidadeDao = unidadeDao;
		this.validator = validator;
		this.result = result;
	}
	
	public AdminVeiculoController() {
		this(null, null, null, null, null);
	}
	
	@Admin
	@Get("/adminVeiculo/lista")
	public void lista() {
		result.include("veiculoList", veiculoDao.lista());
	}
	
	@Admin
	@Get("/adminVeiculo/novo")
	public void novo() {
		result.include("unidadeList", unidadeDao.lista());
	}
	
	@Admin
	@Post("/adminVeiculo/")
	public void adiciona(Veiculo veiculo) {
		if (checkNull(veiculo.getTipo()).equals("")) {
			validator.add(new I18nMessage("veiculo.adiciona", "campo.obrigatorio", "Tipo"));
		}
		validator.onErrorUsePageOf(this).novo();
	
		veiculo.setTipo(veiculo.getTipo().toUpperCase());
		if (veiculo.getPlaca() != null) {
			veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
		}
		veiculoDao.adiciona(veiculo);
		result.include("notice", "Veículo adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Get("/adminVeiculo/edita/{veiculoId}")
	public void edita(Long veiculoId) {
		Veiculo veiculo = veiculoDao.busca(veiculoId);
		if (veiculo == null) {
			result.notFound();
		} else {
			result.include("veiculo", veiculo);
			result.include("unidadeList", unidadeDao.lista());
		}
	}
	
	@Admin
	@Put("/adminVeiculo")
	public void altera(Veiculo veiculo) {
		if (checkNull(veiculo.getTipo()).equals("")) {
			validator.add(new I18nMessage("veiculo.edita", "campo.obrigatorio", "Tipo"));
		}
		
		validator.onErrorUsePageOf(this).edita(veiculo.getId());
		
		veiculo.setTipo(veiculo.getTipo().toUpperCase());
		if (veiculo.getPlaca() != null) {
			veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
		}
		veiculoDao.altera(veiculo);
		result.include("notice", "Veículo atualizado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Delete("/adminVeiculo/{veiculoId}")
	public void remove(Long veiculoId){
		System.out.println("Veículo = " + veiculoId + " FOI REMOVIDO!");
		Veiculo veiculo = veiculoDao.busca(veiculoId);
		veiculoDao.remove(veiculo);
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
