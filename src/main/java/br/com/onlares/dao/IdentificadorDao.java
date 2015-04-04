package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.model.Condominio;
import br.com.onlares.model.Identificador;

public class IdentificadorDao {

	private final EntityManager em;
	
	@Inject
	public IdentificadorDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public IdentificadorDao() {
		this(null); // para uso do CDI
	}
	
	public List<Identificador> lista(Long usuarioId) {
		return em.createQuery("select i from Identificador i"
				+ " where i.usuario.id = :usuarioId", Identificador.class)
				.setParameter("usuarioId", usuarioId).getResultList();
	}
	
	public Identificador busca(Identificador identificador) {
		return buscaPorId(identificador.getId());
	}
	
	public Identificador buscaPorId(Long id) {
		return em.find(Identificador.class, id);
	}
	
	public Identificador buscaPorChave(Long usuarioId, Long condominioId, Long unidadeId) {
		Identificador identificador;
		String strQuery = "SELECT i FROM Identificador i WHERE "
				+ "i.usuarioId = :usuarioId"
				+ "i.condominio.id = :condominioId"
				+ "i.unidade.id = :unidadeId";
		try {
			Query query = em.createQuery(strQuery, Identificador.class);
			query.setParameter("usuarioId", usuarioId);
			query.setParameter("condominioId", condominioId);
			query.setParameter("usuarioId", usuarioId);
			identificador = (Identificador) query.getSingleResult();
		} catch (NoResultException nrExp) {
			identificador = null;
		}
		return identificador;
	}
	
	public Condominio buscaCondominio(Long usuarioId) {
		// TODO implementar
		return new Condominio();
	}
	
}
