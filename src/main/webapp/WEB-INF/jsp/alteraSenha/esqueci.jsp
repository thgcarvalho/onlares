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
								<a href="http://www.onlares.com">
									<img alt="OnLares" src="${ctx}/resources/images/onlares.png" height="70">
								</a>
								<h4 class="blue" id="id-company-text" class="blue" ><a href="http://www.grandev.com.br">&copy; GranDev</a></h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">

								<div id="forgot-box" class="forgot-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i>
												Recuperar senha
											</h4>

											<div class="space-6"></div>
											<p>
												Insira seu email para receber as instruções
											</p>

											<form action="${ctx}/alteraSenha/enviaCodigo" method="post" >
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" name="emailDoUsuario" autofocus />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<button type="submit" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="ace-icon glyphicon glyphicon-ok"></i>
															<span class="bigger-110">Enviar!</span>
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /.widget-main -->

										<div class="toolbar center">
											<a href="${linkTo[LoginController].login()}" class="back-to-login-link">
												Voltar para o login
												<i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.forgot-box -->

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
	
	<!-- ace scripts -->
	<script src="${ctx}/assets/js/ace-elements.min.js"></script>
	<script src="${ctx}/assets/js/ace.min.js"></script>
	
	<!-- page specific plugin scripts -->
	<!-- inline scripts related to this page -->
	
	<!-- bootbox suport modal script -->
	<script src="${ctx}/assets/js/bootbox.min.js"></script>
	<script type="text/javascript">
		jQuery(function($) {
			$("#bootbox-suport").on(ace.click_event, function() {
				bootbox.dialog({
					message: "<span class='bigger-110 blue bolder'>SUPORTE</span><br /><span class='blue bolder'>Email: contato@onlares.com</span><br /><span class='blue bolder'>Fone: (85) 8787.8740</span>",
					buttons: 			
					{
						"success" :
						 {
							"label" : "<i class='ace-icon fa fa-check'></i> OK",
							"className" : "btn-sm btn-success",
							"callback": function() {
								//Example.show("great success");
							}
						}
					}
				});
			});
		});
	</script>
	</body>
</html>


