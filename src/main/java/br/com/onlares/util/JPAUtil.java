package br.com.onlares.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf;

	public static EntityManager getEntityManager() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("onlares");
		}
		return emf.createEntityManager();
	}

	public static void close() {
		emf.close();
	}

	// protected static EntityManagerFactory emf;
	//
	// public static EntityManagerFactory getFactory() {
	// if (emf == null) {
	// emf = Persistence.createEntityManagerFactory("onlares");
	// }
	// return emf;
	// }
	//
	// @PreDestroy
	// public void fechaManager() {
	// this.manager.close();
	// }
}
