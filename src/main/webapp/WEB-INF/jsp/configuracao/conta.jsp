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

			<li class="active">Configuções</li>
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
				Configurações
			</h1>
		</div><!-- /.page-header -->
	
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div>
					<div id="config">
						<div class="tabbable">
							<ul class="nav nav-tabs padding-18">
								<li>
									<a href="${linkTo[ConfiguracaoController].email()}">
										<i class="green ace-icon fa fa-user bigger-120"></i>
										Email
									</a>
								</li>
								
								<li>
									<a href="${linkTo[ConfiguracaoController].notificacoes()}">
										<i class="blue ace-icon fa fa-bell-slash bigger-120"></i>
										Notificacões
									</a>
								</li>
								
								<li>
									<a href="${linkTo[ConfiguracaoController].senha()}">
										<i class="orange ace-icon fa fa-lock bigger-120"></i>
										Senha
									</a>
								</li>
								
								<li class="active">
									<a href="${linkTo[ConfiguracaoController].conta()}">
										<i class="red ace-icon fa fa-wrench bigger-120"></i>
										Conta
									</a>
								</li>
							</ul>
							
							<div class="tab-content no-border padding-24">
						
								<div class="alert alert-warning">
									<button type="button" class="close" data-dismiss="alert">
										<i class="ace-icon fa fa-times"></i>
									</button>
									<strong>Atenção!</strong>
									Você receberá uma confirmação em seu email para iniciar o processo de exclusão de conta.
									<br />
								</div>
							
								<form class="form-horizontal" role="form" action="${ctx}/configuracao/conta" method="post">
									<input type="hidden" name="_method" value="PUT">
									<input type="hidden" name="usuario.id" value="${usuarioLogado.usuario.id}">
									<div class="clearfix form-actions">
										<div class="col-md-offset-5">
											<button class="btn btn-danger" type="submit">
												<i class="ace-icon fa fa-trash bigger-110"></i>
												Excluir minha Conta
											</button>
										</div>
									</div>
								</form>
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
	
</content>

</body>

</html>
