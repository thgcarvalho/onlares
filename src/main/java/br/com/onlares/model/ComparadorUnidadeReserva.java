package br.com.onlares.model;

import java.util.Comparator;

public class ComparadorUnidadeReserva implements Comparator<UnidadeReserva> {

	@Override
	public int compare(UnidadeReserva ur1, UnidadeReserva ur2) {
		return -ur1.getData().compareTo(ur2.getData());
	}

}