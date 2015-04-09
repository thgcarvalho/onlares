package br.com.onlares.model;

import java.util.Comparator;

public class ComparadorUnidade implements Comparator<Unidade> {

	@Override
	public int compare(Unidade u1, Unidade u2) {
		return u1.getDescricao().compareTo(u2.getDescricao());
	}

}