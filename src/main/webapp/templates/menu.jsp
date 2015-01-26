<%@ page pageEncoding="UTF-8"%>
<jsp:include page="/templates/jstl.jsp" />

 <div class="navbar-default sidebar" role="navigation">
     <div class="sidebar-nav navbar-collapse">
         <ul class="nav" id="side-menu">
             <li class="sidebar-search">
                 <div class="input-group custom-search-form">
                     <input type="text" class="form-control" placeholder="Search...">
                     <span class="input-group-btn">
                         <button class="btn btn-default" type="button">
                             <i class="fa fa-search"></i>
                         </button>
                     </span>
                 </div>
                 <!-- /input-group -->
             </li>
             <li>
                 <a href="index"><i class="fa fa-home fa-fw"></i> Home</a>
             </li>
             <li>
                 <a href="#"><i class="fa fa-group fa-fw"></i> Moradores</a>
             </li>
             <li>
                 <a href="#"><i class="fa fa-book fa-fw"></i> Reservas<span class="fa arrow"></span></a>
                 <ul class="nav nav-second-level">
                     <li>
                         <a href="#">Deck</a>
                     </li>
                     <li>
                         <a href="#">Salão de Festas</a>
                     </li>
                 </ul>
                 <!-- /.nav-second-level -->
             </li>
             <li>
                 <a href="#"><i class="fa fa-sign-in fa-fw"></i> Autorizações<span class="fa arrow"></span></a>
                 <ul class="nav nav-second-level">
                     <li>
                         <a href="#">Visitantes</a>
                     </li>
                     <li>
                         <a href="#">Obras</a>
                     </li>
                      <li>
                         <a href="#">Mudanças</a>
                     </li>
                 </ul>
                 <!-- /.nav-second-level -->
             </li>
             <li>
                 <a href="#"><i class="fa fa-book fa-fw"></i> Portaría<span class="fa arrow"></span></a>
                 <ul class="nav nav-second-level">
                     <li>
                         <a href="#">Entradas</a>
                     </li>
                     <li>
                         <a href="#">Relatório</a>
                     </li>
                 </ul>
                 <!-- /.nav-second-level -->
             </li>
             <li>
                 <a href="#"><i class="fa fa-envelope-o fa-fw"></i> Correspondências</a>
             </li>
             <li>
                 <a href="#"><i class="fa fa-warning fa-fw"></i> Avisos</a>
             </li>
             <li>
                 <a href="#"><i class="fa fa-comments fa-fw"></i> Enquetes</a>
             </li>
             <li>
                 <a href="#"><i class="fa fa-files-o fa-fw"></i> Documentos<span class="fa arrow"></span></a>
                 <ul class="nav nav-second-level">
                     <li>
                         <a href="#">Cat 1</a>
                     </li>
                     <li>
                         <a href="#">Cat 2</a>
                     </li>
                 </ul>
                 <!-- /.nav-second-level -->
             </li>
             <li>
                 <a href="#"><i class="fa fa-comment-o fa-fw"></i> Mensagens</a>
             </li>
             <li>
                 <a href="#"><i class="fa fa-phone fa-fw"></i> Fornecedores</a>
             </li>
             
         </ul>
     </div>
     <!-- /.sidebar-collapse -->
 </div>
 <!-- /.navbar-static-side -->