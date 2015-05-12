package br.com.onlares.controller;

import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.UnidadeDao;
import br.com.onlares.dao.VeiculoDao;
import br.com.onlares.model.Veiculo;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AdminAvisoController {
	
	@SuppressWarnings("unused")
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminAvisoController(Validator validator, Result result) {
		this.validator = validator;
		this.result = result;
	}
	
	public AdminAvisoController() {
		this(null, null);
	}
	
	@Admin
	@Get("/adminAviso/novo")
	public void novo() {
	}
	
	@Admin
	@Post("/adminAviso/")
	public void adiciona(String editor1, HttpServletRequest request) {
		System.out.println("Editor=" + editor1);
		
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            System.out.println("paramName=" + paramName);
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                System.out.println("paramValue=" + paramValue);
            }
        }

		
//		if (checkNull(veiculo.getTipo()).equals("")) {
//			validator.add(new I18nMessage("veiculo.adiciona", "campo.obrigatorio", "Tipo"));
//		}
//		validator.onErrorUsePageOf(this).novo();
//	
//		veiculo.setTipo(veiculo.getTipo().toUpperCase());
//		if (veiculo.getPlaca() != null) {
//			veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
//		}
//		veiculoDao.adiciona(veiculo);
//		result.include("notice", "Veículo adicionado com sucesso!");
		result.redirectTo(this).novo();
	}
	
}