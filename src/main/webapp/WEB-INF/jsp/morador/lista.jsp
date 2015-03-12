<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>

	<meta name="description" content="Static &amp; Dynamic Tables" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	
	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/font-awesome/4.2.0/css/font-awesome.min.css" />
	
	<!-- page specific plugin styles -->
	
	<!-- text fonts -->
	<link rel="stylesheet" href="${ctx}/assets/fonts/fonts.googleapis.com.css" />
	
	<!-- ace styles -->
	<link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
	
	<!--[if lte IE 9]>
		<link rel="stylesheet" href="${ctx}/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
	<![endif]-->
	
	<!--[if lte IE 9]>
	  <link rel="stylesheet" href="${ctx}/assets/css/ace-ie.min.css" />
	<![endif]-->
	
	<!-- inline styles related to this page -->
	
	<!-- ace settings handler -->
	<script src="${ctx}/assets/js/ace-extra.min.js"></script>
	
	<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
	
	<!--[if lte IE 8]>
	<script src="${ctx}/assets/js/html5shiv.min.js"></script>
	<script src="${ctx}/assets/js/respond.min.js"></script>
	<![endif]-->

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
				${usuarioLogado.usuario.condominio.nome}
			</span>
		</div><!-- /.nav-search -->
	</div>
	
	<div class="page-content">
		<div class="page-header">
			<h1>
				Moradores
			</h1>
		</div><!-- /.page-header -->
	
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<table id="simple-table" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Nome</th>
									<th>Email</th>
									<th>Unidade</th>
								</tr>
							</thead>
	
							<tbody>
								<c:forEach items="${moradorList}" var="morador">
									<tr>
										<td>${morador.nome}</td>
										<td>${morador.email}</td>
										<td>${morador.unidade.localizacao}</td>
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
	<!-- basic scripts -->
	
	<!--[if !IE]> -->
	<script src="${ctx}/assets/js/jquery.2.1.1.min.js"></script>
	
	<!-- <![endif]-->
	
	<!--[if IE]>
	<script src="${ctx}/assets/js/jquery.1.11.1.min.js"></script>
	<![endif]-->
	
	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery || document.write("<script src='${ctx}/assets/js/jquery.min.js'>"+"<"+"/script>");
	</script>
	
	<!-- <![endif]-->
	
	<!--[if IE]>
	<script type="text/javascript">
	 window.jQuery || document.write("<script src='${ctx}/assets/js/jquery1x.min.js'>"+"<"+"/script>");
	</script>
	<![endif]-->
	<script type="text/javascript">
		if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
	</script>
	<script src="${ctx}/assets/js/bootstrap.min.js"></script>
	
	<!-- page specific plugin scripts -->
	<script src="${ctx}/assets/js/jquery.dataTables.min.js"></script>
	<script src="${ctx}/assets/js/jquery.dataTables.bootstrap.min.js"></script>
	<script src="${ctx}/assets/js/dataTables.tableTools.min.js"></script>
	<script src="${ctx}/assets/js/dataTables.colVis.min.js"></script>
	
	<!-- ace scripts -->
	<script src="${ctx}/assets/js/ace-elements.min.js"></script>
	<script src="${ctx}/assets/js/ace.min.js"></script>
	
	
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
