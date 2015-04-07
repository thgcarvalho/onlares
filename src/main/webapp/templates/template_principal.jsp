<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/templates/head.jsp"%>
	<!-- grandev -->
	<link rel="stylesheet" href="${ctx}/resources/css/style.css" />
	<script src="${ctx}/resources/js/util.js"></script>
	
	<link rel="icon" href="${ctx}/resources/images/favicon-16.png" />
	<link rel="shortcut icon" href="${ctx}/resources/onlares.ico" />
	<link rel="shortcut icon" href="${ctx}/resources/images/favicon-32.png" />
	<!-- head que muda! -->
	<decorator:head />
</head>
<body class="no-skin">

	<div id="navbar" class="navbar navbar-default">
		<!-- Top -->
		<%@ include file="/templates/header.jsp"%>
	</div>
	
	<div class="main-container" id="main-container">
		<!-- Menu -->
		<%@ include file="/templates/menu.jsp"%>
	
		<!-- Page Content -->
	    <div class="main-content">
	        <%@ include file="/templates/messages.jsp"%>
			<!-- body que muda! -->
			<decorator:body />
		</div>
	
		<%@ include file="/templates/footer.jsp"%>
	</div>
	
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
	
	<!-- ace scripts -->
	<script src="${ctx}/assets/js/ace-elements.min.js"></script>
	<script src="${ctx}/assets/js/ace.min.js"></script>
	
	<!-- page specific plugin scripts -->
	<!-- inline scripts related to this page -->
	<decorator:getProperty property="page.local_script"></decorator:getProperty>
	
	<!-- bootbox suport modal script -->
	<script src="${ctx}/assets/js/bootbox.min.js"></script>
	<script type="text/javascript">
		jQuery(function($) {
			$("#bootbox-suport").on(ace.click_event, function() {
				bootbox.dialog({
					message: "<span class='bigger-110 blue bolder'>SUPORTE</span><br /><span class='blue bolder'>Email: contato@onlares.com</span><br /><span class='blue bolder'>Fone: (85) 8787.8740</span>",
					buttons: 			
					{
						"success" :
						 {
							"label" : "<i class='ace-icon fa fa-check'></i> OK",
							"className" : "btn-sm btn-success",
							"callback": function() {
								//Example.show("great success");
							}
						}
					}
				});
			});
		});
	</script>

</body>
</html>