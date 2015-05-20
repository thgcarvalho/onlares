package br.com.onlares.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.dao.AvisoDao;
import br.com.onlares.dao.AvisoVisualizadoDao;
import br.com.onlares.dao.MoradorDao;
import br.com.onlares.model.Aviso;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class MensagemController {
	
	private final AvisoDao avisoDao;
	private final MoradorDao moradorDao;
	private final AvisoVisualizadoDao avisoVisualizadoDao;
	private final Result result;

	@Inject
	public MensagemController(AvisoDao avisoDao, MoradorDao moradorDao, AvisoVisualizadoDao avisoVisualizadoDao, Result result) {
		this.avisoDao = avisoDao;
		this.moradorDao = moradorDao;
		this.avisoVisualizadoDao = avisoVisualizadoDao;
		this.result = result;
	}
	
	public MensagemController() {
		this(null, null, null, null);
	}
	
	@Get("/mensagem/enviadas")
	public void enviadas() {
	}
	
	@Get("/mensagem/recebidas")
	public void recebidas() {
		List<Aviso> avisos = avisoDao.listaSemTexto();
		avisoVisualizadoDao.setaVisualizacao(avisos);
		result.include("mensagemList", avisos);
	}
	
	@Get("/mensagem/novo")
	public void novo() {
		result.include("moradorList", moradorDao.listaRegistrados());
	}
	
	@Get("/mensagem/visualizaEnviada/{id}")
	public void visualizaEnviada(Long id) {
//		Mensagem mensagem = mensagemDao.busca(id);
//		if (mensagem == null) {
//			result.notFound();
//		} else {
//			result.include("mensagem", mensagem);
//		}
	}
	
	@Get("/mensagem/visualizaRecebida/{id}")
	public void visualizaRecebida(Long id) {
//		Mensagem mensagem = mensagemDao.busca(id);
//		if (mensagem == null) {
//			result.notFound();
//		} else {
//			result.include("mensagem", mensagem);
//		}
	}
	
}