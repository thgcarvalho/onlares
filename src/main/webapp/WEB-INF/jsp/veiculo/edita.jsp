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
				<a href="${ctx}/home/index">Home</a>
			</li>
			<li>
			<i class=""></i>
				<a href="${ctx}/veiculo/lista">Veículos</a>
			</li>
			<li class="active">Edita Veículo</li>
		</ul><!-- /.breadcrumb -->
	</div>
	
	<div class="page-content">
		<div class="page-header">
			<h1>
				Edita Veículo
			</h1>
		</div><!-- /.page-header -->
	
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<form class="form-horizontal" role="form" action="${ctx}/veiculo" method="post">
					<input type="hidden" name="_method" value="PUT">
					<input type="hidden" name="veiculo.id" value="${veiculo.id}">
					<input type="hidden" name="veiculo.unidade.id" value="${veiculo.unidade.id}">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="nome"> Tipo* </label>

						<div class="col-sm-9">
							<input type="text" required="required" id="nome" name="veiculo.tipo" value="${veiculo.tipo}" 
							placeholder="Tipo" maxlength="45" autofocus class="col-xs-10 col-sm-5 text-uppercase" 
							data-rel="tooltip" title="Ex: CARRO, MOTO, QUADRICICLO ..." />
						</div>
					</div>

					<div class="space-4"></div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="placa"> Placa </label>

						<div class="col-sm-9">
							<input type="text" name="veiculo.placa" value="${veiculo.placa}" 
							placeholder="Placa" maxlength="8" class="col-xs-10 col-sm-5 input-mask-plaque text-uppercase" id="placa" />
						</div>
					</div>
					
					<div class="space-4"></div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="marca"> Marca</label>

						<div class="col-sm-9">
							<input type="text" name="veiculo.marca" value="${veiculo.marca}" 
							placeholder="Marca" maxlength="45" class="col-xs-10 col-sm-5" id="marca" 
							data-rel="tooltip" title="Ex: Ford, Fiat, Chevrolet, Volkswagen, Honda ..." />
						</div>
					</div>
					
					<div class="space-4"></div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="modelo"> Modelo </label>

						<div class="col-sm-9">
							<input type="text" name="veiculo.modelo" value="${veiculo.modelo}" 
							placeholder="Modelo" maxlength="45" class="col-xs-10 col-sm-5" id="modelo" 
							data-rel="tooltip" title="Ex: Fiesta, Palio, Celta, Gol, Titan ..." />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="cor"> Cor </label>

						<div class="col-sm-9">
							<input type="text" name="veiculo.cor" value="${veiculo.cor}" 
							placeholder="Cor" maxlength="45" class="col-xs-10 col-sm-5" id="cor" />
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
			$.mask.definitions['~']='[+-]';
			$('.input-mask-plaque').mask('aaa-9999');
		});
		
		$('[data-rel=tooltip]').tooltip({container:'body'});
	</script>
		
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		window.onload = function() {
			document.getElementById('menu_veiculos').className = 'active';
		};
	</script>
</content>

</body>

</html>
