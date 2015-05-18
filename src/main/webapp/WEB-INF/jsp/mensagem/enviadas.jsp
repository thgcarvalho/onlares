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
						Enviadas
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

								<li class="active">
									<a href="${linkTo[MensagemController].enviadas()}" data-target="sent">
										<i class="orange ace-icon fa fa-location-arrow bigger-130"></i>
										<span class="bigger-110">Enviadas</span>
									</a>
								</li>
							</ul>

							<div class="tab-content no-border no-padding">
								<div id="inbox" class="tab-pane in active">
									<div class="message-container">
										<div id="id-message-list-navbar" class="message-navbar clearfix">
											<div class="message-bar">

												<div class="message-toolbar hide">
													<div class="inline position-relative align-left">
													</div>

													<button type="button" class="btn btn-xs btn-white btn-primary">
														<i class="ace-icon fa fa-trash-o bigger-125 orange"></i>
														<span class="bigger-110">Deletar</span>
													</button>
												</div>
											</div>

											<div>
												<div class="messagebar-item-left">
													<label class="inline middle">
														<input type="checkbox" id="id-toggle-all" class="ace" />
														<span class="lbl"></span>
													</label>
												</div>

												<div class="messagebar-item-right">
													<div class="inline position-relative">
													</div>
												</div>

												<div class="nav-search minimized">
												</div>
											</div>
										</div>
										<div class="message-list-container">
											<div class="message-list" id="message-list">
												<div class="message-item message-unread">
													<label class="inline">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
													<a href="${linkTo[MensagemController].visualizaEnviada(1)}" data-target="inbox">
														<span class="sender" title="Thiago Carvalho">Thiago Carvalho </span>
														<span class="time">$[mensagem.hora]</span>
	
														<span class="summary">
															<span class="text">
																$[mensagem.assunto]
															</span>
														</span>
													</a>
												</div>
											</div>
										</div>
										<div class="message-footer clearfix">
											<div class="pull-left"> 151 mensagens do total </div>

											<div class="pull-right">
												<div class="inline middle">página 1 de 16 </div>

												&nbsp; &nbsp;
												<ul class="pagination middle">
													<li class="disabled">
														<span>
															<i class="ace-icon fa fa-step-backward middle"></i>
														</span>
													</li>

													<li class="disabled">
														<span>
															<i class="ace-icon fa fa-caret-left bigger-140 middle"></i>
														</span>
													</li>

													<li>
														<span>
															<input value="1" maxlength="3" type="text" />
														</span>
													</li>

													<li>
														<a href="#">
															<i class="ace-icon fa fa-caret-right bigger-140 middle"></i>
														</a>
													</li>

													<li>
														<a href="#">
															<i class="ace-icon fa fa-step-forward middle"></i>
														</a>
													</li>
												</ul>
											</div>
										</div>
										
									</div>
								</div>
							</div><!-- /.tab-content -->
						</div><!-- /.tabbable -->
					</div><!-- /.col -->
				</div><!-- /.row -->
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
