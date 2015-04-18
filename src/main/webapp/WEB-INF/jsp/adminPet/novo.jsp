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
				<a href="${ctx}/adminPet/lista">Pets</a>
			</li>
			<li class="active">Novo Pet</li>
		</ul><!-- /.breadcrumb -->
	</div>
	
	<div class="page-content">
		<div class="page-header">
			<h1>
				Novo Pet
			</h1>
		</div><!-- /.page-header -->
	
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<form class="form-horizontal" role="form" action="${ctx}/adminPet/" method="post">
			
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="nome"> Tipo* </label>

						<div class="col-sm-9">
							<input type="text" required="required" id="nome" name="pet.tipo" value="${pet.tipo}" 
							placeholder="Tipo" maxlength="45" autofocus class="col-xs-10 col-sm-5 text-uppercase" 
							data-rel="tooltip" title="Ex: CACHORRO, GATO ..." />
						</div>
					</div>

					<div class="space-4"></div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="raca"> Raça </label>

						<div class="col-sm-9">
							<input type="text" name="pet.raca" value="${pet.raca}" 
							placeholder="Raça" maxlength="45" class="col-xs-10 col-sm-5" id="marca" 
							data-rel="tooltip" title="Ex: Yorkshire, Poodle, Persa, Siamês ..." />
						</div>
					</div>
					
					<div class="space-4"></div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="nome"> Nome </label>

						<div class="col-sm-9">
							<input type="text" name="pet.nome" value="${pet.nome}" 
							placeholder="Nome" maxlength="45" class="col-xs-10 col-sm-5" id="nome" />
						</div>
					</div>
					
					<div class="space-4"></div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="unidade"> Unidade* </label>
						<div class="col-sm-5">
							<select name="pet.unidade.id" class="col-xs-10 col-sm-5" id="form-field-select-4" data-placeholder="Selecione a unidade...">
								<c:forEach items="${unidadeList}" var="unidade" >							
									<c:choose>
										<c:when test="${pet.unidade.id == unidade.id}">
											<option value="${unidade.id}" selected="selected">${unidade.descricao}</option>
										</c:when>
										<c:otherwise>
											<option value="${unidade.id}">${unidade.descricao}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
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
			document.getElementById('menuadmin_pets').className = 'active';
		};
	</script>
</content>

</body>

</html>
