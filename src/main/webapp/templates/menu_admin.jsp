<%@ page pageEncoding="UTF-8"%>
<jsp:include page="/templates/jstl.jsp" />

<script type="text/javascript">
	try{ace.settings.check('main-container' , 'fixed')}catch(e){}
</script>

<div id="sidebar" class="sidebar                  responsive">
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<a class="btn-admin btn-info" href="${ctx}/home/index">
				<i class="ace-icon fa fa-home"></i>
			</a>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn-admin btn-info"></span>
		</div>
	</div><!-- /.sidebar-shortcuts -->

	<ul  id="menuadmin_home" class="nav nav-list">
	
		<li id="menuadmin_painel" class="">
			<a href="${ctx}/adminPainel/index">
				<i class="menu-icon fa fa-tachometer"></i>
				<span class="menu-text"> Painel </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li id="menuadmin_unidades" class="">
			<a href="${ctx}/adminUnidade/lista">
				<i class="menu-icon fa fa-map-marker"></i>
				<span class="menu-text"> Unidades </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li id="menuadmin_usuarios" class="">
			<a href="${ctx}/adminUsuario/lista">
				<i class="menu-icon fa fa-users"></i>
				<span class="menu-text"> Usuários </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li id="menuadmin_veiculos" class="">
			<a href="${ctx}/adminVeiculo/lista">
				<i class="menu-icon fa fa-car"></i>
				<span class="menu-text"> Veículos </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li id="menuadmin_pets" class="">
			<a href="${ctx}/adminPet/lista">
				<i class="menu-icon fa fa-paw"></i>
				<span class="menu-text"> Pets </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li id="menuadmin_reservas" class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-book"></i>
				<span class="menu-text"> Reservas </span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>

			<ul class="submenu">
				<li class="">
					<a href="${ctx}/adminReserva/lista">
						<i class="menu-icon fa fa-caret-right"></i>
						Espaços
					</a>

					<b class="arrow"></b>
				</li>

				<li class="">
					<a href="${ctx}/adminReserva/listaDoCondominio">
						<i class="menu-icon fa fa-caret-right"></i>
						Todas as reservas solicitadas
					</a>

					<b class="arrow"></b>
				</li>

			</ul>
		</li>
		
		<li id="menuadmin_autorizacoes" class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-sign-in"></i>
				<span class="menu-text"> Autorizações </span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>

			<ul class="submenu">
				<li class="">
					<a href="${ctx}/adminAutorizacao/lista">
						<i class="menu-icon fa fa-caret-right"></i>
						Tipos de autorizações
					</a>

					<b class="arrow"></b>
				</li>

				<li class="">
					<a href="${ctx}/adminAutorizacao/listaDoCondominio">
						<i class="menu-icon fa fa-caret-right"></i>
						Todas as autorizações solicitadas
					</a>

					<b class="arrow"></b>
				</li>

			</ul>
		</li>
		
		<li id="menuadmin_calendario" class="">
			<a href="${ctx}/adminCalendario/index">
				<i class="menu-icon fa fa-calendar"></i>
				<span class="menu-text">
					Calendário
				</span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li id="menuadmin_avisos" class="">
			<a href="${ctx}/adminAviso/lista">
				<i class="menu-icon fa fa-warning"></i>
				<span class="menu-text"> Avisos </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li id="menuadmin_documentos" class="">
			<a href="${ctx}/adminDocumento/lista">
				<i class="menu-icon fa fa-files-o"></i>
				<span class="menu-text"> Documentos </span>
			</a>

			<b class="arrow"></b>
		</li>
		
	</ul><!-- /.nav-list -->

	<div class="sidebar-toggle-admin sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>

	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script>
</div>
