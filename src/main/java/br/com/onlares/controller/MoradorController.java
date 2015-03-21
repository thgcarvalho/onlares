package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.dao.MoradorDao;

@Controller
public class MoradorController {

	private final MoradorDao dao;
	private final Result result;

	@Inject
	public MoradorController(MoradorDao dao, Result result) {
		this.dao = dao;
		this.result = result;
	}
	
	public MoradorController() {
		this(null, null);
	}

	@Get
	public void lista() {
		result.include("moradorList", dao.listaRegistrados());
	}
}
