package br.com.onlares.comparador;

import java.util.Comparator;

import br.com.onlares.model.Usuario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class ComparadorUsuarioNomeCompleto implements Comparator<Usuario> {

	@Override
	public int compare(Usuario u1, Usuario u2) {
		if (u1.getComplemento() == null) {
			return -1;
		} else if (u2.getComplemento() == null) {
			return 1;
		} else {
			return u1.getNomeCompleto().compareTo(u2.getNomeCompleto());
		}
	}

}