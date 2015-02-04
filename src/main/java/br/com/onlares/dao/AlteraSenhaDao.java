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
		em.persist(alteraSenha);
	}
	
	public void edita(AlteraSenha alteraSenha) {
		em.merge(alteraSenha);
	}

	public void remove(AlteraSenha alteraSenha) {
		em.remove(buscaPorCodigo(alteraSenha.getCodigo()));
	}

	public AlteraSenha buscaPorCodigo(String codigo) {
		return em.find(AlteraSenha.class, codigo);
	}

}
