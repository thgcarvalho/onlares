package br.com.onlares.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.FileDownload;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.DocumentoDao;
import br.com.onlares.model.Documento;

import com.google.common.io.ByteStreams;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AdminDocumentoController {
	
	private final DocumentoDao documentoDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminDocumentoController(DocumentoDao documentoDao, Validator validator, Result result) {
		this.documentoDao = documentoDao;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
	public AdminDocumentoController() {
		this(null, null, null);
	}
	
	@Admin
	@Get("/adminDocumento/lista")
	public void lista() {
		result.include("documentoList", documentoDao.listaSemArquivo());
	}
	
	@Admin
	@Get("/adminDocumento/novo")
	public void novo() {
	}
	
	@Admin
	@Get("/adminDocumento/{documentoId}/documento")
	public Download documento(Long documentoId, ServletContext context) throws FileNotFoundException {
		Documento documento = documentoDao.recupera(documentoId);
		if (documento != null) {
			return new ByteArrayDownload(documento.getConteudo(), documento.getContentType(), documento.getNome(), true);
		} else {
			File semFoto = new File(context.getRealPath("/resources/images/sem_foto.jpg"));
			return new FileDownload(semFoto, "image/jpg", "sem_foto.jpg");
		}
	}
	
	@Admin
	@Post("/adminDocumento/")
	public void adiciona(String titulo, UploadedFile arquivo) {
		if (checkNull(titulo).equals("")) {
			validator.add(new I18nMessage("documento.adiciona", "campo.obrigatorio", "Título"));
		}
		if (arquivo == null) {
			validator.add(new I18nMessage("documento.adiciona", "campo.obrigatorio", "Arquivo"));
		}
		
		validator.onErrorUsePageOf(this).novo();
		
		Documento docuemnto = null;
		try {
			docuemnto = new Documento(
					arquivo.getFileName(), 
					ByteStreams.toByteArray(arquivo.getFile()), 
					arquivo.getContentType(), 
					Calendar.getInstance());
			docuemnto.setTitulo(titulo);
		} catch (IOException e) {
			e.printStackTrace();
			validator.add(new SimpleMessage("documento.adiciona", e.getMessage()));
			validator.onErrorUsePageOf(this).novo();
		}
		
		documentoDao.grava(docuemnto);
		result.include("notice", "Documento adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}