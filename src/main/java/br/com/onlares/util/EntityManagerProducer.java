package br.com.onlares.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

public class EntityManagerProducer {
	
	@Produces
	public EntityManager criaEM() {
		return JPAUtil.criaEntityManager();
	}

}
