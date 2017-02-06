<%@ page import="model.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<body>
	<% Utilisateur user = (Utilisateur) session.getAttribute("currentUser"); 
	if(user != null && user.isAdmin()){ %>
		<form action="Article" method="post">
			<input class="btn btn-default inline" type="submit" value="Supprimer le/les article(s) sélectionné(s)">
			<input class="btn btn-default inline" type="button" value="Ajouter un article" name="button_article_add" onclick="self.location.href='Article?type=new'">
			<input type="hidden" name="type" value="del">
	<%} %>
	<% if(request.getAttribute("listArticle") != null){
		ArrayList<Article> myList = (ArrayList) request.getAttribute("listArticle");
		int i = 0;
		for( Article art: myList){ 
			i += 1;
			if(user != null && user.isAdmin()){ %>
			<div class="list">
			<input type="checkbox" name="selection" value="<%= art.getId() %>">
		<%}
			if( i%2 == 0){%>
		<div class="panel panel-primary">
			<% } else { %>
		<div class="panel panel-info">
			<%} %>
			<div class="panel-heading">
			  	<h3 class="panel-title"><%= i%>- <%= art.getName() %></h3>
	        </div>
	        <div class="panel-body">
				<li>Description: <%= art.getDescription() %></li>
				<input class="btn btn-default inline" type="button" value="Liste des prix" name="button_price_<%= art.getId()%>" onclick="self.location.href='Price?id=<%= art.getId() %>'">
				<% if(user != null){%>
				<input class="btn btn-default inline" type="button" value="Ajouter aux favoris" name="button_favori_add" onclick="self.location.href='Favoris?type=add&id=<%= art.getId() %>'">
					<% if(user.isAdmin()){ %>
				<input class="btn btn-default inline" type="button" value="Mettre à jour l'article" name="button_article_up" onclick="self.location.href='Article?type=up&id=<%= art.getId() %>'">
	<%}} %>
	  		</div>
		</div>
	<% if(user != null && user.isAdmin()){ %>
	</div>
	<%}}
	if(user != null && user.isAdmin()){ %>
		</form>
	<%}
	} else {%>
		<jsp:include page="Load"></jsp:include>
	<%} %>
	
	<jsp:include page="Footer.jsp"></jsp:include>
</body>