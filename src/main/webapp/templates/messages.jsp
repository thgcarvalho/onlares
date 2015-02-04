<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp"%>

<!-- Error Messages -->
<c:if test="${not empty errors}">
	<div class="alert alert-danger">
		<button type="button" class="close" data-dismiss="alert">
			<i class="ace-icon fa fa-times"></i>
		</button>
		<h4>Erro(s):</h4>
		<ul class="clearfix">
			<c:forEach items="${errors}" var="error">
				<li><strong><i class="ace-icon fa fa-times"></i> ${error.category}</strong> - ${error.message}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>
<!-- Success Messages -->
<c:if test="${not empty notice}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">×</button>
		${notice}
	</div>
</c:if>

<script type="text/javascript">
	var $path_assets = "dist";//this will be used in gritter alerts containing images
</script>
