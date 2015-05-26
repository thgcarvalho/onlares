package br.com.onlares.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
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
	private final Validator validator;
	private final Result result;

	@Inject
	public MensagemController(MensagemDao mensagemDao, MoradorDao moradorDao, Validator validator, Result result) {
		this.mensagemDao = mensagemDao;
		this.moradorDao = moradorDao;
		this.validator = validator;
		this.result = result;
	}
	
	public MensagemController() {
		this(null, null, null, null);
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
	
	@Get("/mensagem/visualizaRecebida/{id}")
	public void visualizaRecebida(Long id) {
		Mensagem mensagem = mensagemDao.buscaRecebida(id);
		if (mensagem == null) {
			result.notFound();
		} else {
			if (!mensagemDao.foiVisualizado(id)) {
				mensagemDao.setaVisualizacao(id);
				mensagem.setVisualizado(true);
			}
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
	
	@Get("/mensagem/novo")
	public void novo() {
		result.include("moradorList", moradorDao.listaRegistrados());
	}
	
	@Post("/mensagem/")
	public void envia(Mensagem mensagem, List<Long> destinatarios) {
        if (checkNull(mensagem.getAssunto()).equals("")) {
   			validator.add(new I18nMessage("mensagem.envia", "campo.obrigatorio", "Assunto"));
   		}
        if (checkNull(mensagem.getTexto()).equals("")) {
   			validator.add(new I18nMessage("mensagem.envia", "campo.obrigatorio", "Texto"));
   		}
        
		validator.onErrorUsePageOf(this).novo();
		
		mensagemDao.envia(mensagem, destinatarios);
		result.include("notice", "Mensagem enviada com sucesso!");
		result.redirectTo(this).enviadas();
	}
	
	@Delete("/mensagem/removeRecebidas/")
	public void removeRecebidas(List<Long> mensagens, HttpServletRequest request){
		mensagemDao.removeRecebidas(mensagens);
		result.redirectTo(this).recebidas();
	}
	
	@Delete("/mensagem/removeEnviadas/")
	public void removeEnviadas(List<Long> mensagens, HttpServletRequest request){
		mensagemDao.removeEnviadas(mensagens);
		result.redirectTo(this).enviadas();
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}