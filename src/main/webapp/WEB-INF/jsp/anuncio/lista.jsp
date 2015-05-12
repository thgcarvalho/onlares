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

			<li class="active">Anúncios</li>
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
				Anúncios
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
									<th>Atividade</th>
									<th>Título</th>
									<th>Anúnciante</th>
									<th>Fones</th>
								</tr>
							</thead>
	
							<tbody>
								<c:forEach items="${anuncioList}" var="anuncio">
									<tr>
										<td>${anuncio.atividade}</td>
										<td>
											<a href="${linkTo[AnuncioController].visualiza(anuncio.id)}">
											${anuncio.titulo}</a>
										</td>
										<td>${anuncio.anunciante}</td>
										<td>${anuncio.fones}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div><!-- /.span -->
				</div><!-- /.row -->
			</div>
		</div>
	</div>
</div>
	
<content tag="local_script">
	<!-- page specific plugin scripts -->
	<script src="${ctx}/assets/js/jquery.dataTables.min.js"></script>
	<script src="${ctx}/assets/js/jquery.dataTables.bootstrap.min.js"></script>
	<script src="${ctx}/assets/js/dataTables.tableTools.min.js"></script>
	<script src="${ctx}/assets/js/dataTables.colVis.min.js"></script>
	<!-- inline scripts related to this page -->
	
</content>

</body>

</html>
