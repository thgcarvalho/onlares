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
			<li class="active">
				<i class="ace-icon fa fa-user home-icon"></i>
				Admin
			</li>
		</ul><!-- /.breadcrumb -->
		
		<div class="nav-search" id="nav-search">
			<span class="condominium">
				<!-- COND -->
			</span>
		</div><!-- /.nav-search -->

	</div>

	<div class="page-content">
	
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-users smaller-90"></i>
							Moradores
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->

					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-car smaller-90"></i>
							Veículos
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->
				</div><!-- ./row -->
				
				<div class="space-12"></div>

				<div class="row">
					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-paw smaller-90"></i>
							Pets
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->

					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-book smaller-90"></i>
							Reservas
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->
				</div><!-- ./row -->
				
				<div class="space-12"></div>

				<div class="row">
					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-sign-in smaller-90"></i>
							Autorizações
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->

					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-envelope smaller-90"></i>
							Correspondências
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->
				</div><!-- ./row -->
				
				<div class="space-12"></div>

				<div class="row">
					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-calendar smaller-90"></i>
							Calendário
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->

					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-comment smaller-90"></i>
							Mensagens
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->
				</div><!-- ./row -->
						
				<div class="space-12"></div>

				<div class="row">
					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-warning smaller-90"></i>
							Avisos
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->

					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-comments smaller-90"></i>
							Fórum
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->
				</div><!-- ./row -->
				
				<div class="space-12"></div>

				<div class="row">
					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-question smaller-90"></i>
							Enquetes
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->

					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-files-o smaller-90"></i>
							Documentos
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->
				</div><!-- ./row -->
					
				<div class="space-12"></div>

				<div class="row">
					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-phone smaller-90"></i>
							Fornecedores
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->

					<div class="col-sm-6">
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-video-camera smaller-90"></i>
							Câmeras de segurança
						</h3>

						<div class="row">
							
						</div>
					</div><!-- ./span -->
				</div><!-- ./row -->
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
	
		window.onload = function() {
			document.getElementById('menuadmin_painel').className = 'active';
		};
	</script>
</content>

</body>

</html>
