package br.com.onlares.dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import br.com.onlares.model.Condominio;
import br.com.onlares.model.Constantes;
import br.com.onlares.model.Localizador;
import br.com.onlares.model.Unidade;
import br.com.onlares.model.Usuario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class UsuarioDaoTest {
	
	private Condominio condominio;
	
	@Before
	public void criaCondominioInexistente() {
		this.condominio = new Condominio();
		this.condominio.setId(Constantes.CONDOMINIO_INEXISTENTE_ID);
	}
	
	
	@Test
	public void deveInserirUnidadesEmUsuarioSemUnidade() {
		EntityManager mockedEm = mock(EntityManager.class);
		Query mockedQuery = mock(Query.class);
		UsuarioDao usuarioDao = new UsuarioDao(mockedEm, null);
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		Unidade u1 = new Unidade();
		u1.setId(11L);
		Unidade u2 = new Unidade();
		u2.setId(22L);
		
		List<Long> unidades = new ArrayList<Long>();
		unidades.add(u1.getId());
		unidades.add(u2.getId());
		
		Localizador l1 = new Localizador();
		l1.setCondominio(condominio);
		l1.setUsuario(usuario);
		l1.setUnidade(u1);
		
		Localizador l2 = new Localizador();
		l2.setCondominio(condominio);
		l2.setUsuario(usuario);
		l2.setUnidade(u2);
		
		List<Localizador> localizadoresDB = new ArrayList<Localizador>();
		
		when(mockedEm.createQuery("select l from Localizador l"
				+ " where l.usuario.id = :usuarioId"
				+ " and l.condominio.id = :condominioId")).thenReturn(mockedQuery);
		
		when(mockedQuery.setParameter("usuarioId", usuario.getId())).thenReturn(mockedQuery);
		when(mockedQuery.setParameter("condominioId", condominio.getId())).thenReturn(mockedQuery);
	    when(mockedQuery.getResultList()).thenReturn(localizadoresDB);
		
		usuarioDao.altera(usuario, unidades);
		
		verify(mockedEm, times(1)).persist(l1);
		verify(mockedEm, times(1)).persist(l2);
	}
	
	@Test
	public void deveInserirUnidadesEmUsuarioComUnidade() {
		EntityManager mockedEm = mock(EntityManager.class);
		Query mockedQuery = mock(Query.class);
		UsuarioDao usuarioDao = new UsuarioDao(mockedEm, null);
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		Unidade u1 = new Unidade();
		u1.setId(11L);
		Unidade u2 = new Unidade();
		u2.setId(22L);
		
		List<Long> unidades = new ArrayList<Long>();
		unidades.add(u2.getId());
		
		
		Localizador l1 = new Localizador();
		l1.setCondominio(condominio);
		l1.setUsuario(usuario);
		l1.setUnidade(u1);
		
		Localizador l2 = new Localizador();
		l2.setCondominio(condominio);
		l2.setUsuario(usuario);
		l2.setUnidade(u2);
		
		List<Localizador> localizadoresDB = new ArrayList<Localizador>();
		localizadoresDB.add(l1);
		
		when(mockedEm.createQuery("select l from Localizador l"
				+ " where l.usuario.id = :usuarioId"
				+ " and l.condominio.id = :condominioId")).thenReturn(mockedQuery);
		
		when(mockedQuery.setParameter("usuarioId", usuario.getId())).thenReturn(mockedQuery);
		when(mockedQuery.setParameter("condominioId", condominio.getId())).thenReturn(mockedQuery);
	    when(mockedQuery.getResultList()).thenReturn(localizadoresDB);
		
		usuarioDao.altera(usuario, unidades);
		
		verify(mockedEm, times(1)).persist(l2);
	}
	
	@Test
	public void deveRemoverUnidadesDoUsuario() {
		EntityManager mockedEm = mock(EntityManager.class);
		Query mockedQuery = mock(Query.class);
		UsuarioDao usuarioDao = new UsuarioDao(mockedEm, null);
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		Unidade u1 = new Unidade();
		u1.setId(11L);
		Unidade u2 = new Unidade();
		u2.setId(22L);
		
		List<Long> unidades = new ArrayList<Long>();
		
		Localizador l1 = new Localizador();
		l1.setCondominio(condominio);
		l1.setUsuario(usuario);
		l1.setUnidade(u1);
		
		Localizador l2 = new Localizador();
		l2.setCondominio(condominio);
		l2.setUsuario(usuario);
		l2.setUnidade(u2);
		
		List<Localizador> localizadoresDB = new ArrayList<Localizador>();
		localizadoresDB.add(l1);
		localizadoresDB.add(l2);
		
		when(mockedEm.createQuery("select l from Localizador l"
				+ " where l.usuario.id = :usuarioId"
				+ " and l.condominio.id = :condominioId")).thenReturn(mockedQuery);
		
		when(mockedQuery.setParameter("usuarioId", usuario.getId())).thenReturn(mockedQuery);
		when(mockedQuery.setParameter("condominioId", condominio.getId())).thenReturn(mockedQuery);
	    when(mockedQuery.getResultList()).thenReturn(localizadoresDB);
		
		usuarioDao.altera(usuario, unidades);
		
		verify(mockedEm, times(1)).remove(l1);
		verify(mockedEm, times(1)).remove(l2);
	}
	
	@Test
	public void deveInserirTambemRemoverUnidadesDoUsuario() {
		EntityManager mockedEm = mock(EntityManager.class);
		Query mockedQuery = mock(Query.class);
		UsuarioDao usuarioDao = new UsuarioDao(mockedEm, null);
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		Unidade u1 = new Unidade();
		u1.setId(11L);
		Unidade u2 = new Unidade();
		u2.setId(22L);
		Unidade u3 = new Unidade();
		u3.setId(33L);
		
		List<Long> unidades = new ArrayList<Long>();
		unidades.add(u1.getId());
		unidades.add(u3.getId());
		
		Localizador l1 = new Localizador();
		l1.setCondominio(condominio);
		l1.setUsuario(usuario);
		l1.setUnidade(u1);
		
		Localizador l2 = new Localizador();
		l2.setCondominio(condominio);
		l2.setUsuario(usuario);
		l2.setUnidade(u2);
		
		Localizador l3 = new Localizador();
		l3.setCondominio(condominio);
		l3.setUsuario(usuario);
		l3.setUnidade(u3);
		
		List<Localizador> localizadoresDB = new ArrayList<Localizador>();
		localizadoresDB.add(l1);
		localizadoresDB.add(l2);
		
		when(mockedEm.createQuery("select l from Localizador l"
				+ " where l.usuario.id = :usuarioId"
				+ " and l.condominio.id = :condominioId")).thenReturn(mockedQuery);
		
		when(mockedQuery.setParameter("usuarioId", usuario.getId())).thenReturn(mockedQuery);
		when(mockedQuery.setParameter("condominioId", condominio.getId())).thenReturn(mockedQuery);
	    when(mockedQuery.getResultList()).thenReturn(localizadoresDB);
		
		usuarioDao.altera(usuario, unidades);
		
		verify(mockedEm, times(1)).persist(l3);
		verify(mockedEm, times(1)).remove(l2);
	}
	
}
