<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>
	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.custom.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/chosen.min.css" />
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
				<a href="${ctx}/admin/index">Home</a>
			</li>
			<li>
			<i class=""></i>
				<a href="${ctx}/adminAutorizacao/lista">Espaços</a>
			</li>
			<li class="active">Edita Tipo de Autorização</li>
		</ul><!-- /.breadcrumb -->
	</div>
	
	<div class="page-content">
		<div class="page-header">
			<h1>
				Edita Tipo de Autorização
			</h1>
		</div><!-- /.page-header -->
	
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<form class="form-horizontal" role="form" action="${ctx}/adminAutorizacao" method="post">
					<input type="hidden" name="_method" value="PUT">
					<input type="hidden" name="tipoDeAutorizacao.id" value="${tipoDeAutorizacao.id}">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="descricao"> Descrição* </label>
						<div class="col-sm-9">
							<input type="text" required="required" id="descricao" name="tipoDeAutorizacao.descricao" value="${tipoDeAutorizacao.descricao}" 
							placeholder="Descrição" maxlength="45" autofocus class="col-xs-10 col-sm-5" 
							data-rel="tooltip" title="Ex: Obra, Mudança, Serviço ..." />
						</div>
					</div>

					<div class="clearfix form-actions">
						<div class="col-md-offset-5">
							<button class="btn btn-info" type="submit">
								<i class="ace-icon fa fa-check bigger-110"></i>
								Salvar
							</button>
						</div>
					</div>
					
				</form>
				<!-- END PAGE CONTENT -->
			</div> <!-- div class="col-xs-12" -->
		</div> <!-- div class="row" -->
	</div>
</div>
	
<content tag="local_script">
	<!-- page specific plugin scripts -->

	<!--[if lte IE 8]>
	  <script src="${ctx}/assets/js/excanvas.min.js"></script>
	<![endif]-->
	<script src="${ctx}/assets/js/jquery-ui.custom.min.js"></script>
	<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="${ctx}/assets/js/chosen.jquery.min.js"></script>
	<script src="${ctx}/assets/js/fuelux.spinner.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-timepicker.min.js"></script>
	<script src="${ctx}/assets/js/moment.min.js"></script>
	<script src="${ctx}/assets/js/daterangepicker.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-colorpicker.min.js"></script>
	<script src="${ctx}/assets/js/jquery.knob.min.js"></script>
	<script src="${ctx}/assets/js/jquery.autosize.min.js"></script>
	<script src="${ctx}/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
	<script src="${ctx}/assets/js/jquery.maskedinput.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-tag.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$('[data-rel=tooltip]').tooltip({container:'body'});
		
		$('#maximareserva').ace_spinner({value:0,min:0,max:999,step:1, btn_up_class:'btn-info' , btn_down_class:'btn-info'}); 
		$('#minimareserva').ace_spinner({value:0,min:0,max:999,step:1, btn_up_class:'btn-info' , btn_down_class:'btn-info'});
		$('#minimacancela').ace_spinner({value:0,min:0,max:999,step:1, btn_up_class:'btn-info' , btn_down_class:'btn-info'});
		$('#reservasquantidade').ace_spinner({value:0,min:0,max:999,step:1, btn_up_class:'btn-info' , btn_down_class:'btn-info'});
		$('#reservasdias').ace_spinner({value:0,min:0,max:999,step:1, btn_up_class:'btn-info' , btn_down_class:'btn-info'});
	</script>
	
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		window.onload = function() {
			document.getElementById('menuadmin_autorizacoes').className = 'active';
		};
	</script>
</content>

</body>

</html>
