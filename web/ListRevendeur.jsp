<%@ page import="model.Revendeur" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
		      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JEE Project</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<% Utilisateur user = (Utilisateur) session.getAttribute("currentUser"); 
	if(user != null && user.isAdmin()){ %>
		<form action="Revendeur" method="post">
			<input class="btn btn-default inline" type="submit" value="Supprimer le/les revendeur(s) sélectionné(s)">
			<input class="btn btn-default inline" type="button" value="Ajouter un revendeur" name="button_revendeur_add" onclick="self.location.href='Revendeur?type=new'">
			<input type="hidden" name="type" value="del">
	<%} %>
	<% if(request.getAttribute("listRevendeur") != null){
		ArrayList<Revendeur> myList = (ArrayList) request.getAttribute("listRevendeur");
		int i = 0;
		for( Revendeur rev: myList){ 
			i += 1;
			if(user != null && user.isAdmin()){ %>
			<div class="list">
			<input type="checkbox" name="selection" value="<%= rev.getId() %>">
		<%}
			if( i%2 == 0){%>
		<div class="panel panel-primary">
			<% } else { %>
		<div class="panel panel-info">
			<%} %>
			<div class="panel-heading">
			  	<h3 class="panel-title"><%= i%>- <%= rev.getName() %></h3>
	        </div>
	        <div class="panel-body">
				<li>Description: <%= rev.getDescription() %></li>
				<% if(user != null && user.isAdmin()){ %>
				<input class="btn btn-default inline" type="button" value="Mettre à jour le revendeur" name="button_revendeur_up" onclick="self.location.href='Revendeur?type=up&id=<%= rev.getId() %>'">
	<%} %>
	  		</div>
		</div>
	<% if(user != null && user.isAdmin()){ %>
	</div>
	<%}}
	if(user != null && user.isAdmin()){ %>
		</form>
	<%}%>
	<jsp:include page="Footer.jsp"></jsp:include>
	<%} else {%>
		<jsp:include page="Load?revendeur=true"></jsp:include>
	<%} %>
</body>
</html>