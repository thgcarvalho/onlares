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

	<div class="navbar-buttons navbar-header pull-right" role="navigation">
		<ul class="nav ace-nav">
		    <li class="blue">
				<a href="${ctx}/home/index">
					<i class="ace-icon fa fa-home"></i> Home
				</a>
			</li>
		
			<li class="light-blue">
				<a data-toggle="dropdown" href="#" class="dropdown-toggle">
					<img class="nav-user-photo" src="../assets/avatars/avatar2.png" alt="Usuário" />
					<span class="user-info">
						<small>Bem vindo,</small>
						${usuarioLogado.usuario.nome}
					</span>

					<i class="ace-icon fa fa-caret-down"></i>
				</a>

				<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
					<li>
						<a href="#">
							<i class="ace-icon fa fa-cog"></i>
							Configurações
						</a>
					</li>

					<li>
						<a href="profile.html">
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