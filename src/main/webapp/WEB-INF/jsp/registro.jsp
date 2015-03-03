<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<link rel="icon" href="${ctx}/resources/images/favicon-16.png" />
	<link rel="shortcut icon" href="${ctx}/resources/onlares.ico" />
	<link rel="shortcut icon" href="${ctx}/resources/images/favicon-32.png" />
	
	<meta name="description" content="Login" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/font-awesome/4.2.0/css/font-awesome.min.css" />

	<!-- text fonts -->
	<link rel="stylesheet" href="${ctx}/assets/fonts/fonts.googleapis.com.css" />

	<!-- ace styles -->
	<link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" />

	<!--[if lte IE 9]>
		<link rel="stylesheet" href="${ctx}/assets/css/ace-part2.min.css" />
	<![endif]-->
	<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.min.css" />

	<!--[if lte IE 9]>
	  <link rel="stylesheet" href="${ctx}/assets/css/ace-ie.min.css" />
	<![endif]-->

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

	<!--[if lt IE 9]>
	<script src="${ctx}/assets/js/html5shiv.min.js"></script>
	<script src="${ctx}/assets/js/respond.min.js"></script>
	<![endif]-->
	<script language="javascript" type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/validacao.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
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

											<form action="" id="formulario" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" id="email" name="usuario.email" value="${usuario.email}" 
																placeholder="Email" maxlength="45" class="form-control" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" id="nome" name="usuario.nome" value="${usuario.nome}" 
																placeholder="Nome" maxlength="60" class="form-control" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" id="senha" name="senha" value="${usuario.senha}"
																 class="form-control" placeholder="Senha" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" id="confirmar_senha" name="confirmar_senha" 
																class="form-control" placeholder="Repita a Senha" />
															<i class="ace-icon fa fa-retweet"></i>
														</span>
													</label>

													<label class="block">
														<input type="checkbox" class="ace" id="termos" />
														<span class="lbl">
															Li e aceito os
															<a href="#">Termos de uso</a>
														</span>
													</label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="ace-icon fa fa-refresh"></i>
															<span class="bigger-110">Limpar</span>
														</button>

														<button type="submit" class="width-65 pull-right btn btn-sm btn-success">
															<span class="bigger-110">Registrar</span>

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

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
			
		</script>
		
		
		<script type="text/javascript">
		    $(document).ready(function(){
		        $('#formulario').validate({
		            rules: {
		                nome: {
		                    required: true,
		                    minlength: 3
		                },
		                email: {
		                    required: true,
		                    email: true
		                },
		                senha: {
		                    required: true
		                },
		                confirmar_senha: {
		                    required: true,
		                    equalTo: "#senha"
		                },
		                termos: "required"
		            },
		            messages: {
		                nome: {
		                    required: "O campo nome é obrigatório.",
		                    minlength: "O campo nome deve conter no mínimo 3 caracteres."
		                },
		                email: {
		                    required: "O campo email é obrigatório.",
		                    email: "O campo email deve conter um email válido."
		                },
		                senha: {
		                    required: "O campo senha é obrigatório."
		                },
		                confirmar_senha: {
		                    required: "O campo confirmação de senha é obrigatório.",
		                    equalTo: "O campo confirmação de senha deve ser identico ao campo senha."
		                },
		                termos: "Para se cadastrar você deve aceitar os termos de contrato."
		            }
		 
		        });
		    });
		</script>
	</body>
</html>


