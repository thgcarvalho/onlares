package br.com.onlares.controller;

import java.io.File;
import java.io.FileNotFoundException;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.FileDownload;
import br.com.onlares.dao.DocumentoDao;
import br.com.onlares.model.Documento;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class DocumentoController {
	
	private final DocumentoDao documentoDao;
	private final Result result;

	@Inject
	public DocumentoController(DocumentoDao documentoDao, Result result) {
		this.documentoDao = documentoDao;
		this.result = result;
	}
	
	@Deprecated
	public DocumentoController() {
		this(null, null);
	}
	
	@Get("/documento/lista")
	public void lista() {
		result.include("documentoList", documentoDao.listaSemArquivo());
	}
	
	@Get("/documento/{documentoId}/documento")
	public Download documento(Long documentoId, ServletContext context) throws FileNotFoundException {
		Documento documento = documentoDao.recupera(documentoId);
		if (documento != null) {
			return new ByteArrayDownload(documento.getConteudo(), documento.getContentType(), documento.getNome(), true);
		} else {
			File semFoto = new File(context.getRealPath("/resources/images/sem_foto.jpg"));
			return new FileDownload(semFoto, "image/jpg", "sem_foto.jpg");
		}
	}
	
	
}