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
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.PetDao;
import br.com.onlares.dao.UnidadeDao;
import br.com.onlares.model.Pet;

@Controller
public class AdminPetController {

	private final PetDao petDao;
	private final UnidadeDao unidadeDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminPetController(UsuarioLogado usuarioLogado, PetDao petDao, UnidadeDao unidadeDao, Validator validator, Result result) {
		this.petDao = petDao;
		this.unidadeDao = unidadeDao;
		this.validator = validator;
		this.result = result;
	}
	
	public AdminPetController() {
		this(null, null, null, null, null);
	}
	
	@Admin
	@Get("/adminPet/lista")
	public void lista() {
		result.include("petList", petDao.lista());
	}
	
	@Admin
	@Get("/adminPet/novo")
	public void novo() {
		result.include("unidadeList", unidadeDao.lista());
	}
	
	@Admin
	@Post("/adminPet/")
	public void adiciona(Pet pet) {
		if (checkNull(pet.getTipo()).equals("")) {
			validator.add(new I18nMessage("pet.adiciona", "campo.obrigatorio", "Tipo"));
		}
		validator.onErrorUsePageOf(this).novo();
	
		pet.setTipo(pet.getTipo().toUpperCase());
		petDao.adiciona(pet);
		result.include("notice", "Veículo adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Get("/adminPet/edita/{petId}")
	public void edita(Long petId) {
		Pet pet = petDao.busca(petId);
		if (pet == null) {
			result.notFound();
		} else {
			result.include("pet", pet);
			result.include("unidadeList", unidadeDao.lista());
		}
	}
	
	@Admin
	@Put("/adminPet")
	public void altera(Pet pet) {
		if (checkNull(pet.getTipo()).equals("")) {
			validator.add(new I18nMessage("pet.edita", "campo.obrigatorio", "Tipo"));
		}
		
		validator.onErrorUsePageOf(this).edita(pet.getId());
		
		pet.setTipo(pet.getTipo().toUpperCase());
		petDao.altera(pet);
		result.include("notice", "Veículo atualizado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Delete("/adminPet/{petId}")
	public void remove(Long petId){
		System.out.println("Veículo = " + petId + " FOI REMOVIDO!");
		Pet pet = petDao.busca(petId);
		petDao.remove(pet);
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
