<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>
	<meta name="description" content="" />
	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.custom.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/fullcalendar.min.css" />
</head>

<body>

<div class="main-content-inner">
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		</script>

		<ul class="breadcrumb">
			<li class="active">
				<i class="ace-icon fa fa-home home-icon"></i>
				Calendário
			</li>
		</ul><!-- /.breadcrumb -->
		
	</div>

	<div class="page-content">
	
		<%@ include file="/templates/messages.jsp"%>
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-sm-12">
						<div class="space"></div>

						<div id="calendar"></div>
					</div>
				</div>
				<!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div><!-- /.page-content -->
</div>

<content tag="local_script">
	<!-- page specific plugin scripts -->
	<script src="${ctx}/assets/js/jquery-ui.custom.min.js"></script>
	<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="${ctx}/assets/js/moment.min.js"></script>
	<script src="${ctx}/assets/js/fullcalendar.min.js"></script>
	<script src="${ctx}/assets/js/bootbox.min.js"></script>
	
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
	jQuery(function($) {

		/* initialize the external events
		-----------------------------------------------------------------*/
	
		$('#external-events div.external-event').each(function() {
	
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()) // use the element's text as the event title
			};
	
			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);
	
			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
			
		});
	
	
		/* initialize the calendar
		-----------------------------------------------------------------*/
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
	
		var calendar = $('#calendar').fullCalendar({
			//isRTL: true,
			 buttonHtml: {
				prev: '<i class="ace-icon fa fa-chevron-left"></i>',
				next: '<i class="ace-icon fa fa-chevron-right"></i>'
			},
		
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			monthNames:['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],  
            monthNamesShort:['jan.','fev.','mar','abr','mai','jun','jul.','ago','set','out','nov','dez'],  
            dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],  
            dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],  
            buttonText: {  
	            today: 'Hoje',  
	            day: 'dia',  
	            week:'Semana',  
	            month:'Mês'
            },  
			events: '<c:url value="/adminCalendario/load.json"/>',
			timeFormat: 'H(:mm)', // uppercase H for 24-hour clock
			editable: false,
			droppable: false, // this allows things to be dropped onto the calendar !!!
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				bootbox.prompt("Título do novo evento:", function(title) {
					console.log("Novo evento");
			    	if (title !== null) {
			  			$.ajax({
			   				url: '<c:url value="/adminCalendario/adicionar"/>',
			   				data: 'title=' + title +'&start=' + start + '&end=' + end,
			   				type: "POST",
			   				success: function(data) {
				   				location.href = '<c:url value="/adminCalendario/index"/>';
			   				}
			   			}).done(function(data, textStatus, jqXHR){
							//alert("DONE");
						}).fail(function(jqXHR, textStatus, errorThrown){
							//alert("FAIL=" + textStatus +" and " + errorThrown);
						});
			     	}
			    	
				});
				calendar.fullCalendar('unselect');
			},
			eventClick: function(calEvent, jsEvent, view) {
				//display a modal
				var modal = 
				'<div class="modal fade">\
				  <div class="modal-dialog">\
				   <div class="modal-content">\
					 <div class="modal-body">\
					   <button type="button" class="close" data-dismiss="modal" style="margin-top:-10px;">&times;</button>\
					   <form class="no-margin">\
						  <label>Evento: &nbsp;</label>\
						  <span class="middle">' + calEvent.title + '</span>\
					   </form>\
					 </div>\
					 <div class="modal-footer">\
						<button type="button" class="btn btn-sm btn-danger" data-action="delete"><i class="ace-icon fa fa-trash-o"></i> Deletar Evento</button>\
						<button type="button" class="btn btn-sm" data-dismiss="modal"><i class="ace-icon fa fa-times"></i> Cancelar</button>\
					 </div>\
				  </div>\
				 </div>\
				</div>';
			
				var modal = $(modal).appendTo('body');
				modal.find('form').on('submit', function(ev){
					ev.preventDefault();
					calEvent.title = $(this).find("input[type=text]").val();
					calendar.fullCalendar('updateEvent', calEvent);
					modal.modal("hide");
				});
				modal.find('button[data-action=delete]').on('click', function() {
					$.ajax({
						url: '<c:url value="/adminCalendario/"/>' + calEvent._id,
						type: 'POST',
						data: { _method: "DELETE"}
					}).done(function(data, textStatus, jqXHR){
						console.log("REMOVER");
					}).fail(function(jqXHR, textStatus, errorThrown){
						console.log("O item não foi removido!");
						alert("O item não foi removido!");
					});
					
					calendar.fullCalendar('removeEvents' , function(ev){
						return (ev._id == calEvent._id);
					})
					modal.modal("hide");
				});
				
				modal.modal('show').on('hidden', function(){
					modal.remove();
				});
	
				//console.log(calEvent.id);
				//console.log(jsEvent);
				//console.log(view);
	
				// change the border color just for fun
				//$(this).css('border-color', 'red');
			}
		});

	})
	</script>
	
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		$(function() {
			$('#menuadmin_calendario').addClass('active');
		});
	</script>
</content>

</body>

</html>
