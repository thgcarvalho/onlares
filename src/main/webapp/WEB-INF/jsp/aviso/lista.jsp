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
	
		<div class="page-header">
			<h1>
				Avisos
			</h1>
		</div><!-- /.page-header -->
		
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<c:choose>
						<c:when test="${avisoList.isEmpty()}">
							<div class="col-sm-12">
						    	<h4>Não existem avisos cadastrados</h4>
						    </div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${avisoList}" var="aviso">
								<div class="col-xs-12">
									<div class="header bigger lighter blue">
										<a href="${linkTo[AvisoController].visualiza(aviso.id)}">
											<c:choose>
												<c:when test="${aviso.visualizado}">
													<i class="ace-icon fa fa-file-o"></i>
													${aviso.titulo}
												</c:when>
												<c:otherwise>
													<i class="ace-icon fa fa-file"></i>
													<b>
														${aviso.titulo}
													</b>
												</c:otherwise>
											</c:choose>
										</a>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
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
			$('#menu_avisos').addClass('active');
		});
	</script>
</content>

</body>

</html>
