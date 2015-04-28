package br.com.onlares.comparador;

import java.util.Comparator;

import br.com.onlares.model.Reserva;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class ComparadorReserva implements Comparator<Reserva> {

	@Override
	public int compare(Reserva ur1, Reserva ur2) {
		return -ur1.getData().compareTo(ur2.getData());
	}

}