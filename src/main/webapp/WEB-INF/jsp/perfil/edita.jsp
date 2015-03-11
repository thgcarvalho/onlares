<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />

	<meta name="description" content="3 styles with inline editable feature" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/font-awesome/4.2.0/css/font-awesome.min.css" />

	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.custom.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/jquery.gritter.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/select2.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/datepicker.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-editable.min.css" />

	<!-- text fonts -->
	<link rel="stylesheet" href="${ctx}/assets/fonts/fonts.googleapis.com.css" />

	<!-- ace styles -->
	<link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

	<!--[if lte IE 9]>
		<link rel="stylesheet" href="${ctx}/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
	<![endif]-->

	<!--[if lte IE 9]>
	  <link rel="stylesheet" href="${ctx}/assets/css/ace-ie.min.css" />
	<![endif]-->

	<!-- inline styles related to this page -->

	<!-- ace settings handler -->
	<script src="${ctx}/assets/js/ace-extra.min.js"></script>

	<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

	<!--[if lte IE 8]>
	<script src="${ctx}/assets/js/html5shiv.min.js"></script>
	<script src="${ctx}/assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>
<div class="main-content-inner">
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		</script>

		<ul class="breadcrumb">
			<li>
				<i class="ace-icon fa fa-user home-icon"></i>
				<a href="${ctx}/home/index">Home</a>
			</li>

			<li class="active">Perfil</li>
		</ul><!-- /.breadcrumb -->
	</div>
	
	<div class="page-content">
		<div class="page-header">
			<h1>
				Perfil
			</h1>
		</div><!-- /.page-header -->
	
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<form action="${ctx }/perfil/" method="post">
				<input type="hidden" name="_method" value="PUT">
				<input type="hidden" name="usuario.id" value="${usuarioLogado.usuario.id}">
				<input type="hidden" name="usuario.senha" value="${usuarioLogado.usuario.senha}">
				<input type="hidden" name="usuario.foneResidencial" value="${usuarioLogado.usuario.foneResidencial}">
				<input type="hidden" name="usuario.foneCelular" value="${usuarioLogado.usuario.foneCelular}">
				<input type="hidden" name="usuario.unidade.id" value="${usuarioLogado.usuario.unidade.id}">
				<input type="hidden" name="usuario.condominio.id" value="${usuarioLogado.usuario.condominio.id}">
				<div>
					<div id="user-profile-2" class="user-profile">
						<div class="tabbable">
							<ul class="nav nav-tabs padding-18">
								<li class="active">
									<a data-toggle="tab" href="#perfil">
										<i class="green ace-icon fa fa-user bigger-120"></i>
										Perfil
									</a>
								</li>

								<li>

							</ul>

							<div class="tab-content no-border padding-24">
								<div id="perfil" class="tab-pane in active">
									<div class="row">
										<div class="col-xs-12 col-sm-3 center">
											<span class="profile-picture">
												<!-- <img class="editable img-responsive" alt="Avatar" id="avatar2" src="${ctx}/assets/avatars/profile-pic.jpg" /> -->
												<img class="editable img-responsive" alt="Avatar" id="avatar2" 
													src="${linkTo[PerfilController].foto(usuarioLogado.usuario.email)}" />
											</span>

										</div><!-- /.col -->

										<div class="col-xs-12 col-sm-9">

											<div class="profile-user-info">
												
												<div class="profile-info-row">
													<div class="profile-info-name"> Nome* </div>
							
													<div class="profile-info-value">
														<input type="text" required="required" id="nome" name="usuario.nome" value="${usuarioLogado.usuario.nome}" 
														placeholder="Nome" maxlength="45" class="col-xs-10 col-sm-5" />
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name"> Email* </div>
							
													<div class="profile-info-value">
														<input type="email" required="require" id="email" name="usuario.email" value="${usuarioLogado.usuario.email}" 
														placeholder="Email" maxlength="45" class="col-xs-10 col-sm-5" />
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name"> Profissão </div>
							
													<div class="profile-info-value">
														<input type="text" id="profissao" name="profissao" value="" 
														placeholder="Profissão" maxlength="45" class="col-xs-10 col-sm-5" />
													</div>
												</div>
											
												<div class="profile-info-row">
													<div class="profile-info-name"> Unidade </div>

													<div class="profile-info-value">
														<i class="fa fa-map-marker light-orange bigger-110"></i>
														<span>${usuarioLogado.usuario.unidade.localizacao}</span>
													</div>
												</div>


												<div class="profile-info-row">
													<div class="profile-info-name"> Aniversário </div>
							
													<div class="profile-info-value">
														<input type="text"  id="niver" name="idade" value="" 
														placeholder="DD/MM" class="col-xs-10 col-sm-5 input-mask-date" />
													</div>
												</div>
												
											</div>

											<div class="hr hr-8 dotted"></div>

											<div class="profile-user-info">
												<div class="profile-info-row">
													<div class="profile-info-name"> Website </div>
							
													<div class="profile-info-value">
														<input type="text" id="site" name="site" value="" 
														placeholder="Website" maxlength="45" class="col-xs-10 col-sm-5" />
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name">
														<i class="middle ace-icon fa fa-facebook-square bigger-150 blue"></i>
													</div>

													<div class="profile-info-value">
														<input type="text" id="site" name="facebook" value="" 
														placeholder="Facebook" maxlength="45" class="col-xs-10 col-sm-5" />
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name">
														<i class="middle ace-icon fa fa-twitter-square bigger-150 light-blue"></i>
													</div>

													<div class="profile-info-value">
														<input type="text" id="site" name="rwitter" value="" 
														placeholder="Twitter" maxlength="45" class="col-xs-10 col-sm-5" />
													</div>
												</div>
												
											</div>
										</div><!-- /.col -->
									</div><!-- /.row -->

									<div class="space-20"></div>
									
									<div class="clearfix form-actions">
										<div class="col-md-offset-5">
											<button class="btn btn-info" type="submit">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Salvar
											</button>
										</div>
									</div>
									
								</div><!-- /#home -->

							</div>
						</div>
					</div>
				</div>
				</form>
				<!-- PAGE CONTENT ENDS -->
			</div> <!-- div class="col-xs-12" -->
		</div> <!-- div class="row" -->
	</div>
