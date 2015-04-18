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
		
		<li id="menu_veiculos" class="">
			<a href="${ctx}/adminVeiculo/lista">
				<i class="menu-icon fa fa-car"></i>
				<span class="menu-text"> Veículos </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li id="menu_pets" class="">
			<a href="${ctx}/adminPet/lista">
				<i class="menu-icon fa fa-paw"></i>
				<span class="menu-text"> Pets </span>
			</a>

			<b class="arrow"></b>
		</li>

	</ul><!-- /.nav-list -->

	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>

	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script>
</div>
