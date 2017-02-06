<%@ page import="model.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des Favoris</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<% if(request.getAttribute("listFavoris") != null){
		ArrayList<Article> myList = (ArrayList) request.getAttribute("listFavoris");
		int i = 0;%>
		<form action="Favoris" method="post">
			<input class="btn btn-default inline" type="submit" value="Supprimer le/les favoris sélectionné(s)">
			<input type="hidden" name="type" value="del">
		<% for( Article art: myList){ 
			i += 1;%>
			<div class="list">
			<input type="checkbox" name="selection" value="<%= art.getId() %>">
			<% if( i%2 == 0){%>
				<div class="panel panel-primary">
			<% } else { %>
				<div class="panel panel-info">
			<%} %>
					<div class="panel-heading">
					  	<h3 class="panel-title"><%= i%>- <%= art.getName() %></h3>
			        </div>
			        <div class="panel-body">
						<li>Description: <%= art.getDescription() %></li>
			  		</div>
				</div>
			<%} %>
			</div>
		</form>
	<jsp:include page="Footer.jsp"></jsp:include>
	<%} else {%>
		<jsp:include page="Load?favoris=true"></jsp:include>
	<%} %>
</body>
</html>