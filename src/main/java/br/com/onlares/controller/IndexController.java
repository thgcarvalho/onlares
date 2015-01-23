package br.com.onlares.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.onlares.dao.ProdutoDao;
import br.com.onlares.model.Produto;
import br.com.onlares.util.JPAUtil;

@Controller
public class IndexController {

	private final Result result;

	protected IndexController() {
		this(null);
	}
	
	@Inject
	public IndexController(Result result) {
		this.result = result;
	}

	@Path("/")
	public void index() {

	}
	
	@Path("/teste")
	public void teste() {
		result.include("variable", "VRaptor!");
	}
	
	@Path("/teste/lista")
	public List<Produto> lista() {
		EntityManager em = JPAUtil.criaEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		return dao.lista();
	}
}