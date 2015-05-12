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
					Anunciantes
				</div>
			</div>

			<div class="modal-body">
				<c:forEach items="${coletorDeAnuncio.visualidados}" var="anuncio">
					<h3 class="lighter">${anuncio.atividade}</h3>
						<a href="#" target="_blank">
						${anuncio.titulo} <i class="ace-icon fa fa-external-link"></i> </a>
					<br />
					${anuncio.fone1} / ${anuncio.fone2} 
					<hr />
				</c:forEach>
			</div>
		</div><!-- /.modal-content -->

		<button class="aside-trigger btn btn-info btn-app btn-xs ace-settings-btn" data-target="#right-menu" data-toggle="modal" type="button">
			<i data-icon1="fa-phone" data-icon2="fa-phone" class="ace-icon fa fa-phone bigger-110 icon-only"></i>
		</button>
	</div><!-- /.modal-dialog -->
</div>
