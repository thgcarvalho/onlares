<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/templates/head.jsp"%>
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
		<%@ include file="/templates/menu_admin.jsp"%>
	
		<!-- Page Content -->
	    <div class="main-content">
	        <%@ include file="/templates/messages.jsp"%>
			<!-- body que muda! -->
			<decorator:body />
		</div>
	
		<%@ include file="/templates/footer.jsp"%>
	</div>
	
	<decorator:getProperty property="page.local_script"></decorator:getProperty>

</body>
</html>