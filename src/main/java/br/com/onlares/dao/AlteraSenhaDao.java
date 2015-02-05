package br.com.onlares.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.AlteraSenha;


/**
 * @author Thiago Carvalho
 * 
 */
public class AlteraSenhaDao {

	private final EntityManager em;
	
	@Inject
	public AlteraSenhaDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public AlteraSenhaDao() {
		this(null); // para uso do CDI
	}
	
	public void adiciona(AlteraSenha alteraSenha) {
		em.getTransaction().begin();
		em.persist(alteraSenha);
		em.getTransaction().commit();
	}
	
	public void edita(AlteraSenha alteraSenha) {
		em.getTransaction().begin();
		em.merge(alteraSenha);
		em.getTransaction().commit();
	}

	public void remove(AlteraSenha alteraSenha) {
		em.getTransaction().begin();
		em.remove(buscaPorCodigo(alteraSenha.getCodigo()));
		em.getTransaction().commit();
	}

	public AlteraSenha buscaPorCodigo(String codigo) {
		return em.find(AlteraSenha.class, codigo);
	}

}
