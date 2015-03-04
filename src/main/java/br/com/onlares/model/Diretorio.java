package br.com.onlares.model;

import java.net.URI;

public interface Diretorio {

	URI grava(Arquivo arquivo);

	Arquivo recupera(URI chave);
}