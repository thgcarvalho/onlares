package br.com.onlares.comparador;

import java.util.Comparator;

import br.com.onlares.model.Unidade;

public class ComparadorUnidade implements Comparator<Unidade> {

	@Override
	public int compare(Unidade u1, Unidade u2) {
		return u1.getDescricao().compareTo(u2.getDescricao());
	}

}