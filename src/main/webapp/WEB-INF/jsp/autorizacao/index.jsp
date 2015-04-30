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

			<li class="active">Tipos de Autorizações</li>
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
				Tipos de Autorizações
			</h1>
		</div><!-- /.page-header -->
		
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<c:choose>
						<c:when test="${tipoDeAutorizacaoList.isEmpty()}">
						        <h1>Não existem tipos de autorizações cadastradas</h1>
						</c:when>
						<c:otherwise>
							<c:forEach items="${tipoDeAutorizacaoList}" var="tipoDeAutorizacao">
								<div class="well">
									<h4 class="green smaller lighter">${tipoDeAutorizacao.descricao}</h4>
									<a class="blue" id="hide-option" href="${linkTo[AutorizacaoController].novo(tipoDeAutorizacao.id)}" title="Solicitar">
										<i class="ace-icon fa fa-hand-o-right"></i>
										Solicitar
									</a>
								</div>
							</c:forEach>
						</c:otherwise>
						</c:choose>
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
			document.getElementById('menu_autorizacoes').className = 'active';
		};
	</script>
	
</content>

</body>

</html>
