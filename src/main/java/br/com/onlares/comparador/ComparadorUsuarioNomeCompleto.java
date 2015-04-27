package br.com.onlares.comparador;

import java.util.Comparator;

import br.com.onlares.model.Usuario;

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