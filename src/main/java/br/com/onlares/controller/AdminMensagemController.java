package br.com.onlares.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.MensagemDao;
import br.com.onlares.dao.MoradorDao;
import br.com.onlares.model.Mensagem;
import br.com.onlares.model.Usuario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AdminMensagemController {
	
	private final MensagemDao mensagemDao;
	private final MoradorDao moradorDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminMensagemController(MensagemDao mensagemDao, MoradorDao moradorDao, Validator validator, Result result) {
		this.mensagemDao = mensagemDao;
		this.moradorDao = moradorDao;
		this.validator = validator;
		this.result = result;
	}
	
	public AdminMensagemController() {
		this(null, null, null, null);
	}
	
	@Admin
	@Get("/adminMensagem/novo")
	public void novo() {
		result.include("moradorList", moradorDao.listaRegistrados());
	}
	
	@Admin
	@Post("/adminMensagem/")
	public void envia(Mensagem mensagem) {
        if (checkNull(mensagem.getAssunto()).equals("")) {
   			validator.add(new I18nMessage("mensagem.envia", "campo.obrigatorio", "Assunto"));
   		}
        if (checkNull(mensagem.getTexto()).equals("")) {
   			validator.add(new I18nMessage("mensagem.envia", "campo.obrigatorio", "Texto"));
   		}
        
		validator.onErrorUsePageOf(this).novo();
		
		List<Usuario> moradores = moradorDao.listaRegistrados();
		List<Long> destinatarios = new ArrayList<Long>();
		for (Usuario usuario : moradores) {
			destinatarios.add(usuario.getId());
		}
		mensagemDao.envia(mensagem, destinatarios);
		result.include("notice", "Mensagem enviada com sucesso!");
		result.redirectTo(this).novo();
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}