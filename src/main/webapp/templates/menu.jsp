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
			<button class="btn btn-success">
				<i class="ace-icon fa fa-phone"></i>
			</button>

			<button class="btn btn-info">
				<i class="ace-icon fa fa-users"></i>
			</button>

			<button class="btn btn-warning">
				<i class="ace-icon fa fa-calendar"></i>
			</button>

			<button class="btn btn-danger">
				<i class="ace-icon fa fa-warning"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span>

			<span class="btn btn-info"></span>

			<span class="btn btn-warning"></span>

			<span class="btn btn-danger"></span>
		</div>
	</div><!-- /.sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li id="menu_home" class="">
			<a href="${ctx}/home/index">
				<i class="menu-icon fa fa-home"></i>
				<span class="menu-text"> Home </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li id="menu_moradores" class="">
			<a href="${ctx}/morador/lista">
				<i class="menu-icon fa fa-users"></i>
				<span class="menu-text"> Moradores </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li id="menu_veiculos" class="">
			<a href="${ctx}/veiculo/lista">
				<i class="menu-icon fa fa-car"></i>
				<span class="menu-text"> Veículos </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li id="menu_pets" class="">
			<a href="${ctx}/pet/lista">
				<i class="menu-icon fa fa-paw"></i>
				<span class="menu-text"> Pets </span>
			</a>

			<b class="arrow"></b>
		</li>
<!-- 
		<li class="disabled">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-book"></i>
				<span class="menu-text"> Reservas </span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>

			<ul class="submenu">
				<li class="disabled">
					<a href="#">
						<i class="menu-icon fa fa-caret-right"></i>
						Deck
					</a>

					<b class="arrow"></b>
				</li>

				<li class="disabled">
					<a href="#">
						<i class="menu-icon fa fa-caret-right"></i>
						Salão de Festas
					</a>

					<b class="arrow"></b>
				</li>
			</ul>
		</li>

		<li class="disabled">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-sign-in"></i>
				<span class="menu-text"> Autorizações </span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>

			<ul class="submenu">
				<li class="disabled">
					<a href="#">
						<i class="menu-icon fa fa-caret-right"></i>
						Visitantes
					</a>

					<b class="arrow"></b>
				</li>

				<li class="disabled">
					<a href="#">
						<i class="menu-icon fa fa-caret-right"></i>
						Obras
					</a>

					<b class="arrow"></b>
				</li>

				<li class="disabled">
					<a href="#">
						<i class="menu-icon fa fa-caret-right"></i>
						Mudanças
					</a>

					<b class="arrow"></b>
				</li>
			</ul>
		</li>

		<li class="disabled">
			<a href="#">
				<i class="menu-icon fa fa-envelope-o"></i>
				<span class="menu-text"> Correspondências </span>
			</a>

			<b class="arrow"></b>
		</li>

		<li class="disabled">
			<a href="#">
				<i class="menu-icon fa fa-calendar"></i>

				<span class="menu-text">
					Calendário

					<span class="badge badge-transparent tooltip-error" title="0 Eventos Importantes">
						<i class="ace-icon fa fa-exclamation-triangle red bigger-130"></i>
					</span>
				</span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li class="disabled">
			<a href="#">
				<i class="menu-icon fa fa-comment-o"></i>
				<span class="menu-text"> Mensagens </span>
			</a>

			<b class="arrow"></b>
		</li>

		<li class="disabled">
			<a href="#">
				<i class="menu-icon fa fa-warning"></i>
				<span class="menu-text"> Avisos </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li class="disabled">
			<a href="#">
				<i class="menu-icon fa fa-comments"></i>
				<span class="menu-text"> Fórum </span>
			</a>

			<b class="arrow"></b>
		</li>

		<li class="disabled">
			<a href="#">
				<i class="menu-icon fa fa-question"></i>
				<span class="menu-text"> Enquetes </span>
			</a>

			<b class="arrow"></b>
		</li>

		<li class="disabled">
			<a href="#">
				<i class="menu-icon fa fa-files-o"></i>
				<span class="menu-text"> Documentos </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<li class="disabled">
			<a href="#">
				<i class="menu-icon fa fa-phone"></i>
				<span class="menu-text"> Fornecedores </span>
			</a>

			<b class="arrow"></b>
		</li>
 -->
		<li>
			<a href="#" id="bootbox-suport">
				<i class="menu-icon fa fa-headphones"></i>
				<span class="menu-text"> Suporte </span>
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
