package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.comparador.ComparadorUnidade;
import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Constantes;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;
import br.com.onlares.model.Pet;
import br.com.onlares.model.Unidade;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class PetDao {

	private final EntityManager em;
	private final Long condominioId;
	private final Long unidadeId;
	
	@Inject
	public PetDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
		this.unidadeId = LocalizadorDoUsuarioLogado.getUnidadeIdAtual(usuarioLogado);
	}
	
	@Deprecated
	public PetDao() {
		this(null, null); // para uso do CDI
	}
	
	public Pet busca(Pet pet) {
		return em.find(Pet.class, pet.getId());
	}

	public List<Pet> lista() {
		List<Pet> pets = new ArrayList<Pet>();
		List<Unidade> unidadesDoCondominio = em.createQuery("select distinct l.unidade from Localizador l"
				+ " where l.condominio.id = :condominioId"
				+ " and l.unidade.id <> :unidadeId", Unidade.class)
				.setParameter("condominioId", condominioId)
				.setParameter("unidadeId", Constantes.UNIDADE_NAO_RELACIONADA_ID).getResultList();

		ComparadorUnidade comparadorUnidade = new ComparadorUnidade();
		Collections.sort(unidadesDoCondominio, comparadorUnidade);
		
		for (Unidade unidade : unidadesDoCondominio) {
			pets.addAll(em.createQuery("select v from Pet v"
				+ " where v.unidade.id = :unidadeId", Pet.class)
				.setParameter("unidadeId", unidade.getId()).getResultList());

		}
		return pets;
	}
	
	public void adiciona(Pet pet) {
		em.persist(pet);
	}
	
	public void altera(Pet pet) {
		em.merge(pet);
	}
	
	public Pet busca(Long petId) {
		Pet pet;
		// TODO deveria obter somente do condominio em questao
		String strQuery = "SELECT v FROM Pet v"
				+ " WHERE v.id = :petId";
		try {
			Query query = em.createQuery(strQuery, Pet.class);
			query.setParameter("petId", petId);
			pet = (Pet) query.getSingleResult();
		} catch (NoResultException nrExp) {
			pet = null;
		}
		return pet;
	}
	
	public Pet buscaNaUnidade(Long petId) {
		Pet pet;
		String strQuery = "SELECT v FROM Pet v"
				+ " WHERE v.unidade.id = :unidadeId AND v.id = :petId";
		try {
			Query query = em.createQuery(strQuery, Pet.class);
			query.setParameter("unidadeId", unidadeId);
			query.setParameter("petId", petId);
			pet = (Pet) query.getSingleResult();
		} catch (NoResultException nrExp) {
			pet = null;
		}
		return pet;
	}
	
	public List<Pet> listaDaUnidade() {
		List<Pet> pets;
		try {
			pets = em.createQuery("select v from Pet v"
					+ " where v.unidade.id = :unidadeId", Pet.class)
					.setParameter("unidadeId", unidadeId).getResultList();
		} catch (EntityNotFoundException e) {
			pets = new ArrayList<Pet>();
		}
		return pets;
	}
	
	public void remove(Pet pet) {
		em.remove(busca(pet));
	}

}
