package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.model.Veiculo;

public class VeiculoDao {

	private final EntityManager em;
	
	@Inject
	public VeiculoDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public VeiculoDao() {
		this(null); // para uso do CDI
	}
	
	public Veiculo busca(Veiculo veiculo) {
		return em.find(Veiculo.class, veiculo.getId());
	}

	@SuppressWarnings("unchecked")
	public List<Veiculo> lista() {
		return em.createQuery("select v from Veiculo v").getResultList();
	}
	
	public void adiciona(Veiculo veiculo) {
		em.getTransaction().begin();
		em.persist(veiculo);
		em.getTransaction().commit();
	}
	
	public void altera(Veiculo veiculo) {
		em.getTransaction().begin();
		em.merge(veiculo);
		em.getTransaction().commit();
	}
	
	public Veiculo buscaNaUnidade(Long unidadeId, String placa) {
		Veiculo veiculo;
		String strQuery = "SELECT v FROM Veiculo v"
				+ " WHERE v.unidade.id = :unidadeId AND v.placa = :placa";
		try {
			Query query = em.createQuery(strQuery, Veiculo.class);
			query.setParameter("unidadeId", unidadeId);
			query.setParameter("placa", placa);
			veiculo = (Veiculo) query.getSingleResult();
		} catch (NoResultException nrExp) {
			veiculo = null;
		}
		return veiculo;
	}
	
	public List<Veiculo> listaDaUnidade(Long unidadeId) {
		List<Veiculo> veiculos;
		try {
			veiculos = em.createQuery("select v from Veiculo v"
					+ " where v.unidade.id = :unidadeId", Veiculo.class)
					.setParameter("unidadeId", unidadeId).getResultList();
		} catch (EntityNotFoundException e) {
			veiculos = new ArrayList<Veiculo>();
		}
		return veiculos;
	}
	
	public boolean existe(Veiculo veiculo, Long unidadeId) {
		return !em.createQuery("select v from Veiculo v"
			+ " where v.placa = :placa"
			+ " and v.unidade.id = :unidadeId", Veiculo.class)
			.setParameter("placa", veiculo.getPlaca())
			.setParameter("unidadeId", unidadeId)
			.getResultList().isEmpty();
	}
	
	public void remove(Veiculo veiculo) {
		em.getTransaction().begin();
		em.remove(busca(veiculo));
		em.getTransaction().commit();
	}

}