</div>
	
<content tag="local_script">

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

	<!-- page specific plugin scripts -->

	<!--[if lte IE 8]>
	  <script src="${ctx}/assets/js/excanvas.min.js"></script>
	<![endif]-->
	<script src="${ctx}/assets/js/jquery-ui.custom.min.js"></script>
	<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="${ctx}/assets/js/jquery.gritter.min.js"></script>
	<script src="${ctx}/assets/js/bootbox.min.js"></script>
	<script src="${ctx}/assets/js/jquery.easypiechart.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
	<script src="${ctx}/assets/js/jquery.hotkeys.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-wysiwyg.min.js"></script>
	<script src="${ctx}/assets/js/select2.min.js"></script>
	<script src="${ctx}/assets/js/fuelux.spinner.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-editable.min.js"></script>
	<script src="${ctx}/assets/js/ace-editable.min.js"></script>
	<script src="${ctx}/assets/js/jquery.maskedinput.min.js"></script>

	<!-- ace scripts -->
	<script src="${ctx}/assets/js/ace-elements.min.js"></script>
	<script src="${ctx}/assets/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
		
			//editables on first profile page
			$.fn.editable.defaults.mode = 'inline';
			$.fn.editableform.loading = "<div class='editableform-loading'><i class='ace-icon fa fa-spinner fa-spin fa-2x light-blue'></i></div>";
		    $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="ace-icon fa fa-check"></i></button>'+
		                                '<button type="button" class="btn editable-cancel"><i class="ace-icon fa fa-times"></i></button>';    
			
			//editables 
			
			//text editable
		    $('#username')
			.editable({
				type: 'text',
				name: 'username'
		    });
		
		
			
			//select2 editable
			var countries = [];
		    $.each({ "CA": "Canada", "IN": "India", "NL": "Netherlands", "TR": "Turkey", "US": "United States"}, function(k, v) {
		        countries.push({id: k, text: v});
		    });
		
			var cities = [];
			cities["CA"] = [];
			$.each(["Toronto", "Ottawa", "Calgary", "Vancouver"] , function(k, v){
				cities["CA"].push({id: v, text: v});
			});
			cities["IN"] = [];
			$.each(["Delhi", "Mumbai", "Bangalore"] , function(k, v){
				cities["IN"].push({id: v, text: v});
			});
			cities["NL"] = [];
			$.each(["Amsterdam", "Rotterdam", "The Hague"] , function(k, v){
				cities["NL"].push({id: v, text: v});
			});
			cities["TR"] = [];
			$.each(["Ankara", "Istanbul", "Izmir"] , function(k, v){
				cities["TR"].push({id: v, text: v});
			});
			cities["US"] = [];
			$.each(["New York", "Miami", "Los Angeles", "Chicago", "Wysconsin"] , function(k, v){
				cities["US"].push({id: v, text: v});
			});
			
			var currentValue = "NL";
		    $('#country').editable({
				type: 'select2',
				value : 'NL',
				//onblur:'ignore',
		        source: countries,
				select2: {
					'width': 140
				},		
				success: function(response, newValue) {
					if(currentValue == newValue) return;
					currentValue = newValue;
					
					var new_source = (!newValue || newValue == "") ? [] : cities[newValue];
					
					//the destroy method is causing errors in x-editable v1.4.6+
					//it worked fine in v1.4.5
					/**			
					$('#city').editable('destroy').editable({
						type: 'select2',
						source: new_source
					}).editable('setValue', null);
					*/
					
					//so we remove it altogether and create a new element
					var city = $('#city').removeAttr('id').get(0);
					$(city).clone().attr('id', 'city').text('Select City').editable({
						type: 'select2',
						value : null,
						//onblur:'ignore',
						source: new_source,
						select2: {
							'width': 140
						}
					}).insertAfter(city);//insert it after previous instance
					$(city).remove();//remove previous instance
					
				}
		    });
		
			$('#city').editable({
				type: 'select2',
				value : 'Amsterdam',
				//onblur:'ignore',
		        source: cities[currentValue],
				select2: {
					'width': 140
				}
		    });
		
		
			
			//custom date editable
			$('#signup').editable({
				type: 'adate',
				date: {
					//datepicker plugin options
					    format: 'yyyy/mm/dd',
					viewformat: 'yyyy/mm/dd',
					 weekStart: 1
					 
					//,nativeUI: true//if true and browser support input[type=date], native browser control will be used
					//,format: 'yyyy-mm-dd',
					//viewformat: 'yyyy-mm-dd'
				}
			})
		
		    $('#age').editable({
		        type: 'spinner',
				name : 'age',
				spinner : {
					min : 16,
					max : 99,
					step: 1,
					on_sides: true
					//,nativeUI: true//if true and browser support input[type=number], native browser control will be used
				}
			});
			
		
		    $('#login').editable({
		        type: 'slider',
				name : 'login',
				
				slider : {
					 min : 1,
					  max: 50,
					width: 100
					//,nativeUI: true//if true and browser support input[type=range], native browser control will be used
				},
				success: function(response, newValue) {
					if(parseInt(newValue) == 1)
						$(this).html(newValue + " hour ago");
					else $(this).html(newValue + " hours ago");
				}
			});
		
			$('#about').editable({
				mode: 'inline',
		        type: 'wysiwyg',
				name : 'about',
		
				wysiwyg : {
					//css : {'max-width':'300px'}
				},
				success: function(response, newValue) {
				}
			});
			
			
			
			// *** editable avatar *** //
			try {//ie8 throws some harmless exceptions, so let's catch'em
		
				//first let's add a fake appendChild method for Image element for browsers that have a problem with this
				//because editable plugin calls appendChild, and it causes errors on IE at unpredicted points
				try {
					document.createElement('IMG').appendChild(document.createElement('B'));
				} catch(e) {
					Image.prototype.appendChild = function(el){}
				}
		
				var last_gritter
				$('#avatar').editable({
					type: 'image',
					name: 'avatar',
					value: null,
					image: {
						//specify ace file input plugin's options here
						btn_choose: 'Alterar foto',
						droppable: true,
						maxSize: 110000,//~100Kb
		
						//and a few extra ones here
						name: 'avatar',//put the field name here as well, will be used inside the custom plugin
						on_error : function(error_type) {//on_error function will be called when the selected file has a problem
							if(last_gritter) $.gritter.remove(last_gritter);
							if(error_type == 1) {//file format error
								last_gritter = $.gritter.add({
									title: 'Arquivo não é uma imagem!',
									text: 'Insira uma imagem do tipo jpg|gif|png',
									class_name: 'gritter-error gritter-center'
								});
							} else if(error_type == 2) {//file size rror
								last_gritter = $.gritter.add({
									title: 'Imagem é muito grande!',
									text: 'Imagem não pode exceder 100Kb!',
									class_name: 'gritter-error gritter-center'
								});
							}
							else {//other error
							}
						},
						on_success : function() {
							$.gritter.removeAll();
						}
					},
				    url: function(params) {
						// ***UPDATE AVATAR HERE*** //
						//for a working upload example you can replace the contents of this function with 
						//examples/profile-avatar-update.js
		
						var deferred = new $.Deferred
		
						var value = $('#avatar').next().find('input[type=hidden]:eq(0)').val();
						if(!value || value.length == 0) {
							deferred.resolve();
							return deferred.promise();
						}
		
		
						//dummy upload
						setTimeout(function(){
							if("FileReader" in window) {
								//for browsers that have a thumbnail of selected image
								var thumb = $('#avatar').next().find('img').data('thumb');
								if(thumb) $('#avatar').get(0).src = thumb;
							}
							
							deferred.resolve({'status':'OK'});
		
							if(last_gritter) $.gritter.remove(last_gritter);
							last_gritter = $.gritter.add({
								title: 'Foto atualizada!',
								text: 'Perfil foi alterado.',
								class_name: 'gritter-info gritter-center'
							});
							
						 } , parseInt(Math.random() * 800 + 800))
		
						return deferred.promise();
						
						// ***END OF UPDATE AVATAR HERE*** //
					},
					
					success: function(response, newValue) {
					}
				})
			}catch(e) {}
			
			/**
			//let's display edit mode by default?
			var blank_image = true;//somehow you determine if image is initially blank or not, or you just want to display file input at first
			if(blank_image) {
				$('#avatar').editable('show').on('hidden', function(e, reason) {
					if(reason == 'onblur') {
						$('#avatar').editable('show');
						return;
					}
					$('#avatar').off('hidden');
				})
			}
			*/
		
			//another option is using modals
			$('#avatar2').on('click', function(){
				var modal = 
				'<div class="modal fade">\
				  <div class="modal-dialog">\
				   <div class="modal-content">\
					<div class="modal-header">\
						<button type="button" class="close" data-dismiss="modal">&times;</button>\
						<h4 class="blue">Alterar foto</h4>\
					</div>\
					\
					<form class="no-margin" action="${linkTo[PerfilController].armazenaFoto}" method="post" enctype="multipart/form-data">\
					<div class="modal-body">\
						<div class="space-4"></div>\
						<div style="width:75%;margin-left:12%;"><input type="file" name="foto" /></div>\
					 </div>\
					\
					 <div class="modal-footer center">\
						<button type="submit" class="btn btn-sm btn-success"><i class="ace-icon fa fa-check"></i> Enviar</button>\
						<button type="button" class="btn btn-sm" data-dismiss="modal"><i class="ace-icon fa fa-times"></i> Cancelar</button>\
					 </div>\
					</form>\
				  </div>\
				 </div>\
				</div>';
				
				
				var modal = $(modal);
				modal.modal("show").on("hidden", function(){
					modal.remove();
				});
		
				var working = false;
		
				var form = modal.find('form:eq(0)');
				var file = form.find('input[type=file]').eq(0);
				file.ace_file_input({
					style:'well',
					btn_choose:'Click to choose new avatar',
					btn_change:null,
					no_icon:'ace-icon fa fa-picture-o',
					thumbnail:'small',
					before_remove: function() {
						//don't remove/reset files while being uploaded
						return !working;
					},
					allowExt: ['jpg', 'jpeg', 'png', 'gif'],
					allowMime: ['image/jpg', 'image/jpeg', 'image/png', 'image/gif']
				});
		
				/**
				form.on('submit', function(){
					console.log("submit");
					if(!file.data('ace_input_files')) return false;
					
					file.ace_file_input('disable');
					form.find('button').attr('disabled', 'disabled');
					form.find('.modal-body').append("<div class='center'><i class='ace-icon fa fa-spinner fa-spin bigger-150 orange'></i></div>");
					
					var deferred = new $.Deferred;
					working = true;
					deferred.done(function() {
						form.find('button').removeAttr('disabled');
						form.find('input[type=file]').ace_file_input('enable');
						form.find('.modal-body > :last-child').remove();
						
						modal.modal("hide");
		
						var thumb = file.next().find('img').data('thumb');
						if(thumb) $('#avatar2').get(0).src = thumb;
		
						working = false;
					});
					console.log("setTimeout");
					
					setTimeout(function(){
						deferred.resolve();
					} , parseInt(Math.random() * 800 + 800));
					console.log("return true;");
					return true;
				});
				*/
			});
		
			
		
			//////////////////////////////
			$('#profile-feed-1').ace_scroll({
				height: '250px',
				mouseWheelLock: true,
				alwaysVisible : true
			});
		
			$('a[ data-original-title]').tooltip();
		
			$('.easy-pie-chart.percentage').each(function(){
			var barColor = $(this).data('color') || '#555';
			var trackColor = '#E2E2E2';
			var size = parseInt($(this).data('size')) || 72;
			$(this).easyPieChart({
				barColor: barColor,
				trackColor: trackColor,
				scaleColor: false,
				lineCap: 'butt',
				lineWidth: parseInt(size/10),
				animate:false,
				size: size
			}).css('color', barColor);
			});
		  
			///////////////////////////////////////////
		
			//right & left position
			//show the user info on right or left depending on its position
			$('#user-profile-2 .memberdiv').on('mouseenter touchstart', function(){
				var $this = $(this);
				var $parent = $this.closest('.tab-pane');
		
				var off1 = $parent.offset();
				var w1 = $parent.width();
		
				var off2 = $this.offset();
				var w2 = $this.width();
		
				var place = 'left';
				if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) place = 'right';
				
				$this.find('.popover').removeClass('right left').addClass(place);
			}).on('click', function(e) {
				e.preventDefault();
			});
		
		
			///////////////////////////////////////////
			$('#user-profile-3')
			.find('input[type=file]').ace_file_input({
				style:'well',
				btn_choose:'Change avatar',
				btn_change:null,
				no_icon:'ace-icon fa fa-picture-o',
				thumbnail:'large',
				droppable:true,
				
				allowExt: ['jpg', 'jpeg', 'png', 'gif'],
				allowMime: ['image/jpg', 'image/jpeg', 'image/png', 'image/gif']
			})
			.end().find('button[type=reset]').on(ace.click_event, function(){
				$('#user-profile-3 input[type=file]').ace_file_input('reset_input');
			})
			.end().find('.date-picker').datepicker().next().on(ace.click_event, function(){
				$(this).prev().focus();
			})
			$('.input-mask-phone').mask('(999) 999-9999');
			$('.input-mask-date').mask('99/99');
		
			$('#user-profile-3').find('input[type=file]').ace_file_input('show_file_list', [{type: 'image', name: $('#avatar').attr('src')}]);
		
		
			////////////////////
			//change profile
			$('[data-toggle="buttons"] .btn').on('click', function(e){
				var target = $(this).find('input[type=radio]');
				var which = parseInt(target.val());
				$('.user-profile').parent().addClass('hide');
				$('#user-profile-'+which).parent().removeClass('hide');
			});
			
			
			
			/////////////////////////////////////
			$(document).one('ajaxloadstart.page', function(e) {
				//in ajax mode, remove remaining elements before leaving page
				try {
					$('.editable').editable('destroy');
				} catch(e) {}
				$('[class*=select2]').remove();
			});
		});
	</script>
	
</content>

</body>

</html>
