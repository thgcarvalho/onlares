<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html>
<html>

<head>

	<meta name="description" content="Static &amp; Dynamic Tables" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	
	<!-- grandev -->
	<link rel="stylesheet" href="${ctx}/resources/css/style.css" />
	
	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctx}/assets/font-awesome/4.2.0/css/font-awesome.min.css" />
	
	<!-- page specific plugin styles -->
	
	<!-- text fonts -->
	<link rel="stylesheet" href="${ctx}/assets/fonts/fonts.googleapis.com.css" />
	
	<!-- ace styles -->
	<link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
	
	<!--[if lte IE 9]>
		<link rel="stylesheet" href="${ctx}/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
	<![endif]-->
	
	<!--[if lte IE 9]>
	  <link rel="stylesheet" href="${ctx}/assets/css/ace-ie.min.css" />
	<![endif]-->
	
	<!-- inline styles related to this page -->
	
	<!-- ace settings handler -->
	<script src="${ctx}/assets/js/ace-extra.min.js"></script>
	
	<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
	
	<!--[if lte IE 8]>
	<script src="${ctx}/assets/js/html5shiv.min.js"></script>
	<script src="${ctx}/assets/js/respond.min.js"></script>
	<![endif]-->

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
				<a href="${ctx}/home/index">Admin</a>
			</li>

			<li class="active">Unidades</li>
		</ul><!-- /.breadcrumb -->
				
		<div class="nav-search" id="nav-search">
			<span class="condominium">
				${usuarioLogado.usuario.condominio.nome}
			</span>
		</div><!-- /.nav-search -->
	</div>
	
	<div class="page-content">
		<div class="page-header">
			<h1>
				Unidades
			</h1>
		</div><!-- /.page-header -->
		
		<div class="clearfix">
			<div class="pull-right tableTools-buttons">
				<a href="${ctx}/adminUnidade/novo">
					<button class="btn btn-success" type="submit" >
						<i class="ace-icon fa fa-plus bigger-110"></i>
						Novo
					</button>
				</a>
			</div>
		</div>
	
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<table id="dynamic-table" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Unidade</th>
									<th></th>
								</tr>
							</thead>
	
							<tbody>
								<c:forEach items="${unidadeList}" var="unidade">
									<tr id="myTableRow">
										<td>${unidade.localizacao}</td>
										<td>
											<div class="hidden-sm hidden-xs action-buttons">
												<a class="editar" href="${linkTo[AdminUnidadeController].edita(unidade.id)}" 
													title="Editar '${unidade.localizacao}'." >
													<i class="ace-icon fa fa-pencil bigger-130"></i>
												</a>
												
												<a class="deletar" href="${linkTo[AdminUnidadeController].remove(unidade.id)}" 
                    								title="Remover '${unidade.localizacao}'." >
													<i class="ace-icon fa fa-trash-o bigger-130"></i>
												</a>
																			
											</div>

											<div class="hidden-md hidden-lg">
												<div class="inline pos-rel">
													<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">
														<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
													</button>

													<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
														<li>
															<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																<span class="green">
																	<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																</span>
															</a>
														</li>

														<li>
															<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																<span class="red">
																	<i class="ace-icon fa fa-trash-o bigger-120"></i>
																</span>
															</a>
														</li>
													</ul>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div><!-- /.span -->
				</div><!-- /.row -->
				<!-- END PAGE CONTENT -->
			</div>
		</div>
	</div>
</div>
	
