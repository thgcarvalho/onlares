package br.com.onlares.comparador;

import java.util.Comparator;

import br.com.onlares.model.Usuario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class ComparadorUsuarioNome implements Comparator<Usuario> {

	@Override
	public int compare(Usuario u1, Usuario u2) {
		if (u1.getNome() == null) {
			return -1;
		} else if (u2.getNome() == null) {
			return 1;
		} else {
			return u1.getNome().compareTo(u2.getNome());
		}
	}

}