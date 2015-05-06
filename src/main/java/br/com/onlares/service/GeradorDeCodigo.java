package br.com.onlares.service;

import java.util.Random;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class GeradorDeCodigo {

	public String gerar(int tamanho) {
		Random random = new Random();
		// Alguns caracteres removidos para evitar semelhanca visual
		char[] caracteresAceitos = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 
				'v', 'x', 'y', 'z',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 
				'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z', 
				'1', '2', '3', '4', '5', '6', '7', '8', '9'};

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tamanho; i++) {
			sb.append(caracteresAceitos[random.nextInt(caracteresAceitos.length)]);
		}
		return sb.toString();
	}
}
