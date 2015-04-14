package br.com.onlares.model;

import java.util.Comparator;

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