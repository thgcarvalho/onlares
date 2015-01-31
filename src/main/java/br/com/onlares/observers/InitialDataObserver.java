package br.com.onlares.observers;

//import javax.enterprise.event.Observes;
//import javax.persistence.EntityManager;
//
//import br.com.caelum.vraptor.events.VRaptorInitialized;
//import br.com.onlares.model.Condominio;
//import br.com.onlares.model.Endereco;
//import br.com.onlares.model.Usuario;
//import br.com.onlares.util.JPAUtil;

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
//	public void insert(@Observes VRaptorInitialized event) {
//		
//		EntityManager em = JPAUtil.criaEntityManager();
//		em.getTransaction().begin();
//		
//		Condominio cdm1 = new Condominio();
//		cdm1.setNome("Condominios 1");
//		em.persist(cdm1);
//
//		Endereco end1 = new Endereco();
//		Endereco end2 = new Endereco();
//		end1.setApartamento("123");
//		end1.setTorre("1");
//		em.persist(end1);
//		end2.setApartamento("321");
//		end2.setTorre("2");
//		em.persist(end2);
//		
//		em.getTransaction().commit();
//		em.close();
//		
//		EntityManager em2 = JPAUtil.criaEntityManager();
//		em2.getTransaction().begin();
//		
//		Usuario usr1 = new Usuario("onlares@onlares.com.br", "onlares", "OnLares");
//		Usuario usr2 = new Usuario("tcarvalho@grandev.com.br", "onlares", "Thiago Carvalho");
//		Usuario usr3 = new Usuario("eu@joaoneto.blog.br", "onlares", "João Neto");
//		usr1.setCondominio(cdm1);
//		usr2.setCondominio(cdm1);
//		usr2.setCondominio(cdm1);
//		usr1.setEndereco(end1);
//		usr2.setEndereco(end2);
//		usr2.setEndereco(end2);
//		em2.persist(usr1);
//		em2.persist(usr2);
//		em2.persist(usr3);
//
//		em2.getTransaction().commit();
//		em2.close();
//	}	
}