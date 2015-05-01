<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>
	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.custom.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/chosen.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/datepicker.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-timepicker.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/daterangepicker.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datetimepicker.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/colorpicker.min.css" />
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
				<a href="${ctx}/adminAutorizacao/lista">Tipos de Autorizações</a>
			</li>
			<li class="active">Solicitar</li>
		</ul><!-- /.breadcrumb -->
	</div>
	
	<div class="page-content">
		<div class="page-header">
			<h1>
				Solicitar Autorização
				<br />Tipo: ${tipoDeAutorizacao.descricao} 
				<br />Unidade: ${usuarioLogado.localizadorAtual.unidade.descricao}
			</h1>
		</div><!-- /.page-header -->
		
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<form class="form-horizontal" role="form" action="${ctx}/autorizacao/" method="post">
					<input type="hidden" name="autorizacao.unidade.id" value="${usuarioLogado.localizadorAtual.unidade.id}">
					<input type="hidden" name="autorizacao.tipoDeAutorizacao.id" value="${tipoDeAutorizacao.id}">
					<div class="form-group">
						<div class="input-group bootstrap-timepicker">
							<label class="col-sm-5 control-label" for="calendario"> Data </label>
							<div class="input-group">
								<input id="calendario" name="autorizacao.data" type="text" class="form-control date-picker" data-date-format="dd/mm/yyyy"/>
								<span class="input-group-addon">
									<i class="fa fa-calendar bigger-110"></i>
								</span>
							</div>
						</div>
					</div>
						
					<div class="space-4"></div>
						
					<div class="form-group">
						<div class="input-group bootstrap-timepicker">
							<label class="col-sm-5 control-label" for="timepicker1"> Hora </label>
							<div class="input-group">
								<input id="timepicker1" name="autorizacao.horaString" type="text" class="form-control" data-format="hh:mm" />
								<span class="input-group-addon">
									<i class="fa fa-clock-o bigger-110"></i>
								</span>
							</div>
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
		jQuery(function($) {
			//datepicker plugin
			//link
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true,
				startDate: '0d',
				//endDate: '+20d',
				//daysOfWeekDisabled: "1,3,6",
				format: 'dd/mm/yyyy',                
				language: 'pt-BR'
			})
			//show datepicker when clicking on the icon
			.next().on(ace.click_event, function(){
				console.log('next');
				$(this).prev().focus();
			});
		
			//or change it into a date range picker
			$('.input-daterange').datepicker({autoclose:true});
		
		
			//to translate the daterange picker, please copy the "examples/daterange-fr.js" contents here before initialization
			$('input[name=date-range-picker]').daterangepicker({
				'applyClass' : 'btn-sm btn-success',
				'cancelClass' : 'btn-sm btn-default',
				locale: {
					applyLabel: 'Apply',
					cancelLabel: 'Cancel',
				}
			})
			.prev().on(ace.click_event, function(){
				$(this).next().focus();
			});
		
		
			$('#timepicker1').timepicker({
				minuteStep: 1,
				showSeconds: false,
				showMeridian: false,
				language: 'pt-BR'
			}).next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
			
			$('#date-timepicker1').datetimepicker().next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
		
		});
	</script>
		
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		window.onload = function() {
			document.getElementById('menu_autorizacoes').className = 'active';
		};
	</script>
</content>

</body>

</html>
