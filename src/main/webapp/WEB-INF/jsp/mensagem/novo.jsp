<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>
	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.custom.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/chosen.min.css" />
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
														<button type="submit" class="btn btn-sm btn-primary no-border btn-white btn-round">
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

				<form id="myform" class="form-horizontal message-form col-xs-12" action="${ctx}/mensagem/" method="post" >
				
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-select-4">Para*:</label>
						<div class="col-sm-6 col-xs-12">
							<select multiple="" name="destinatarios[]" class="chosen-select form-control col-sm-6 col-xs-12" id="form-field-select-4" data-placeholder="Usuário(s)">
								<c:forEach items="${moradorList}" var="morador" >							
									<c:choose>
										<c:when test="${mensagem.contemUsuario(morador.id)}">
											<option value="${morador.id}" selected="selected">${morador.nome} - ${morador.localizacoes}</option>
										</c:when>
										<c:otherwise>
											<option value="${morador.id}">${morador.nome} - ${morador.localizacoes}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="hr hr-18 dotted"></div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-subject">Assunto*:</label>
						<div class="col-sm-6 col-xs-12">
							<input maxlength="100" type="text" name="mensagem.assunto" class="col-xs-12" name="subject" id="form-field-subject" placeholder="Assunto" />
						</div>
					</div>

					<div class="hr hr-18 dotted"></div>

					<input type="hidden" id="html" name="mensagem.texto" />
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right">
							<span class="inline space-24 hidden-480"></span>
							Mensagem*:
						</label>
						<div class="col-sm-9">
							<div class="wysiwyg-editor" name="editor1" id="editor1"></div>
						</div>
					</div>

					<div class="space"></div>
					
					<div class="clearfix form-actions">
						<div class="col-md-offset-5">
							<button class="btn btn-info" type="submit">
								<i class="ace-icon fa fa-check bigger-110"></i>
								Enviar
							</button>
						</div>
					</div>
					
				</form>
				<!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div><!-- /.page-content -->
</div>

<content tag="local_script">
	<!-- page specific plugin scripts -->
	<!--[if lte IE 8]>
		 <script src="${ctx}/assets/js/excanvas.min.js"></script>
	<![endif]-->
	<script src="${ctx}/assets/js/jquery-ui.custom.min.js"></script>
	<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="${ctx}/assets/js/chosen.jquery.min.js"></script>
	<script src="${ctx}/assets/js/fuelux.spinner.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-timepicker.min.js"></script>
	<script src="${ctx}/assets/js/moment.min.js"></script>
	<script src="${ctx}/assets/js/daterangepicker.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-colorpicker.min.js"></script>
	<script src="${ctx}/assets/js/jquery.knob.min.js"></script>
	<script src="${ctx}/assets/js/jquery.autosize.min.js"></script>
	<script src="${ctx}/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
	<script src="${ctx}/assets/js/jquery.maskedinput.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-tag.min.js"></script>
	
	<script src="${ctx}/assets/js/jquery.hotkeys.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-wysiwyg.min.js"></script>
	

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($){
			if(!ace.vars['touch']) {
				$('.chosen-select').chosen({allow_single_deselect:true}); 
				//resize the chosen on window resize
		
				$(window)
				.off('resize.chosen')
				.on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					})
				}).trigger('resize.chosen');
				//resize chosen on sidebar collapse/expand
				$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
					if(event_name != 'sidebar_collapsed') return;
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					})
				});
		
		
				$('#chosen-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			}
		
			//and its width cannot be determined.
			//so we set the width after modal is show
			$('#modal-form').on('shown.bs.modal', function () {
				if(!ace.vars['touch']) {
					$(this).find('.chosen-container').each(function(){
						$(this).find('a:first-child').css('width' , '210px');
						$(this).find('.chosen-drop').css('width' , '210px');
						$(this).find('.chosen-search input').css('width' , '200px');
					});
				}
			})
			/**
			//or you can activate the chosen plugin after modal is shown
			//this way select element becomes visible with dimensions and chosen works as expected
			$('#modal-form').on('shown', function () {
				$(this).find('.modal-chosen').chosen();
			})
			*/
			$(document).one('ajaxloadstart.page', function(e) {
				$('textarea[class*=autosize]').trigger('autosize.destroy');
				$('.limiterBox,.autosizejs').remove();
				$('.daterangepicker.dropdown-menu,.colorpicker.dropdown-menu,.bootstrap-datetimepicker-widget.dropdown-menu').remove();
			});
			
			
		
			$("#myform").submit(function() {
			   // Retrieve the HTML from the plugin
			   var html = $('#editor1').html();
			   // Put this in the hidden field
			   $("#html").val(html);
			});
			
			function showErrorAlert (reason, detail) {
				var msg='';
				if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
				else {
					//console.log("error uploading file", reason, detail);
				}
				$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
				 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
			}
			
			//$('#editor1').ace_wysiwyg();//this will create the default editor will all buttons
		
			//but we want to change a few buttons colors for the third style
			$('#editor1').ace_wysiwyg({
				toolbar:
				[
					'bold',
					'italic',
					'strikethrough',
					'underline',
					null,
					'justifyleft',
					'justifycenter',
					'justifyright',
					null,
					'createLink',
					'unlink',
					null,
					'undo',
					'redo'
				]
			}).prev().addClass('wysiwyg-style1');
		
		});
	</script>
	
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		window.onload = function() {
			document.getElementById('menu_mensagens').className = 'active';
		};
	</script>
</content>

</body>

</html>
