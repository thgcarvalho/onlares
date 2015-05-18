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
						Nova Mensagem
					</h1>
				</div><!-- /.page-header -->
				
				<div class="row">
					<div class="col-xs-12">
						<div class="tabbable">
							<ul id="inbox-tabs" class="inbox-tabs nav nav-tabs padding-16 tab-size-bigger tab-space-1">
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
										<div id="id-message-new-navbar" class="message-navbar clearfix">
											<div class="message-bar">
											</div>

											<div>
												<div class="messagebar-item-left">
													<a href="${linkTo[MensagemController].recebidas()}" class="btn-back-message-list">
														<i class="ace-icon fa fa-arrow-left bigger-110 middle blue"></i>
														<b class="middle bigger-110">Voltar</b>
													</a>
												</div>

												<div class="messagebar-item-right">
													<span class="inline btn-send-message">
														<button type="button" class="btn btn-sm btn-primary no-border btn-white btn-round">
															<span class="bigger-110">Enviar</span>

															<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
														</button>
													</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div><!-- /.tab-content -->
						</div><!-- /.tabbable -->
					</div><!-- /.col -->
				</div><!-- /.row -->

				<form id="id-message-form" class="form-horizontal message-form col-xs-12">
					<div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-recipient">Para:</label>

							<div class="col-sm-6 col-xs-12">
								<div class="input-icon block col-xs-12 no-padding">
									<input type="text" class="col-xs-12" maxlength="45" name="recipient" id="form-field-recipient" placeholder="Usuário(s)" />
									<i class="ace-icon fa fa-user"></i>
								</div>
							</div>
						</div>

						<div class="hr hr-18 dotted"></div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-subject">Assunto:</label>

							<div class="col-sm-6 col-xs-12">
								<div class="input-icon block col-xs-12 no-padding">
									<input maxlength="100" type="text" class="col-xs-12" name="subject" id="form-field-subject" placeholder="Assunto" />
									<i class="ace-icon fa fa-comment-o"></i>
								</div>
							</div>
						</div>

						<div class="hr hr-18 dotted"></div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">
								<span class="inline space-24 hidden-480"></span>
								Mensagem:
							</label>

							<div class="col-sm-9">
								<div class="wysiwyg-editor"></div>
							</div>
						</div>

						<div class="space"></div>
					</div>
				</form>
				<!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div><!-- /.page-content -->
</div>

<content tag="local_script">
	<!-- page specific plugin scripts -->
	<script src="${ctx}/assets/js/bootstrap-tag.min.js"></script>
	<script src="${ctx}/assets/js/jquery.hotkeys.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-wysiwyg.min.js"></script>
		
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
