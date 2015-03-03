<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<script src="${ctx}/assets/js/jquery.2.1.1.min.js"></script>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />

	<meta name="description" content="and Validation" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/font-awesome/4.2.0/css/font-awesome.min.css" />

	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="${ctx}/assets/css/select2.min.css" />

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

	<body class="login-layout light-login">
		<%@ include file="/templates/messages.jsp"%>
	
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<br />
							<br />
							<div class="center">
								<img alt="OnLares" src="${ctx}/resources/images/onlares.png" height="70">
								<h4 class="blue" id="id-company-text" class="blue" >&copy; GranDev</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">

								<div id="signup-box" class="signup-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="ace-icon fa fa-users blue"></i>
												Registro de novo usuário
											</h4>

											<div class="space-6"></div>
											<p> Insira os dados abaixo: </p>

										<form id="validation-form" method="post">
    										<fieldset>
										        <label class="block clearfix"> <span class="block input-icon input-icon-right">
										                <input type="text" id="email" name="email" value="" 
										                placeholder="Email" maxlength="45" class="form-control" />
										                <i class="ace-icon fa fa-envelope"></i>
										            </span>
										
										        </label>
										        <label class="block clearfix"> <span class="block input-icon input-icon-right">
										                <input type="text" id="nome" name="nome" value="" 
										                placeholder="Nome" maxlength="60" class="form-control" />
										                <i class="ace-icon fa fa-user"></i>
										            </span>
										
										        </label>
										        <label class="block clearfix"> <span class="block input-icon input-icon-right">
										                <input type="password" id="senha" name="senha" value=""
										                class="form-control" placeholder="Senha" />
										                <i class="ace-icon fa fa-lock"></i>
										            </span>
										
										        </label>
										        <label class="block clearfix"> <span class="block input-icon input-icon-right">
										                <input type="password" id="confirmar_senha" name="confirmar_senha" 
										                class="form-control" placeholder="Repita a Senha" />
										                <i class="ace-icon fa fa-retweet"></i>
										            </span>
										
										        </label>
										        <label class="block">
										            <input type="checkbox" class="ace" id="termos" name="termos" /> <span class="lbl">
										                Li e aceito os
										                <a href="#">Termos de uso</a>
										            </span>
										
										        </label>
										        <div class="space-24"></div>
										        <div class="clearfix">
										            <button type="reset" class="width-30 pull-left btn btn-sm"> <i class="ace-icon fa fa-refresh"></i>
										 				<span class="bigger-110">Limpar</span>
										
										            </button>
										            <button type="submit" class="width-65 pull-right btn btn-sm btn-success"> <span class="bigger-110">Registrar</span>
										 				<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
										
										            </button>
										        </div>
										    </fieldset>
										</form>
										</div>

										<div class="toolbar center">
											<a href="${linkTo[LoginController].login()}" class="back-to-login-link">
												<i class="ace-icon fa fa-arrow-left"></i>
												Voltar para o login
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.signup-box -->
							</div><!-- /.position-relative -->

						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		
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
		<script src="${ctx}/assets/js/fuelux.wizard.min.js"></script>
		<script src="${ctx}/assets/js/jquery.validate.min.js"></script>
		<script src="${ctx}/assets/js/additional-methods.min.js"></script>
		<script src="${ctx}/assets/js/bootbox.min.js"></script>
		<script src="${ctx}/assets/js/jquery.maskedinput.min.js"></script>
		<script src="${ctx}/assets/js/select2.min.js"></script>

		<!-- ace scripts -->
		<script src="${ctx}/assets/js/ace-elements.min.js"></script>
		<script src="${ctx}/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			
				$('[data-rel=tooltip]').tooltip();
			
				$(".select2").css('width','200px').select2({allowClear:true})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				}); 
			
			
				var $validation = false;
				$('#fuelux-wizard-container')
				.ace_wizard({
					//step: 2 //optional argument. wizard will jump to step "2" at first
					//buttons: '.wizard-actions:eq(0)'
				})
				.on('actionclicked.fu.wizard' , function(e, info){
					if(info.step == 1 && $validation) {
						if(!$('#validation-form').valid()) e.preventDefault();
					}
				})
				.on('finished.fu.wizard', function(e) {
					bootbox.dialog({
						message: "Thank you! Your information was successfully saved!", 
						buttons: {
							"success" : {
								"label" : "OK",
								"className" : "btn-sm btn-primary"
							}
						}
					});
				}).on('stepclick.fu.wizard', function(e){
					//e.preventDefault();//this will prevent clicking and selecting steps
				});
			
			
				//jump to a step
				/**
				var wizard = $('#fuelux-wizard-container').data('fu.wizard')
				wizard.currentStep = 3;
				wizard.setState();
				*/
			
				//determine selected step
				//wizard.selectedItem().step
			
			
			
				//hide or show the other form which requires validation
				//this is for demo only, you usullay want just one form in your application
				$('#skip-validation').removeAttr('checked').on('click', function(){
					$validation = this.checked;
					if(this.checked) {
						$('#sample-form').hide();
						$('#validation-form').removeClass('hide');
					}
					else {
						$('#validation-form').addClass('hide');
						$('#sample-form').show();
					}
				})
			
			
			
				//documentation : http://docs.jquery.com/Plugins/Validation/validate
			
			
				jQuery.validator.addMethod("phone", function (value, element) {
					return this.optional(element) || /^\(\d{3}\) \d{3}\-\d{4}( x\d{1,6})?$/.test(value);
				}, "Enter a valid phone number.");
			
				$('#validation-form').validate({
					errorElement: 'div',
					errorClass: 'help-block',
					focusInvalid: false,
					ignore: "",
					rules: {
						email: {
							required: true,
							email:true
						},
						senha: {
							required: true,
							minlength: 5
						},
						confirmar_senha: {
							required: true,
							minlength: 5,
							equalTo: "#senha"
						},
						nome: {
							required: true
						},
						termos: {
							required: true,
						}
					},
			
					messages: {
						email: {
							required: "Email é obrigatório.",
							email: "Insira um email válido."
						},
						nome: {
							required: "Nome é obrigatório.",
							email: "Insira um email válido."
						},
						senha: {
							required: "Senha é obrigatória.",
							minlength: "Insira uma senha segura."
						},
						confirmar_senha: {
							required: "Confirmação de senha é obrigatória.",
							equalTo: "A Senhas não estão iguais."
						},
						termos: "É necessário aceitar os termos de uso"
					},
			
			
					highlight: function (e) {
						$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
					},
			
					success: function (e) {
						$(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
						$(e).remove();
					},
			
					errorPlacement: function (error, element) {
						if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
							var controls = element.closest('div[class*="col-"]');
							if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
							else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
						}
						else if(element.is('.select2')) {
							error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
						}
						else if(element.is('.chosen-select')) {
							error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
						}
						else error.insertAfter(element.parent());
					},
			
					submitHandler: function (form) {
					},
					invalidHandler: function (form) {
					}
				});
			
				
				
				
				$('#modal-wizard-container').ace_wizard();
				$('#modal-wizard .wizard-actions .btn[data-dismiss=modal]').removeAttr('disabled');
				
				
				/**
				$('#date').datepicker({autoclose:true}).on('changeDate', function(ev) {
					$(this).closest('form').validate().element($(this));
				});
				
				$('#mychosen').chosen().on('change', function(ev) {
					$(this).closest('form').validate().element($(this));
				});
				*/
				
				
				$(document).one('ajaxloadstart.page', function(e) {
					//in ajax mode, remove remaining elements before leaving page
					$('[class*=select2]').remove();
				});
			})
		</script>
	</body>
</html>


