<%@ page pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Validação de formulários com jQuery</title>
<script language="javascript" type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/validacao.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
<form id="form_validacao" action="" method="post">
  Nome:<br />
  <input type="text" name="nome" id="nome" />
  <br /><br />
  Email:<br />
  <input type="text" name="email" id="email" />
  <br /><br />
  Senha:<br />
  <input type="password" name="senha" id="senha" />
  <br /><br />
  Confirma Senha:<br />
  <input type="password" name="repete_senha" id="repete_senha" />
  <br /><br />
  Time Favorito:<br />
  <select name="time" id="time">
  	<option value="">Escolha seu time</option>
    <option value="1">Flamengo</option>
    <option value="2">Vasco</option>
    <option value="3">Botafogo</option>
    <option value="4">Fluminense</option>
  </select>
  <br /><br />
  Observação:<br />
  <textarea name="obs" id="obs" cols="50" rows="5"></textarea>
  <br /><br />
  Aceita os termos do contrato:<br />
  <input type="checkbox" name="termo" id="termo"/>
  <br />
  <input type="submit" value="Cadastrar" />
</form>
</body>
</html>
