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
			<li>
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="${ctx}/admin/index">Home</a>
			</li>

			<li class="active">Mensagens</li>
		</ul><!-- /.breadcrumb -->
		
	</div>

	<div class="page-content">
	
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				
				<div class="page-header">
					<h1>
						Enviada
					</h1>
				</div><!-- /.page-header -->
				
				<div class="row">
					<div class="col-xs-12">
						<div class="tabbable">
							<ul id="inbox-tabs" class="inbox-tabs nav nav-tabs padding-16 tab-size-bigger tab-space-1">
								<li class="li-new-mail pull-right">
									<a href="${linkTo[MensagemController].novo()}" data-target="write" class="btn-new-mail">
										<span class="btn btn-success no-border">
											<i class="ace-icon fa fa-envelope bigger-130"></i>
											<span class="bigger-110">Nova Mensagem</span>
										</span>
									</a>
								</li><!-- /.li-new-mail -->

								<li>
									<a href="${linkTo[MensagemController].recebidas()}" data-target="inbox">
										<i class="blue ace-icon fa fa-inbox bigger-130"></i>
										<span class="bigger-110">Recebidas</span>
									</a>
								</li>

								<li>
									<a href="${linkTo[MensagemController].enviadas()}" data-target="sent">
										<i class="orange ace-icon fa fa-location-arrow bigger-130"></i>
										<span class="bigger-110">Enviadas</span>
									</a>
								</li>
							</ul>

							<div class="tab-content no-border no-padding">
								<div id="inbox" class="tab-pane in active">
									<div class="message-container">
										<div id="id-message-item-navbar" class="message-navbar clearfix">
											<div class="message-bar">
											</div>

											<div>
												<div class="messagebar-item-left">
													<a href="${linkTo[MensagemController].enviadas()}">
														<i class="ace-icon fa fa-arrow-left blue bigger-110 middle"></i>
														<b class="bigger-110 middle">Voltar</b>
													</a>
												</div>

												<div class="messagebar-item-right">
													<i class="ace-icon fa fa-clock-o bigger-110 orange"></i>
													<span class="grey">${mensagem.dataFormatada}, ${mensagem.horaFormatada}</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div><!-- /.tab-content -->
						</div><!-- /.tabbable -->
					</div><!-- /.col -->
				</div><!-- /.row -->

				<div class="message-content" id="id-message-content">
					<div class="message-header clearfix">
						<div class="pull-left">
							<span class="blue bigger-125"> ${mensagem.assunto} </span>

							<div class="space-4"></div>

							&nbsp;
							Para: 
							<c:forEach items="${destinatarios}" var="usuario">
								<a href="${linkTo[PerfilController].visualiza(usuario.id)}" class="sender">${usuario.nome}</a>; 
							</c:forEach>
						</div>
					</div>

					<div class="hr hr-double"></div>

					<div class="message-body">
						${mensagem.texto}
					</div>

					<div class="hr hr-double"></div>
				</div><!-- /.message-content -->
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
			$('#menu_mensagens').addClass('active');
		});
	</script>
</content>

</body>

</html>
