<%@ page import="model.Prix" %>
<%@ page import="model.Article" %>
<%@ page import="model.Revendeur" %>
<%@ page import="model.Utilisateur" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
	if( (user == null || ! user.isAdmin()) || request.getAttribute("idArt") == null){
		response.sendRedirect("Home.jsp");
	}
%>    


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire des Prix</title>
<jsp:include page="Header.jsp"></jsp:include>
</head>
<body>
		<%
			int idArt = 0;
			int idRev = 0;
			float prix = 0;
			
			if(request.getAttribute("idArt") != null){
				idArt = (int) request.getAttribute("idArt");
			}
			if(request.getAttribute("idRev") != null){
				idRev = (int) request.getAttribute("idRev");
			}
			if(request.getAttribute("idRev") != null){
				prix = (float) request.getAttribute("price");
			}
			String type = (String) request.getAttribute("type");
		%>
	<form class="form_base" action="Price" method="post">
		<% if(type != null && type.equals("up")) { %>
			<label for="price">Prix:</label>
			<input type="text" name="price" value="<%= prix %>">
			<br/>
			<input type="hidden" name="article" value="<%= idArt %>">
			<input type="hidden" name="revendeur" value="<%= idRev %>">
		<%} else { %>
		<label for="price">Prix:</label>
		<input class="form-control" type="text" name="price" value="">
		<input type="hidden" name="article" value="<%= idArt %>"/>
		
		<br/>
		<label for="revendeur">Revendeur:</label>
		<select class="form-control" name="revendeur">
			<% 
			if(request.getAttribute("listRevendeur") != null){
			ArrayList<Revendeur> myList = (ArrayList) request.getAttribute("listRevendeur");
			for(Revendeur rev : myList){ %>
				<option value="<%= rev.getId() %>"><%= rev.getName() %></option>
			<%}}%>
		</select>
		<br/>
		<%}%>
		<input type="hidden" name="type" value="<%= type %>">
		<input class="btn btn-default inline" type="submit" value="Valider">
	</form>
</body>
</html>