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
				<a href="${ctx}/adminReserva/lista">Reservas</a>
			</li>
			<li class="active">Edita Reserva</li>
		</ul><!-- /.breadcrumb -->
	</div>
	
	<div class="page-content">
		<div class="page-header">
			<h1>
				Edita Reserva
			</h1>
		</div><!-- /.page-header -->
	
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<form class="form-horizontal" role="form" action="${ctx}/adminReserva" method="post">
					<input type="hidden" name="_method" value="PUT">
					<input type="hidden" name="reserva.id" value="${reserva.id}">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="nome"> Descrição* </label>
						<div class="col-sm-9">
							<input type="text" required="required" id="nome" name="reserva.descricao" value="${reserva.descricao}" 
							placeholder="Descrição" maxlength="45" autofocus class="col-xs-10 col-sm-5" 
							data-rel="tooltip" title="Ex: Deck, Salão de Festas ..." />
						</div>
					</div>

					<div class="space-4"></div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="maximareserva"> Máximo de dias de antecedência para reservar </label>
						<div class="col-sm-9">
						<span class="input-icon">
							<input type="text" name="reserva.antecedenciaMaximaParaReservar" value="${reserva.antecedenciaMaximaParaReservar}" 
							placeholder="" maxlength="3" class="col-xs-10 col-sm-5" id="maximareserva" />
						</span>
						</div>
					</div>
					
					<div class="space-4"></div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="minimareserva"> Mínimo de dias de antecedência para reservar </label>
						<div class="col-sm-9">
						<span class="input-icon">
							<input type="text" name="reserva.antecedenciaMinimaParaReservar" value="${reserva.antecedenciaMinimaParaReservar}" 
							placeholder="" maxlength="3" class="col-xs-10 col-sm-5" id="minimareserva" />
						</span>
						</div>
					</div>
					
					<div class="space-4"></div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="minimacancela"> Mínimo de dias de antecedência para cancelar </label>
						<div class="col-sm-9">
						<span class="input-icon">
							<input type="text" name="reserva.antecedenciaMinimaParaCancelar" value="${reserva.antecedenciaMinimaParaCancelar}" 
							placeholder="" maxlength="3" class="col-xs-10 col-sm-5" id="minimacancela" />
						</span>
						</div>
					</div>
					
					<div class="space-4"></div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"> Reservas por unidade </label>
						<div class="col-sm-9">
							<input type="text" name="reserva.reservasQuantidade" value="${reserva.reservasQuantidade}" 
								placeholder="" maxlength="3" class="col-xs-10 col-sm-5" id="reservasquantidade" />
							<span class="espaco">a cada</span>
							<input type="text" name="reserva.reservasDias" value="${reserva.reservasDias}" 
								placeholder="" maxlength="3" class="col-xs-10 col-sm-5" id="reservasdias" />
							<span class="margem">dias</span>
						</div>
					</div>
					
					<div class="space-4"></div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="permitirposterior"> Permitir reserva antes da conclusão da atual? </label>
						<div class="col-sm-9">
							<label>
								<input name="reserva.permitirPosterior" class="ace ace-switch" type="checkbox" ${reserva.permitirPosterior ? 'checked' : ''}/>
								<span class="lbl" data-lbl="SIM&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NÂO"></span>
							</label>
						</div>
					</div>
					
					<div class="space-4"></div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="permitirposterior"> Permitir a utilização do espaço de unidades sem reserva </label>
						<div class="col-sm-9">
							<label>
								<input name="reserva.permitirSemReserva" class="ace ace-switch" type="checkbox" ${reserva.permitirSemReserva ? 'checked' : ''}/>
								<span class="lbl" data-lbl="SIM&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NÂO"></span>
							</label>
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
