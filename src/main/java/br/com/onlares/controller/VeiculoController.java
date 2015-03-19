package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.VeiculoDao;
import br.com.onlares.model.Unidade;
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
			this.unidadeId = usuarioLogado.getUsuario().getUnidade().getId();
		} else {
			this.unidadeId = 0L;
		}
	}
	
	public VeiculoController() {
		this(null, null, null, null);
	}
	
	@Get
	public void lista() {
		result.include("veiculoUnidadeList", dao.listaDaUnidade(this.unidadeId));
	}
	
	@Get
	public void novo() {
	}
	
	@Admin
	@Post("/veiculo")
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
	
//		Unidade unidade = new Unidade();
//		unidade.setId(unidadeId);
//		veiculo.setUnidade(unidade);
		dao.adiciona(veiculo);
		result.include("notice", "Veículo adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
}
