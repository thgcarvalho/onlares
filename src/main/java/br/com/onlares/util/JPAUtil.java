package br.com.onlares.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static EntityManager criaEntityManager() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("onlares");
		return factory.createEntityManager();
	}
}
