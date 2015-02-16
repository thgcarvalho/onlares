package br.com.onlares.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.onlares.annotations.Admin;

@Controller
public class AdminController {
	
	@Admin
	@Get("/admin/index")
	public void index() {

	}
	
}