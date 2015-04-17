package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.ComparadorUnidade;
import br.com.onlares.model.Constantes;
import br.com.onlares.model.Unidade;
import br.com.onlares.model.Veiculo;

public class VeiculoDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public VeiculoDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		if (usuarioLogado != null && usuarioLogado.getUsuario() != null
				&& usuarioLogado.getLocalizadorAtual().getCondominio() != null) {
			this.condominioId = usuarioLogado.getLocalizadorAtual().getCondominio().getId();
		} else {
			this.condominioId = Constantes.CONDOMINIO_INEXISTENTE_ID;
		}
	}
	
	@Deprecated
	public VeiculoDao() {
		this(null, null); // para uso do CDI
	}
	
	public Veiculo busca(Veiculo veiculo) {
		return em.find(Veiculo.class, veiculo.getId());
	}

	public List<Veiculo> lista() {
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		List<Unidade> unidadesDoCondominio = em.createQuery("select distinct l.unidade from Localizador l"
				+ " where l.condominio.id = :condominioId"
				+ " and l.unidade.id <> :unidadeId", Unidade.class)
				.setParameter("condominioId", condominioId)
				.setParameter("unidadeId", Constantes.UNIDADE_NAO_RELACIONADA_ID).getResultList();

		ComparadorUnidade comparadorUnidade = new ComparadorUnidade();
		Collections.sort(unidadesDoCondominio, comparadorUnidade);
		
		for (Unidade unidade : unidadesDoCondominio) {
			veiculos.addAll(em.createQuery("select v from Veiculo v"
				+ " where v.unidade.id = :unidadeId", Veiculo.class)
				.setParameter("unidadeId", unidade.getId()).getResultList());

		}
		return veiculos;
	}
	
	public void adiciona(Veiculo veiculo) {
		em.persist(veiculo);
	}
	
	public void altera(Veiculo veiculo) {
		em.merge(veiculo);
	}
	
	public Veiculo busca(Long veiculoId) {
		Veiculo veiculo;
		// TODO deveria obter somente do condominio em questao
		String strQuery = "SELECT v FROM Veiculo v"
				+ " WHERE v.id = :veiculoId";
		try {
			Query query = em.createQuery(strQuery, Veiculo.class);
			query.setParameter("veiculoId", veiculoId);
			veiculo = (Veiculo) query.getSingleResult();
		} catch (NoResultException nrExp) {
			veiculo = null;
		}
		return veiculo;
	}
	
	public Veiculo buscaNaUnidade(Long unidadeId, Long veiculoId) {
		Veiculo veiculo;
		String strQuery = "SELECT v FROM Veiculo v"
				+ " WHERE v.unidade.id = :unidadeId AND v.id = :veiculoId";
		try {
			Query query = em.createQuery(strQuery, Veiculo.class);
			query.setParameter("unidadeId", unidadeId);
			query.setParameter("veiculoId", veiculoId);
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
	
	public void remove(Veiculo veiculo) {
		em.remove(busca(veiculo));
	}

}
