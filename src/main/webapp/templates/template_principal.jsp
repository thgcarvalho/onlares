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
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">

			<%@ include file="/templates/header.jsp"%>
			
			<%@ include file="/templates/menu.jsp"%>

		</nav>
		
		<!-- Page Content -->
        <div id="page-wrapper">
        
	        <%@ include file="/templates/messages.jsp"%>
			<!-- body que muda! -->
			<decorator:body />
			
        </div>
        <!-- /#page-wrapper -->

	</div>

	<%@ include file="/templates/footer.jsp"%>

</body>
</html>