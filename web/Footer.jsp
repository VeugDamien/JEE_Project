<%@page import="java.util.Date"%>
<%@page import="javax.swing.text.Document"%>
<%@ page import="model.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<footer class="navbar navbar-default">
<div class="footer_div">
<ul>
	<h3>Plan du Site</h3>
	<li><a href="Home.jsp">Home</a></li>
	<% Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
		if( user != null) {%>
	<li><a href="ListFavoris.jsp">Favoris</a></li>
	<% if(user.isAdmin()){ %>
	<li><a href="ListRevendeur.jsp">Revendeur</a></li>
	<%}} else { %>
	<li><a href="Login.jsp">Login</a></li>
	<%} %>
</ul>
<ul>
	<h3>Infos</h3>
	<li><a href="cg.jsp">Condition Générale</a></li>
</ul>

</div>
<div class="date">
<%= new Date()%>
</div>
</footer>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" 
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>