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
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="${ctx}/admin/index">Home</a>
			</li>

			<li class="active">Reservas</li>
		</ul><!-- /.breadcrumb -->
				
		<div class="nav-search" id="nav-search">
			<span class="condominium">
				<!-- COND -->
			</span>
		</div><!-- /.nav-search -->
	</div>
	
	<div class="page-content">
	
		<div class="page-header">
			<h1>
				Reservas - ${reserva.descricao}
			</h1>
		</div><!-- /.page-header -->
		
		
		<div class="clearfix">
			<div class="pull-right tableTools-buttons">
	      		<a href="${linkTo[ReservaController].novo(reserva.id)}">
					<button class="btn btn-success" type="submit" >
						<i class="ace-icon fa fa-book bigger-110"></i>
						Reservar
					</button>
				</a>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<table id="simple-table" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Data</th>
									<th>Hora</th>
								</tr>
							</thead>
	
							<tbody>
								<c:choose>
								<c:when test="${reservaUnidadeList.isEmpty()}">
								    <tr>
								        <td colspan="6">Não existem reservas cadastradas</td>
								    </tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${reservaUnidadeList}" var="reservaUnidade">
										<tr>
											<td>${reservaUnidade.dataFormatada}</td>
											<td>${reservaUnidade.horaFormatada}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div><!-- /.span -->
				</div><!-- /.row -->
				<!-- PAGE CONTENT ENDS -->
			</div>
		</div>
	</div>
</div>
	
<content tag="local_script">
	<!-- page specific plugin scripts -->
	
	<!-- inline scripts related to this page -->
	
	<!-- delete script -->
	<script type="text/javascript">
		$(".deletar").on("click", function(event) {
			event.preventDefault();
			if (confirm('Você realmente deseja exlucir esse registro?')) {
				$.ajax({
					url: $(this).attr("href"),
					type: 'POST',
					data: { _method: "DELETE"}
				}).done(function(data, textStatus, jqXHR){
					console.log("REMOVER");
				}).fail(function(jqXHR, textStatus, errorThrown){
					console.log("O item não foi removido!");
					alert("O item não foi removido!");
				});
			      
			    $(this).closest('tr').remove();
			    return false;
			}
		});
	</script>
	
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		window.onload = function() {
			document.getElementById('menu_reservas').className = 'active';
		};
	</script>
	
</content>

</body>

</html>
