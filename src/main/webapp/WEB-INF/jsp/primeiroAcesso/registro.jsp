<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/templates/head.jsp"%>
	<!-- grandev -->
	<link rel="stylesheet" href="${ctx}/resources/css/style.css" />
	
	<link rel="icon" href="${ctx}/resources/images/favicon-16.png" />
	<link rel="shortcut icon" href="${ctx}/resources/onlares.ico" />
	<link rel="shortcut icon" href="${ctx}/resources/images/favicon-32.png" />
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

											<form action="<c:url value="registrar" />" id="validation-form" method="post">
	    										<fieldset>
											        <label class="block clearfix"> <span class="block input-icon input-icon-right">
											                <input type="text" id="email" name="usuario.email" value="" 
											                placeholder="Email" maxlength="45" class="form-control" />
											                <i class="ace-icon fa fa-envelope"></i>
											            </span>
											
											        </label>
											        <label class="block clearfix"> <span class="block input-icon input-icon-right">
											                <input type="text" id="nome" name="usuario.nome" value="" 
											                placeholder="Nome" maxlength="60" class="form-control" />
											                <i class="ace-icon fa fa-user"></i>
											            </span>
											
											        </label>
											        <label class="block clearfix"> <span class="block input-icon input-icon-right">
											                <input type="password" id="senha" name="usuario.senha" value=""
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
			
				$('#validation-form').validate({
					errorElement: 'div',
					errorClass: 'help-block',
					focusInvalid: false,
					ignore: "",
					rules: {
						"usuario.email": {
							required: true,
							email:true
						},
						"usuario.nome": {
							required: true
						},
						"usuario.senha": {
							required: true,
							minlength: 6
						},
						confirmar_senha: {
							required: true,
							minlength: 6,
							equalTo: "#senha"
						},
						termos: {
							required: true,
						}
					},
			
					messages: {
						"usuario.email": {
							required: "Email é obrigatório.",
							email: "Insira um email válido."
						},
						"usuario.nome": {
							required: "Nome é obrigatório.",
							email: "Insira um email válido."
						},
						"usuario.senha": {
							required: "Senha é obrigatória.",
							minlength: "Insira uma senha com no mínimo 6 caracteres"
						},
						confirmar_senha: {
							required: "Confirmação de senha é obrigatória.",
							minlength: "Insira uma senha com no mínimo 6 caracteres",
							equalTo: "A Senhas não conferem."
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
					}
				});
				
			})
		</script>
	</body>
</html>


