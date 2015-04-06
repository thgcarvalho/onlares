package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.exception.RestricaoDeIntegridadeException;
import br.com.onlares.model.Condominio;
import br.com.onlares.model.Localizador;
import br.com.onlares.model.Unidade;
import br.com.onlares.model.Usuario;

public class UnidadeDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public UnidadeDao(EntityManager em, UsuarioLogado usuarioLogado) {
		// TODO VERIFICAR A NECESSIDADE DE OBTER UsuarioLogado NESSE DAO
		this.em = em;
		this.condominioId = usuarioLogado.getLocalizadorAtual().getCondominio().getId();
	}
	
	@Deprecated
	public UnidadeDao() {
		this(null, null); // para uso do CDI
	}
	
	public void adiciona(Unidade unidade) {
		Localizador localizador = new Localizador();
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		localizador.setCondominio(condominio);
		localizador.setUnidade(unidade);
		
		em.getTransaction().begin();
		em.persist(unidade);
		em.persist(localizador);
		em.getTransaction().commit();
	}
		
	public void altera(Unidade unidade) {
		if (mesmoCondominio(unidade)) {
			em.getTransaction().begin();
			em.merge(unidade);
			em.getTransaction().commit();
		}
	}

	public void remove(Unidade unidade) {
		if (mesmoCondominio(unidade)) {
			List<Localizador> localizadores = em.createQuery("select l from Localizador l"
					+ " where l.unidade.id = :unidadeId", Localizador.class)
					.setParameter("unidadeId", unidade.getId()).getResultList();
			
			em.getTransaction().begin();
			System.out.println("localizadores.size()=" + localizadores.size());
			for (Localizador localizador : localizadores) {
				System.out.println(localizador.getId());
				em.remove(localizador);
			}
			em.remove(unidade);
			em.getTransaction().commit();
		}
	}
	
	public void verificaIntegridade(long unidadeId) throws RestricaoDeIntegridadeException {
		boolean temUsuario = !em.createQuery("select l.usuario from Localizador l"
				+ " where l.unidade.id = :unidadeId"
				+ " and l.condominio.id = :condominioId", Usuario.class)
				.setParameter("unidadeId", unidadeId)
				.setParameter("condominioId", condominioId)
				.getResultList().isEmpty();
		if (temUsuario) {
			// TODO throw new RestricaoDeIntegridadeException("Existe(m) usuário(s) relacionados com está unidade");
		}
	}

	public Unidade busca(Unidade unidade) {
		return buscaPorId(unidade.getId());
	}
	
	public Unidade buscaPorId(long id) {
		Unidade unidade = em.find(Unidade.class, id);
		if (mesmoCondominio(unidade)) {
			return unidade;
		}
		return null;
	}

	public boolean existe(Unidade unidade) {
		return !em.createQuery("select l.unidade from Localizador l"
				+ " where l.unidade.descricao = :descricao"
				+ " and l.condominio.id = :condominioId", Unidade.class)
			.setParameter("descricao", unidade.getDescricao())
			.setParameter("condominioId", condominioId)
			.getResultList().isEmpty();
	}
	
	public List<Unidade> lista() {
		return em.createQuery("select l.unidade from Localizador l"
			+ " where l.condominio.id = :condominioId", Unidade.class)
			.setParameter("condominioId", condominioId).getResultList();
	}
	
	private boolean mesmoCondominio(Unidade unidade) {
		boolean mesmoCondominio = !em.createQuery("select l.unidade from Localizador l"
				+ " where l.condominio.id = :condominioId",Unidade.class)
				.setParameter("condominioId", condominioId).getResultList()
				.isEmpty();
		if (mesmoCondominio) {
			return true;
		} else {
			System.out.println("CONDOMÍNIOS DIFERENTES: unidade="
					+ unidade.getDescricao() + " (" + unidade.getId() + ") != " + condominioId);
			return false;
		}
	}

}
