package br.com.onlares.observers;

import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.events.VRaptorInitialized;
import br.com.onlares.model.Produto;
import br.com.onlares.model.Usuario;
import br.com.onlares.util.JPAUtil;

public class InitialDataObserver {

	/**
	 * Esse é um exemplo simples de observer do CDI com VRaptor 4
	 * 
	 * Ele é utilizado para inserir um usuário e alguns produtos
	 * sempre que a app é startada, pois estamos usando um banco
	 * em memória. Você pode ler mais a respeito de observers em:
	 *  
	 * http://www.vraptor.org/pt/docs/eventos/
	 */
	public void insert(@Observes VRaptorInitialized event) {
		
		EntityManager em = JPAUtil.criaEntityManager();
		em.getTransaction().begin();

		em.persist(new Usuario("onlares@onlares.com.br", "onlares"));

		em.persist(new Produto("Produto 01", 100.99, 2));
		em.persist(new Produto("Produto 02", 10.99, 4));
		em.persist(new Produto("Produto 03", 1.99, 6));
		
		em.getTransaction().commit();
		em.close();
	}	
}