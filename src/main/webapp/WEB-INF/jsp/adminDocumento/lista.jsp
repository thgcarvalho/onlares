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
						    <h2>Não existem documentos cadastrados</h2>
						</c:when>
						<c:otherwise>
							<c:forEach items="${documentoList}" var="documento">
								<div class="col-xs-3">
									<h3 class="header smaller lighter blue">
										<i class="ace-icon fa fa-file"></i>
										${documento.titulo}
									</h3>
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
	
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		$(function() {
			$('#menu_documentos').addClass('active');
		});
	</script>
</content>

</body>

</html>
