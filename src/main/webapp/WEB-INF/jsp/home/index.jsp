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
	
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-sm-6">
						<h3 class="header smaller lighter blue">
							<i class="ace-icon fa fa-bullhorn"></i>
							Alertas
						</h3>

						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert">
								<i class="ace-icon fa fa-times"></i>
							</button>

							<strong>
								<i class="ace-icon fa fa-times"></i>
								Oh pressão!
							</strong>

							Mudar algumas coisas e tente enviar novamente.
							<br />
						</div>

						<div class="alert alert-warning">
							<button type="button" class="close" data-dismiss="alert">
								<i class="ace-icon fa fa-times"></i>
							</button>
							<strong>Atenção!</strong>

							Melhor visualizar os avisos, você não está olhando esta área.
							<br />
						</div>

						<div class="alert alert-block alert-success">
							<button type="button" class="close" data-dismiss="alert">
								<i class="ace-icon fa fa-times"></i>
							</button>

							<strong>
								<i class="ace-icon fa fa-check"></i>
								Muito bem!
							</strong>
							Você lidado com êxito todas as mensagem de alerta.
							<br />
						</div>

						<div class="alert alert-info">
							<button type="button" class="close" data-dismiss="alert">
								<i class="ace-icon fa fa-times"></i>
							</button>
							<strong>Aviso!</strong>
							Esse alerta precisa de sua atenção, mas não é super importante.
							<br />
						</div>
					</div><!-- /.col -->

					<div class="col-sm-6">
						<h3 class="header smaller lighter blue">
							<i class="ace-icon fa fa-rss"></i>
							Notícias
						</h3>

						<div class="well">
							<h4 class="green smaller lighter">Inadimplência</h4>
							 A inadimplência em condomínios e loteamentos pelo país afora revolta moradores e dá origem a ações judiciais de cobrança que se arrastam por anos e anos. Síndicos, administradores e advogados…
						</div>
						<div class="well well-lg">
							<h4 class="green smaller lighter">Animais</h4>
							Não é permitido ao síndico ou a assembleia deliberar em detrimento ao direito de propriedade. A convivência com animais em condomínios é uma das grandes causas de discórdias e brigas…
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
				
				<hr />
				
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-box">
							<div class="widget-header">
								<h4 class="smaller">
									EDITAL DE CONVOCAÇÃO
									<small>22/05/2015</small>
								</h4>
							</div>

							<div class="widget-body">
								<div class="widget-main">
									<p class="muted">
										Pelo presente Edital de Convocação, e na melhor forma de direito, a Administração do COND. RESIDENCIAL GIARDINI DI PADOVA, situado à Av. Godofredo Maciel, 3255 - Maraponga, convoca todos os condôminos do referido Condomínio, para comparecerem à Assembleia Geral Extraordinária, a se realizar no SALÃO DE FESTAS do Condomínio, às 19:00 horas do dia 22 de maio de 2015, em primeira convocação, com o quorum mínimo de ½ (metade) dos titulares, e às 19:30 horas do mesmo dia (segunda-feira), em segunda convocação, com qualquer número, oportunidade em que serão deliberados os seguintes assuntos:
									</p>	
									<ul>
										<li>PRESTAÇÃO DE CONTAS REFERENTE AOS MESES DE OUTUBRO, NOVEMBRO E DEZEMBRO DE 2014;</li>
										<li>PREVISÃO ORÇAMENTÁRIA REFERENTE AO EXERCÍCIO DE 2015 ( REAJUSTE DA TAXA DE CONDOMÍNIO );</li>
										<li>REVISÃO E ALTERAÇÃO DAS REGRAS DE CONVIVÊNCIA;</li>
										<li>DIVERSOS;</li>
									</ul>	
									<p class="muted">
										É de se esclarecer que as deliberações de assembléias gerais obrigam a todos seus proprietários, independentemente de seu comparecimento ou uso do voto, não tendo direito a voto os proprietários das unidades inadimplentes e morador.
									</p>
									<br />
									<p>
										<button class="btn btn-success btn-sm tooltip-successs">Comparecerei</button>
										<button class="btn btn-warning btn-sm tooltip-warning">Talvéz</button>
										<button class="btn btn-danger btn-sm tooltip-error">Não poderei</button>
									</p>
								</div>
							</div>
						</div>
					</div><!-- /.col -->
								
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
			$('#menu_home').addClass('active');
		});
	</script>
</content>

</body>

</html>
