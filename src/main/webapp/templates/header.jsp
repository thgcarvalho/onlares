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
		
			<c:if test="${usuarioLogado.usuario.admin}">
      			<li class="blue" >
					<a href="${ctx}/admin/index">
						<i class="ace-icon fa fa-user"></i> Administrativo
					</a>
				</li>
			</c:if>
		
		
			<li class="purple">
				<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					<i class="ace-icon fa fa-bell icon-animated-bell"></i>
					<span class="badge badge-important">8</span>
				</a>

				<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
					<li class="dropdown-header">
						<i class="ace-icon fa fa-exclamation-triangle"></i>
						8 Notifications
					</li>

					<li class="dropdown-content">
						<ul class="dropdown-menu dropdown-navbar navbar-pink">
							<li>
								<a href="#">
									<div class="clearfix">
										<span class="pull-left">
											<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
											New Comments
										</span>
										<span class="pull-right badge badge-info">+12</span>
									</div>
								</a>
							</li>

							<li>
								<a href="#">
									<i class="btn btn-xs btn-primary fa fa-user"></i>
									Bob just signed up as an editor ...
								</a>
							</li>

							<li>
								<a href="#">
									<div class="clearfix">
										<span class="pull-left">
											<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
											New Orders
										</span>
										<span class="pull-right badge badge-success">+8</span>
									</div>
								</a>
							</li>

							<li>
								<a href="#">
									<div class="clearfix">
										<span class="pull-left">
											<i class="btn btn-xs no-hover btn-info fa fa-twitter"></i>
											Followers
										</span>
										<span class="pull-right badge badge-info">+11</span>
									</div>
								</a>
							</li>
						</ul>
					</li>

					<li class="dropdown-footer">
						<a href="#">
							See all notifications
							<i class="ace-icon fa fa-arrow-right"></i>
						</a>
					</li>
				</ul>
			</li>

			<li class="green">
				<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					<i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
					<span class="badge badge-success">5</span>
				</a>

				<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
					<li class="dropdown-header">
						<i class="ace-icon fa fa-envelope-o"></i>
						5 Messages
					</li>

					<li class="dropdown-content">
						<ul class="dropdown-menu dropdown-navbar">
							<li>
								<a href="#" class="clearfix">
									<img src="../assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
									<span class="msg-body">
										<span class="msg-title">
											<span class="blue">Alex:</span>
											Ciao sociis natoque penatibus et auctor ...
										</span>

										<span class="msg-time">
											<i class="ace-icon fa fa-clock-o"></i>
											<span>a moment ago</span>
										</span>
									</span>
								</a>
							</li>

							<li>
								<a href="#" class="clearfix">
									<img src="../assets/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
									<span class="msg-body">
										<span class="msg-title">
											<span class="blue">Susan:</span>
											Vestibulum id ligula porta felis euismod ...
										</span>

										<span class="msg-time">
											<i class="ace-icon fa fa-clock-o"></i>
											<span>20 minutes ago</span>
										</span>
									</span>
								</a>
							</li>

							<li>
								<a href="#" class="clearfix">
									<img src="../assets/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
									<span class="msg-body">
										<span class="msg-title">
											<span class="blue">Bob:</span>
											Nullam quis risus eget urna mollis ornare ...
										</span>

										<span class="msg-time">
											<i class="ace-icon fa fa-clock-o"></i>
											<span>3:15 pm</span>
										</span>
									</span>
								</a>
							</li>

							<li>
								<a href="#" class="clearfix">
									<img src="../assets/avatars/avatar2.png" class="msg-photo" alt="Kate's Avatar" />
									<span class="msg-body">
										<span class="msg-title">
											<span class="blue">Kate:</span>
											Ciao sociis natoque eget urna mollis ornare ...
										</span>

										<span class="msg-time">
											<i class="ace-icon fa fa-clock-o"></i>
											<span>1:33 pm</span>
										</span>
									</span>
								</a>
							</li>

							<li>
								<a href="#" class="clearfix">
									<img src="../assets/avatars/avatar5.png" class="msg-photo" alt="Fred's Avatar" />
									<span class="msg-body">
										<span class="msg-title">
											<span class="blue">Fred:</span>
											Vestibulum id penatibus et auctor  ...
										</span>

										<span class="msg-time">
											<i class="ace-icon fa fa-clock-o"></i>
											<span>10:09 am</span>
										</span>
									</span>
								</a>
							</li>
						</ul>
					</li>

					<li class="dropdown-footer">
						<a href="inbox.html">
							See all messages
							<i class="ace-icon fa fa-arrow-right"></i>
						</a>
					</li>
				</ul>
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