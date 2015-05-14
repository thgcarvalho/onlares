<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>
	<meta name="description" content="" />
	<!-- page specific plugin styles -->
</head>

<body>

<div class="main-content-inner">
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		</script>

		<ul class="breadcrumb">
			<li class="active">
				<i class="ace-icon fa fa-home home-icon"></i>
				Home
			</li>
		</ul><!-- /.breadcrumb -->
		
	</div>

	<div class="page-content">
	
		<div class="page-header">
			<h1>
				Documentos
			</h1>
		</div><!-- /.page-header -->
		
		<div class="clearfix">
			<div class="pull-right tableTools-buttons">
	      		<a href="${ctx}/adminDocumento/novo">
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
					<c:choose>
						<c:when test="${documentoList.isEmpty()}">
							<div class="col-sm-12">
						    	<h4>Não existem documentos cadastrados</h4>
						    </div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${documentoList}" var="documento">
								<div class="col-xs-3">
									<h3 class="header smaller lighter blue">
										<i class="ace-icon fa fa-file"></i>
										${documento.titulo}
									</h3>
									
									<div class="hidden-sm hidden-xs action-buttons pull-right">
										<a class="deletar" href="${linkTo[AdminDocumentoController].remove(documento.id)}" 
                  								title="Remover '${documento.titulo}'" >
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
													<a href="${linkTo[AdminDocumentoController].remove(documento.id)}" 
														class="deletar tooltip-error" data-rel="tooltip" title="Remover" >
														<span class="red">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</span>
													</a>
												</li>
											</ul>
										</div>
									</div>
									<br/>
									<a href="${linkTo[AdminDocumentoController].documento(documento.id)}" class="thumbnail" target="_blank">
										<img class="img-responsive" src="${ctx}/resources/images/document-down-icon.png" alt="${documento.titulo}" />
									</a>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
				<!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div><!-- /.page-content -->
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
					data: { _method: "DELETE"},
					success: function(data) {
		   				location.href = '<c:url value="/adminDocumento/lista"/>';
	   				}
				}).done(function(data, textStatus, jqXHR){
					console.log("REMOVER");
				}).fail(function(jqXHR, textStatus, errorThrown){
					console.log("O item não foi removido!");
					alert("O item não foi removido!");
				});
			      
			    //$(this).closest('tr').remove();
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
	
		$(function() {
			$('#menuadmin_documentos').addClass('active');
		});
	</script>
</content>

</body>

</html>
