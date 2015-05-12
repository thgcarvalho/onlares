<%@ page pageEncoding="UTF-8"%>
<jsp:include page="/templates/jstl.jsp" />

<div id="right-menu" class="modal aside" data-body-scroll="false" data-offset="true" data-placement="right" data-fixed="true" data-backdrop="false" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header no-padding">
				<div class="table-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
					</button>
					Anúncios
				</div>
			</div>

			<div class="modal-body">
				<a href="${linkTo[AnuncioController].lista()}">
					Visualizar todos os anúncios <i class="ace-icon fa fa-external-link"></i>
				</a>
				<hr />
			
				<c:forEach items="${coletorDeAnuncio.visualidados}" var="anuncio">
					<a href="${linkTo[AnuncioController].visualiza(anuncio.id)}">
						<h3 class="lighter">${anuncio.atividade}</h3>
							${anuncio.titulo}
						<br />
						${anuncio.fone1} / ${anuncio.fone2} 
					</a>
					<hr />
				</c:forEach>
			</div>
		</div><!-- /.modal-content -->

		<button class="aside-trigger btn btn-info btn-app btn-xs ace-settings-btn" data-target="#right-menu" data-toggle="modal" type="button">
			<i data-icon1="fa-bullhorn" data-icon2="fa-bullhorn" class="ace-icon fa fa-bullhorn bigger-110 icon-only"></i>
		</button>
	</div><!-- /.modal-dialog -->
</div>
