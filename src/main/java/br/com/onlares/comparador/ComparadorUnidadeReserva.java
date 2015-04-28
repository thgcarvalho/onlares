package br.com.onlares.comparador;

import java.util.Comparator;

import br.com.onlares.model.UnidadeReserva;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class ComparadorUnidadeReserva implements Comparator<UnidadeReserva> {

	@Override
	public int compare(UnidadeReserva ur1, UnidadeReserva ur2) {
		return -ur1.getData().compareTo(ur2.getData());
	}

}