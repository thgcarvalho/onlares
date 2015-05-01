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
				<a href="${ctx}/admin/index">Home</a>
			</li>

			<li class="active">Autorizações</li>
		</ul><!-- /.breadcrumb -->
				
		<div class="nav-search" id="nav-search">
			<span class="condominium">
				<!-- COND -->
			</span>
		</div><!-- /.nav-search -->
	</div>
	
	<div class="page-content">
	
		<div class="page-header">
			<h1>
				Autorizações da Unidade - ${usuarioLogado.localizadorAtual.unidade.descricao}
			</h1>
		</div><!-- /.page-header -->
		
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<table id="simple-table" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Data</th>
									<th>Hora</th>
									<th>Autorização solicitada</th>
									<th></th>
								</tr>
							</thead>
	
							<tbody>
								<c:choose>
								<c:when test="${autorizacaoList.isEmpty()}">
								    <tr>
								        <td colspan="4">Não existem autorizações solicitadas</td>
								    </tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${autorizacaoList}" var="autorizacao">
										<tr>
											<td>${autorizacao.dataFormatada}</td>
											<td>${autorizacao.horaFormatada}</td>
											<td>${autorizacao.tipoDeAutorizacao.descricao}</td>
											<td>
												<div class="hidden-sm hidden-xs action-buttons">
													<a class="deletar" href="${linkTo[AutorizacaoController].remove(autorizacao.id)}" 
	                    								title="Cancelar" >
														<i class="ace-icon fa fa-times bigger-130"></i> Cancelar
													</a>
																				
												</div>
	
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
														</button>
	
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
															<li>
																<a href="${linkTo[AutorizacaoController].remove(autorizacao.id)}" 
																	class="deletar tooltip-error" data-rel="tooltip" title="Cancelar" >
																	<span class="red">
																		<i class="ace-icon fa fa-times bigger-120"></i>
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
			document.getElementById('menu_autorizacoes').className = 'active';
		};
	</script>
	
</content>

</body>

</html>
