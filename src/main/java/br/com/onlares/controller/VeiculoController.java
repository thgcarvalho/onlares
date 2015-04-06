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
import br.com.onlares.dao.VeiculoDao;
import br.com.onlares.model.Veiculo;

@Controller
public class VeiculoController {

	private final VeiculoDao dao;
	private final Validator validator;
	private final Result result;
	private final Long unidadeId;

	@Inject
	public VeiculoController(UsuarioLogado usuarioLogado, VeiculoDao dao, Validator validator, Result result) {
		this.dao = dao;
		this.validator = validator;
		this.result = result;
		if (usuarioLogado != null) {
			this.unidadeId = usuarioLogado.getLocalizadorAtual().getUnidade().getId();
		} else {
			this.unidadeId = 0L;
		}
	}
	
	public VeiculoController() {
		this(null, null, null, null);
	}
	
	@Get("/veiculo/lista")
	public void lista() {
		result.include("veiculoUnidadeList", dao.listaDaUnidade(this.unidadeId));
	}
	
	@Get("/veiculo/novo")
	public void novo() {
	}
	
	@Post("/veiculo/")
	public void adiciona(Veiculo veiculo) {
		if (checkNull(veiculo.getTipo()).equals("")) {
			validator.add(new I18nMessage("veiculo.adiciona", "campo.obrigatorio", "Tipo"));
		}
		if (checkNull(veiculo.getPlaca()).equals("")) {
			validator.add(new I18nMessage("veiculo.adiciona", "campo.obrigatorio", "Placa"));
		}
		if (dao.existe(veiculo, this.unidadeId)) {
			validator.add(new SimpleMessage("veiculo.adiciona", "Veículo já cadastrado"));
		}
		validator.onErrorUsePageOf(this).novo();
	
		veiculo.setTipo(veiculo.getTipo().toUpperCase());
		veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
		dao.adiciona(veiculo);
		result.include("notice", "Veículo adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Get("/veiculo/edita/{placa}")
	public void edita(String placa) {
		Veiculo veiculo = dao.buscaNaUnidade(this.unidadeId, placa);
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
		if (checkNull(veiculo.getPlaca()).equals("")) {
			validator.add(new I18nMessage("veiculo.edita", "campo.obrigatorio", "Placa"));
		}
		
		List<Veiculo> veiculos = dao.listaDaUnidade(this.unidadeId);
		for (Veiculo veiculoCadastrado : veiculos) {
			if (veiculoCadastrado.getPlaca().equals(veiculo.getPlaca())) {
				if (!veiculoCadastrado.getId().equals(veiculo.getId())) {
					validator.add(new SimpleMessage("veiculo.edita", "Veículo já cadastrado"));
					break;
				}
			}
		}
		
		validator.onErrorUsePageOf(this).edita(veiculo.getPlaca());
		
		veiculo.setTipo(veiculo.getTipo().toUpperCase());
		veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
		dao.altera(veiculo);
		result.include("notice", "Veículo atualizado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Delete("/veiculo/{placa}")
	public void remove(String placa){
		System.out.println("Veículo = " + placa + " FOI REMOVIDO!");
		Veiculo usuario = dao.buscaNaUnidade(this.unidadeId, placa);
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
