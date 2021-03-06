<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>
	<!-- page specific plugin styles -->
</head>

<body>
<div class="main-content-inner">
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		</script>

		<ul class="breadcrumb">
			<li>
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="${ctx}/home/index">Home</a>
			</li>

			<li class="active">Pets</li>
		</ul><!-- /.breadcrumb -->
				
		<div class="nav-search" id="nav-search">
			<span class="condominium">
				<!-- COND -->
			</span>
		</div><!-- /.nav-search -->
	</div>
	
	<div class="page-content">
	
		<c:choose>
		<c:when test="${usuarioLogado.localizadorAtual.unidade.id == 0}">
		   <div class="page-header">
				<h1>
					Não é possível manipular pets em uma unidade não relacionada.
				</h1>
			</div><!-- /.page-header -->
		</c:when>
		<c:otherwise>
			<div class="page-header">
				<h1>
					Pets da Unidade - ${usuarioLogado.localizadorAtual.unidade.descricao}
				</h1>
			</div><!-- /.page-header -->
			
			<div class="clearfix">
				<div class="pull-right tableTools-buttons">
		      		<a href="${ctx}/pet/novo">
						<button class="btn btn-success" type="submit" >
							<i class="ace-icon fa fa-plus bigger-110"></i>
							Novo
						</button>
					</a>
				</div>
			</div>
	
			<%@ include file="/templates/messages.jsp"%>
		
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="col-xs-12">
							<table id="simple-table" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>Tipo</th>
										<th>Raça</th>
										<th>Nome</th>
										<th></th>
									</tr>
								</thead>
		
								<tbody>
									<c:choose>
									<c:when test="${petUnidadeList.isEmpty()}">
									    <tr>
									        <td colspan="6">A unidade não possui pets cadastrados</td>
									    </tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${petUnidadeList}" var="pet">
											<tr>
												<td>${pet.tipo}</td>
												<td>${pet.raca}</td>
												<td>${pet.nome}</td>
												<td>
													<div class="hidden-sm hidden-xs action-buttons">
														<a class="editar" href="${linkTo[PetController].edita(pet.id)}" 
															title="Editar" >
															<i class="ace-icon fa fa-pencil bigger-130"></i>
														</a>
														
														<a class="deletar" href="${linkTo[PetController].remove(pet.id)}" 
		                    								title="Remover" >
															<i class="ace-icon fa fa-trash-o bigger-130"></i>
														</a>
																					
													</div>
		
													<div class="hidden-md hidden-lg">
														<div class="inline pos-rel">
															<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">
																<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
															</button>
		
															<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
																<li>
																	<a href="${linkTo[PetController].edita(pet.id)}" 
																		class="tooltip-success" data-rel="tooltip" title="Editar">
																		<span class="green">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																		</span>
																	</a>
																</li>
		
																<li>
																	<a href="${linkTo[PetController].remove(pet.id)}" 
																		class="deletar tooltip-error" data-rel="tooltip" title="Remover" >
																		<span class="red">
																			<i class="ace-icon fa fa-trash-o bigger-120"></i>
																		</span>
																	</a>
																</li>
															</ul>
														</div>
													</div>
												</td>
											</tr>
										</c:forEach>
									</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div><!-- /.span -->
					</div><!-- /.row -->
					<!-- PAGE CONTENT ENDS -->
				</div>
			</div>
		</c:otherwise>
		</c:choose>
	</div>
</div>
	
<content tag="local_script">
	<!-- page specific plugin scripts -->
	
	<!-- inline scripts related to this page -->
	
	<!-- delete script -->
	<script type="text/javascript">
		$(".deletar").on("click", function(event) {
			event.preventDefault();
			if (confirm('Você realmente deseja exlucir esse registro?')) {
				$.ajax({
					url: $(this).attr("href"),
					type: 'POST',
					data: { _method: "DELETE"}
				}).done(function(data, textStatus, jqXHR){
					console.log("REMOVER");
				}).fail(function(jqXHR, textStatus, errorThrown){
					console.log("O item não foi removido!");
					alert("O item não foi removido!");
				});
			      
			    $(this).closest('tr').remove();
			    return false;
			}
		});
	</script>
	
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		window.onload = function() {
			document.getElementById('menu_pets').className = 'active';
		};
	</script>
	
</content>

</body>

</html>
