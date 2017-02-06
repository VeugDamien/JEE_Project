<%@ page import="model.Prix" %>
<%@ page import="model.Utilisateur" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Project</title>
<jsp:include page="Header.jsp"></jsp:include>
</head>
<body>
	<% Utilisateur user = (Utilisateur) session.getAttribute("currentUser"); 
	if(user != null && user.isAdmin()){ %>
		<form action="Price" method="post">
			<input class="btn btn-default inline" type="submit" value="Supprimer le/les prix sélectonné(s)"> 
			<input class="btn btn-default inline" type="button" value="Ajouter un prix" name="button_price_add" onclick="self.location.href='Price?type=new&idArt=${ id }'">
			<input type="hidden" name="article" value="${ id }">
			<input type="hidden" name="type" value="del">
	<%} %>
	<% if(request.getAttribute("listPrice") != null){
		ArrayList<Prix> myList = (ArrayList) request.getAttribute("listPrice");
		int i = 0;
		for( Prix prix: myList){ 
			i += 1;
			if(user != null && user.isAdmin()){ %>
			<div>
			<input type="checkbox" name="selection" value="<%= prix.getRevendeur().getId() %>">
		<%}
			if( i%2 == 0){%>
		<div class="panel panel-primary">
			<% } else { %>
		<div class="panel panel-info">
			<%} %>
			<div class="panel-heading">
			  	<h3 class="panel-title"><%= i%>- <%= prix.getRevendeur().getName() %></h3>
	        </div>
	        <div class="panel-body">
				<li>Prix: <%= prix.getPrice() %></li>
				<li>Description: <%= prix.getRevendeur().getDescription() %></li>
				<% if(user != null && user.isAdmin()){ %>
					<input class="btn btn-default inline" type="button" value="Mettre à jour le prix" name="button_price_update" onclick="self.location.href='Price?type=up&idRev=<%= prix.getRevendeur().getId()%>&idArt=<%= prix.getArticle().getId() %>'">
				<%} %>
			</div>
		</div>
	<%} if(user != null && user.isAdmin()){ %>
	</div>
	<%}}
	if(user != null && user.isAdmin()){ %>
		</form>
	<%}%>
</body>
</html>