<%@ page import="model.Article" %>
<%@ page import="model.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
	if( user == null || ! user.isAdmin()){
		response.sendRedirect("Home.jsp");
	}  
	model.Article art = (Article) request.getAttribute("article");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire Article</title>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
	<form class="form_base" action="Article" method="post" id="formart">
	<% if( art != null){ %>
		<input type="hidden" name="id" value="<%= art.getId() %>">
		<input class="form-control" type="hidden" name="type" value="<%= request.getAttribute("type") %>">
		<label for="name">Nom de l'article:</label>
		<input class="form-control" type="text" value="<%= art.getName() %>" name="name"/>
		<br/>
		<label for="description">Description:</label>
		<textarea class="multi form-control" name="description" form="formart"><%= art.getDescription() %></textarea>
		<br/>
		<input class="btn btn-default inline" type="submit"/>
	</form>
	<% } else { %>
		<input type="hidden" name="id" value="">
		<input type="hidden" name="type" value="new">
		<label for="name">Nom de l'article:</label>
		<input class="form-control" type="text" value="" name="name"/>
		<br/>
		<label for="description">Description:</label>
		<textarea class="form-control multi" name="description" form="formart"></textarea>
		<br/>
		<input class="btn btn-default inline" type="submit"/>
	</form>
	<% } %>
</body>
</html>