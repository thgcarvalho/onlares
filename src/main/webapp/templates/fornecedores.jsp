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
					Based on Modal boxes
				</div>
			</div>

			<div class="modal-body">
				<h3 class="lighter">Custom Elements and Content</h3>

				<br />
				With no modal backdrop
				<br />
				<br />
				<br />
				2
				<br />
				<br />
				<br />
				3
			</div>
		</div><!-- /.modal-content -->

		<button class="aside-trigger btn btn-info btn-app btn-xs ace-settings-btn" data-target="#right-menu" data-toggle="modal" type="button">
			<i data-icon1="fa-plus" data-icon2="fa-minus" class="ace-icon fa fa-plus bigger-110 icon-only"></i>
		</button>
	</div><!-- /.modal-dialog -->
</div>
