package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.dao.VeiculoDao;

@Controller
public class VeiculoController {

	private final VeiculoDao dao;
	private final Result result;
	private final Long unidadeId;

	@Inject
	public VeiculoController(UsuarioLogado usuarioLogado, VeiculoDao dao, Result result) {
		if (usuarioLogado != null) {
			this.unidadeId = usuarioLogado.getUsuario().getUnidade().getId();
		} else {
			this.unidadeId = 0L;
		}
		this.dao = dao;
		this.result = result;
	}
	
	public VeiculoController() {
		this(null, null, null);
	}
	
	@Get
	public void lista() {
		result.include("veiculoUnidadeList", dao.listaDaUnidade(unidadeId));
	}
}
