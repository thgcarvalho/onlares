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
					<div class="col-sm-9">
						<div class="space"></div>

						<div id="calendar"></div>
					</div>

					<div class="col-sm-3">
						<div class="widget-box transparent">
							<div class="widget-header">
								<h4>Draggable events</h4>
							</div>

							<div class="widget-body">
								<div class="widget-main no-padding">
									<div id="external-events">
										<div class="external-event label-grey" data-class="label-grey">
											<i class="ace-icon fa fa-arrows"></i>
											My Event 1
										</div>

										<div class="external-event label-success" data-class="label-success">
											<i class="ace-icon fa fa-arrows"></i>
											My Event 2
										</div>

										<div class="external-event label-danger" data-class="label-danger">
											<i class="ace-icon fa fa-arrows"></i>
											My Event 3
										</div>

										<div class="external-event label-purple" data-class="label-purple">
											<i class="ace-icon fa fa-arrows"></i>
											My Event 4
										</div>

										<div class="external-event label-yellow" data-class="label-yellow">
											<i class="ace-icon fa fa-arrows"></i>
											My Event 5
										</div>

										<div class="external-event label-pink" data-class="label-pink">
											<i class="ace-icon fa fa-arrows"></i>
											My Event 6
										</div>

										<div class="external-event label-info" data-class="label-info">
											<i class="ace-icon fa fa-arrows"></i>
											My Event 7
										</div>

										<label>
											<input type="checkbox" class="ace ace-checkbox" id="drop-remove" />
											<span class="lbl"> Remove after drop</span>
										</label>
									</div>
								</div>
							</div>
						</div>
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
			events: '<c:url value="/calendario/load.json"/>'
			,
			editable: true,
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) { // this function is called when something is dropped
			
				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');
				var $extraEventClass = $(this).attr('data-class');
				
				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);
				
				// assign it the date that was reported
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;
				if($extraEventClass) copiedEventObject['className'] = [$extraEventClass];
				
				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
				
				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}
				
			}
			,
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				
				bootbox.prompt("Título do novo evento:", function(title) {
					if (title !== null) {
						calendar.fullCalendar('renderEvent',
							{
								title: title,
								start: start,
								end: end,
								allDay: allDay,
								className: 'label-info'
							},
							true // make the event "stick"
						);
					}
				});
				
	
				calendar.fullCalendar('unselect');
			}
			,
			eventClick: function(calEvent, jsEvent, view) {
	
				//display a modal
				var modal = 
				'<div class="modal fade">\
				  <div class="modal-dialog">\
				   <div class="modal-content">\
					 <div class="modal-body">\
					   <button type="button" class="close" data-dismiss="modal" style="margin-top:-10px;">&times;</button>\
					   <form class="no-margin">\
						  <label>Change event name &nbsp;</label>\
						  <input class="middle" autocomplete="off" type="text" value="' + calEvent.title + '" />\
						 <button type="submit" class="btn btn-sm btn-success"><i class="ace-icon fa fa-check"></i> Save</button>\
					   </form>\
					 </div>\
					 <div class="modal-footer">\
						<button type="button" class="btn btn-sm btn-danger" data-action="delete"><i class="ace-icon fa fa-trash-o"></i> Delete Event</button>\
						<button type="button" class="btn btn-sm" data-dismiss="modal"><i class="ace-icon fa fa-times"></i> Cancel</button>\
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
					calendar.fullCalendar('removeEvents' , function(ev){
						return (ev._id == calEvent._id);
					})
					modal.modal("hide");
				});
				
				modal.modal('show').on('hidden', function(){
					modal.remove();
				});
	
				console.log(calEvent.id);
				console.log(jsEvent);
				console.log(view);
	
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
			$('#menu_calendario').addClass('active');
		});
	</script>
</content>

</body>

</html>
