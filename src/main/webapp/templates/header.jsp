<%@ page pageEncoding="UTF-8"%>
<jsp:include page="/templates/jstl.jsp" />

<script type="text/javascript">
	try{ace.settings.check('navbar' , 'fixed')}catch(e){}
</script>

<div class="navbar-container" id="navbar-container">
	<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
		<span class="sr-only">Toggle sidebar</span>

		<span class="icon-bar"></span>

		<span class="icon-bar"></span>

		<span class="icon-bar"></span>
	</button>

	<div class="navbar-header pull-left">
		<a href="${ctx}/home/index" class="navbar-brand">
			<img alt="OnLares" src="${ctx}/resources/images/onlares_branco.png" height="26">
		</a>
	</div>
	
	<form name="unidadesForm" id="unidadesForm" action="${ctx}/alteraUnidade" method="post" >
		<div class="navbar-buttons navbar-header" role="navigation">
			<ul class="nav ace-nav">
				<li>
				<div class="styled-select">
					<select name="localizador" id="unidadesBox" onchange="this.form.submit();" onMouseOver="this.style.textDecoration='underline';" onMouseOut="this.style.textDecoration='none';" >
						<c:forEach items="${usuarioLogado.localizadores}" var="localizador" >							
							<c:choose>
								<c:when test="${usuarioLogado.localizadorAtual.id == localizador.id}">
									<option value="${localizador.id}" selected="selected">${localizador.condominio.nome} - Unidade: ${localizador.unidade.descricao}</option>
								</c:when>
								<c:otherwise>
									<option value="${localizador.id}">${localizador.condominio.nome} - Unidade: ${localizador.unidade.descricao}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				</li>
			</ul>
		</div>
	</form>
	
	<div class="navbar-buttons navbar-header pull-right" role="navigation">
		<ul class="nav ace-nav">
			<li>
				<c:if test="${usuarioLogado.usuario.admin}">
	      			<li class="blue" >
						<a href="${ctx}/admin/index">
							<i class="ace-icon fa fa-user"></i>
						</a>
					</li>
				</c:if>
			</li>
		
			<li class="purple">
				<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					<i class="ace-icon fa fa-bell icon-animated-bell"></i>
					<span class="badge badge-important">${usuarioLogado.usuario.notificacoes}</span>
				</a>

				<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
					<li class="dropdown-header">
						<i class="ace-icon fa fa-exclamation-triangle"></i>
						${usuarioLogado.usuario.notificacoes} Notificações
					</li>

					<li class="dropdown-footer">
						<a href="#">
							Ver todas as notificações
							<i class="ace-icon fa fa-arrow-right"></i>
						</a>
					</li>
				</ul>
			</li>

			<li class="green">
				<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					<i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
					<span class="badge badge-success">${usuarioLogado.usuario.mensagens}</span>
				</a>

				<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
					<li class="dropdown-header">
						<i class="ace-icon fa fa-envelope-o"></i>
						${usuarioLogado.usuario.mensagens} Mensagens
					</li>

					<li class="dropdown-footer">
						<a href="#">
							Ver todas as mensagens
							<i class="ace-icon fa fa-arrow-right"></i>
						</a>
					</li>
				</ul>
			</li>

			<li class="light-blue">
				<a data-toggle="dropdown" href="#" class="dropdown-toggle">
					<span class="user-info">
						<small>Bem vindo,</small>
						${usuarioLogado.usuario.nome}
					</span>

					<i class="ace-icon fa fa-caret-down"></i>
				</a>

				<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
					<li>
						<a href="${linkTo[ConfiguracaoController].email()}">
							<i class="ace-icon fa fa-cog"></i>
							Configurações
						</a>
					</li>

					<li>
						<a href="${linkTo[PerfilController].edita()}">
							<i class="ace-icon fa fa-user"></i>
							Perfil
						</a>
					</li>

					<li class="divider"></li>

					<li>
						<a href="${ctx}/logout">
							<i class="ace-icon fa fa-power-off"></i>
							Sair
						</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div><!-- /.navbar-container -->