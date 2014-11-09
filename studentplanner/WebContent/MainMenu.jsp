<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="edu.ecu.seng6240.team6.Helper.*" %> 
<%@ page import="edu.ecu.seng6240.team6.models.*" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	SessionManager sessionManager = new SessionManager(request);
	User user = sessionManager.getUser();

 	if (user == null){
		response.sendRedirect("/NotAuthorized.jsp");
	}
	request.setAttribute("user", user);
	pageContext.setAttribute("user", user);
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Main menu</title>
</head>
<body>
	<c:choose>
		<c:when test="${user.getRole() eq 'Student'}">	
			<h1>Student Event Management</h1>
		</c:when>
		<c:otherwise>
			<h1>User Management</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>