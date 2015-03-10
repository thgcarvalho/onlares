//package br.com.onlares.dao;
//
//import java.net.URI;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//
//import br.com.onlares.model.Arquivo;
//
//public class ArquivoDao implements Diretorio {
//	
//	private final EntityManager em;
//	
//	@Inject
//	public ArquivoDao(EntityManager em) {
//		this.em = em;
//	}
//	
//	@Deprecated
//	public ArquivoDao() {
//		this(null); // para uso do CDI
//	}
//
//	@Override
//	public URI grava(Arquivo arquivo) {
//		em.getTransaction().begin();
//		em.persist(arquivo);
//		em.getTransaction().commit();
//		return getURI(arquivo);
//	}
//	
//	public URI getURI(Arquivo arquivo) {
//		return URI.create("db://" + arquivo.getId());
//	}
//	
//	@Override
//	public Arquivo recupera(URI chave) {
//		if (chave == null) {
//			return null;
//		}
//		// scheme é o protocolo. No caso de db:// é o db
//		if (!chave.getScheme().equals("db")) {
//			throw new IllegalArgumentException(chave + " não é uma URI de banco de dados");
//		}
//		// authority é o que vem depois do db://
//		Long id = Long.valueOf(chave.getAuthority());
//		return em.find(Arquivo.class, id);
//	}
//	
//	@Override
//	public void deleta(Arquivo arquivo) {
//		em.getTransaction().begin();
//		em.remove(arquivo);
//		em.getTransaction().commit();
//	}
//	
//}