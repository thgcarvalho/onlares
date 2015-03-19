package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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
	
	public List<Veiculo> listaDaUnidade(Long unidadeId) {
		return em.createQuery("select v from Veiculo v"
				+ " where v.unidade.id = :unidadeId", Veiculo.class)
				.setParameter("unidadeId", unidadeId).getResultList();
	}

}
