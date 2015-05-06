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
import br.com.onlares.dao.PetDao;
import br.com.onlares.model.Pet;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class PetController {

	private final PetDao dao;
	private final Validator validator;
	private final Result result;

	@Inject
	public PetController(UsuarioLogado usuarioLogado, PetDao dao, Validator validator, Result result) {
		this.dao = dao;
		this.validator = validator;
		this.result = result;
	}
	
	public PetController() {
		this(null, null, null, null);
	}
	
	@Get("/pet/lista")
	public void lista() {
		result.include("petUnidadeList", dao.listaDaUnidade());
	}
	
	@Get("/pet/novo")
	public void novo() {
	}
	
	@Post("/pet/")
	public void adiciona(Pet pet) {
		if (checkNull(pet.getTipo()).equals("")) {
			validator.add(new I18nMessage("pet.adiciona", "campo.obrigatorio", "Tipo"));
		}
		validator.onErrorUsePageOf(this).novo();
	
		pet.setTipo(pet.getTipo().toUpperCase());
		dao.adiciona(pet);
		result.include("notice", "Pet adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Get("/pet/edita/{petId}")
	public void edita(Long petId) {
		Pet pet = dao.buscaNaUnidade(petId);
		if (pet == null) {
			result.notFound();
		} else {
			result.include("pet", pet);
		}
	}
	
	@Put("/pet")
	public void altera(Pet pet) {
		if (checkNull(pet.getTipo()).equals("")) {
			validator.add(new I18nMessage("pet.edita", "campo.obrigatorio", "Tipo"));
		}
		
		validator.onErrorUsePageOf(this).edita(pet.getId());
		
		pet.setTipo(pet.getTipo().toUpperCase());
		dao.altera(pet);
		result.include("notice", "Pet atualizado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Delete("/pet/{petId}")
	public void remove(Long petId){
		System.out.println("Pet = " + petId + " FOI REMOVIDO!");
		Pet usuario = dao.buscaNaUnidade(petId);
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
