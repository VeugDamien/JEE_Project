<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<% 
	if(session.getAttribute("login") != null){
		response.sendRedirect("Home.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connection</title>
<jsp:include page="Header.jsp"></jsp:include>
</head>
<body>
<% if(request.getAttribute("error") == "1") {%>
	<FONT color="red">Identifiant ou mot de passe incorrect!</FONT>
<%}%>
<form class="form_base" action="Login" method="post">
	<label for="login">Login:</label>
    <input class="form-control" type="text" name="login" id="login" required="true"><br><br>
    <label for="password">Password:</label>
    <input class="form-control" type="password" id="password" name="password" required="true"><br><br>
    <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login" id="log">
</form>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>