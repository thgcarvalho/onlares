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
				<i class="ace-icon fa fa-user home-icon"></i>
				<a href="${ctx}/home/index">Home</a>
			</li>
			<li>
			<i class=""></i>
				<a href="${ctx}/adminAviso/lista">Avisos</a>
			</li>
			<li class="active">Aviso</li>
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
				Aviso
			</h1>
		</div><!-- /.page-header -->
	
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-sm-12">
						<div class="widget-box">
							<div class="widget-header widget-header-flat">
								<h4 class="widget-title">${aviso.titulo}</h4>
							</div>
							
							<div class="widget-body">
								<div class="widget-main">
									${aviso.texto}
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- PAGE CONTENT ENDS -->
			</div> <!-- div class="col-xs-12" -->
		</div> <!-- div class="row" -->
	</div>
</div>
	
<content tag="local_script">
	<!-- page specific plugin scripts -->


	<!-- inline scripts related to this page -->
	
</content>

</body>

</html>
