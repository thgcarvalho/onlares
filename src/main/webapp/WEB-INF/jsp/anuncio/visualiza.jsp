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
				<i class="ace-icon fa fa-user home-icon"></i>
				<a href="${ctx}/home/index">Home</a>
			</li>
			<li>
			<i class=""></i>
				<a href="${ctx}/anuncio/lista">Anúncios</a>
			</li>
			<li class="active">Anúncio</li>
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
				Anúncio
			</h1>
		</div><!-- /.page-header -->
	
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div>
					<div id="user-profile-2" class="user-profile">
						<div class="tabbable">
							<div class="tab-content no-border padding-24">
								<div id="home" class="tab-pane in active">
									<div class="row">
									
										<div class="col-xs-12 col-sm-9">
											<div class="profile-user-info">
												<div class="profile-info-row">
													<div class="profile-info-name bigger"> Atividade </div>
													<div class="profile-info-value bigger">
														<span>${anuncio.atividade}</span>
													</div>
												</div>
											
												<div class="profile-info-row">
													<div class="profile-info-name more"> Título </div>
													<div class="profile-info-value more">
														<span>${anuncio.titulo}</span>
													</div>
												</div>
											
												<div class="profile-info-row">
													<div class="profile-info-name more"> Descrição </div>
													<div class="profile-info-value more">
														<span>${anuncio.descricao}</span>
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name more"> Anunciante </div>
													<div class="profile-info-value more">
														<span>${anuncio.anunciante}</span>
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name more"> Endereço </div>
													<div class="profile-info-value more">
														<i class="ace-icon fa fa-map-marker bigger-125 blue"></i>
														<span class="pequeno-espaco">${anuncio.endereco}</span>
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name more"> Fones </div>
													<div class="profile-info-value more">
														<i class="ace-icon fa fa-phone bigger-125 blue"></i>
														<span class="pequeno-espaco">${anuncio.fones}</span>
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name more"> Email </div>
													<div class="profile-info-value more">
														<i class="ace-icon fa fa-envelope bigger-125 blue"></i>
														<span class="pequeno-espaco">${anuncio.email}</span>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name more"> Site </div>
													<div class="profile-info-value more">
														<i class="ace-icon fa fa-globe bigger-125 blue"></i>
														<a href="${anuncio.site}" class="btn btn-link">
															<span>${anuncio.site}</span>
														</a>
													</div>
												</div>

											</div>
										</div><!-- /.col -->
									</div><!-- /.row -->

								</div><!-- /#home -->

							</div>
						</div>
					</div>
				</div>
				<!-- PAGE CONTENT ENDS -->
			</div> <!-- div class="col-xs-12" -->
		</div> <!-- div class="row" -->
	</div>
</div>
	
<content tag="local_script">
	<!-- page specific plugin scripts -->


	<!-- inline scripts related to this page -->
	
</content>

</body>

</html>
