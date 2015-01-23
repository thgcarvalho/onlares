<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap Core CSS -->
<link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

<!-- DataTables CSS -->
<link href="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="../bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<title>Produto</title>
</head>
<body>
	<h1>Listagem de produtos</h1>

	 <table class="table table-striped table-bordered table-hover" id="dataTables-example">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Valor</th>
				<th>Quantidade</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${produtoList}" var="produto">
				<tr>
					<td><c:out value="${produto.nome}"></c:out></td>
					<td><c:out value="${produto.valor}"></c:out></td>
					<td><c:out value="${produto.quantidade}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>