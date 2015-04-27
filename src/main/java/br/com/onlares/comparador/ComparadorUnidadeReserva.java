package br.com.onlares.comparador;

import java.util.Comparator;

import br.com.onlares.model.UnidadeReserva;

public class ComparadorUnidadeReserva implements Comparator<UnidadeReserva> {

	@Override
	public int compare(UnidadeReserva ur1, UnidadeReserva ur2) {
		return -ur1.getData().compareTo(ur2.getData());
	}

}