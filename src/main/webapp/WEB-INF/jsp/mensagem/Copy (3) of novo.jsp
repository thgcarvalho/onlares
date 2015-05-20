<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>
	<meta name="description" content="" />
	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.custom.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/chosen.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/datepicker.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-timepicker.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/daterangepicker.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datetimepicker.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/colorpicker.min.css" />
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

				<form id="id-message-form" class="form-horizontal message-form col-xs-12" role="form">
				
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
							<input maxlength="100" type="text" class="col-xs-12" name="subject" id="form-field-subject" placeholder="Assunto" />
						</div>
					</div>

					<div class="hr hr-18 dotted"></div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right">
							<span class="inline space-24 hidden-480"></span>
							Mensagem*:
						</label>
						<div class="col-sm-9">
							<div class="wysiwyg-editor"></div>
						</div>
					</div>

					<div class="space"></div>
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
		jQuery(function($) {
			//handling tabs and loading/displaying relevant messages and forms
			//not needed if using the alternative view, as described in docs
			$('#inbox-tabs a[data-toggle="tab"]').on('show.bs.tab', function (e) {
				var currentTab = $(e.target).data('target');
				if(currentTab == 'write') {
					Inbox.show_form();
				}
				else if(currentTab == 'inbox') {
					Inbox.show_list();
				}
			})
			
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
			
			//select read
			$('#id-select-message-read').on('click', function(e) {
				e.preventDefault();
				Inbox.select_read();
			});
		
			//select unread
			$('#id-select-message-unread').on('click', function(e) {
				e.preventDefault();
				Inbox.select_unread();
			});
		
			/////////
		
			//display first message in a new area
			$('.message-list .message-item:eq(0) .text').on('click', function() {
				//show the loading icon
				$('.message-container').append('<div class="message-loading-overlay"><i class="fa-spin ace-icon fa fa-spinner orange2 bigger-160"></i></div>');
				
				$('.message-inline-open').removeClass('message-inline-open').find('.message-content').remove();
		
				var message_list = $(this).closest('.message-list');
		
				$('#inbox-tabs a[href="#inbox"]').parent().removeClass('active');
				//some waiting
				setTimeout(function() {
		
					//hide everything that is after .message-list (which is either .message-content or .message-form)
					message_list.next().addClass('hide');
					$('.message-container').find('.message-loading-overlay').remove();
		
					//close and remove the inline opened message if any!
		
					//hide all navbars
					$('.message-navbar').addClass('hide');
					//now show the navbar for single message item
					$('#id-message-item-navbar').removeClass('hide');
		
					//hide all footers
					$('.message-footer').addClass('hide');
					//now show the alternative footer
					$('.message-footer-style2').removeClass('hide');
					
					
					//move .message-content next to .message-list and hide .message-list
					$('.message-content').removeClass('hide').insertAfter(message_list.addClass('hide'));
		
					//add scrollbars to .message-body
					$('.message-content .message-body').ace_scroll({
						size: 150,
						mouseWheelLock: true,
						styleClass: 'scroll-visible'
					});
		
				}, 500 + parseInt(Math.random() * 500));
			});
		
		
			//display second message right inside the message list
			$('.message-list .message-item:eq(1) .text').on('click', function(){
				var message = $(this).closest('.message-item');
		
				//if message is open, then close it
				if(message.hasClass('message-inline-open')) {
					message.removeClass('message-inline-open').find('.message-content').remove();
					return;
				}
		
				$('.message-container').append('<div class="message-loading-overlay"><i class="fa-spin ace-icon fa fa-spinner orange2 bigger-160"></i></div>');
				setTimeout(function() {
					$('.message-container').find('.message-loading-overlay').remove();
					message
						.addClass('message-inline-open')
						.append('<div class="message-content" />')
					var content = message.find('.message-content:last').html( $('#id-message-content').html() );
		
					//remove scrollbar elements
					content.find('.scroll-track').remove();
					content.find('.scroll-content').children().unwrap();
					
					content.find('.message-body').ace_scroll({
						size: 150,
						mouseWheelLock: true,
						styleClass: 'scroll-visible'
					});
			
				}, 500 + parseInt(Math.random() * 500));
				
			});
		
			//back to message list
			$('.btn-back-message-list').on('click', function(e) {
				
				e.preventDefault();
				$('#inbox-tabs a[href="#inbox"]').tab('show');
			});
		
		
		
			//hide message list and display new message form
			/**
			$('.btn-new-mail').on('click', function(e){
				e.preventDefault();
				Inbox.show_form();
			});
			*/
		
		
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
				,
				select_read : function() {
					$('.message-unread input[type=checkbox]').removeAttr('checked').closest('.message-item').removeClass('selected');
					
					var count = 0;
					$('.message-item:not(.message-unread) input[type=checkbox]').each(function(){
						this.checked = true;
						$(this).closest('.message-item').addClass('selected');
						count++;
					});
					Inbox.display_bar(count);
				}
				,
				select_unread : function() {
					$('.message-item:not(.message-unread) input[type=checkbox]').removeAttr('checked').closest('.message-item').removeClass('selected');
					
					var count = 0;
					$('.message-unread input[type=checkbox]').each(function(){
						this.checked = true;
						$(this).closest('.message-item').addClass('selected');
						count++;
					});
					
					Inbox.display_bar(count);
				}
			}
		
			//show message list (back from writing mail or reading a message)
			Inbox.show_list = function() {
				$('.message-navbar').addClass('hide');
				$('#id-message-list-navbar').removeClass('hide');
		
				$('.message-footer').addClass('hide');
				$('.message-footer:not(.message-footer-style2)').removeClass('hide');
		
				$('.message-list').removeClass('hide').next().addClass('hide');
				//hide the message item / new message window and go back to list
			}
		
			//show write mail form
			Inbox.show_form = function() {
				if($('.message-form').is(':visible')) return;
				if(!form_initialized) {
					initialize_form();
				}
				
				
				var message = $('.message-list');
				$('.message-container').append('<div class="message-loading-overlay"><i class="fa-spin ace-icon fa fa-spinner orange2 bigger-160"></i></div>');
				
				setTimeout(function() {
					message.next().addClass('hide');
					
					$('.message-container').find('.message-loading-overlay').remove();
					
					$('.message-list').addClass('hide');
					$('.message-footer').addClass('hide');
					$('.message-form').removeClass('hide').insertAfter('.message-list');
					
					$('.message-navbar').addClass('hide');
					$('#id-message-new-navbar').removeClass('hide');
					
					
					//reset form??
					$('.message-form .wysiwyg-editor').empty();
				
					$('.message-form .ace-file-input').closest('.file-input-container:not(:first-child)').remove();
					$('.message-form input[type=file]').ace_file_input('reset_input');
					
					$('.message-form').get(0).reset();
					
				}, 300 + parseInt(Math.random() * 300));
			}
		
		
			var form_initialized = false;
			function initialize_form() {
				if(form_initialized) return;
				form_initialized = true;
				
				//intialize wysiwyg editor
				$('.message-form .wysiwyg-editor').ace_wysiwyg({
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
		
		
		
				//file input
				$('.message-form input[type=file]').ace_file_input()
				.closest('.ace-file-input')
				.addClass('width-90 inline')
				.wrap('<div class="form-group file-input-container"><div class="col-sm-7"></div></div>');
		
				//Add Attachment
				//the button to add a new file input
				$('#id-add-attachment')
				.on('click', function(){
					var file = $('<input type="file" name="attachment[]" />').appendTo('#form-attachments');
					file.ace_file_input();
					
					file.closest('.ace-file-input')
					.addClass('width-90 inline')
					.wrap('<div class="form-group file-input-container"><div class="col-sm-7"></div></div>')
					.parent().append('<div class="action-buttons pull-right col-xs-1">\
						<a href="#" data-action="delete" class="middle">\
							<i class="ace-icon fa fa-trash-o red bigger-130 middle"></i>\
						</a>\
					</div>')
					.find('a[data-action=delete]').on('click', function(e){
						//the button that removes the newly inserted file input
						e.preventDefault();
						$(this).closest('.file-input-container').hide(300, function(){ $(this).remove() });
					});
				});
			}//initialize_form
		
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			$('#id-disable-check').on('click', function() {
				var inp = $('#form-input-readonly').get(0);
				if(inp.hasAttribute('disabled')) {
					inp.setAttribute('readonly' , 'true');
					inp.removeAttribute('disabled');
					inp.value="This text field is readonly!";
				}
				else {
					inp.setAttribute('disabled' , 'disabled');
					inp.removeAttribute('readonly');
					inp.value="This text field is disabled!";
				}
			});
		
		
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
		
		
			$('[data-rel=tooltip]').tooltip({container:'body'});
			$('[data-rel=popover]').popover({container:'body'});
			
			$('textarea[class*=autosize]').autosize({append: "\n"});
			$('textarea.limited').inputlimiter({
				remText: '%n character%s remaining...',
				limitText: 'max allowed : %n.'
			});
		
			$.mask.definitions['~']='[+-]';
			$('.input-mask-date').mask('99/99/9999');
			$('.input-mask-phone').mask('(999) 999-9999');
			$('.input-mask-eyescript').mask('~9.99 ~9.99 999');
			$(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});
		
		
		
			$( "#input-size-slider" ).css('width','200px').slider({
				value:1,
				range: "min",
				min: 1,
				max: 8,
				step: 1,
				slide: function( event, ui ) {
					var sizing = ['', 'input-sm', 'input-lg', 'input-mini', 'input-small', 'input-medium', 'input-large', 'input-xlarge', 'input-xxlarge'];
					var val = parseInt(ui.value);
					$('#form-field-4').attr('class', sizing[val]).val('.'+sizing[val]);
				}
			});
		
			$( "#input-span-slider" ).slider({
				value:1,
				range: "min",
				min: 1,
				max: 12,
				step: 1,
				slide: function( event, ui ) {
					var val = parseInt(ui.value);
					$('#form-field-5').attr('class', 'col-xs-'+val).val('.col-xs-'+val);
				}
			});
		
		
			
			//"jQuery UI Slider"
			//range slider tooltip example
			$( "#slider-range" ).css('height','200px').slider({
				orientation: "vertical",
				range: true,
				min: 0,
				max: 100,
				values: [ 17, 67 ],
				slide: function( event, ui ) {
					var val = ui.values[$(ui.handle).index()-1] + "";
		
					if( !ui.handle.firstChild ) {
						$("<div class='tooltip right in' style='display:none;left:16px;top:-6px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>")
						.prependTo(ui.handle);
					}
					$(ui.handle.firstChild).show().children().eq(1).text(val);
				}
			}).find('span.ui-slider-handle').on('blur', function(){
				$(this.firstChild).hide();
			});
			
			
			$( "#slider-range-max" ).slider({
				range: "max",
				min: 1,
				max: 10,
				value: 2
			});
			
			$( "#slider-eq > span" ).css({width:'90%', 'float':'left', margin:'15px'}).each(function() {
				// read initial values from markup and remove that
				var value = parseInt( $( this ).text(), 10 );
				$( this ).empty().slider({
					value: value,
					range: "min",
					animate: true
					
				});
			});
			
			$("#slider-eq > span.ui-slider-purple").slider('disable');//disable third item
		
			
			$('#id-input-file-1 , #id-input-file-2').ace_file_input({
				no_file:'No File ...',
				btn_choose:'Choose',
				btn_change:'Change',
				droppable:false,
				onchange:null,
				thumbnail:false //| true | large
				//whitelist:'gif|png|jpg|jpeg'
				//blacklist:'exe|php'
				//onchange:''
				//
			});
			//pre-show a file name, for example a previously selected file
			//$('#id-input-file-1').ace_file_input('show_file_list', ['myfile.txt'])
		
		
			$('#id-input-file-3').ace_file_input({
				style:'well',
				btn_choose:'Drop files here or click to choose',
				btn_change:null,
				no_icon:'ace-icon fa fa-cloud-upload',
				droppable:true,
				thumbnail:'small'//large | fit
				//,icon_remove:null//set null, to hide remove/reset button
				/**,before_change:function(files, dropped) {
					//Check an example below
					//or examples/file-upload.html
					return true;
				}*/
				/**,before_remove : function() {
					return true;
				}*/
				,
				preview_error : function(filename, error_code) {
					//name of the file that failed
					//error_code values
					//1 = 'FILE_LOAD_FAILED',
					//2 = 'IMAGE_LOAD_FAILED',
					//3 = 'THUMBNAIL_FAILED'
					//alert(error_code);
				}
		
			}).on('change', function(){
				//console.log($(this).data('ace_input_files'));
				//console.log($(this).data('ace_input_method'));
			});
			
			
			//$('#id-input-file-3')
			//.ace_file_input('show_file_list', [
				//{type: 'image', name: 'name of image', path: 'http://path/to/image/for/preview'},
				//{type: 'file', name: 'hello.txt'}
			//]);
		
			
			
		
			//dynamically change allowed formats by changing allowExt && allowMime function
			$('#id-file-format').removeAttr('checked').on('change', function() {
				var whitelist_ext, whitelist_mime;
				var btn_choose
				var no_icon
				if(this.checked) {
					btn_choose = "Drop images here or click to choose";
					no_icon = "ace-icon fa fa-picture-o";
		
					whitelist_ext = ["jpeg", "jpg", "png", "gif" , "bmp"];
					whitelist_mime = ["image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp"];
				}
				else {
					btn_choose = "Drop files here or click to choose";
					no_icon = "ace-icon fa fa-cloud-upload";
					
					whitelist_ext = null;//all extensions are acceptable
					whitelist_mime = null;//all mimes are acceptable
				}
				var file_input = $('#id-input-file-3');
				file_input
				.ace_file_input('update_settings',
				{
					'btn_choose': btn_choose,
					'no_icon': no_icon,
					'allowExt': whitelist_ext,
					'allowMime': whitelist_mime
				})
				file_input.ace_file_input('reset_input');
				
				file_input
				.off('file.error.ace')
				.on('file.error.ace', function(e, info) {
					//console.log(info.file_count);//number of selected files
					//console.log(info.invalid_count);//number of invalid files
					//console.log(info.error_list);//a list of errors in the following format
					
					//info.error_count['ext']
					//info.error_count['mime']
					//info.error_count['size']
					
					//info.error_list['ext']  = [list of file names with invalid extension]
					//info.error_list['mime'] = [list of file names with invalid mimetype]
					//info.error_list['size'] = [list of file names with invalid size]
					
					
					/**
					if( !info.dropped ) {
						//perhapse reset file field if files have been selected, and there are invalid files among them
						//when files are dropped, only valid files will be added to our file array
						e.preventDefault();//it will rest input
					}
					*/
					
					
					//if files have been selected (not dropped), you can choose to reset input
					//because browser keeps all selected files anyway and this cannot be changed
					//we can only reset file field to become empty again
					//on any case you still should check files with your server side script
					//because any arbitrary file can be uploaded by user and it's not safe to rely on browser-side measures
				});
			
			});
		
			$('#spinner1').ace_spinner({value:0,min:0,max:200,step:10, btn_up_class:'btn-info' , btn_down_class:'btn-info'})
			.closest('.ace-spinner')
			.on('changed.fu.spinbox', function(){
				//alert($('#spinner1').val())
			}); 
			$('#spinner2').ace_spinner({value:0,min:0,max:10000,step:100, touch_spinner: true, icon_up:'ace-icon fa fa-caret-up bigger-110', icon_down:'ace-icon fa fa-caret-down bigger-110'});
			$('#spinner3').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'ace-icon fa fa-plus bigger-110', icon_down:'ace-icon fa fa-minus bigger-110', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
			$('#spinner4').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'ace-icon fa fa-plus', icon_down:'ace-icon fa fa-minus', btn_up_class:'btn-purple' , btn_down_class:'btn-purple'});
		
			//$('#spinner1').ace_spinner('disable').ace_spinner('value', 11);
			//or
			//$('#spinner1').closest('.ace-spinner').spinner('disable').spinner('enable').spinner('value', 11);//disable, enable or change value
			//$('#spinner1').closest('.ace-spinner').spinner('value', 0);//reset to 0
		
		
			//datepicker plugin
			//link
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true
			})
			//show datepicker when clicking on the icon
			.next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
		
			//or change it into a date range picker
			$('.input-daterange').datepicker({autoclose:true});
		
		
			//to translate the daterange picker, please copy the "examples/daterange-fr.js" contents here before initialization
			$('input[name=date-range-picker]').daterangepicker({
				'applyClass' : 'btn-sm btn-success',
				'cancelClass' : 'btn-sm btn-default',
				locale: {
					applyLabel: 'Apply',
					cancelLabel: 'Cancel',
				}
			})
			.prev().on(ace.click_event, function(){
				$(this).next().focus();
			});
		
		
			$('#timepicker1').timepicker({
				minuteStep: 1,
				showSeconds: true,
				showMeridian: false
			}).next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
			
			$('#date-timepicker1').datetimepicker().next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
			
		
			$('#colorpicker1').colorpicker();
		
			$('#simple-colorpicker-1').ace_colorpicker();
			//$('#simple-colorpicker-1').ace_colorpicker('pick', 2);//select 2nd color
			//$('#simple-colorpicker-1').ace_colorpicker('pick', '#fbe983');//select #fbe983 color
			//var picker = $('#simple-colorpicker-1').data('ace_colorpicker')
			//picker.pick('red', true);//insert the color if it doesn't exist
		
		
			$(".knob").knob();
			
			
			var tag_input = $('#form-field-tags');
			try{
				tag_input.tag(
				  {
					placeholder:tag_input.attr('placeholder'),
					//enable typeahead by specifying the source array
					source: ace.vars['US_STATES'],//defined in ace.js >> ace.enable_search_ahead
					/**
					//or fetch data from database, fetch those that match "query"
					source: function(query, process) {
					  $.ajax({url: 'remote_source.php?q='+encodeURIComponent(query)})
					  .done(function(result_items){
						process(result_items);
					  });
					}
					*/
				  }
				)
		
				//programmatically add a new
				var $tag_obj = $('#form-field-tags').data('tag');
				$tag_obj.add('Programmatically Added');
			}
			catch(e) {
				//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
				tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
				//$('#form-field-tags').autosize({append: "\n"});
			}
			
			
			/////////
			$('#modal-form input[type=file]').ace_file_input({
				style:'well',
				btn_choose:'Drop files here or click to choose',
				btn_change:null,
				no_icon:'ace-icon fa fa-cloud-upload',
				droppable:true,
				thumbnail:'large'
			})
			
			//chosen plugin inside a modal will have a zero width because the select element is originally hidden
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
