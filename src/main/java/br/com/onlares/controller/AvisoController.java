package br.com.onlares.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.dao.AvisoDao;
import br.com.onlares.dao.AvisoVisualizadoDao;
import br.com.onlares.model.Aviso;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AvisoController {
	
	private final AvisoDao avisoDao;
	private final AvisoVisualizadoDao avisoVisualizadoDao;
	private final UsuarioLogado usuarioLogado;
	private final Result result;

	@Inject
	public AvisoController(AvisoDao avisoDao, AvisoVisualizadoDao avisoVisualizadoDao, UsuarioLogado usuarioLogado, Result result) {
		this.avisoDao = avisoDao;
		this.avisoVisualizadoDao = avisoVisualizadoDao;
		this.usuarioLogado = usuarioLogado;
		this.result = result;
	}
	
	public AvisoController() {
		this(null, null, null, null);
	}
	
	@Get("/aviso/lista")
	public void lista() {
		List<Aviso> avisos = avisoDao.listaSemTexto();
		avisoVisualizadoDao.setaVisualizacao(avisos);
		result.include("avisoList", avisos);
	}
	
	@Get("/aviso/visualiza/{id}")
	public void visualiza(Long id) {
		Aviso aviso = avisoDao.busca(id);
		if (aviso == null) {
			result.notFound();
		} else {
			if (!avisoVisualizadoDao.foiVisualizado(id)) {
				avisoVisualizadoDao.adiciona(id);
				usuarioLogado.getUsuario().removeUmAviso();
			}
			result.include("aviso", aviso);
		}
	}
	
}