<content tag="local_script">
	<!-- basic scripts -->
	
	<!--[if !IE]> -->
	<script src="${ctx}/assets/js/jquery.2.1.1.min.js"></script>
	
	<!-- <![endif]-->
	
	<!--[if IE]>
	<script src="${ctx}/assets/js/jquery.1.11.1.min.js"></script>
	<![endif]-->
	
	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery || document.write("<script src='${ctx}/assets/js/jquery.min.js'>"+"<"+"/script>");
	</script>
	
	<!-- <![endif]-->
	
	<!--[if IE]>
	<script type="text/javascript">
	 window.jQuery || document.write("<script src='${ctx}/assets/js/jquery1x.min.js'>"+"<"+"/script>");
	</script>
	<![endif]-->
	<script type="text/javascript">
		if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
	</script>
	<script src="${ctx}/assets/js/bootstrap.min.js"></script>
	
	<!-- page specific plugin scripts -->
	<script src="${ctx}/assets/js/jquery.dataTables.min.js"></script>
	<script src="${ctx}/assets/js/jquery.dataTables.bootstrap.min.js"></script>
	<script src="${ctx}/assets/js/dataTables.tableTools.min.js"></script>
	<script src="${ctx}/assets/js/dataTables.colVis.min.js"></script>
	
	<!-- ace scripts -->
	<script src="${ctx}/assets/js/ace-elements.min.js"></script>
	<script src="${ctx}/assets/js/ace.min.js"></script>
	
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
			//initiate dataTables plugin
			var oTable1 = 
			$('#dynamic-table')
			//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
			.dataTable( {
				bAutoWidth: false,
				"aoColumns": [
				  null,
				  { "bSortable": false }
				],
				"aaSorting": [],
		
				//,
				//"sScrollY": "200px",
				//"bPaginate": false,
		
				//"sScrollX": "100%",
				//"sScrollXInner": "120%",
				//"bScrollCollapse": true,
				//Note: if you are applying horizontal scrolling (sScrollX) on a ".table-bordered"
				//you may want to wrap the table inside a "div.dataTables_borderWrap" element
		
				//"iDisplayLength": 50
				"language": {
	                "url": "../resources/lang/dataTables.pt.lang"
	            }
		    } );
			//oTable1.fnAdjustColumnSizing();
		
		
			//TableTools settings
			TableTools.classes.container = "btn-group btn-overlap";
			TableTools.classes.print = {
				"body": "DTTT_Print",
				"info": "tableTools-alert gritter-item-wrapper gritter-info gritter-center white",
				"message": "tableTools-print-navbar"
			}
		
			//initiate TableTools extension
			var tableTools_obj = new $.fn.dataTable.TableTools( oTable1, {
				"sSwfPath": "${ctx}/assets/swf/copy_csv_xls_pdf.swf",
				
				"sRowSelector": "td:not(:last-child)",
				"sRowSelect": "multi",
				"fnRowSelected": function(row) {
					//check checkbox when row is selected
					try { $(row).find('input[type=checkbox]').get(0).checked = true }
					catch(e) {}
				},
				"fnRowDeselected": function(row) {
					//uncheck checkbox
					try { $(row).find('input[type=checkbox]').get(0).checked = false }
					catch(e) {}
				},
		
				"sSelectedClass": "success",
		        "aButtons": [
					{
						"sExtends": "copy",
						"sToolTip": "Copy to clipboard",
						"sButtonClass": "btn btn-white btn-primary btn-bold",
						"sButtonText": "<i class='fa fa-copy bigger-110 pink'></i>",
						"fnComplete": function() {
							this.fnInfo( '<h3 class="no-margin-top smaller">Table copied</h3>\
								<p>Copied '+(oTable1.fnSettings().fnRecordsTotal())+' row(s) to the clipboard.</p>',
								1500
							);
						}
					},
					
					{
						"sExtends": "csv",
						"sToolTip": "Export to CSV",
						"sButtonClass": "btn btn-white btn-primary  btn-bold",
						"sButtonText": "<i class='fa fa-file-excel-o bigger-110 green'></i>"
					},
					
					{
						"sExtends": "pdf",
						"sToolTip": "Export to PDF",
						"sButtonClass": "btn btn-white btn-primary  btn-bold",
						"sButtonText": "<i class='fa fa-file-pdf-o bigger-110 red'></i>"
					},
					
					{
						"sExtends": "print",
						"sToolTip": "Print view",
						"sButtonClass": "btn btn-white btn-primary  btn-bold",
						"sButtonText": "<i class='fa fa-print bigger-110 grey'></i>",
						
						"sMessage": "<div class='navbar navbar-default'><div class='navbar-header pull-left'><a class='navbar-brand' href='#'><small>Optional Navbar &amp; Text</small></a></div></div>",
						
						"sInfo": "<h3 class='no-margin-top'>Print view</h3>\
								  <p>Please use your browser's print function to\
								  print this table.\
								  <br />Press <b>escape</b> when finished.</p>",
					}
		        ]
		    } );
			//we put a container before our table and append TableTools element to it
		    $(tableTools_obj.fnContainer()).appendTo($('.tableTools-container'));
			
			//also add tooltips to table tools buttons
			//addding tooltips directly to "A" buttons results in buttons disappearing (weired! don't know why!)
			//so we add tooltips to the "DIV" child after it becomes inserted
			//flash objects inside table tools buttons are inserted with some delay (100ms) (for some reason)
			setTimeout(function() {
				$(tableTools_obj.fnContainer()).find('a.DTTT_button').each(function() {
					var div = $(this).find('> div');
					if(div.length > 0) div.tooltip({container: 'body'});
					else $(this).tooltip({container: 'body'});
				});
			}, 200);
			
			
			
			//ColVis extension
			var colvis = new $.fn.dataTable.ColVis( oTable1, {
				"buttonText": "<i class='fa fa-search'></i>",
				"aiExclude": [0, 6],
				"bShowAll": true,
				//"bRestore": true,
				"sAlign": "right",
				"fnLabel": function(i, title, th) {
					return $(th).text();//remove icons, etc
				}
				
			}); 
			
			//style it
			$(colvis.button()).addClass('btn-group').find('button').addClass('btn btn-white btn-info btn-bold')
			
			//and append it to our table tools btn-group, also add tooltip
			$(colvis.button())
			.prependTo('.tableTools-container .btn-group')
			.attr('title', 'Show/hide columns').tooltip({container: 'body'});
			
			//and make the list, buttons and checkboxed Ace-like
			$(colvis.dom.collection)
			.addClass('dropdown-menu dropdown-light dropdown-caret dropdown-caret-right')
			.find('li').wrapInner('<a href="javascript:void(0)" />') //'A' tag is required for better styling
			.find('input[type=checkbox]').addClass('ace').next().addClass('lbl padding-8');
		
		
			
			/////////////////////////////////
			//table checkboxes
			$('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
			
			//select/deselect all rows according to table header checkbox
			$('#dynamic-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) tableTools_obj.fnSelect(row);
					else tableTools_obj.fnDeselect(row);
				});
			});
			
			//select/deselect a row when the checkbox is checked/unchecked
			$('#dynamic-table').on('click', 'td input[type=checkbox]' , function(){
				var row = $(this).closest('tr').get(0);
				if(!this.checked) tableTools_obj.fnSelect(row);
				else tableTools_obj.fnDeselect($(this).closest('tr').get(0));
			});
			
		
			
			
				$(document).on('click', '#dynamic-table .dropdown-toggle', function(e) {
				e.stopImmediatePropagation();
				e.stopPropagation();
				e.preventDefault();
			});
			
			
			//And for the first simple table, which doesn't have TableTools or dataTables
			//select/deselect all rows according to table header checkbox
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
					else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				});
			});
			
			//select/deselect a row when the checkbox is checked/unchecked
			$('#simple-table').on('click', 'td input[type=checkbox]' , function(){
				var $row = $(this).closest('tr');
				if(this.checked) $row.addClass(active_class);
				else $row.removeClass(active_class);
			});
		
			
		
			/********************************/
			//add tooltip for small view action buttons in dropdown menu
			$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
			
			//tooltip placement on right or left
			function tooltip_placement(context, source) {
				var $source = $(source);
				var $parent = $source.closest('table')
				var off1 = $parent.offset();
				var w1 = $parent.width();
		
				var off2 = $source.offset();
				//var w2 = $source.width();
		
				if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
				return 'left';
			}
		
		})
	</script>
	
	<!-- delete script -->
	<script type="text/javascript">
		$(".deletar").on("click", function(event) {
			if (confirm('Você realmente deseja exlucir esse registro?')) {
				event.preventDefault();
				$.ajax({
					url: $(this).attr("href"),
					type: 'POST',
					data: { _method: "DELETE"}
				}).done(function(data, textStatus, jqXHR){
					console.log("REMOVER");
				}).fail(function(jqXHR, textStatus, errorThrown){
					console.log("O item não foi removido!");
					alert("O item não foi removido!");
				});
			      
			    $(this).closest('tr').remove();
			    return false;
			}
		});
	</script>
	
	<!-- menu script -->
	<script type="text/javascript">
		$('li').click(function(e) {
			$('li').removeClass('active');
	        $(this).addClass('active');
	    });
	
		window.onload = function() {
			document.getElementById('menuadmin_unidades').className = 'active';
		};
	</script>
</content>

</body>

</html>
