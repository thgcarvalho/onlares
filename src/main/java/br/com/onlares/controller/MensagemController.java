package br.com.onlares.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.dao.MensagemDao;
import br.com.onlares.dao.MoradorDao;
import br.com.onlares.model.Mensagem;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class MensagemController {
	
	private final MensagemDao mensagemDao;
	private final MoradorDao moradorDao;
	private final Result result;

	@Inject
	public MensagemController(MensagemDao mensagemDao, MoradorDao moradorDao, Result result) {
		this.mensagemDao = mensagemDao;
		this.moradorDao = moradorDao;
		this.result = result;
	}
	
	public MensagemController() {
		this(null, null, null);
	}
	
	@Get("/mensagem/recebidas")
	public void recebidas() {
		List<Mensagem> recebidas = mensagemDao.listaRecebidas();
		result.include("mensagemList", recebidas);
		result.include("mensagemTotal", recebidas.size());
	}
	
	@Get("/mensagem/enviadas")
	public void enviadas() {
		List<Mensagem> enviadas = mensagemDao.listaEnviadas();
		result.include("mensagemList", enviadas);
		result.include("mensagemTotal", enviadas.size());
	}
	
	@Get("/mensagem/novo")
	public void novo() {
		result.include("moradorList", moradorDao.listaRegistrados());
	}
	
	@Get("/mensagem/visualizaRecebida/{id}")
	public void visualizaRecebida(Long id) {
		Mensagem mensagem = mensagemDao.buscaRecebida(id);
		if (mensagem == null) {
			result.notFound();
		} else {
			result.include("mensagem", mensagem);
		}
	}
	
	@Get("/mensagem/visualizaEnviada/{id}")
	public void visualizaEnviada(Long id) {
		Mensagem mensagem = mensagemDao.buscaEnviada(id);
		if (mensagem == null) {
			result.notFound();
		} else {
			result.include("mensagem", mensagem);
			result.include("destinatarios", mensagemDao.buscaDestinatarios(mensagem));
		}
	}
	
}