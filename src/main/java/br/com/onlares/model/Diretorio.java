package br.com.onlares.model;

import java.net.URI;

public interface Diretorio {

	URI grava(Foto arquivo);

	Foto recupera(URI chave);
}