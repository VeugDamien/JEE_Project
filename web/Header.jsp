<%@ page import="model.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
%> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
		integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">		
<link rel="stylesheet" href="global.css">  		
<header>
	<nav class="navbar navbar-default">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"> <% if(session.getAttribute("currentUser")!= null){ %>
            	Bonjour ${ currentUser.login }<%} %></a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <!--  <li class="active"><a href="Home.jsp">Home</a></li>-->
              <li><a href="Home.jsp">Home</a></li>
              <% if(user != null){ %>
              <li><a href="ListFavoris.jsp">Favoris</a></li>
              	<% if(user.isAdmin()) {%>
              <li><a href="ListRevendeur.jsp">Revendeurs</a></li>
	          <%}}%>
            </ul>
            <form class="search" action="Search" method="get">	
				<input type="text" class="form-control inline" name="text_search" value="${ text_looking}">
				<input class="btn btn-default inline" type="submit" value="Search">
		  	</form>
		  	<ul class="nav navbar-nav">
              <% if(session.getAttribute("currentUser")!= null){ %>
              <li><a href="Logout">Logout</a></li>
              <%} else {%>
              <li><a href="Login.jsp">Login</a></li>
              <%} %>
			</ul>
          </div>
        </div>
      </nav>
</header>