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

			<li class="active">Moradores</li>
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
				Moradores
				<small>
					<i class="ace-icon fa fa-angle-double-right"></i>
					lista de usuários cadastrados
				</small>
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
									<th>Nome</th>
									<th>Unidade</th>
								</tr>
							</thead>
	
							<tbody>
								<c:forEach items="${moradorList}" var="morador">
									<tr>
										<td>
											<a href="${linkTo[PerfilController].visualiza(morador.id)}" class="sender">
											${morador.nome}
											</a>
										</td>
										<td>${morador.localizacoes}</td>
										
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
	
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		window.onload = function() {
			document.getElementById('menu_moradores').className = 'active';
		};
	</script>
	
</content>

</body>

</html>
