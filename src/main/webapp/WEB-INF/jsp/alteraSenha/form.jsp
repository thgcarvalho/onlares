<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>
<head>
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
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												Redefinição de senha
											</h4>

											<div class="space-6"></div>

											<form action="${ctx}/alteraSenha/altera" method="post">
												<input type="hidden" name="alteraSenha.codigo" value="${codigo}" />
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Nova Senha"
															 name="alteraSenha.novaSenha" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Repita a Nova Senha"
								 							 name="alteraSenha.confirmacaoDeNovaSenha" />
															<i class="ace-icon fa fa-retweet"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">

														<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">Alterar</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>
										</div><!-- /.widget-main -->
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->
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
	</body>
</html>


