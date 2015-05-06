<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>
	<meta name="description" content="" />
	<!-- page specific plugin styles -->
</head>

<body>

<div class="main-content-inner">
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		</script>

		<ul class="breadcrumb">
			<li class="active">
				<i class="ace-icon fa fa-home home-icon"></i>
				Home
			</li>
		</ul><!-- /.breadcrumb -->
		
	</div>

	<div class="page-content">
	
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-3">
						<h3 class="header smaller lighter blue">
							<i class="ace-icon fa fa-file"></i>
							Convenção
						</h3>
						<a href="email-confirmation.html" class="thumbnail" target="_blank">
							<img class="img-responsive" src="${ctx}/assets/images/email1.png" alt="Email Template" />
						</a>
					</div>

					<div class="col-xs-3">
						<h3 class="header smaller lighter blue">
							<i class="ace-icon fa fa-file"></i>
							Regimento interno
						</h3>
						<a href="email-navbar.html" class="thumbnail" target="_blank">
							<img class="img-responsive" src="${ctx}/assets/images/email2.png" alt="Email Template" />
						</a>
					</div>

					<div class="col-xs-3">
						<h3 class="header smaller lighter blue">
							<i class="ace-icon fa fa-list"></i>
							Atas
						</h3>
						<a href="email-newsletter.html" class="thumbnail" target="_blank">
							<img class="img-responsive" src="${ctx}/assets/images/email3.png" alt="Email Template" />
						</a>
					</div>

					<div class="col-xs-3">
						<h3 class="header smaller lighter blue">
							<i class="ace-icon fa fa-list"></i>
							Balancetes
						</h3>
						<a href="email-contrast.html" class="thumbnail" target="_blank">
							<img class="img-responsive" src="${ctx}/assets/images/email4.png" alt="Email Template" />
						</a>
					</div>
				</div>
				<!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div><!-- /.page-content -->
</div>

<content tag="local_script">
	<!-- page specific plugin scripts -->
	<!-- inline scripts related to this page -->
	
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		$(function() {
			$('#menu_documento').addClass('active');
		});
	</script>
</content>

</body>

</html>
