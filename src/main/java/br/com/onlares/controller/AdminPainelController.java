package br.com.onlares.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.onlares.annotations.Admin;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AdminPainelController {
	
	@Admin
	@Get("/adminPainel/index")
	public void index() {

	}
	
}