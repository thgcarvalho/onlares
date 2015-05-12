<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>
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
				<i class="ace-icon fa fa-user home-icon"></i>
				<a href="${ctx}/admin/index">Home</a>
			</li>
			<li>
			<i class=""></i>
				<a href="${ctx}/adminAviso/lista">Avisos</a>
			</li>
			<li class="active">Novo Aviso</li>
		</ul><!-- /.breadcrumb -->
	</div>
	
	<div class="page-content">
		<div class="page-header">
			<h1>
				Novo Aviso
			</h1>
		</div><!-- /.page-header -->
	
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS <textarea class="wysiwyg-editor" id="editor1" name="editor" placeholder="Enter text ..."></textarea>-->
				<form class="form-horizontal" id="myform" role="form" action="${ctx}/adminAviso/" method="post">
					<input type="hidden" id="html" name="html" />
					<div class="wysiwyg-editor" name="editor1" id="editor1"></div>

					
					<!--
					<textarea class="wysiwyg-editor" id="editor1" name="editor1"></textarea>
					<br><br><p>click me</p>
					<textarea class="wysiwyg-editor" id="editor1" name="editor" placeholder="Enter text ..."></textarea>
				
					<div class="wysiwyg-editor" id="editor2"></div>
		-->
					<div class="hr hr-double dotted"></div>
					
					<div class="clearfix form-actions">
						<div class="col-md-offset-5">
							<button class="btn btn-info" type="submit">
								<i class="ace-icon fa fa-check bigger-110"></i>
								Salvar
							</button>
						</div>
					</div>
		
					<script type="text/javascript">
						var $path_assets = "dist";//this will be used in loading jQuery UI if needed!
					</script>
				</form>
				<!-- END PAGE CONTENT -->
			</div> <!-- div class="col-xs-12" -->
		</div> <!-- div class="row" -->
	</div>
</div>
	
<content tag="local_script">
	<!-- page specific plugin scripts -->
	<script src="${ctx}/assets/js/jquery-ui.custom.min.js"></script>
	<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="${ctx}/assets/js/markdown.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-markdown.min.js"></script>
	<script src="${ctx}/assets/js/jquery.hotkeys.min.js"></script>
	<script src="${ctx}/assets/js/bootstrap-wysiwyg.min.js"></script>
	<script src="${ctx}/assets/js/bootbox.min.js"></script>
		
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($){
			$("#myform").submit(function() {
			   // Retrieve the HTML from the plugin
			   var html = $('#editor1').html();
			   alert(html);
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
					'font',
					null,
					'fontSize',
					null,
					{name:'bold', className:'btn-info'},
					{name:'italic', className:'btn-info'},
					{name:'strikethrough', className:'btn-info'},
					{name:'underline', className:'btn-info'},
					null,
					{name:'insertunorderedlist', className:'btn-success'},
					{name:'insertorderedlist', className:'btn-success'},
					{name:'outdent', className:'btn-purple'},
					{name:'indent', className:'btn-purple'},
					null,
					{name:'justifyleft', className:'btn-primary'},
					{name:'justifycenter', className:'btn-primary'},
					{name:'justifyright', className:'btn-primary'},
					{name:'justifyfull', className:'btn-inverse'},
					null,
					{name:'createLink', className:'btn-pink'},
					{name:'unlink', className:'btn-pink'},
					null,
					{name:'insertImage', className:'btn-success'},
					null,
					'foreColor',
					null,
					{name:'undo', className:'btn-grey'},
					{name:'redo', className:'btn-grey'}
				],
				'wysiwyg': {
					fileUploadError: showErrorAlert
				}
			}).prev().addClass('wysiwyg-style2');
		
		});
	</script>
	
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		window.onload = function() {
			document.getElementById('menuadmin_avisos').className = 'active';
		};
	</script>
</content>

</body>

</html>
