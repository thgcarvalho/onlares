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
									<form id="myform" class="form-horizontal message-form col-xs-12" action="${ctx}/mensagem/removeEnviadas/" method="post" >
										<div id="id-message-list-navbar" class="message-navbar clearfix">
											<div class="message-bar">
												<div class="message-toolbar hide">
													<div class="inline position-relative align-left">
													</div>
													<button name="_method" value="DELETE" class="btn btn-sm btn-danger no-border ">
														<i class="ace-icon fa fa-trash-o bigger-125"></i>
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
											<c:choose>
												<c:when test="${mensagemList.isEmpty()}">
													<h3>Sem mensagens</h3>
												</c:when>
												<c:otherwise>
													<c:forEach items="${mensagemList}" var="mensagem" >
														<div class="message-item">
															<label class="inline">
																<input type="checkbox" class="ace" name="mensagens[]" value="${mensagem.id}" /> 
																<span class="lbl"></span>
															</label>
															<a href="${linkTo[MensagemController].visualizaEnviada(mensagem.id)}" data-target="inbox">
																<span class="sender">${mensagem.usuario.nome} </span>
																<span class="data">${mensagem.dataFormatada}</span>
			
																<span class="summary">
																	<span class="text">
																		${mensagem.assunto}
																	</span>
																</span>
															</a>
														</div>
														</c:forEach>
												</c:otherwise>
											</c:choose>
											</div>
										</div>
										<div class="message-footer clearfix">
											<div class="pull-left"> ${mensagemTotal} mensagens do total </div>

											<div class="pull-right">
											</div>
										</div>
										</form>
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
	<script type="text/javascript">
		jQuery(function($){
			$("#myform").submit(function() {
			   // Retrieve the HTML from the plugin
			   var html = $('#editor1').html();
			   // Put this in the hidden field
			   $("#html").val(html);
			});
		
			//basic initializations
			$('.message-list .message-item input[type=checkbox]').removeAttr('checked');
			$('.message-list').on('click', '.message-item input[type=checkbox]' , function() {
				$(this).closest('.message-item').toggleClass('selected');
				if(this.checked) Inbox.display_bar(1);//display action toolbar when a message is selected
				else {
					Inbox.display_bar($('.message-list input[type=checkbox]:checked').length);
					//determine number of selected messages and display/hide action toolbar accordingly
				}		
			});
			
			//check/uncheck all messages
			$('#id-toggle-all').removeAttr('checked').on('click', function(){
				if(this.checked) {
					Inbox.select_all();
				} else Inbox.select_none();
			});
			
			//select all
			$('#id-select-message-all').on('click', function(e) {
				e.preventDefault();
				Inbox.select_all();
			});
			
			//select none
			$('#id-select-message-none').on('click', function(e) {
				e.preventDefault();
				Inbox.select_none();
			});
			
			/////////
		
			//display first message in a new area
			$('.message-list .message-item:eq(0) .text').on('click', function() {
				//show the loading icon
				$('.message-container').append('<div class="message-loading-overlay"><i class="fa-spin ace-icon fa fa-spinner orange2 bigger-160"></i></div>');
				
				$('.message-inline-open').removeClass('message-inline-open').find('.message-content').remove();
			});
		
			var Inbox = {
				//displays a toolbar according to the number of selected messages
				display_bar : function (count) {
					if(count == 0) {
						$('#id-toggle-all').removeAttr('checked');
						$('#id-message-list-navbar .message-toolbar').addClass('hide');
						$('#id-message-list-navbar .message-infobar').removeClass('hide');
					}
					else {
						$('#id-message-list-navbar .message-infobar').addClass('hide');
						$('#id-message-list-navbar .message-toolbar').removeClass('hide');
					}
				}
				,
				select_all : function() {
					var count = 0;
					$('.message-item input[type=checkbox]').each(function(){
						this.checked = true;
						$(this).closest('.message-item').addClass('selected');
						count++;
					});
					
					$('#id-toggle-all').get(0).checked = true;
					
					Inbox.display_bar(count);
				}
				,
				select_none : function() {
					$('.message-item input[type=checkbox]').removeAttr('checked').closest('.message-item').removeClass('selected');
					$('#id-toggle-all').get(0).checked = false;
					
					Inbox.display_bar(0);
				}
			}
		
		});
	</script>
	
	<!-- confirm script -->
	<script type="text/javascript">
		$( "#myform").submit(function(event) {
			if (!confirm('Você realmente deseja exlucir essas mensagens?')){
			  event.preventDefault();
			}
		});
	</script>
	
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
