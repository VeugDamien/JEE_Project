<%@ page import="model.Revendeur" %>
<%@ page import="model.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
	if( user == null || ! user.isAdmin()){
		response.sendRedirect("Home.jsp");
	}  
	Revendeur rev = (Revendeur) request.getAttribute("revendeur");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire Revendeur</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<form class="form_base" action="Revendeur" method="post" id="formrev">
	<% if( rev != null){ %>
		<input type="hidden" name="id" value="<%= rev.getId() %>">
		<input type="hidden" name="type" value="<%= request.getAttribute("type") %>">
		<label for="name">Nom du revendeur:</label>
		<input class="form-control" type="text" value="<%= rev.getName() %>" name="name"/>
		<br/>
		<label for="description">Description:</label>
		<textarea class="form-control multi" name="description" form="formrev"><%= rev.getDescription() %></textarea>
		<br/>
		<input class="btn btn-default inline" type="submit"/>
	<% } else { %>
		<input type="hidden" name="id" value="">
		<input type="hidden" name="type" value="new">
		<label for="name">Nom du revendeur:</label>
		<input class="form-control" type="text" value="" name="name"/>
		<br/>
		<label for="description">Description:</label>
		<textarea class="form-control multi" name="description" form="formrev"></textarea>
		<br/>
		<input class="btn btn-default inline" type="submit"/>
	<% } %>
	</form>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>